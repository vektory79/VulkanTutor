package me.vektory79.vulkan.kotlin

import me.vektory79.vulkan.kotlin.struct.KVkDebugUtilsMessengerCreateInfoEXT
import me.vektory79.vulkan.kotlin.struct.KVkInstanceCreateInfo
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
import org.lwjgl.vulkan.VkInstance
import org.lwjgl.vulkan.VkInstanceCreateInfo
import org.lwjgl.vulkan.VkLayerProperties
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

    context(stack: MemoryStack)
    fun createDebugUtilsMessenger(createInfo: KVkDebugUtilsMessengerCreateInfoEXT): Long {
        val pDebugMessenger: LongBuffer = stack.longs(VK_NULL_HANDLE)
        checkedExtensionCall("vkCreateDebugUtilsMessengerEXT") {
            EXTDebugUtils.vkCreateDebugUtilsMessengerEXT(
                this,
                createInfo.struct,
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

    context(stack: MemoryStack)
    fun getPhysicalDevices(): PhysicalDevices {
        val deviceCount = stack.ints(0)
        checkVkResult(vkEnumeratePhysicalDevices(this, deviceCount, null))
        if (deviceCount[0] == 0) {
            throw RuntimeException("Failed to find GPUs with Vulkan support")
        }
        val ppPhysicalDevices: PointerBuffer = stack.mallocPointer(deviceCount[0])
        vkEnumeratePhysicalDevices(this, deviceCount, ppPhysicalDevices)
        return PhysicalDevices(ppPhysicalDevices)
    }

    companion object {
        context(stack: MemoryStack)
        fun vkCreateInstance(
            init: context(MemoryStack) () -> KVkInstanceCreateInfo
        ): KVkInstance {
            val createInfo = init()
            // We need to retrieve the pointer of the created instance
            val instancePtr = stack.mallocPointer(1)
            checkVkResult(vkCreateInstance(createInfo.struct, null, instancePtr))
            return KVkInstance(instancePtr[0], createInfo.struct)
        }
    }
}

context(stack: MemoryStack)
fun vkGetInstanceLayerProperties(): VkLayerProperties.Buffer {
    val layerCount = stack.ints(0)
    vkEnumerateInstanceLayerProperties(layerCount, null)
    val availableLayers: VkLayerProperties.Buffer = VkLayerProperties.malloc(layerCount[0], stack)
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
