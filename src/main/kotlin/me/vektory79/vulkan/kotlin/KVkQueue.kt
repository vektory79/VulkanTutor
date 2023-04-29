package me.vektory79.vulkan.kotlin

import org.lwjgl.vulkan.VK10.VK_NULL_HANDLE
import org.lwjgl.vulkan.VK10.vkGetDeviceQueue
import org.lwjgl.vulkan.VkQueue

class KVkQueue(val struct: VkQueue) {
    val device: KVkDevice
        get() = KVkDevice(struct.device)

    companion object {
        fun vkGetDeviceQueue(device: KVkDevice, graphicsFamily: Int): KVkQueue {
            stackPush {
                val pGraphicsQueue = pointers(VK_NULL_HANDLE)
                vkGetDeviceQueue(device.struct, graphicsFamily, 0, pGraphicsQueue)
                return KVkQueue(VkQueue(pGraphicsQueue[0], device.struct))
            }
        }
    }
}
