package me.vektory79.vulkan.kotlin.struct

import me.vektory79.vulkan.kotlin.KVkBits
import me.vektory79.vulkan.kotlin.KVkStruct
import me.vektory79.vulkan.kotlin.KVkStructArray
import me.vektory79.vulkan.kotlin.VkStruct
import me.vektory79.vulkan.kotlin.flags.KVkQueueFlags
import org.lwjgl.vulkan.VK10
import org.lwjgl.vulkan.VK10.VK_QUEUE_COMPUTE_BIT
import org.lwjgl.vulkan.VK10.VK_QUEUE_GRAPHICS_BIT
import org.lwjgl.vulkan.VK10.VK_QUEUE_SPARSE_BINDING_BIT
import org.lwjgl.vulkan.VK10.VK_QUEUE_TRANSFER_BIT
import org.lwjgl.vulkan.VkQueueFamilyProperties
import java.rmi.UnexpectedException

@VkStruct
@JvmInline
value class KVkQueueFamilyProperties(override val struct: VkQueueFamilyProperties) :
    KVkStruct<VkQueueFamilyProperties> {
    val queueFlags get() = KVkQueueFlags(struct.queueFlags())
    val queueCount: UInt get() = struct.queueCount().toUInt()
    val timestampValidBits: UInt get() = struct.timestampValidBits().toUInt()
}

@VkStruct
@JvmInline
value class KVkQueueFamilyPropertiesArray(override val struct: VkQueueFamilyProperties.Buffer) :
    KVkStructArray<VkQueueFamilyProperties, VkQueueFamilyProperties.Buffer, KVkQueueFamilyProperties> {
    override var sType: Int
        get() = 0
        set(_) {
        }

    override fun get(i: Int): KVkQueueFamilyProperties = KVkQueueFamilyProperties(struct.get(i))
    override fun wrap(struct: VkQueueFamilyProperties): KVkQueueFamilyProperties = KVkQueueFamilyProperties(struct)
}
