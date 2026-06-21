package me.vektory79.vulkan.kotlin

import org.lwjgl.vulkan.VK10.VK_NULL_HANDLE
import org.lwjgl.vulkan.VK10.vkGetDeviceQueue
import org.lwjgl.vulkan.VkQueue

/**
 * Vulkan-очередь — канал отправки команд на GPU.
 *
 * Получается через ``vkGetDeviceQueue`` из логического устройства. Очередь принадлежит
 * определённому семейству очередей и используется для записи командных буферов
 * (``vkQueueSubmit``) и презентации изображений (``vkQueuePresentKHR``).
 *
 * @property struct LWJGL-обёртка [org.lwjgl.vulkan.VkQueue] над нативным хэндлом.
 */
class KVkQueue(val struct: VkQueue) {
    /**
     * Логическое устройство, из которого была получена эта очередь.
     */
    val device: KVkDevice
        get() = KVkDevice(struct.device)

    companion object {
        /**
         * Получает очередь из логического устройства.
         *
         * Вызывает ``vkGetDeviceQueue`` для указанного семейства очередей (индекс 0 внутри семейства).
         *
         * @param device логическое устройство.
         * @param graphicsFamily индекс семейства очередей.
         * @return обёртка над полученной очередью.
         */
        fun vkGetDeviceQueue(device: KVkDevice, graphicsFamily: Int): KVkQueue {
            stackPush {
                val pGraphicsQueue = pointers(VK_NULL_HANDLE)
                vkGetDeviceQueue(device.struct, graphicsFamily, 0, pGraphicsQueue)
                return KVkQueue(VkQueue(pGraphicsQueue[0], device.struct))
            }
        }
    }
}
