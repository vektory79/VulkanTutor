package me.vektory79.vulkan.kotlin.struct

import me.vektory79.vulkan.kotlin.KVkStruct
import me.vektory79.vulkan.kotlin.VkStruct
import me.vektory79.vulkan.kotlin.flags.KVkCompositeAlphaFlagsKHR
import me.vektory79.vulkan.kotlin.flags.KVkImageUsageFlags
import me.vektory79.vulkan.kotlin.flags.KVkSurfaceTransformFlagsBitsKHR
import me.vektory79.vulkan.kotlin.flags.KVkSurfaceTransformFlagsKHR
import org.lwjgl.vulkan.VkSurfaceCapabilitiesKHR

@VkStruct
@JvmInline
value class KVkSurfaceCapabilitiesKHR(override val struct: VkSurfaceCapabilitiesKHR) : KVkStruct<VkSurfaceCapabilitiesKHR> {
    val minImageCount get() = struct.minImageCount().toUInt()
    val maxImageCount get() = struct.maxImageCount().toUInt()
    val currentExtent get() = KVkExtent2D(struct.currentExtent())
    val minImageExtent get() = KVkExtent2D(struct.minImageExtent())
    val maxImageExtent get() = KVkExtent2D(struct.maxImageExtent())
    val maxImageArrayLayers get() = struct.maxImageArrayLayers().toUInt()
    val supportedTransforms get() = KVkSurfaceTransformFlagsKHR(struct.supportedTransforms())
    val currentTransform get() = KVkSurfaceTransformFlagsBitsKHR.toEnum(struct.currentTransform())
    val supportedCompositeAlpha get() = KVkCompositeAlphaFlagsKHR(struct.supportedCompositeAlpha())
    val supportedUsageFlags get() = KVkImageUsageFlags(struct.supportedUsageFlags())
}
