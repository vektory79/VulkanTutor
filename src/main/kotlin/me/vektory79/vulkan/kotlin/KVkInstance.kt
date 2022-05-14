package me.vektory79.vulkan.kotlin

import org.lwjgl.PointerBuffer
import org.lwjgl.system.MemoryStack
import org.lwjgl.system.MemoryUtil
import org.lwjgl.vulkan.EXTDebugUtils
import org.lwjgl.vulkan.VK10.VK_ERROR_EXTENSION_NOT_PRESENT
import org.lwjgl.vulkan.VK10.VK_NULL_HANDLE
import org.lwjgl.vulkan.VK10.VK_SUCCESS
import org.lwjgl.vulkan.VK10.vkCreateInstance
import org.lwjgl.vulkan.VK10.vkDestroyInstance
import org.lwjgl.vulkan.VK10.vkEnumerateInstanceLayerProperties
import org.lwjgl.vulkan.VK10.vkEnumeratePhysicalDevices
import org.lwjgl.vulkan.VK10.vkGetInstanceProcAddr
import org.lwjgl.vulkan.VkAllocationCallbacks
import org.lwjgl.vulkan.VkDebugUtilsMessengerCreateInfoEXT
import org.lwjgl.vulkan.VkInstance
import org.lwjgl.vulkan.VkInstanceCreateInfo
import org.lwjgl.vulkan.VkLayerProperties
import org.lwjgl.vulkan.VkPhysicalDevice
import java.nio.LongBuffer

class KVkInstance(handle: Long, ci: VkInstanceCreateInfo, val allocator: VkAllocationCallbacks? = null) :
    VkInstance(handle, ci) {
    private fun checkedExtensionCall(funcName: String, call: () -> Int) {
        checkVkResult(
            if (vkGetInstanceProcAddr(
                    this,
                    funcName
                ) != MemoryUtil.NULL
            ) {
                call()
            } else VK_ERROR_EXTENSION_NOT_PRESENT
        )
    }

    fun destroy() {
        vkDestroyInstance(this, allocator)
    }

    context(MemoryStack)
    fun createDebugUtilsMessenger(createInfo: VkDebugUtilsMessengerCreateInfoEXT): Long {
        val pDebugMessenger: LongBuffer = longs(VK_NULL_HANDLE)
        checkedExtensionCall("vkCreateDebugUtilsMessengerEXT") {
            EXTDebugUtils.vkCreateDebugUtilsMessengerEXT(
                this,
                createInfo,
                allocator,
                pDebugMessenger
            )
        }
        return pDebugMessenger[0]
    }

    fun destroyDebugUtilsMessenger(debugMessenger: Long) {
        checkedExtensionCall("vkDestroyDebugUtilsMessengerEXT") {
            EXTDebugUtils.vkDestroyDebugUtilsMessengerEXT(this, debugMessenger, allocator)
            VK_SUCCESS
        }
    }

    context(MemoryStack)
    fun getPhysicalDevices(): PhysicalDevices {
        val deviceCount = ints(0)
        checkVkResult(vkEnumeratePhysicalDevices(this, deviceCount, null))
        if (deviceCount[0] == 0) {
            throw RuntimeException("Failed to find GPUs with Vulkan support")
        }
        val ppPhysicalDevices: PointerBuffer = mallocPointer(deviceCount[0])
        vkEnumeratePhysicalDevices(this, deviceCount, ppPhysicalDevices)
        return PhysicalDevices(ppPhysicalDevices)
    }
}

context(MemoryStack)
fun vkCreateInstance(
    init: context(MemoryStack) () -> VkInstanceCreateInfo
): KVkInstance {
    val createInfo = init(this@MemoryStack)
    // We need to retrieve the pointer of the created instance
    val instancePtr = mallocPointer(1)
    checkVkResult(vkCreateInstance(createInfo, null, instancePtr))
    return KVkInstance(instancePtr[0], createInfo)
}

context(MemoryStack)
fun vkGetInstanceLayerProperties(): VkLayerProperties.Buffer {
    val layerCount = ints(0)
    vkEnumerateInstanceLayerProperties(layerCount, null)
    val availableLayers: VkLayerProperties.Buffer = VkLayerProperties.malloc(layerCount[0], this@MemoryStack)
    vkEnumerateInstanceLayerProperties(layerCount, availableLayers)
    return availableLayers
}

@JvmInline
value class PhysicalDevices(val ppPhysicalDevices: PointerBuffer) {
    inline fun iterate(instance: VkInstance, processor: (KVkPhysicalDevice) -> Unit) {
        for (i in 0 until ppPhysicalDevices.capacity()) {
            val device = KVkPhysicalDevice(ppPhysicalDevices[i], instance)
            processor(device)
        }
    }
}
