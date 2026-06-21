package me.vektory79.vulkan.kotlin.struct

import me.vektory79.vulkan.kotlin.KVkStruct
import me.vektory79.vulkan.kotlin.VkStruct
import me.vektory79.vulkan.kotlin.flags.KVkCompositeAlphaFlagsKHR
import me.vektory79.vulkan.kotlin.flags.KVkImageUsageFlags
import me.vektory79.vulkan.kotlin.flags.KVkSurfaceTransformFlagsBitsKHR
import me.vektory79.vulkan.kotlin.flags.KVkSurfaceTransformFlagsKHR
import org.lwjgl.vulkan.VkSurfaceCapabilitiesKHR

/**
 * Kotlin-обёртка над [VkSurfaceCapabilitiesKHR].
 *
 * Описывает возможности поверхности для создания swap chain: диапазон размеров,
 * количество изображений, поддерживаемые трансформации, цветовые пространства и форматы.
 * Получается через `vkGetPhysicalDeviceSurfaceCapabilitiesKHR`.
 *
 * @property struct LWJGL-структура [VkSurfaceCapabilitiesKHR].
 */
@VkStruct
@JvmInline
value class KVkSurfaceCapabilitiesKHR(override val struct: VkSurfaceCapabilitiesKHR) : KVkStruct<VkSurfaceCapabilitiesKHR> {
    /**
     * Минимальное количество изображений в swap chain.
     */
    val minImageCount get() = struct.minImageCount().toUInt()

    /**
     * Максимальное количество изображений в swap chain (0 = без ограничений).
     */
    val maxImageCount get() = struct.maxImageCount().toUInt()

    /**
     * Текущие размеры поверхности (ширина/высота).
     */
    val currentExtent get() = KVkExtent2D(struct.currentExtent())

    /**
     * Минимально допустимые размеры изображений swap chain.
     */
    val minImageExtent get() = KVkExtent2D(struct.minImageExtent())

    /**
     * Максимально допустимые размеры изображений swap chain.
     */
    val maxImageExtent get() = KVkExtent2D(struct.maxImageExtent())

    /**
     * Максимальное количество слоёв массива изображений в swap chain.
     */
    val maxImageArrayLayers get() = struct.maxImageArrayLayers().toUInt()

    /**
     * Битовая маска поддерживаемых пре-трансформаций поверхности.
     */
    val supportedTransforms get() = KVkSurfaceTransformFlagsKHR(struct.supportedTransforms())

    /**
     * Текущая трансформация поверхности.
     */
    val currentTransform get() = KVkSurfaceTransformFlagsBitsKHR.toEnum(struct.currentTransform())

    /**
     * Битовая маска поддерживаемых режимов композитирования альфа-канала.
     */
    val supportedCompositeAlpha get() = KVkCompositeAlphaFlagsKHR(struct.supportedCompositeAlpha())

    /**
     * Битовая маска поддерживаемых флагов использования изображений swap chain.
     */
    val supportedUsageFlags get() = KVkImageUsageFlags(struct.supportedUsageFlags())
}
