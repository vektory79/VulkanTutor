package me.vektory79.vulkan.kotlin

import org.lwjgl.system.MemoryStack
import org.lwjgl.system.Struct
import org.lwjgl.system.StructBuffer
import java.util.function.Consumer

/**
 * Маркерная аннотация для всех Vulkan-структур и фабричных методов библиотеки-обёртки.
 *
 * Используется вместе с [`@DslMarker`][kotlin.DslMarker] для предотвращения случайного
 * использования кода из разных DSL-контекстов. Все классы, реализующие [KVkStruct],
 * интерфейсы флагов [KVkBits] / [KVkFlags], а также фабричные методы создания структур
 * помечаются этой аннотацией.
 */
@DslMarker
annotation class VkStruct

/**
 * Базовый интерфейс для всех Kotlin-обёрток Vulkan-структур.
 *
 * Каждый класс, реализующий этот интерфейс, оборачивает соответствующий LWJGL-класс
 * [Struct] и предоставляет свойство [struct] для доступа к нативному объекту.
 *
 * @param T тип LWJGL-структуры (например, [org.lwjgl.vulkan.VkApplicationInfo], [org.lwjgl.vulkan.VkInstanceCreateInfo]).
 * @property struct LWJGL-структура, к которой предоставляет доступ эта обёртка.
 */
interface KVkStruct<T : Struct<T>> {
    /**
     * LWJGL-структура, к которой предоставляет доступ эта обёртка.
     */
    val struct: T
}

/**
 * Создаёт и инициализирует Vulkan-структуру на [MemoryStack].
 *
 * Вызывает фабричный метод [create], затем применяет лямбду-инициализатор [init]
 * к созданному объекту. Аллокация происходит на текущем стеке [stack].
 *
 * @param init лямбда для инициализации полей структуры.
 * @param create фабричный метод, создающий обёрнутую структуру на стеке.
 * @return инициализированная обёртка структуры.
 */
context(stack: MemoryStack)
inline fun <S : Struct<S>, K : KVkStruct<S>> calloc(
    noinline init: K.() -> Unit,
    create: context(MemoryStack) () -> K
): K =
    create(stack).apply {
        init()
    }

/**
 * Интерфейс для массивов Vulkan-структур, оборачивающий LWJGL [StructBuffer].
 *
 * Предоставляет доступ к элементам массива через обёртки [K], а также методы
 * навигации по буферу (position, limit, flip и т.д.), аналогичные [java.nio.Buffer].
 *
 * @param S тип LWJGL-структуры.
 * @param T тип LWJGL-буфера структур ([StructBuffer]).
 * @param K тип Kotlin-обёртки элемента массива.
 * @property struct LWJGL-буфер структур.
 */
interface KVkStructArray<S : Struct<S>, T : StructBuffer<S, T>, K : KVkStruct<S>> : Iterable<K> {
    /**
     * LWJGL-буфер структур, к которому предоставляет доступ эта обёртка.
     */
    val struct: T

    /**
     * Тип структуры (sType). Устанавливается при создании массива.
     */
    var sType: Int

    /**
     * Создаёт Kotlin-обёртку [K] для указанной LWJGL-структуры [struct].
     */
    fun wrap(struct: S): K

    /**
     * Размер одной структуры в байтах.
     */
    val sizeof
        get() = struct.sizeof()

    /**
     * Текущая позиция буфера (индекс следующего элемента для чтения/записи).
     */
    var position
        get() = struct.position()
        set(value) {
            struct.position(value)
        }

    /**
     * Лимит буфера (индекс первого недоступного элемента).
     */
    var limit
        get() = struct.limit()
        set(value) {
            struct.limit(value)
        }

    /**
     * Вместимость буфера (максимальное количество элементов).
     */
    val capacity
        get() = struct.capacity()

    /**
     * Запоминает текущую позицию буфера.
     */
    fun mark() {
        struct.mark()
    }

    /**
     * Восстанавливает позицию буфера до запомненной отметки.
     */
    fun reset() {
        struct.reset()
    }

    /**
     * Очищает буфер: устанавливает limit в capacity, position в 0.
     */
    fun clear() {
        struct.clear()
    }

    /**
     * Подготавливает буфер к чтению: устанавливает limit в текущую позицию, position в 0.
     */
    fun flip() {
        struct.flip()
    }

    /**
     * Сбрасывает позицию в 0, сохраняет лимит.
     */
    fun rewind() {
        struct.rewind()
    }

    /**
     * Количество элементов между текущей позицией и лимитом.
     */
    val remaining: Int
        get() = struct.remaining()

    /**
 * `true`, если между текущей позицией и лимитом есть элементы.
     */
    val hasRemaining: Boolean
        get() = struct.hasRemaining()

    /**
     * Возвращает Kotlin-обёртку элемента по индексу.
     */
    operator fun get(i: Int): K

    /**
     * Устанавливает элемент по индексу.
     */
    operator fun set(i: Int, value: K) {
        struct.put(i, value.struct)
    }

    /**
     * Применяет [consumer] ко всем элементам буфера от позиции до лимита.
     */
    fun apply(consumer: Consumer<S>) {
        struct.apply(consumer)
    }

    /**
     * Применяет [consumer] к элементу по индексу [i].
     */
    fun apply(i: Int, consumer: Consumer<S>) {
        struct.apply(i, consumer)
    }

    /**
     * Создаёт итератор по элементам массива.
     */
    override operator fun iterator(): Iterator<K> = ArrayIterator(this)
}

/**
 * Итератор для массивов Vulkan-структур. Проходит по элементам LWJGL-буфера
 * и возвращает Kotlin-обёртки.
 *
 * @param S тип LWJGL-структуры.
 * @param T тип LWJGL-буфера структур.
 * @param K тип Kotlin-обёртки элемента.
 * @param A тип массива структур.
 */
class ArrayIterator<S : Struct<S>, T : StructBuffer<S, T>, K : KVkStruct<S>, A : KVkStructArray<S, T, K>>(private val array: A) : Iterator<K> {
    private val iterator = array.struct.iterator()
    override fun hasNext(): Boolean = iterator.hasNext()

    override fun next(): K {
        return array.wrap(iterator.next())
    }
}

/**
 * Создаёт массив Vulkan-структур с заданным sType.
 *
 * Для каждого инициализатора из [inits] устанавливает позицию буфера, sType
 * и применяет инициализатор. После создания позиция сбрасывается в 0.
 *
 * @param sType тип структуры для всех элементов массива.
 * @param inits коллектор инициализаторов элементов.
 * @create фабричный метод, создающий массив на стеке.
 * @return заполненный массив структур.
 */
inline fun <
        S : Struct<S>,
        T : StructBuffer<S, T>,
        K : KVkStruct<S>,
        B : KVkStructArray<S, T, K>,
        C : KVkStructArrayInitCollector<S, T, K, B>
        >
        callocArray(sType: Int, inits: C, create: () -> B): B {
    val buffer: B = create()
    for (i in 0 until inits.size) {
        buffer.position = i
        buffer.sType = sType
        inits[i](buffer)
    }
    buffer.position = 0
    return buffer
}

/**
 * Коллектор инициализаторов для массивов Vulkan-структур.
 *
 * Используется с [callocArray] для поэлементной инициализации массива.
 * Каждый вызов [add] регистрирует лямбду-инициализатор для одного элемента.
 *
 * @param S тип LWJGL-структуры.
 * @param T тип LWJGL-буфера структур.
 * @param K тип Kotlin-обёртки элемента.
 * @param B тип массива структур.
 */
@VkStruct
open class KVkStructArrayInitCollector<
        S : Struct<S>,
        T : StructBuffer<S, T>,
        K : KVkStruct<S>,
        B : KVkStructArray<S, T, K>,
        > {
    private val initializers = mutableListOf<B.() -> Unit>()

    /**
     * Добавляет инициализатор для одного элемента массива.
     *
     * @param init лямбда, настраивающая один элемент массива.
     */
    fun add(init: B.() -> Unit) {
        initializers.add(init)
    }

    /**
     * Количество зарегистрированных инициализаторов (размер будущего массива).
     */
    val size: Int
        get() = initializers.size

    /**
     * Возвращает инициализатор по индексу.
     */
    operator fun get(i: Int) = initializers[i]
}

/**
 * Базовый интерфейс для всех битовых флагов Vulkan.
 *
 * Каждый enum-класс, реализующий этот интерфейс, представляет отдельный бит
 * из Vulkan-перечисления флагов (например, `VkQueueFlagBits`, `VkImageUsageFlagBits`).
 *
 * @property value целочисленное значение бита (степень двойки).
 */
interface KVkBits {
    /**
     * Целочисленное значение бита.
     */
    val value: Int

    /**
     * Проверяет, установлен ли этот бит в комбинации флагов [flags].
     *
     * @param flags комбинация флагов для проверки.
     * @return `true`, если бит установлен.
     */
    fun check(flags: Int) = flags and value != 0
}

/**
 * Базовый интерфейс для всех комбинаций флагов Vulkan.
 *
 * Value-классы, реализующие этот интерфейс, оборачивают целочисленное значение
 * комбинации битов и предоставляют свойства-чекеры для каждого отдельного флага.
 *
 * @property flags целочисленное значение комбинации битов.
 */
interface KVkFlags {
    /**
     * Целочисленное значение комбинации битов.
     */
    val flags: Int
}
