package me.vektory79.vulkan.kotlin.flags

import me.vektory79.vulkan.kotlin.KVkBits
import me.vektory79.vulkan.kotlin.KVkFlags
import org.lwjgl.vulkan.VK10.VK_QUEUE_COMPUTE_BIT
import org.lwjgl.vulkan.VK10.VK_QUEUE_GRAPHICS_BIT
import org.lwjgl.vulkan.VK10.VK_QUEUE_SPARSE_BINDING_BIT
import org.lwjgl.vulkan.VK10.VK_QUEUE_TRANSFER_BIT
import java.rmi.UnexpectedException

/**
 * Битовые флаги, описывающие типы команд, которые поддерживает очередь Vulkan.
 *
 * Соответствует `VkQueueFlagBits` из Vulkan API. Каждый бит указывает на поддержку
 * определённого типа операций: графических, вычислительных, передачи данных и sparse-биндинга.
 *
 * @property value целочисленное значение бита.
 * @see KVkQueueFlags
 */
enum class KVkQueueFlagsBits(override val value: Int) : KVkBits {
    /** Очередь поддерживает графические команды (draw, blit, resolve, clear). */
    GRAPHICS(VK_QUEUE_GRAPHICS_BIT),
    /** Очередь поддерживает вычислительные команды (dispatch). */
    COMPUTE(VK_QUEUE_COMPUTE_BIT),
    /** Очередь поддерживает команды передачи (buffer/image copy, blit, resolve). */
    TRANSFER(VK_QUEUE_TRANSFER_BIT),
    /** Очередь поддерживает команды sparse-биндинга (bind memory, memory fence). */
    SPARSE_BINDING(VK_QUEUE_SPARSE_BINDING_BIT);

    companion object {
        /**
         * Преобразует целочисленное значение бита в соответствующий элемент перечисления.
         *
         * @param value целочисленное значение (VK_QUEUE_*_BIT).
         * @return соответствующий флаг.
         * @throws UnexpectedException если значение не соответствует ни одному известному флагу.
         */
        fun toEnum(value: Int): KVkQueueFlagsBits {
            return when(value) {
                VK_QUEUE_GRAPHICS_BIT -> GRAPHICS
                VK_QUEUE_COMPUTE_BIT -> COMPUTE
                VK_QUEUE_TRANSFER_BIT -> TRANSFER
                VK_QUEUE_SPARSE_BINDING_BIT -> SPARSE_BINDING
                else -> throw UnexpectedException("Unexpected queue flags bits")
            }
        }
    }
}

/**
 * Комбинация битовых флагов очереди Vulkan.
 *
 * Оборачивает целочисленное значение `VkQueueFlags` и предоставляет свойства-чекеры
 * для каждого типа команды: [graphics], [compute], [transfer], [sparseBinding].
 *
 * @property flags целочисленное значение комбинации битов.
 * @see KVkQueueFlagsBits
 */
@JvmInline
value class KVkQueueFlags(override val flags: Int) : KVkFlags {
    /** `true`, если очередь поддерживает графические команды. */
    val graphics get() = KVkQueueFlagsBits.GRAPHICS.check(flags)
    /** `true`, если очередь поддерживает вычислительные команды. */
    val compute get() = KVkQueueFlagsBits.COMPUTE.check(flags)
    /** `true`, если очередь поддерживает команды передачи. */
    val transfer get() = KVkQueueFlagsBits.TRANSFER.check(flags)
    /** `true`, если очередь поддерживает команды sparse-биндинга. */
    val sparseBinding get() = KVkQueueFlagsBits.SPARSE_BINDING.check(flags)
}
