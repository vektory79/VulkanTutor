package me.vektory79.vulkan.kotlin.flags

import me.vektory79.vulkan.kotlin.KVkBits
import me.vektory79.vulkan.kotlin.KVkFlags
import org.lwjgl.vulkan.KHRSurface.VK_COMPOSITE_ALPHA_INHERIT_BIT_KHR
import org.lwjgl.vulkan.KHRSurface.VK_COMPOSITE_ALPHA_OPAQUE_BIT_KHR
import org.lwjgl.vulkan.KHRSurface.VK_COMPOSITE_ALPHA_POST_MULTIPLIED_BIT_KHR
import org.lwjgl.vulkan.KHRSurface.VK_COMPOSITE_ALPHA_PRE_MULTIPLIED_BIT_KHR
import java.rmi.UnexpectedException

/**
 * Битовые флаги композитирования альфа-канала Vulkan, соответствующие `VkCompositeAlphaFlagBitsKHR`.
 *
 * Определяют, как альфа-канал изображений swap chain композитируется с содержимым
 * окна на рабочем столе. Включает непрозрачный, предварительно и пост-умноженный режимы.
 *
 * @property value целочисленное значение бита.
 * @see KVkCompositeAlphaFlagsKHR
 */
enum class KVkCompositeAlphaFlagsBitsKHR(override val value: Int) : KVkBits {
    /** Изображения swap chain непрозрачны; альфа-канал игнорируется. */
    OPAQUE(VK_COMPOSITE_ALPHA_OPAQUE_BIT_KHR),
    /** Альфа-канал предварительно умножен на цвета изображения. */
    PRE_MULTIPLIED(VK_COMPOSITE_ALPHA_PRE_MULTIPLIED_BIT_KHR),
    /** Альфа-канал не умножен на цвета изображения. */
    POST_MULTIPLIED(VK_COMPOSITE_ALPHA_POST_MULTIPLIED_BIT_KHR),
    /** Режим композитирования наследуется из параметров swap chain. */
    INHERIT(VK_COMPOSITE_ALPHA_INHERIT_BIT_KHR);

    companion object {
        /**
         * Преобразует целочисленное значение бита в соответствующий элемент перечисления.
         *
         * @param value целочисленное значение (VK_COMPOSITE_ALPHA_*_BIT).
         * @return соответствующий флаг.
         * @throws UnexpectedException если значение не соответствует ни одному известному флагу.
         */
        fun toEnum(value: Int): KVkCompositeAlphaFlagsBitsKHR {
            return when(value) {
                VK_COMPOSITE_ALPHA_OPAQUE_BIT_KHR -> OPAQUE
                VK_COMPOSITE_ALPHA_PRE_MULTIPLIED_BIT_KHR -> PRE_MULTIPLIED
                VK_COMPOSITE_ALPHA_POST_MULTIPLIED_BIT_KHR -> POST_MULTIPLIED
                VK_COMPOSITE_ALPHA_INHERIT_BIT_KHR -> INHERIT
                else -> throw UnexpectedException("Unexpected composite alpha flags")
            }
        }
    }
}

/**
 * Комбинация битовых флагов композитирования альфа-канала Vulkan.
 *
 * Оборачивает целочисленное значение `VkCompositeAlphaFlagsKHR` и предоставляет
 * свойства-чекеры для каждого режима: [opaque], [preMultiplied], [postMultiplied], [inherit].
 *
 * @property flags целочисленное значение комбинации битов.
 * @see KVkCompositeAlphaFlagsBitsKHR
 */
@JvmInline
value class KVkCompositeAlphaFlagsKHR(override val flags: Int) : KVkFlags {
    /** `true`, если поддерживается непрозрачный режим. */
    val opaque get() = KVkCompositeAlphaFlagsBitsKHR.OPAQUE.check(flags)
    /** `true`, если поддерживается предварительно умноженный режим. */
    val preMultiplied get() = KVkCompositeAlphaFlagsBitsKHR.PRE_MULTIPLIED.check(flags)
    /** `true`, если поддерживается пост-умноженный режим. */
    val postMultiplied get() = KVkCompositeAlphaFlagsBitsKHR.POST_MULTIPLIED.check(flags)
    /** `true`, если поддерживается наследование режима. */
    val inherit get() = KVkCompositeAlphaFlagsBitsKHR.INHERIT.check(flags)
}
