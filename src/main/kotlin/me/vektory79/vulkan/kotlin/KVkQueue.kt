package me.vektory79.vulkan.kotlin

import org.lwjgl.system.MemoryStack
import org.lwjgl.vulkan.VK10
import org.lwjgl.vulkan.VK10.vkGetDeviceQueue
import org.lwjgl.vulkan.VkQueue

class KVkQueue(val struct: VkQueue) {
    val device: KVkDevice
        get() = KVkDevice(struct.device)
}

context(MemoryStack)
fun vkGetDeviceQueue(device: KVkDevice, graphicsFamily: Int): KVkQueue {
    val pGraphicsQueue = pointers(VK10.VK_NULL_HANDLE)
    vkGetDeviceQueue(device.struct, graphicsFamily, 0, pGraphicsQueue)
    return KVkQueue(VkQueue(pGraphicsQueue[0], device.struct))
}
