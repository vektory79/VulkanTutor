package me.vektory79.vulkan.kotlin.struct

import me.vektory79.vulkan.kotlin.KVkStruct
import me.vektory79.vulkan.kotlin.KVkStructArray
import me.vektory79.vulkan.kotlin.VkStruct
import org.lwjgl.vulkan.VkExtensionProperties
import java.nio.ByteBuffer

/**
 * Kotlin-обёртка над [VkExtensionProperties].
 *
 * Содержит информацию о доступном расширении Vulkan: имя и версию спецификации.
 * Получается через `vkEnumerateInstanceExtensionProperties` или `vkEnumerateDeviceExtensionProperties`.
 *
 * @property struct LWJGL-структура [VkExtensionProperties].
 */
@VkStruct
@JvmInline
value class KVkExtensionProperties(override val struct: VkExtensionProperties) :
    KVkStruct<VkExtensionProperties> {
    /**
     * Имя расширения как ByteBuffer (массив из 128 байт).
     */
    val extensionName: ByteBuffer get() = struct.extensionName()

    /**
     * Имя расширения как строка (например, "VK_KHR_surface").
     */
    val extensionNameString: String get() = struct.extensionNameString()

    /**
     * Версия спецификации расширения.
     */
    val specVersion get() = struct.specVersion().toUInt()
}

/**
 * Массив структур [KVkExtensionProperties].
 *
 * Оборачивает [VkExtensionProperties.Buffer] для итерации по списку доступных расширений.
 *
 * @property struct LWJGL-буфер [VkExtensionProperties.Buffer].
 */
@VkStruct
@JvmInline
value class KVkExtensionPropertiesArray(override val struct: VkExtensionProperties.Buffer) :
    KVkStructArray<VkExtensionProperties, VkExtensionProperties.Buffer, KVkExtensionProperties> {
    override var sType: Int
        get() = 0
        set(_) {}

    override fun get(i: Int): KVkExtensionProperties = KVkExtensionProperties(struct[i])

    override fun wrap(struct: VkExtensionProperties): KVkExtensionProperties = KVkExtensionProperties(struct)
}
