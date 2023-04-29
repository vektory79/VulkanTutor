package me.vektory79.vulkan.kotlin

import me.vektory79.vulkan.kotlin.struct.KVkDeviceCreateInfo
import org.lwjgl.PointerBuffer
import org.lwjgl.system.MemoryStack
import org.lwjgl.vulkan.VK10
import org.lwjgl.vulkan.VK10.vkDestroyDevice
import org.lwjgl.vulkan.VkDevice

class KVkDevice(val struct: VkDevice) {
    fun destroy() {
        vkDestroyDevice(struct, null)
    }

    companion object {

        context(MemoryStack)
        fun vkCreateDevice(physicalDevice: KVkPhysicalDevice, createInfo: KVkDeviceCreateInfo): KVkDevice {
            val pDevice: PointerBuffer = pointers(VK10.VK_NULL_HANDLE)
            checkVkResult(VK10.vkCreateDevice(physicalDevice, createInfo.struct, null, pDevice))
            return KVkDevice(VkDevice(pDevice[0], physicalDevice, createInfo.struct))
        }
    }
}
