package me.vektory79.vulkan.kotlin

import me.vektory79.vulkan.kotlin.struct.KVkExtensionPropertiesArray
import me.vektory79.vulkan.kotlin.struct.KVkQueueFamilyPropertiesArray
import me.vektory79.vulkan.kotlin.struct.KVkSurfaceCapabilitiesKHR
import me.vektory79.vulkan.kotlin.struct.KVkSurfaceFormatKHRArray
import org.lwjgl.system.MemoryStack
import org.lwjgl.vulkan.KHRSurface
import org.lwjgl.vulkan.KHRSurface.vkGetPhysicalDeviceSurfaceCapabilitiesKHR
import org.lwjgl.vulkan.KHRSurface.vkGetPhysicalDeviceSurfaceSupportKHR
import org.lwjgl.vulkan.VK10.VK_FALSE
import org.lwjgl.vulkan.VK10.VK_TRUE
import org.lwjgl.vulkan.VK10.vkEnumerateDeviceExtensionProperties
import org.lwjgl.vulkan.VK10.vkGetPhysicalDeviceQueueFamilyProperties
import org.lwjgl.vulkan.VkExtensionProperties
import org.lwjgl.vulkan.VkInstance
import org.lwjgl.vulkan.VkPhysicalDevice
import org.lwjgl.vulkan.VkQueueFamilyProperties
import org.lwjgl.vulkan.VkSurfaceCapabilitiesKHR
import org.lwjgl.vulkan.VkSurfaceFormatKHR
import java.nio.IntBuffer

class KVkPhysicalDevice(handle: Long, instance: VkInstance) : VkPhysicalDevice(handle, instance) {
    context(MemoryStack)
    val queueFamilyProperties: KVkQueueFamilyPropertiesArray
        get() {
            val queueFamilyCount = ints(0)
            vkGetPhysicalDeviceQueueFamilyProperties(this, queueFamilyCount, null)
            val queueFamilies = VkQueueFamilyProperties.malloc(queueFamilyCount[0], this@MemoryStack)
            vkGetPhysicalDeviceQueueFamilyProperties(this, queueFamilyCount, queueFamilies)
            return KVkQueueFamilyPropertiesArray(queueFamilies)
        }

    context(MemoryStack)
    val availableExtensions: KVkExtensionPropertiesArray
        get() {
            val extensionCount = ints(0)
            vkEnumerateDeviceExtensionProperties(this, null as String?, extensionCount, null)
            val availableExtensions =
                VkExtensionProperties.malloc(extensionCount[0], this@MemoryStack)
            vkEnumerateDeviceExtensionProperties(this, null as String?, extensionCount, availableExtensions)
            return KVkExtensionPropertiesArray(availableExtensions)
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

    context(MemoryStack)
    fun getSurfaceCapabilities(surface: KVkSurface): KVkSurfaceCapabilitiesKHR {
        val capabilities = VkSurfaceCapabilitiesKHR.malloc(this@MemoryStack)
        vkGetPhysicalDeviceSurfaceCapabilitiesKHR(this, surface.surface, capabilities)
        return KVkSurfaceCapabilitiesKHR(capabilities)
    }

    context(MemoryStack)
    fun getSurfaceFormats(surface: KVkSurface): KVkSurfaceFormatKHRArray? {
        val count: IntBuffer = ints(0)
        KHRSurface.vkGetPhysicalDeviceSurfaceFormatsKHR(this, surface.surface, count, null)

        if (count[0] != 0) {
            val formats = VkSurfaceFormatKHR.malloc(count[0], this@MemoryStack)
            KHRSurface.vkGetPhysicalDeviceSurfaceFormatsKHR(this, surface.surface, count, formats)
            return KVkSurfaceFormatKHRArray(formats)
        }
        return null
    }

    context(MemoryStack)
    fun getPhysicalDeviceSurfacePresentModes(surface: KVkSurface): IntBuffer? {
        val count: IntBuffer = ints(0)
        KHRSurface.vkGetPhysicalDeviceSurfacePresentModesKHR(this, surface.surface, count, null)

        if (count[0] != 0) {
            val presentModes = mallocInt(count[0])
            KHRSurface.vkGetPhysicalDeviceSurfacePresentModesKHR(this, surface.surface, count, presentModes)
            return presentModes
        }
        return null
    }
}
