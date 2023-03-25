package me.vektory79.vulkan.tutor

import me.vektory79.vulkan.kotlin.KVkDevice
import me.vektory79.vulkan.kotlin.KVkInstance
import me.vektory79.vulkan.kotlin.KVkPhysicalDevice
import me.vektory79.vulkan.kotlin.KVkQueue
import me.vektory79.vulkan.kotlin.KVkSurface
import me.vektory79.vulkan.kotlin.glfwCreateWindowSurface
import me.vektory79.vulkan.kotlin.stackPush
import me.vektory79.vulkan.kotlin.struct.vkApplicationInfo
import me.vektory79.vulkan.kotlin.struct.vkDebugUtilsMessengerCreateInfoEXT
import me.vektory79.vulkan.kotlin.struct.vkDeviceCreateInfo
import me.vektory79.vulkan.kotlin.struct.vkDeviceQueueCreateInfoArray
import me.vektory79.vulkan.kotlin.struct.vkInstanceCreateInfo
import me.vektory79.vulkan.kotlin.struct.vkPhysicalDeviceFeatures
import me.vektory79.vulkan.kotlin.vkCreateDevice
import me.vektory79.vulkan.kotlin.vkCreateInstance
import me.vektory79.vulkan.kotlin.vkGetDeviceQueue
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
import org.lwjgl.vulkan.EXTDebugUtils.VK_DEBUG_UTILS_MESSAGE_SEVERITY_ERROR_BIT_EXT
import org.lwjgl.vulkan.EXTDebugUtils.VK_DEBUG_UTILS_MESSAGE_SEVERITY_VERBOSE_BIT_EXT
import org.lwjgl.vulkan.EXTDebugUtils.VK_DEBUG_UTILS_MESSAGE_SEVERITY_WARNING_BIT_EXT
import org.lwjgl.vulkan.EXTDebugUtils.VK_DEBUG_UTILS_MESSAGE_TYPE_GENERAL_BIT_EXT
import org.lwjgl.vulkan.EXTDebugUtils.VK_DEBUG_UTILS_MESSAGE_TYPE_PERFORMANCE_BIT_EXT
import org.lwjgl.vulkan.EXTDebugUtils.VK_DEBUG_UTILS_MESSAGE_TYPE_VALIDATION_BIT_EXT
import org.lwjgl.vulkan.EXTDebugUtils.VK_EXT_DEBUG_UTILS_EXTENSION_NAME
import org.lwjgl.vulkan.VK10.VK_API_VERSION_1_0
import org.lwjgl.vulkan.VK10.VK_FALSE
import org.lwjgl.vulkan.VK10.VK_MAKE_VERSION
import org.lwjgl.vulkan.VK10.VK_QUEUE_GRAPHICS_BIT
import org.lwjgl.vulkan.VkDebugUtilsMessengerCallbackDataEXT
import org.lwjgl.vulkan.VkDebugUtilsMessengerCallbackEXTI
import org.lwjgl.vulkan.VkLayerProperties
import java.util.stream.Collectors
import java.util.stream.IntStream

@Suppress("UNUSED_PARAMETER")
private fun debugCallback(messageSeverity: Int, messageType: Int, pCallbackData: Long, pUserData: Long): Int {
    val callbackData = VkDebugUtilsMessengerCallbackDataEXT.create(pCallbackData)
    System.err.println("Validation layer: " + callbackData.pMessageString())
    return VK_FALSE
}

class HelloTriangleApplication05 {

    private lateinit var instance: KVkInstance
    private var window: Long = 0
    private var debugMessenger: Long = 0
    private lateinit var surface: KVkSurface
    private lateinit var physicalDevice: KVkPhysicalDevice
    private lateinit var device: KVkDevice
    private lateinit var graphicsQueue: KVkQueue
    private lateinit var presentQueue: KVkQueue

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
        setupDebugMessenger()
        createSurface()
        pickPhysicalDevice()
        createLogicalDevice()
    }

    private fun mainLoop() {
        while (!glfwWindowShouldClose(window)) {
            glfwPollEvents()
        }
    }

    private fun cleanup() {
        device.destroy()
        if (ENABLE_VALIDATION_LAYERS) {
            instance.destroyDebugUtilsMessenger(debugMessenger)
        }
        surface.destroy()
        instance.destroy()
        glfwDestroyWindow(window)
        glfwTerminate()
    }

    private fun createInstance() {
        if (ENABLE_VALIDATION_LAYERS && !checkValidationLayerSupport()) {
            throw RuntimeException("Validation requested but not supported")
        }

        stackPush {
            instance = vkCreateInstance {
                vkInstanceCreateInfo {
                    pApplicationInfo = vkApplicationInfo {
                        pApplicationName = UTF8("Hello Triangle")
                        applicationVersion = VK_MAKE_VERSION(1, 0, 0)
                        pEngineName = UTF8("No Engine")
                        engineVersion = VK_MAKE_VERSION(1, 0, 0)
                        apiVersion = VK_API_VERSION_1_0
                    }
                    // enabledExtensionCount is implicitly set when you call ppEnabledExtensionNames
                    ppEnabledExtensionNames = getRequiredExtensions()
                    // same with enabledLayerCount
                    ppEnabledLayerNames = if (ENABLE_VALIDATION_LAYERS) validationLayersAsPointerBuffer() else null
                }
            }
        }
    }

    context(MemoryStack)
    private fun getRequiredExtensions(): PointerBuffer? {
        val glfwExtensions = glfwGetRequiredInstanceExtensions()
        if (ENABLE_VALIDATION_LAYERS && glfwExtensions != null) {
            return mallocPointer(glfwExtensions.capacity() + 1).apply {
                put(glfwExtensions)
                put(UTF8(VK_EXT_DEBUG_UTILS_EXTENSION_NAME))
                // Rewind the buffer before returning it to reset its position back to 0
                rewind()
            }
        }
        return glfwExtensions
    }

    context(MemoryStack)
    private fun validationLayersAsPointerBuffer(): PointerBuffer? {
        val buffer = mallocPointer(VALIDATION_LAYERS.size)
        VALIDATION_LAYERS.stream()
            .map(this@MemoryStack::UTF8)
            .forEach(buffer::put)
        return buffer.rewind()
    }

    private fun setupDebugMessenger() {
        if (!ENABLE_VALIDATION_LAYERS) return
        stackPush {
            debugMessenger = instance.createDebugUtilsMessenger(
                vkDebugUtilsMessengerCreateInfoEXT {
                    messageSeverity =
                        VK_DEBUG_UTILS_MESSAGE_SEVERITY_VERBOSE_BIT_EXT or
                            VK_DEBUG_UTILS_MESSAGE_SEVERITY_WARNING_BIT_EXT or
                            VK_DEBUG_UTILS_MESSAGE_SEVERITY_ERROR_BIT_EXT
                    messageType =
                        VK_DEBUG_UTILS_MESSAGE_TYPE_GENERAL_BIT_EXT or
                            VK_DEBUG_UTILS_MESSAGE_TYPE_VALIDATION_BIT_EXT or
                            VK_DEBUG_UTILS_MESSAGE_TYPE_PERFORMANCE_BIT_EXT
                    pfnUserCallback = VkDebugUtilsMessengerCallbackEXTI(::debugCallback)
                }
            )
        }
    }

    private fun createSurface() {
        stackPush {
            surface = glfwCreateWindowSurface(instance, window)
        }
    }

    private fun pickPhysicalDevice() {
        stackPush {
            val physicalDevices = instance.getPhysicalDevices()
            physicalDevices.iterate(instance) { device ->
                if (isDeviceSuitable(device)) {
                    physicalDevice = device
                    return
                }
            }
            throw RuntimeException("Failed to find a suitable GPU")
        }
    }

    private fun isDeviceSuitable(device: KVkPhysicalDevice): Boolean {
        val indices = findQueueFamilies(device)
        return indices.isComplete
    }

    private fun findQueueFamilies(device: KVkPhysicalDevice): QueueFamilyIndices {
        val indices = QueueFamilyIndices()
        stackPush {
            val queueFamilies = device.queueFamilyProperties()
            for (index in 0..queueFamilies.capacity()) {
                if (queueFamilies[index].queueFlags() and VK_QUEUE_GRAPHICS_BIT != 0) {
                    indices.graphicsFamily = index
                }
                if (device.surfaceSupport(index, surface)) {
                    indices.presentFamily = index
                }
            }
            return indices
        }
    }

    private fun createLogicalDevice() {
        stackPush {
            val indices: QueueFamilyIndices = findQueueFamilies(physicalDevice)

            val createInfo = vkDeviceCreateInfo {
                pQueueCreateInfos = vkDeviceQueueCreateInfoArray {
                    add {
                        queueFamilyIndex = indices.graphicsFamily!!
                        pQueuePriorities = floats(1.0f)
                    }
                }
                pEnabledFeatures = vkPhysicalDeviceFeatures()
                if (ENABLE_VALIDATION_LAYERS) {
                    ppEnabledLayerNames = validationLayersAsPointerBuffer()
                }
            }

            device = vkCreateDevice(physicalDevice, createInfo)
            graphicsQueue = vkGetDeviceQueue(device, indices.graphicsFamily!!)
            presentQueue = vkGetDeviceQueue(device, indices.presentFamily!!)
        }
    }

    private fun checkValidationLayerSupport(): Boolean {
        stackPush {
            val availableLayers = vkGetInstanceLayerProperties()
            val availableLayerNames = availableLayers.stream()
                .map { obj: VkLayerProperties -> obj.layerNameString() }
                .collect(Collectors.toSet())
            return availableLayerNames.containsAll(VALIDATION_LAYERS)
        }
    }

    class QueueFamilyIndices {
        // We use Integer to use null as the empty value
        var graphicsFamily: Int? = null
        var presentFamily: Int? = null
        val isComplete: Boolean
            get() = graphicsFamily != null && presentFamily != null

        fun unique(): IntArray? = IntStream.of(graphicsFamily!!, presentFamily!!).distinct().toArray()
    }
}

fun main() {
    val app = HelloTriangleApplication05()

    app.run()
}
