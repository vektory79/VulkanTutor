---
filePattern: "**/*.kt"
---

# kdoc

**Правила написания KDoc для библиотеки-обёртки Vulkan.**

## Когда применять

- При добавлении нового публичного элемента API библиотеки (класса, интерфейса, свойства, метода)
- При изменении существующего публичного API
- При рефакторинге, меняющем сигнатуру или назначение элемента

## Правила

### 1. Документируем только публичный API библиотеки

Пишем KDoc для элементов в `src/main/kotlin/me/vektory79/vulkan/kotlin/`.
Не пишем KDoc для кода в `src/main/kotlin/me/vektory79/vulkan/tutor/` (уроки) и тестов.

### 2. Источник информации — LWJGL и Vulkan

Информацию для документации берём из:

- **Исходников LWJGL** — Javadoc-комментарии, обычные комментарии, блоки `<pre>{@code struct VkXxx {...}}</pre>`
  - Документация может быть в обычных комментариях (не Javadoc)
  - Описание всех полей структуры может быть в описании класса, а не у каждого поля
- **Vulkan спецификации** — когда LWJGL не содержит достаточной информации
- **Заголовочных файлов Vulkan** (`vulkan_core.h`) — для описания структур и констант

### 3. Типы KDoc-ссылок

| Тип элемента | Формат ссылки | Пример |
|---|---|---|
| **Kotlin-класс/метод в проекте** | `[ClassName]` | `[KVkStruct]`, `[calloc]` |
| **LWJGL-класс (Java-структура)** | `[org.lwjgl.vulkan.VkXxx]` | `[org.lwjgl.vulkan.VkApplicationInfo]` |
| **C-enum / typedef из Vulkan** | `` `VkXxx` `` | `` `VkQueueFlagBits` ``, `` `VkFormat` `` |
| **Нативная C-функция Vulkan** | `` `vkXxx` `` | `` `vkCreateInstance` ``, `` `vkGetPhysicalDeviceFeatures` `` |

**Почему так:**

- LWJGL-структуры (`VkApplicationInfo`, `VkDeviceCreateInfo` и др.) — это реальные Java-классы, резолвимые IDE
- C-enums (`VkQueueFlagBits`, `VkFormat`, `VkColorSpaceKHR`) и typedef-алиасы (`VkQueueFlags`, `VkImageUsageFlags`) — это **не** Java-классы в LWJGL, а константы в интерфейсах (`VK10`, `KHRSurface` и т.д.) — ссылки на них не резолвятся
- Нативные функции (`vkCreateInstance`, `vkGetPhysicalDeviceFeatures`) — C-функции, не резолвимые из Kotlin

**Проверка:** если IDE показывает «Cannot resolve symbol» — ссылка невалидна. Исправить на обратные кавычки.

### 4. Структура KDoc

Каждый публичный элемент получает KDoc с:

- **Одним абзацем** — что это и зачем
- **@property** / **@param** / **@return** — для свойств, параметров, возвращаемых значений
- **Примером** — для фабричных методов и сложных API

Пример:

```kotlin
/**
 * Kotlin-обёртка над [org.lwjgl.vulkan.VkApplicationInfo].
 *
 * Содержит информацию о приложении и движке, которая передаётся при создании
 * Vulkan-инстанса. Используется драйвером для оптимизации и отладки.
 *
 * @property struct LWJGL-структура [org.lwjgl.vulkan.VkApplicationInfo].
 */
@JvmInline
value class KVkApplicationInfo(override val struct: VkApplicationInfo) : KVkStruct<VkApplicationInfo> {
    /**
     * Версия Vulkan API, которую приложение намерено использовать.
     */
    var apiVersion: Int
        get() = struct.apiVersion()
        set(value) { struct.apiVersion(value) }
}
```

### 5. Язык — Русский

Пишем KDoc на русском языке, если пользователь не указал иное.

### 6. Не дублируем очевидное

Не описываем то, что понятно из названия:

- `val queueCount` → «Количество очередей в семействе» (нужно)
- `val queueCount` → «Возвращает количество очередей в семействе как UInt» (избыточно)

## Чек-лист

- [ ] KDoc добавлен ко всем публичным элементам нового/изменённого API
- [ ] Все KDoc-ссылки резолвятся (нет «Cannot resolve symbol»)
- [ ] Использованы правильные типы ссылок (см. таблицу выше)
- [ ] Информация взята из LWJGL или Vulkan спецификации
- [ ] Язык — русский
