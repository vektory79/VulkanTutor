package me.vektory79.vulkan.kotlin.struct

import me.vektory79.vulkan.kotlin.KVkStruct
import org.lwjgl.vulkan.VkExtent2D

@JvmInline
value class KVkExtent2D(override val struct: VkExtent2D) : KVkStruct<VkExtent2D> {
    var width: UInt
        get() = struct.width().toUInt()
        set(value) { struct.width(value.toInt()) }

    var height: UInt
        get() = struct.height().toUInt()
        set(value) { struct.height(value.toInt()) }

    fun set(width: UInt, height: UInt): VkExtent2D = struct.set(width.toInt(), height.toInt())

    fun set(src: KVkExtent2D): VkExtent2D = struct.set(src.struct)
}