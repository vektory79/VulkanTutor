package me.vektory79.vulkan.kotlin

import org.lwjgl.system.MemoryStack
import org.lwjgl.vulkan.VK10.vkCreateInstance
import org.lwjgl.vulkan.VK10.vkEnumerateInstanceLayerProperties
import org.lwjgl.vulkan.VkInstance
import org.lwjgl.vulkan.VkInstanceCreateInfo
import org.lwjgl.vulkan.VkLayerProperties

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
