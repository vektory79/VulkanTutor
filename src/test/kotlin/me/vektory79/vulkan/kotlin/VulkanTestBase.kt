package me.vektory79.vulkan.kotlin

import me.vektory79.vulkan.kotlin.struct.KVkApplicationInfo
import me.vektory79.vulkan.kotlin.struct.KVkInstanceCreateInfo
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.lwjgl.glfw.GLFW.*
import org.lwjgl.glfw.GLFWVulkan.glfwGetRequiredInstanceExtensions
import org.lwjgl.vulkan.VK10.VK_API_VERSION_1_0
import org.lwjgl.vulkan.VK10.VK_MAKE_VERSION

/**
 * Base class for Vulkan integration tests.
 *
 * GLFW is initialized once per test class to avoid glfwInit/glfwTerminate cycle
 * that crashes the Virtio-GPU driver. Each test gets a fresh Vulkan instance.
 */
abstract class VulkanTestBase {

    companion object {
        private var glfwInitialized = false

        @JvmStatic
        @BeforeAll
        fun initGlfw() {
            if (glfwInitialized) return
            glfwInit()
            glfwWindowHint(GLFW_CLIENT_API, GLFW_NO_API)
            glfwWindowHint(GLFW_RESIZABLE, GLFW_FALSE)
            glfwInitialized = true
        }

        @JvmStatic
        @AfterAll
        fun terminateGlfw() {
            if (glfwInitialized) {
                glfwTerminate()
                glfwInitialized = false
            }
        }
    }

    protected var window: Long = 0
    protected var instance: KVkInstance? = null

    @BeforeEach
    open fun createWindowAndInstance() {
        window = glfwCreateWindow(800, 600, "Vulkan Test", 0, 0)

        stackPush {
            instance = KVkInstance.vkCreateInstance {
                KVkInstanceCreateInfo.vkInstanceCreateInfo {
                    pApplicationInfo = KVkApplicationInfo.vkApplicationInfo {
                        pApplicationName = this@stackPush.UTF8("TestApp")
                        applicationVersion = VK_MAKE_VERSION(1, 0, 0)
                        pEngineName = this@stackPush.UTF8("TestEngine")
                        engineVersion = VK_MAKE_VERSION(1, 0, 0)
                        apiVersion = VK_API_VERSION_1_0
                    }
                    ppEnabledExtensionNames = glfwGetRequiredInstanceExtensions()
                    ppEnabledLayerNames = null
                }
            }
        }
    }

    @AfterEach
    open fun destroyWindowAndInstance() {
        instance?.destroy()
        glfwDestroyWindow(window)
    }
}
