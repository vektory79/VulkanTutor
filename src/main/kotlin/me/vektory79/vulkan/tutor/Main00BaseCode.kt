package me.vektory79.vulkan.tutor

import org.lwjgl.glfw.GLFW.GLFW_CLIENT_API
import org.lwjgl.glfw.GLFW.GLFW_NO_API
import org.lwjgl.glfw.GLFW.glfwCreateWindow
import org.lwjgl.glfw.GLFW.glfwDestroyWindow
import org.lwjgl.glfw.GLFW.glfwInit
import org.lwjgl.glfw.GLFW.glfwPollEvents
import org.lwjgl.glfw.GLFW.glfwTerminate
import org.lwjgl.glfw.GLFW.glfwWindowHint
import org.lwjgl.glfw.GLFW.glfwWindowShouldClose
import org.lwjgl.vulkan.VK10.vkEnumerateInstanceExtensionProperties
import org.lwjgl.vulkan.VkExtensionProperties

fun main() {
    glfwInit()
    glfwWindowHint(GLFW_CLIENT_API, GLFW_NO_API)
    val window = glfwCreateWindow(800, 600, "Vulkan window", 0, 0)

    try {
        val layerName: String? = null
        val extensionCount = intArrayOf(0)
        val properties: VkExtensionProperties.Buffer? = null
        vkEnumerateInstanceExtensionProperties(layerName, extensionCount, properties)

        println("${extensionCount[0]} extensions supported")

        while (!glfwWindowShouldClose(window)) {
            glfwPollEvents();
        }
    } finally {
        glfwDestroyWindow(window);

        glfwTerminate();
    }
}
