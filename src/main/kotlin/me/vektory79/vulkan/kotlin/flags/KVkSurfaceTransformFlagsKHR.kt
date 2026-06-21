package me.vektory79.vulkan.kotlin.flags

import me.vektory79.vulkan.kotlin.KVkBits
import me.vektory79.vulkan.kotlin.KVkFlags
import org.lwjgl.vulkan.KHRSurface.VK_SURFACE_TRANSFORM_HORIZONTAL_MIRROR_BIT_KHR
import org.lwjgl.vulkan.KHRSurface.VK_SURFACE_TRANSFORM_HORIZONTAL_MIRROR_ROTATE_180_BIT_KHR
import org.lwjgl.vulkan.KHRSurface.VK_SURFACE_TRANSFORM_HORIZONTAL_MIRROR_ROTATE_270_BIT_KHR
import org.lwjgl.vulkan.KHRSurface.VK_SURFACE_TRANSFORM_HORIZONTAL_MIRROR_ROTATE_90_BIT_KHR
import org.lwjgl.vulkan.KHRSurface.VK_SURFACE_TRANSFORM_IDENTITY_BIT_KHR
import org.lwjgl.vulkan.KHRSurface.VK_SURFACE_TRANSFORM_INHERIT_BIT_KHR
import org.lwjgl.vulkan.KHRSurface.VK_SURFACE_TRANSFORM_ROTATE_180_BIT_KHR
import org.lwjgl.vulkan.KHRSurface.VK_SURFACE_TRANSFORM_ROTATE_270_BIT_KHR
import org.lwjgl.vulkan.KHRSurface.VK_SURFACE_TRANSFORM_ROTATE_90_BIT_KHR
import java.rmi.UnexpectedException

/**
 * Битовые флаги трансформаций поверхности Vulkan, соответствующие `VkSurfaceTransformFlagBitsKHR`.
 *
 * Каждый флаг описывает геометрическое преобразование, которое может быть применено
 * к изображениям swap chain при выводе на поверхность: поворот, зеркальное отражение.
 *
 * @property value целочисленное значение бита.
 * @see KVkSurfaceTransformFlagsKHR
 */
enum class KVkSurfaceTransformFlagsBitsKHR(override val value: Int) : KVkBits {
    /** Без трансформации (тождественное преобразование). */
    IDENTITY(VK_SURFACE_TRANSFORM_IDENTITY_BIT_KHR),
    /** Поворот на 90 градусов по часовой стрелке. */
    ROTATE_90(VK_SURFACE_TRANSFORM_ROTATE_90_BIT_KHR),
    /** Поворот на 180 градусов. */
    ROTATE_180(VK_SURFACE_TRANSFORM_ROTATE_180_BIT_KHR),
    /** Поворот на 270 градусов по часовой стрелке. */
    ROTATE_270(VK_SURFACE_TRANSFORM_ROTATE_270_BIT_KHR),
    /** Горизонтальное зеркальное отражение. */
    HORIZONTAL_MIRROR(VK_SURFACE_TRANSFORM_HORIZONTAL_MIRROR_BIT_KHR),
    /** Горизонтальное зеркальное отражение + поворот на 90 градусов. */
    HORIZONTAL_MIRROR_ROTATE_90(VK_SURFACE_TRANSFORM_HORIZONTAL_MIRROR_ROTATE_90_BIT_KHR),
    /** Горизонтальное зеркальное отражение + поворот на 180 градусов. */
    HORIZONTAL_MIRROR_ROTATE_180(VK_SURFACE_TRANSFORM_HORIZONTAL_MIRROR_ROTATE_180_BIT_KHR),
    /** Горизонтальное зеркальное отражение + поворот на 270 градусов. */
    HORIZONTAL_MIRROR_ROTATE_270(VK_SURFACE_TRANSFORM_HORIZONTAL_MIRROR_ROTATE_270_BIT_KHR),
    /** Трансформация наследуется из параметров swap chain. */
    INHERIT(VK_SURFACE_TRANSFORM_INHERIT_BIT_KHR);

    companion object {
        /**
         * Преобразует целочисленное значение бита в соответствующий элемент перечисления.
         *
         * @param value целочисленное значение (VK_SURFACE_TRANSFORM_*_BIT).
         * @return соответствующий флаг.
         * @throws UnexpectedException если значение не соответствует ни одному известному флагу.
         */
        fun toEnum(value: Int): KVkSurfaceTransformFlagsBitsKHR {
            return when(value) {
                VK_SURFACE_TRANSFORM_IDENTITY_BIT_KHR -> IDENTITY
                VK_SURFACE_TRANSFORM_ROTATE_90_BIT_KHR -> ROTATE_90
                VK_SURFACE_TRANSFORM_ROTATE_180_BIT_KHR -> ROTATE_180
                VK_SURFACE_TRANSFORM_ROTATE_270_BIT_KHR -> ROTATE_270
                VK_SURFACE_TRANSFORM_HORIZONTAL_MIRROR_BIT_KHR -> HORIZONTAL_MIRROR
                VK_SURFACE_TRANSFORM_HORIZONTAL_MIRROR_ROTATE_90_BIT_KHR -> HORIZONTAL_MIRROR_ROTATE_90
                VK_SURFACE_TRANSFORM_HORIZONTAL_MIRROR_ROTATE_180_BIT_KHR -> HORIZONTAL_MIRROR_ROTATE_180
                VK_SURFACE_TRANSFORM_HORIZONTAL_MIRROR_ROTATE_270_BIT_KHR -> HORIZONTAL_MIRROR_ROTATE_270
                VK_SURFACE_TRANSFORM_INHERIT_BIT_KHR -> INHERIT
                else -> throw UnexpectedException("Unexpected surface transform mode")
            }
        }
    }
}

/**
 * Комбинация битовых флагов трансформаций поверхности Vulkan.
 *
 * Оборачивает целочисленное значение `VkSurfaceTransformFlagsKHR` и предоставляет
 * свойства-чекеры для каждой трансформации.
 *
 * @property flags целочисленное значение комбинации битов.
 * @see KVkSurfaceTransformFlagsBitsKHR
 */
@JvmInline
value class KVkSurfaceTransformFlagsKHR(override val flags: Int) : KVkFlags {
    /** `true`, если поддерживается тождественное преобразование. */
    val identity get() = KVkSurfaceTransformFlagsBitsKHR.IDENTITY.check(flags)
    /** `true`, если поддерживается поворот на 90 градусов. */
    val rotate90 get() = KVkSurfaceTransformFlagsBitsKHR.ROTATE_90.check(flags)
    /** `true`, если поддерживается поворот на 180 градусов. */
    val rotate180 get() = KVkSurfaceTransformFlagsBitsKHR.ROTATE_180.check(flags)
    /** `true`, если поддерживается поворот на 270 градусов. */
    val rotate270 get() = KVkSurfaceTransformFlagsBitsKHR.ROTATE_270.check(flags)
    /** `true`, если поддерживается горизонтальное зеркальное отражение. */
    val horizontalMirror get() = KVkSurfaceTransformFlagsBitsKHR.HORIZONTAL_MIRROR.check(flags)
    /** `true`, если поддерживается горизонтальное отражение + поворот на 90 градусов. */
    val horizontalMirrorRotate90 get() = KVkSurfaceTransformFlagsBitsKHR.HORIZONTAL_MIRROR_ROTATE_90.check(flags)
    /** `true`, если поддерживается горизонтальное отражение + поворот на 180 градусов. */
    val horizontalMirrorRotate180 get() = KVkSurfaceTransformFlagsBitsKHR.HORIZONTAL_MIRROR_ROTATE_180.check(flags)
    /** `true`, если поддерживается горизонтальное отражение + поворот на 270 градусов. */
    val horizontalMirrorRotate270 get() = KVkSurfaceTransformFlagsBitsKHR.HORIZONTAL_MIRROR_ROTATE_270.check(flags)
    /** `true`, если поддерживается наследование трансформации. */
    val inherit get() = KVkSurfaceTransformFlagsBitsKHR.INHERIT.check(flags)
}
