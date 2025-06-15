package me.vektory79.vulkan.kotlin.struct

import me.vektory79.vulkan.kotlin.KVkStruct
import me.vektory79.vulkan.kotlin.KVkStructArray
import me.vektory79.vulkan.kotlin.VkStruct
import me.vektory79.vulkan.kotlin.flags.KVkQueueFlags
import org.lwjgl.vulkan.VkQueueFamilyProperties

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
        set(_) {}

    override fun get(i: Int): KVkQueueFamilyProperties = KVkQueueFamilyProperties(struct[i])
    override fun wrap(struct: VkQueueFamilyProperties): KVkQueueFamilyProperties = KVkQueueFamilyProperties(struct)
}
