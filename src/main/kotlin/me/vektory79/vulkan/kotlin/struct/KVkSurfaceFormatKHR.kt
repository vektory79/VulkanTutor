package me.vektory79.vulkan.kotlin.struct

import me.vektory79.vulkan.kotlin.KVkStruct
import me.vektory79.vulkan.kotlin.KVkStructArray
import me.vektory79.vulkan.kotlin.VkStruct
import me.vektory79.vulkan.kotlin.flags.KVkColorSpaceKHR
import me.vektory79.vulkan.kotlin.flags.KVkFormat
import org.lwjgl.vulkan.VkSurfaceFormatKHR

/**
 * Kotlin-обёртка над [VkSurfaceFormatKHR].
 *
 * Описывает поддерживаемый формат пикселей и цветовое пространство для поверхности.
 * Получается через `vkGetPhysicalDeviceSurfaceFormatsKHR`.
 *
 * @property struct LWJGL-структура [VkSurfaceFormatKHR].
 */
@VkStruct
@JvmInline
value class KVkSurfaceFormatKHR(override val struct: VkSurfaceFormatKHR) : KVkStruct<VkSurfaceFormatKHR> {
    /**
     * Формат пикселей изображения swap chain ([KVkFormat]).
     */
    val format get() = KVkFormat.toEnum(struct.format())

    /**
     * Цветовое пространство изображения swap chain ([KVkColorSpaceKHR]).
     */
    val colorSpace get() = KVkColorSpaceKHR.toEnum(struct.colorSpace())
}

/**
 * Массив структур [KVkSurfaceFormatKHR].
 *
 * Оборачивает [VkSurfaceFormatKHR.Buffer] для итерации по списку поддерживаемых форматов поверхности.
 *
 * @property struct LWJGL-буфер [VkSurfaceFormatKHR.Buffer].
 */
@VkStruct
@JvmInline
value class KVkSurfaceFormatKHRArray(override val struct: VkSurfaceFormatKHR.Buffer) :
    KVkStructArray<VkSurfaceFormatKHR, VkSurfaceFormatKHR.Buffer, KVkSurfaceFormatKHR> {
    override var sType: Int
        get() = 0
        set(_) {}

    override fun get(i: Int): KVkSurfaceFormatKHR = KVkSurfaceFormatKHR(struct[i])

    override fun wrap(struct: VkSurfaceFormatKHR): KVkSurfaceFormatKHR = KVkSurfaceFormatKHR(struct)
}
