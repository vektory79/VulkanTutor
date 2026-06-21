package me.vektory79.vulkan.kotlin

import me.vektory79.vulkan.kotlin.struct.KVkDeviceCreateInfo
import org.lwjgl.PointerBuffer
import org.lwjgl.system.MemoryStack
import org.lwjgl.vulkan.VK10
import org.lwjgl.vulkan.VK10.vkDestroyDevice
import org.lwjgl.vulkan.VkDevice

/**
 * Логическое Vulkan-устройство, представляющее соединение приложения с GPU.
 *
 * Создаётся на основе [KVkPhysicalDevice] и определяет набор очередей, расширений и слоёв,
 * которые приложение будет использовать. Через логическое устройство подаются команды
 * в очереди и создаются ресурсы (буферы, изображения, пайплайны).
 *
 * @property struct LWJGL-обёртка [org.lwjgl.vulkan.VkDevice] над нативным хэндлом.
 */
class KVkDevice(val struct: VkDevice) {
    /**
     * Уничтожает логическое устройство и освобождает все связанные с ним ресурсы.
     *
     * Вызывает ``vkDestroyDevice``. После вызова все ресурсы, созданные через это
     * устройство, становятся невалидными.
     */
    fun destroy() {
        vkDestroyDevice(struct, null)
    }

    companion object {
        /**
         * Создаёт логическое устройство на основе физического.
         *
         * Вызывает ``vkCreateDevice`` с указанными параметрами из [createInfo].
         *
         * @param physicalDevice физическое устройство (GPU), для которого создаётся логическое устройство.
         * @param createInfo параметры создания устройства.
         * @return созданное логическое устройство.
         */
        context(stack: MemoryStack)
        fun vkCreateDevice(physicalDevice: KVkPhysicalDevice, createInfo: KVkDeviceCreateInfo): KVkDevice {
            val pDevice: PointerBuffer = stack.pointers(VK10.VK_NULL_HANDLE)
            checkVkResult(VK10.vkCreateDevice(physicalDevice, createInfo.struct, null, pDevice))
            return KVkDevice(VkDevice(pDevice[0], physicalDevice, createInfo.struct))
        }
    }
}
