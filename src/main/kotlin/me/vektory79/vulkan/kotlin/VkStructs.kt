package me.vektory79.vulkan.kotlin

import org.lwjgl.system.MemoryStack
import org.lwjgl.system.Struct
import org.lwjgl.system.StructBuffer
import java.util.function.Consumer

@DslMarker
annotation class VkStruct

interface KVkStruct<T : Struct> {
    val struct: T
}

context(MemoryStack)
inline fun <S : Struct, K : KVkStruct<S>> calloc(
    noinline init: K.() -> Unit,
    create: context(MemoryStack) () -> K
): K =
    create(this@MemoryStack).apply {
        init()
    }

interface KVkStructArray<S : Struct, T : StructBuffer<S, T>, K : KVkStruct<S>> : Iterable<S> {
    val struct: T

    var sType: Int

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

    override operator fun iterator(): Iterator<S> = struct.iterator()
}

inline fun <
    S : Struct,
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
    S : Struct,
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
