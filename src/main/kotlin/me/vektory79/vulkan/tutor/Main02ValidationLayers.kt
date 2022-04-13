package me.vektory79.vulkan.tutor

import me.vektory79.vulkan.kotlin.apiVersion
import me.vektory79.vulkan.kotlin.applicationVersion
import me.vektory79.vulkan.kotlin.createVkApplicationInfo
import me.vektory79.vulkan.kotlin.createVkInstanceCreateInfo
import me.vektory79.vulkan.kotlin.engineVersion
import me.vektory79.vulkan.kotlin.pApplicationInfo
import me.vektory79.vulkan.kotlin.pApplicationName
import me.vektory79.vulkan.kotlin.pEngineName
import me.vektory79.vulkan.kotlin.ppEnabledExtensionNames
import me.vektory79.vulkan.kotlin.ppEnabledLayerNames
import me.vektory79.vulkan.kotlin.vkCreateInstance
import me.vektory79.vulkan.kotlin.vkGetInstanceLayerProperties
import org.lwjgl.PointerBuffer
import org.lwjgl.glfw.GLFW.GLFW_CLIENT_API
import org.lwjgl.glfw.GLFW.GLFW_FALSE
import org.lwjgl.glfw.GLFW.GLFW_NO_API
import org.lwjgl.glfw.GLFW.GLFW_RESIZABLE
import org.lwjgl.glfw.GLFW.glfwCreateWindow
import org.lwjgl.glfw.GLFW.glfwDestroyWindow
import org.lwjgl.glfw.GLFW.glfwInit
import org.lwjgl.glfw.GLFW.glfwPollEvents
import org.lwjgl.glfw.GLFW.glfwTerminate
import org.lwjgl.glfw.GLFW.glfwWindowHint
import org.lwjgl.glfw.GLFW.glfwWindowShouldClose
import org.lwjgl.glfw.GLFWVulkan.glfwGetRequiredInstanceExtensions
import org.lwjgl.system.MemoryStack
import org.lwjgl.system.MemoryStack.stackPush
import org.lwjgl.vulkan.EXTDebugUtils
import org.lwjgl.vulkan.VK10
import org.lwjgl.vulkan.VK10.VK_API_VERSION_1_0
import org.lwjgl.vulkan.VK10.VK_MAKE_VERSION
import org.lwjgl.vulkan.VK10.vkDestroyInstance
import org.lwjgl.vulkan.VkDebugUtilsMessengerCallbackDataEXT
import org.lwjgl.vulkan.VkInstance
import org.lwjgl.vulkan.VkLayerProperties
import java.util.stream.Collectors

private fun debugCallback(messageSeverity: Int, messageType: Int, pCallbackData: Long, pUserData: Long): Int {
    val callbackData = VkDebugUtilsMessengerCallbackDataEXT.create(pCallbackData)
    System.err.println("Validation layer: " + callbackData.pMessageString())
    return VK10.VK_FALSE
}

class HelloTriangleApplication02 {

    private var instance: VkInstance? = null
    private var window: Long = 0

    fun run() {
        initWindow()
        initVulkan()
        mainLoop()
        cleanup()
    }

    private fun initWindow() {
        glfwInit()
        glfwWindowHint(GLFW_CLIENT_API, GLFW_NO_API)
        glfwWindowHint(GLFW_RESIZABLE, GLFW_FALSE)
        window = glfwCreateWindow(WIDTH, HEIGHT, "Vulkan", 0, 0)
    }

    private fun initVulkan() {
        createInstance()
    }

    private fun mainLoop() {
        while (!glfwWindowShouldClose(window)) {
            glfwPollEvents()
        }
    }

    private fun cleanup() {
        instance?.also {
            vkDestroyInstance(it, null)
        }
        glfwDestroyWindow(window)
        glfwTerminate()
    }

    private fun createInstance() {
        if (ENABLE_VALIDATION_LAYERS && !checkValidationLayerSupport()) {
            throw RuntimeException("Validation requested but not supported")
        }

        stackPush().use { stack ->
            instance = vkCreateInstance(stack) {
                createVkInstanceCreateInfo(stack) {
                    pApplicationInfo = createVkApplicationInfo(stack) {
                        pApplicationName = stack.UTF8Safe("Hello Triangle")
                        applicationVersion = VK_MAKE_VERSION(1, 0, 0)
                        pEngineName = stack.UTF8Safe("No Engine")
                        engineVersion = VK_MAKE_VERSION(1, 0, 0)
                        apiVersion = VK_API_VERSION_1_0
                    }
                    // enabledExtensionCount is implicitly set when you call ppEnabledExtensionNames
                    ppEnabledExtensionNames = getRequiredExtensions(stack)
                    // same with enabledLayerCount
                    ppEnabledLayerNames = if (ENABLE_VALIDATION_LAYERS) validationLayersAsPointerBuffer(stack) else null
                }
            }
        }
    }

    private fun getRequiredExtensions(stack: MemoryStack): PointerBuffer? {
        val glfwExtensions = glfwGetRequiredInstanceExtensions()
        if (ENABLE_VALIDATION_LAYERS) {
            val extensions = stack.mallocPointer(glfwExtensions!!.capacity() + 1)
            extensions.put(glfwExtensions)
            extensions.put(stack.UTF8(EXTDebugUtils.VK_EXT_DEBUG_UTILS_EXTENSION_NAME))

            // Rewind the buffer before returning it to reset its position back to 0
            return extensions.rewind()
        }
        return glfwExtensions
    }

    private fun validationLayersAsPointerBuffer(stack: MemoryStack): PointerBuffer? {
        val buffer = stack.mallocPointer(VALIDATION_LAYERS.size)
        VALIDATION_LAYERS.stream()
            .map(stack::UTF8)
            .forEach(buffer::put)
        return buffer.rewind()
    }

    private fun checkValidationLayerSupport(): Boolean {
        stackPush().use { stack ->
            val availableLayers = vkGetInstanceLayerProperties(stack)
            val availableLayerNames = availableLayers.stream()
                .map { obj: VkLayerProperties -> obj.layerNameString() }
                .collect(Collectors.toSet())
            return availableLayerNames.containsAll(VALIDATION_LAYERS)
        }
    }
}

fun main() {
    val app = HelloTriangleApplication02()

    app.run()
}