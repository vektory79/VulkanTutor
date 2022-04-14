package me.vektory79.vulkan.kotlin

import org.lwjgl.system.MemoryStack
import org.lwjgl.system.MemoryUtil
import org.lwjgl.vulkan.EXTDebugUtils
import org.lwjgl.vulkan.EXTDebugUtils.*
import org.lwjgl.vulkan.VK10
import org.lwjgl.vulkan.VK10.VK_ERROR_EXTENSION_NOT_PRESENT
import org.lwjgl.vulkan.VK10.VK_NULL_HANDLE
import org.lwjgl.vulkan.VK10.vkCreateInstance
import org.lwjgl.vulkan.VK10.vkEnumerateInstanceLayerProperties
import org.lwjgl.vulkan.VK10.vkGetInstanceProcAddr
import org.lwjgl.vulkan.VkAllocationCallbacks
import org.lwjgl.vulkan.VkDebugUtilsMessengerCreateInfoEXT
import org.lwjgl.vulkan.VkInstance
import org.lwjgl.vulkan.VkInstanceCreateInfo
import org.lwjgl.vulkan.VkLayerProperties
import java.nio.LongBuffer

context(MemoryStack)
fun vkCreateInstance(
    init: context(MemoryStack) () -> VkInstanceCreateInfo
): VkInstance {
    val createInfo = init(this@MemoryStack)
    // We need to retrieve the pointer of the created instance
    val instancePtr = mallocPointer(1)
    checkVkResult(vkCreateInstance(createInfo, null, instancePtr))
    return VkInstance(instancePtr[0], createInfo)
}

context(MemoryStack)
fun vkGetInstanceLayerProperties(): VkLayerProperties.Buffer {
    val layerCount = ints(0)
    vkEnumerateInstanceLayerProperties(layerCount, null)
    val availableLayers: VkLayerProperties.Buffer = VkLayerProperties.malloc(layerCount[0], this@MemoryStack)
    vkEnumerateInstanceLayerProperties(layerCount, availableLayers)
    return availableLayers
}

context(MemoryStack)
fun vkCreateDebugUtilsMessengerEXT(
    instance: VkInstance?,
    createInfo: VkDebugUtilsMessengerCreateInfoEXT
): Long {
    val pDebugMessenger: LongBuffer = longs(VK_NULL_HANDLE)

    checkVkResult(
        if (vkGetInstanceProcAddr(
                instance!!,
                "vkCreateDebugUtilsMessengerEXT"
            ) != MemoryUtil.NULL
        ) {
            vkCreateDebugUtilsMessengerEXT(
                instance,
                createInfo,
                null,
                pDebugMessenger
            )
        } else VK_ERROR_EXTENSION_NOT_PRESENT
    )
    return pDebugMessenger[0]
}

fun vkDestroyDebugUtilsMessengerEXT(
    instance: VkInstance?,
    debugMessenger: Long,
    allocationCallbacks: VkAllocationCallbacks?
) {
    if (vkGetInstanceProcAddr(instance, "vkDestroyDebugUtilsMessengerEXT") != MemoryUtil.NULL) {
        EXTDebugUtils.vkDestroyDebugUtilsMessengerEXT(instance!!, debugMessenger, allocationCallbacks)
    }
}
