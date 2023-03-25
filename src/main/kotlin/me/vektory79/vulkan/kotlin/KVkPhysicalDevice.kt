package me.vektory79.vulkan.kotlin

import org.lwjgl.system.MemoryStack
import org.lwjgl.vulkan.KHRSurface.vkGetPhysicalDeviceSurfaceSupportKHR
import org.lwjgl.vulkan.VK10.VK_FALSE
import org.lwjgl.vulkan.VK10.VK_TRUE
import org.lwjgl.vulkan.VK10.vkGetPhysicalDeviceQueueFamilyProperties
import org.lwjgl.vulkan.VkInstance
import org.lwjgl.vulkan.VkPhysicalDevice
import org.lwjgl.vulkan.VkQueueFamilyProperties
import java.nio.IntBuffer

class KVkPhysicalDevice(handle: Long, instance: VkInstance) : VkPhysicalDevice(handle, instance) {
    context(MemoryStack)
    fun queueFamilyProperties(): VkQueueFamilyProperties.Buffer {
        val queueFamilyCount = ints(0)
        vkGetPhysicalDeviceQueueFamilyProperties(this, queueFamilyCount, null)
        val queueFamilies = VkQueueFamilyProperties.malloc(queueFamilyCount[0], this@MemoryStack)
        vkGetPhysicalDeviceQueueFamilyProperties(this, queueFamilyCount, queueFamilies)
        return queueFamilies
    }

    fun surfaceSupport(pipelineFamilyId: Int, surface: KVkSurface): Boolean {
        stackPush {
            val presentSupport: IntBuffer = ints(VK_FALSE)
            vkGetPhysicalDeviceSurfaceSupportKHR(
                this@KVkPhysicalDevice,
                pipelineFamilyId,
                surface.surface,
                presentSupport
            )
            return presentSupport[0] == VK_TRUE
        }
    }
}
