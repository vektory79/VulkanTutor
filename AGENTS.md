# VulkanTutor — Руководство для контрибьюторов

Проект состоит из двух частей:
1. **Библиотека-обёртка** над Vulkan API (LWJGL3) на Kotlin
2. **Тутториал по Vulkan** — каждый урок — отдельный файл-entry point с последовательной нумерацией

## Структура проекта

```
src/main/kotlin/me/vektory79/vulkan/
├── kotlin/                          # Библиотека-обёртка
│   ├── KVkInstance.kt               # Vulkan-инстанс
│   ├── KVkPhysicalDevice.kt         # Физическое устройство (GPU)
│   ├── KVkDevice.kt                 # Логическое устройство
│   ├── KVkQueue.kt                  # Очередь
│   ├── KVkSurface.kt                # Поверхность окна
│   ├── VkStructs.kt                 # Базовые интерфейсы структур
│   ├── VkException.kt               # Обработка ошибок
│   ├── VkFunctions.kt               # Утилиты для Vulkan-функций
│   ├── Memory.kt                    # Работа с MemoryStack
│   ├── flags/                       # Обёртки флагов (KVkFormat, KVkQueueFlags и др.)
│   └── struct/                      # Обёртки структур (KVkApplicationInfo, KVkInstanceCreateInfo и др.)
│
└── tutor/                           # Тутториал
    ├── Main00BaseCode.kt            # Базовый код (GLFW-окно, перечисление расширений)
    ├── Main01InstanceCreation.kt    # Создание Vulkan-инстанса
    ├── Main02ValidationLayers.kt    # Слои валидации
    ├── Main03PhysicalDeviceSelection.kt  # Выбор GPU
    ├── Main04LogicalDevice.kt       # Логическое устройство
    ├── Main05WindowSurface.kt       # Поверхность окна
    ├── Main06SwapChainCreation.kt   # Создание swap chain
    └── ...                          # Дальнейшие уроки
```

- **Библиотека:** `src/main/kotlin/me/vektory79/vulkan/kotlin/`
- **Тутториал:** `src/main/kotlin/me/vektory79/vulkan/tutor/`
- **Тесты:** отсутствуют
- **Ресурсы:** шейдеры, текстуры и т.д. — добавляются по мере необходимости

## Команды сборки и запуска

| Команда | Описание |
|---------|----------|
| `./gradlew build` | Сборка проекта (компиляция + JAR) |
| `./gradlew classes` | Только компиляция |
| `./gradlew clean` | Очистка build-артефактов |

Запуск конкретного урока через IDE (IntelliJ IDEA) — выберите `Main**XX**...kt` и нажмите Run. Альтернативно: `./gradlew run --args='...'` с указанным main-классом.

## Архитектура библиотеки-обёртки

### Структуры (struct/)

Каждая LWJGL-структура `VkXxx` обёртывается Kotlin-классом `KVkXxx`:

**Принципы обёртки:**
- **@JvmInline value class** — для простых структур, обеспечивая нулевую overhead
- **Реализация KVkStruct<T>** — интерфейс с свойством `struct: T` для доступа к LWJGL-объекту
- **Аннотация @VkStruct** — DslMarker для маркировки всех структур
- **Иммутабельность по умолчанию** — свойства только для чтения (`val`), если не требуется изменение
- **Каскадные геттеры** — доступ к вложенным структурам через обёртки

**Пример обёртки KVkApplicationInfo:**
```kotlin
@VkStruct
@JvmInline
value class KVkApplicationInfo(override val struct: VkApplicationInfo) : KVkStruct<VkApplicationInfo> {
    var pApplicationName: ByteBuffer?
        get() = struct.pApplicationName()
        set(value) { struct.pApplicationName(value) }
    val applicationName: String?
        get() = struct.pApplicationNameString()
    var applicationVersion: Int
        get() = struct.applicationVersion()
        set(value) { struct.applicationVersion(value) }
}
```

**Фабричные методы в companion object:**
```kotlin
companion object {
    context(stack: MemoryStack)
    @VkStruct
    fun vkApplicationInfo(init: KVkApplicationInfo.() -> Unit): KVkApplicationInfo =
        calloc(init) {
            KVkApplicationInfo(
                VkApplicationInfo.calloc(stack)
                    .apply { sType(VK10.VK_STRUCTURE_TYPE_APPLICATION_INFO) })
        }
}
```

### Массивы структур

Для массивов создаётся отдельный класс `KVkXxxArray`:
- Реализует `KVkStructArray<S, T, K>` интерфейс
- Поддерживает итерацию через `Iterable<K>`
- Использует `KVkStructArrayInitCollector` для инициализации

### Флаги (flags/)

**Принципы обёртки:**
- **Enum class** — для перечисляемых значений (KVkFormat, KVkColorSpaceKHR)
- **KVkBits интерфейс** — для битовых флагов (KVkImageUsageFlagBits)
- **KVkFlags интерфейс** — для комбинаций флагов (KVkImageUsageFlags)
- **toEnum() метод** — статический метод для конвертации int → enum
- **Иммутабельность** — флаги только для чтения

**Пример обёртки KVkFormat:**
```kotlin
enum class KVkFormat(val format: Int) {
    UNDEFINED(VK_FORMAT_UNDEFINED),
    R8G8B8A8_UNORM(VK_FORMAT_R8G8B8A8_UNORM),
    // ... другие форматы
    
    companion object {
        fun toEnum(value: Int): KVkFormat = when(value) {
            VK_FORMAT_UNDEFINED -> UNDEFINED
            VK_FORMAT_R8G8B8A8_UNORM -> R8G8B8A8_UNORM
            // ... остальные
        }
    }
}
```

### Базовые интерфейсы

- **KVkStruct<T>** — базовый интерфейс всех структур
- **KVkStructArray<S, T, K>** — интерфейс для массивов структур
- **KVkBits** — интерфейс для битовых флагов
- **KVkFlags** — интерфейс для комбинаций флагов
- **calloc()** — helper функция для создания структур с инициализацией

## Стиль кода и соглашения

- **Язык:** Kotlin (JVM 17+, компилятор 21)
- **Отступы:** 4 пробела
- **Именование файлов:** `Main{NN}{ShortDescription}.kt` — где `NN` — порядковый номер урока (00, 01, 02...)
- **Пакет:** `me.vektory79.vulkan.tutor`
- **Импорты:** группировка по модулям (org.lwjgl.glfw, org.lwjgl.vulkan и т.д.)
- **Vulkan-константы:** использовать LWJGL-обёртки (`VK10.vk...`, `GLFW.GLFW_...`)
- **Контекстные параметры Kotlin:** включены через флаг `-Xcontext-parameters`

## VCS: коммиты и пулл-реквесты

### Коммиты
- Формат: `Main{NN}: краткое описание изменений`
- Примеры:
  - `Main03: добавлена проверка queue family`
  - `Main06: исправлена инициализация swap chain`

### Пулл-реквесты
- Укажите номер урока в заголовке PR
- Кратко опишите, что изменено и почему
- Прикрепите скриншот/лог, если изменения визуальные

## Ссылки

- [Vulkan Tutorials (Russian)](https://habr.com/ru/post/537208/)
- [Vulkan Tutorials Source (Java)](https://github.com/Naitsirc98/Vulkan-Tutorial-Java)
- [LWJGL Customizer](https://www.lwjgl.org/customize)
