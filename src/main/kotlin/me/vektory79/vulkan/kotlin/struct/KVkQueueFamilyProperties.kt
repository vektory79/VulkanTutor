package me.vektory79.vulkan.kotlin.struct

import me.vektory79.vulkan.kotlin.KVkStruct
import me.vektory79.vulkan.kotlin.KVkStructArray
import me.vektory79.vulkan.kotlin.VkStruct
import me.vektory79.vulkan.kotlin.flags.KVkQueueFlags
import org.lwjgl.vulkan.VkQueueFamilyProperties

/**
 * Kotlin-обёртка над [VkQueueFamilyProperties].
 *
 * Описывает свойства семейства очередей физического устройства: поддерживаемые типы команд,
 * количество очередей и точность таймстемпов. Получается через `vkGetPhysicalDeviceQueueFamilyProperties`.
 *
 * @property struct LWJGL-структура [VkQueueFamilyProperties].
 */
@VkStruct
@JvmInline
value class KVkQueueFamilyProperties(override val struct: VkQueueFamilyProperties) :
    KVkStruct<VkQueueFamilyProperties> {
    /**
     * Битовая маска типов команд, поддерживаемых очередями этого семейства ([KVkQueueFlags]).
     */
    val queueFlags get() = KVkQueueFlags(struct.queueFlags())

    /**
     * Количество очередей в этом семействе.
     */
    val queueCount: UInt get() = struct.queueCount().toUInt()

    /**
     * Количество бит, доступных для таймстемпов в очередях этого семейства.
     */
    val timestampValidBits: UInt get() = struct.timestampValidBits().toUInt()
}

/**
 * Массив структур [KVkQueueFamilyProperties].
 *
 * Оборачивает [VkQueueFamilyProperties.Buffer] для итерации по списку семейств очередей GPU.
 *
 * @property struct LWJGL-буфер [VkQueueFamilyProperties.Buffer].
 */
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
