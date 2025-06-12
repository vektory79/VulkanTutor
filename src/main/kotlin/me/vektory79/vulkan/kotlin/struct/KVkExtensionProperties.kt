package me.vektory79.vulkan.kotlin.struct

import me.vektory79.vulkan.kotlin.KVkStruct
import me.vektory79.vulkan.kotlin.KVkStructArray
import me.vektory79.vulkan.kotlin.VkStruct
import org.lwjgl.vulkan.VkExtensionProperties
import java.nio.ByteBuffer

@VkStruct
@JvmInline
value class KVkExtensionProperties(override val struct: VkExtensionProperties) :
    KVkStruct<VkExtensionProperties> {
    val extensionName: ByteBuffer get() = struct.extensionName()
    val extensionNameString: String get() = struct.extensionNameString()
    val specVersion get() = struct.specVersion().toUInt()
}

@VkStruct
@JvmInline
value class KVkExtensionPropertiesArray(override val struct: VkExtensionProperties.Buffer) :
    KVkStructArray<VkExtensionProperties, VkExtensionProperties.Buffer, KVkExtensionProperties> {
    override var sType: Int
        get() = 0
        set(_) {
        }

    override fun get(i: Int): KVkExtensionProperties = KVkExtensionProperties(struct.get(i))

    override fun wrap(struct: VkExtensionProperties): KVkExtensionProperties = KVkExtensionProperties(struct)
}
