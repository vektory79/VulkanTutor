package me.vektory79.vulkan.kotlin

import org.lwjgl.system.MemoryStack
import org.lwjgl.system.Struct
import org.lwjgl.system.StructBuffer
import java.util.function.Consumer

@DslMarker
annotation class VkStruct

interface KVkStruct<T : Struct<T>> {
    val struct: T
}

context(stack: MemoryStack)
inline fun <S : Struct<S>, K : KVkStruct<S>> calloc(
    noinline init: K.() -> Unit,
    create: context(MemoryStack) () -> K
): K =
    create(stack).apply {
        init()
    }

interface KVkStructArray<S : Struct<S>, T : StructBuffer<S, T>, K : KVkStruct<S>> : Iterable<K> {
    val struct: T

    var sType: Int

    fun wrap(struct: S): K

    val sizeof
        get() = struct.sizeof()

    var position
        get() = struct.position()
        set(value) {
            struct.position(value)
        }

    var limit
        get() = struct.limit()
        set(value) {
            struct.limit(value)
        }

    val capacity
        get() = struct.capacity()

    fun mark() {
        struct.mark()
    }

    fun reset() {
        struct.reset()
    }

    fun clear() {
        struct.clear()
    }

    fun flip() {
        struct.flip()
    }

    fun rewind() {
        struct.rewind()
    }

    val remaining: Int
        get() = struct.remaining()

    val hasRemaining: Boolean
        get() = struct.hasRemaining()

    operator fun get(i: Int): K

    operator fun set(i: Int, value: K) {
        struct.put(i, value.struct)
    }

    fun apply(consumer: Consumer<S>) {
        struct.apply(consumer)
    }

    fun apply(i: Int, consumer: Consumer<S>) {
        struct.apply(i, consumer)
    }

    override operator fun iterator(): Iterator<K> = ArrayIterator(this)
}

class ArrayIterator<S : Struct<S>, T : StructBuffer<S, T>, K : KVkStruct<S>, A : KVkStructArray<S, T, K>>(private val array: A) : Iterator<K> {
    val iterator = array.struct.iterator()
    override fun hasNext(): Boolean = iterator.hasNext()

    override fun next(): K {
        return array.wrap(iterator.next())
    }
}

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

@VkStruct
open class KVkStructArrayInitCollector<
        S : Struct<S>,
        T : StructBuffer<S, T>,
        K : KVkStruct<S>,
        B : KVkStructArray<S, T, K>,
        > {
    private val initializers = mutableListOf<B.() -> Unit>()

    @VkStruct
    fun add(init: B.() -> Unit) {
        initializers.add(init)
    }

    val size: Int
        get() = initializers.size

    operator fun get(i: Int) = initializers[i]
}

interface KVkBits {
    val value: Int

    fun check(flags: Int) = flags and value != 0
}

interface KVkFlags {
    val flags: Int
}