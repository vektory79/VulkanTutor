package me.vektory79.vulkan.kotlin

import org.lwjgl.system.MemoryStack
import org.lwjgl.vulkan.VK10.vkCreateInstance
import org.lwjgl.vulkan.VK10.vkEnumerateInstanceLayerProperties
import org.lwjgl.vulkan.VkInstance
import org.lwjgl.vulkan.VkInstanceCreateInfo
import org.lwjgl.vulkan.VkLayerProperties

fun vkCreateInstance(
    stack: MemoryStack,
    init: (stack: MemoryStack) -> VkInstanceCreateInfo
): VkInstance {
    val createInfo = init(stack)
    // We need to retrieve the pointer of the created instance
    val instancePtr = stack.mallocPointer(1)
    checkVkResult(vkCreateInstance(createInfo, null, instancePtr))
    return VkInstance(instancePtr[0], createInfo)
}

fun vkGetInstanceLayerProperties(stack: MemoryStack): VkLayerProperties.Buffer {
    val layerCount = stack.ints(0)
    vkEnumerateInstanceLayerProperties(layerCount, null)
    val availableLayers: VkLayerProperties.Buffer = VkLayerProperties.malloc(layerCount[0], stack)
    vkEnumerateInstanceLayerProperties(layerCount, availableLayers)
    return availableLayers
}
