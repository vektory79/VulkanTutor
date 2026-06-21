package me.vektory79.vulkan.kotlin.flags

import me.vektory79.vulkan.kotlin.KVkBits
import me.vektory79.vulkan.kotlin.KVkFlags
import org.lwjgl.vulkan.VK10.VK_IMAGE_USAGE_COLOR_ATTACHMENT_BIT
import org.lwjgl.vulkan.VK10.VK_IMAGE_USAGE_DEPTH_STENCIL_ATTACHMENT_BIT
import org.lwjgl.vulkan.VK10.VK_IMAGE_USAGE_INPUT_ATTACHMENT_BIT
import org.lwjgl.vulkan.VK10.VK_IMAGE_USAGE_SAMPLED_BIT
import org.lwjgl.vulkan.VK10.VK_IMAGE_USAGE_STORAGE_BIT
import org.lwjgl.vulkan.VK10.VK_IMAGE_USAGE_TRANSFER_DST_BIT
import org.lwjgl.vulkan.VK10.VK_IMAGE_USAGE_TRANSFER_SRC_BIT
import org.lwjgl.vulkan.VK10.VK_IMAGE_USAGE_TRANSIENT_ATTACHMENT_BIT
import java.rmi.UnexpectedException

/**
 * Битовые флаги, описывающие способы использования изображения Vulkan.
 *
 * Соответствует `VkImageUsageFlagBits` из Vulkan API. Каждый бит указывает,
 * что изображение может использоваться для определённой роли: источник/приёмник
 * копирования, семплирование, хранение, прикрепление цвета/глубины и т.д.
 *
 * @property value целочисленное значение бита.
 * @see KVkImageUsageFlags
 */
enum class KVkImageUsageFlagBits(override val value: Int) : KVkBits {
    /** Изображение может быть источником команд копирования и блиттинга. */
    TRANSFER_SRC(VK_IMAGE_USAGE_TRANSFER_SRC_BIT),
    /** Изображение может быть приёмником команд копирования и блиттинга. */
    TRANSFER_DST(VK_IMAGE_USAGE_TRANSFER_DST_BIT),
    /** Изображение может быть семплировано шейдером. */
    SAMPLED(VK_IMAGE_USAGE_SAMPLED_BIT),
    /** Изображение может использоваться как storage image в шейдере. */
    STORAGE(VK_IMAGE_USAGE_STORAGE_BIT),
    /** Изображение может использоваться как color attachment во фреймбуфере. */
    COLOR_ATTACHMENT(VK_IMAGE_USAGE_COLOR_ATTACHMENT_BIT),
    /** Изображение может использоваться как depth/stencil attachment во фреймбуфере. */
    DEPTH_STENCIL_ATTACHMENT(VK_IMAGE_USAGE_DEPTH_STENCIL_ATTACHMENT_BIT),
    /** Изображение может использоваться как transient attachment во фреймбуфере. */
    TRANSIENT_ATTACHMENT(VK_IMAGE_USAGE_TRANSIENT_ATTACHMENT_BIT),
    /** Изображение может использоваться как input attachment во фреймбуфере. */
    INPUT_ATTACHMENT(VK_IMAGE_USAGE_INPUT_ATTACHMENT_BIT);

    companion object {
        /**
         * Преобразует целочисленное значение бита в соответствующий элемент перечисления.
         *
         * @param value целочисленное значение (VK_IMAGE_USAGE_*_BIT).
         * @return соответствующий флаг.
         * @throws UnexpectedException если значение не соответствует ни одному известному флагу.
         */
        fun toEnum(value: Int): KVkImageUsageFlagBits {
            return when(value) {
                VK_IMAGE_USAGE_TRANSFER_SRC_BIT -> TRANSFER_SRC
                VK_IMAGE_USAGE_TRANSFER_DST_BIT -> TRANSFER_DST
                VK_IMAGE_USAGE_SAMPLED_BIT -> SAMPLED
                VK_IMAGE_USAGE_STORAGE_BIT -> STORAGE
                VK_IMAGE_USAGE_COLOR_ATTACHMENT_BIT -> COLOR_ATTACHMENT
                VK_IMAGE_USAGE_DEPTH_STENCIL_ATTACHMENT_BIT -> DEPTH_STENCIL_ATTACHMENT
                VK_IMAGE_USAGE_TRANSIENT_ATTACHMENT_BIT -> TRANSIENT_ATTACHMENT
                VK_IMAGE_USAGE_INPUT_ATTACHMENT_BIT -> INPUT_ATTACHMENT
                else -> throw UnexpectedException("Unexpected image usage flags")
            }
        }
    }
}

/**
 * Комбинация битовых флагов использования изображения Vulkan.
 *
 * Оборачивает целочисленное значение `VkImageUsageFlags` и предоставляет свойства-чекеры
 * для каждого способа использования: [transferSrc], [transferDst], [sampled], [storage],
 * [colorAttachment], [depthStencilAttachment], [transientAttachment], [inputAttachment].
 *
 * @property flags целочисленное значение комбинации битов.
 * @see KVkImageUsageFlagBits
 */
@JvmInline
value class KVkImageUsageFlags(override val flags: Int) : KVkFlags {
    /** `true`, если изображение может быть источником передачи. */
    val transferSrc get() = KVkImageUsageFlagBits.TRANSFER_SRC.check(flags)
    /** `true`, если изображение может быть приёмником передачи. */
    val transferDst get() = KVkImageUsageFlagBits.TRANSFER_DST.check(flags)
    /** `true`, если изображение может быть семплировано шейдером. */
    val sampled get() = KVkImageUsageFlagBits.SAMPLED.check(flags)
    /** `true`, если изображение может использоваться как storage. */
    val storage get() = KVkImageUsageFlagBits.STORAGE.check(flags)
    /** `true`, если изображение может быть color attachment. */
    val colorAttachment get() = KVkImageUsageFlagBits.COLOR_ATTACHMENT.check(flags)
    /** `true`, если изображение может быть depth/stencil attachment. */
    val depthStencilAttachment get() = KVkImageUsageFlagBits.DEPTH_STENCIL_ATTACHMENT.check(flags)
    /** `true`, если изображение может быть transient attachment. */
    val transientAttachment get() = KVkImageUsageFlagBits.TRANSIENT_ATTACHMENT.check(flags)
    /** `true`, если изображение может быть input attachment. */
    val inputAttachment get() = KVkImageUsageFlagBits.INPUT_ATTACHMENT.check(flags)
}
