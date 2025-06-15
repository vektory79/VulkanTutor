package me.vektory79.vulkan.kotlin

import org.lwjgl.glfw.GLFWVulkan.glfwCreateWindowSurface
import org.lwjgl.system.MemoryStack
import org.lwjgl.vulkan.KHRSurface.vkDestroySurfaceKHR
import org.lwjgl.vulkan.VK10.VK_NULL_HANDLE
import org.lwjgl.vulkan.VK10.VK_SUCCESS

class KVkSurface(private val instance: KVkInstance, val surface: Long) {
    fun destroy() {
        vkDestroySurfaceKHR(instance, surface, null)
    }

    companion object {
        context(stack: MemoryStack)
        fun glfwCreateWindowSurface(instance: KVkInstance, window: Long): KVkSurface {
            val pSurface = stack.longs(VK_NULL_HANDLE)
            if (glfwCreateWindowSurface(instance, window, null, pSurface) != VK_SUCCESS) {
                throw RuntimeException("Failed to create window surface")
            }
            return KVkSurface(instance, pSurface[0])
        }
    }
}
