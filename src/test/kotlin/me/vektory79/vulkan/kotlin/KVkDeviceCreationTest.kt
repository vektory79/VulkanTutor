package me.vektory79.vulkan.kotlin

import me.vektory79.vulkan.kotlin.struct.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test
import org.lwjgl.vulkan.KHRSwapchain.VK_KHR_SWAPCHAIN_EXTENSION_NAME
import org.lwjgl.vulkan.VK10.VK_NULL_HANDLE

@Tag("integration")
class KVkDeviceCreationTest : VulkanTestBase() {

    private var physicalDevice: KVkPhysicalDevice? = null
    private var graphicsFamily: Int = -1

    @Test
    fun `create device without extensions`() {
        selectDevice()
        val device = physicalDevice!!
        var logicalDevice: KVkDevice? = null

        try {
            stackPush {
                val createInfo = KVkDeviceCreateInfo.vkDeviceCreateInfo {
                    pQueueCreateInfos = KVkDeviceQueueCreateInfoArray.vkDeviceQueueCreateInfoArray {
                        add {
                            queueFamilyIndex = graphicsFamily
                            pQueuePriorities = floats(1.0f)
                        }
                    }
                    pEnabledFeatures = KVkPhysicalDeviceFeatures.vkPhysicalDeviceFeatures()
                }

                logicalDevice = KVkDevice.vkCreateDevice(device, createInfo)
            }

            assertThat(logicalDevice).isNotNull()
            assertThat(logicalDevice!!.struct.address()).isNotEqualTo(VK_NULL_HANDLE)
        } finally {
            logicalDevice?.destroy()
        }
    }

    @Test
    fun `create device with swapchain extension`() {
        selectDevice()
        val device = physicalDevice!!
        var logicalDevice: KVkDevice? = null

        try {
            stackPush {
                val createInfo = KVkDeviceCreateInfo.vkDeviceCreateInfo {
                    pQueueCreateInfos = KVkDeviceQueueCreateInfoArray.vkDeviceQueueCreateInfoArray {
                        add {
                            queueFamilyIndex = graphicsFamily
                            pQueuePriorities = floats(1.0f)
                        }
                    }
                    pEnabledFeatures = KVkPhysicalDeviceFeatures.vkPhysicalDeviceFeatures()
                    ppEnabledExtensionNames = this@stackPush.mallocPointer(1).apply {
                        put(this@stackPush.UTF8(VK_KHR_SWAPCHAIN_EXTENSION_NAME))
                        rewind()
                    }
                }

                logicalDevice = KVkDevice.vkCreateDevice(device, createInfo)
            }

            assertThat(logicalDevice).isNotNull()
            assertThat(logicalDevice!!.struct.address()).isNotEqualTo(VK_NULL_HANDLE)
        } finally {
            logicalDevice?.destroy()
        }
    }

    @Test
    fun `getDeviceQueue returns queue belonging to device`() {
        selectDevice()
        val device = physicalDevice!!
        var logicalDevice: KVkDevice? = null

        try {
            stackPush {
                val createInfo = KVkDeviceCreateInfo.vkDeviceCreateInfo {
                    pQueueCreateInfos = KVkDeviceQueueCreateInfoArray.vkDeviceQueueCreateInfoArray {
                        add {
                            queueFamilyIndex = graphicsFamily
                            pQueuePriorities = floats(1.0f)
                        }
                    }
                    pEnabledFeatures = KVkPhysicalDeviceFeatures.vkPhysicalDeviceFeatures()
                }

                logicalDevice = KVkDevice.vkCreateDevice(device, createInfo)
            }

            val queue = KVkQueue.vkGetDeviceQueue(logicalDevice!!, graphicsFamily)

            assertThat(queue.struct.address()).isNotEqualTo(VK_NULL_HANDLE)
            assertThat(queue.device.struct.address()).isEqualTo(logicalDevice.struct.address())
        } finally {
            logicalDevice?.destroy()
        }
    }

    @Test
    fun `device queue create info array with multiple queues`() {
        selectDevice()
        val device = physicalDevice!!
        var logicalDevice: KVkDevice? = null

        try {
            stackPush {
                val createInfo = KVkDeviceCreateInfo.vkDeviceCreateInfo {
                    pQueueCreateInfos = KVkDeviceQueueCreateInfoArray.vkDeviceQueueCreateInfoArray {
                        add {
                            queueFamilyIndex = graphicsFamily
                            pQueuePriorities = floats(1.0f)
                        }
                        add {
                            queueFamilyIndex = graphicsFamily
                            pQueuePriorities = floats(0.5f)
                        }
                    }
                    pEnabledFeatures = KVkPhysicalDeviceFeatures.vkPhysicalDeviceFeatures()
                }

                logicalDevice = KVkDevice.vkCreateDevice(device, createInfo)
            }

            assertThat(logicalDevice).isNotNull()
        } finally {
            logicalDevice?.destroy()
        }
    }

    private fun selectDevice() {
        stackPush {
            val physicalDevices = instance!!.getPhysicalDevices()
            physicalDevices.iterate(instance!!) { device ->
                val families = device.queueFamilyProperties
                for (i in 0 until families.capacity) {
                    if (families[i].queueFlags.graphics) {
                        physicalDevice = device
                        graphicsFamily = i
                        return@iterate
                    }
                }
            }
        }
    }
}
