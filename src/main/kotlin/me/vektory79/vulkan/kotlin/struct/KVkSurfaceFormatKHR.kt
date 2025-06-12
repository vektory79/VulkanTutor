package me.vektory79.vulkan.kotlin.struct

import me.vektory79.vulkan.kotlin.KVkStruct
import me.vektory79.vulkan.kotlin.KVkStructArray
import me.vektory79.vulkan.kotlin.VkStruct
import me.vektory79.vulkan.kotlin.flags.KVkColorSpaceKHR
import me.vektory79.vulkan.kotlin.flags.KVkFormat
import org.lwjgl.vulkan.VkExtensionProperties
import org.lwjgl.vulkan.VkSurfaceFormatKHR

@VkStruct
@JvmInline
value class KVkSurfaceFormatKHR(override val struct: VkSurfaceFormatKHR) : KVkStruct<VkSurfaceFormatKHR> {
    val format get() = KVkFormat.toEnum(struct.format())
    val colorSpace get() = KVkColorSpaceKHR.toEnum(struct.colorSpace())
}

@VkStruct
@JvmInline
value class KVkSurfaceFormatKHRArray(override val struct: VkSurfaceFormatKHR.Buffer) :
    KVkStructArray<VkSurfaceFormatKHR, VkSurfaceFormatKHR.Buffer, KVkSurfaceFormatKHR> {
    override var sType: Int
        get() = 0
        set(_) {
        }

    override fun get(i: Int): KVkSurfaceFormatKHR = KVkSurfaceFormatKHR(struct.get(i))

    override fun wrap(struct: VkSurfaceFormatKHR): KVkSurfaceFormatKHR = KVkSurfaceFormatKHR(struct)
}
