package me.vektory79.vulkan.kotlin.flags

import me.vektory79.vulkan.kotlin.KVkBits
import me.vektory79.vulkan.kotlin.KVkFlags
import org.lwjgl.vulkan.VK10.VK_QUEUE_COMPUTE_BIT
import org.lwjgl.vulkan.VK10.VK_QUEUE_GRAPHICS_BIT
import org.lwjgl.vulkan.VK10.VK_QUEUE_SPARSE_BINDING_BIT
import org.lwjgl.vulkan.VK10.VK_QUEUE_TRANSFER_BIT
import java.rmi.UnexpectedException


enum class KVkQueueFlagsBits(override val value: Int) : KVkBits {
    GRAPHICS(VK_QUEUE_GRAPHICS_BIT),
    COMPUTE(VK_QUEUE_COMPUTE_BIT),
    TRANSFER(VK_QUEUE_TRANSFER_BIT),
    SPARSE_BINDING(VK_QUEUE_SPARSE_BINDING_BIT);

    companion object {
        fun toEnum(value: Int): KVkQueueFlagsBits {
            return when(value) {
                VK_QUEUE_GRAPHICS_BIT -> GRAPHICS
                VK_QUEUE_COMPUTE_BIT -> COMPUTE
                VK_QUEUE_TRANSFER_BIT -> TRANSFER
                VK_QUEUE_SPARSE_BINDING_BIT -> SPARSE_BINDING
                else -> throw UnexpectedException("Unexpected queue flags bits")
            }
        }
    }
}

@JvmInline
value class KVkQueueFlags(override val flags: Int) : KVkFlags {
    val graphics get() = KVkQueueFlagsBits.GRAPHICS.check(flags)
    val compute get() = KVkQueueFlagsBits.COMPUTE.check(flags)
    val transfer get() = KVkQueueFlagsBits.TRANSFER.check(flags)
    val sparseBinding get() = KVkQueueFlagsBits.SPARSE_BINDING.check(flags)
}