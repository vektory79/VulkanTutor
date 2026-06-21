package me.vektory79.vulkan.kotlin.struct

import me.vektory79.vulkan.kotlin.KVkStruct
import org.lwjgl.vulkan.VkExtent2D

/**
 * Kotlin-обёртка над [VkExtent2D].
 *
 * Описывает двумерные размеры (ширину и высоту) в пикселях. Используется для
 * размеров поверхностей, изображений swap chain и других 2D-ресурсов.
 *
 * @property struct LWJGL-структура [VkExtent2D].
 */
@JvmInline
value class KVkExtent2D(override val struct: VkExtent2D) : KVkStruct<VkExtent2D> {
    /**
     * Ширина в пикселях.
     */
    var width: UInt
        get() = struct.width().toUInt()
        set(value) { struct.width(value.toInt()) }

    /**
     * Высота в пикселях.
     */
    var height: UInt
        get() = struct.height().toUInt()
        set(value) { struct.height(value.toInt()) }

    /**
     * Устанавливает ширину и высоту.
     */
    fun set(width: UInt, height: UInt): VkExtent2D = struct.set(width.toInt(), height.toInt())

    /**
     * Копирует размеры из другого [KVkExtent2D].
     */
    fun set(src: KVkExtent2D): VkExtent2D = struct.set(src.struct)
}
