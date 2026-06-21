---
name: vulkan-testing
description: Интеграционное тестирование библиотеки-обёртки Vulkan через реальные вызовы API
---

# Vulkan Testing — Интеграционное тестирование библиотеки-обёртки

## Цель

Тестировать **корректность работы обёрток** через реальные вызовы Vulkan API. Обёртка — тонкий слой над LWJGL, и единственный способ проверить её — прогнать данные через нативный Vulkan и убедиться, что результат совпадает с ожидаемым.

## Что НЕ тестируем

- **Отдельные геттеры, сеттеры, конструкторы** — это тестирование LWJGL, а не нашей обёртки
- **Маппинг enum ↔ int** — тривиальное соответствие по конвенции; если константа не совпадает, это баг, который проявится при первом реальном использовании
- **sType, инициализацию полей** — `@JvmInline value class` проксирует напрямую в LWJGL-структуру

## Что тестируем

| Уровень | Что проверяем | Пример |
|---------|---------------|--------|
| **Инстанс** | Создание/уничтожение, handle ≠ NULL, перечисление GPU | `KVkInstanceCreationTest` |
| **Physical Device** | Queue families, extensions, выбор подходящего GPU | `KVkPhysicalDeviceTest` |
| **Surface** | Создание через GLFW, capabilities, formats, surfaceSupport | `KVkSurfaceTest` |
| **Logical Device** | Создание с/без extensions, получение очереди, связь queue→device | `KVkDeviceCreationTest` |
| **Ошибки** | `describeResultCode`, `checkVkResult`, `VkException` | `VkResultCodeTest` |

## Принципы

1. **Львиное зеркало (Lion's Mirror)** — создать структуру через обёртку → передать в нативный Vulkan API → результат должен быть корректным
2. **Пайплайн, не изоляция** — тесты проверяют не отдельные методы, а цепочки: instance → physical device → logical device → queue
3. **Реальные данные** — тесты работают с реальным GPU, реальными расширениями, реальными queue families

## Архитектура тестов

### VulkanTestBase

Базовый класс для всех интеграционных тестов. GLFW инициализируется **один раз** через `@BeforeAll`/`@AfterAll`, чтобы избежать краша Virtio-GPU драйвера при многократном `glfwInit`/`glfwTerminate`.

```kotlin
@Tag("integration")
class MyTest : VulkanTestBase() {
    @Test
    fun `my test`() {
        // instance и window уже созданы
        stackPush {
            val physicalDevices = instance!!.getPhysicalDevices()
            // ...
        }
    }
}
```

Каждый тест получает свежий Vulkan instance через `@BeforeEach` и уничтожает его через `@AfterEach`.

### Альтернативный подход — отдельный setup

Если тесту нужна кастомная конфигурация (validation layers, debug messenger), override `createWindowAndInstance()`:

```kotlin
@BeforeEach
override fun createWindowAndInstance() {
    super.createWindowAndInstance()
    stackPush {
        surface = KVkSurface.glfwCreateWindowSurface(instance!!, window)
    }
}
```

### Unit-тесты без GPU

Тесты, не требующие Vulkan (проверка ошибок, маппинг кодов), помечаются `@Tag("unit")` и не наследуют `VulkanTestBase`.

## Конфигурация

### build.gradle.kts

```kotlin
tasks.test {
    useJUnitPlatform()
    jvmArgs("-Dorg.lwjgl.system.stackSize=2048")
    maxParallelForks = 1
}
```

- `-Dorg.lwjgl.system.stackSize=2048` — MemoryStack 2MB (RTX 5090 возвращает много расширений, стандартных 64KB не хватает)
- `maxParallelForks = 1` — один процесс, чтобы избежать конкурентного доступа к GPU

### Запуск

```bash
./gradlew test                              # все тесты
./gradlew test --tests "KVkInstanceCreationTest"  # конкретный класс
./gradlew test --tests "*integration*"      # только интеграционные
```

## Чек-лист при добавлении нового теста

- [ ] Наследуется от `VulkanTestBase` (если нужен GPU)
- [ ] Помечен `@Tag("integration")` или `@Tag("unit")`
- [ ] Создаёт ресурсы через обёртку, а не через LWJGL напрямую
- [ ] Проверяет результат через AssertJ (`assertThat`)
- [ ] Уничтожает VK-ресурсы в `finally` (device, queue)
- [ ] Не тестирует отдельные геттеры/сеттеры
