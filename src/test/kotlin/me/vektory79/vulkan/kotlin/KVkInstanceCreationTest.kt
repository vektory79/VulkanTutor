package me.vektory79.vulkan.kotlin

import me.vektory79.vulkan.kotlin.struct.KVkDeviceCreateInfo
import me.vektory79.vulkan.kotlin.struct.KVkDeviceQueueCreateInfoArray
import me.vektory79.vulkan.kotlin.struct.KVkPhysicalDeviceFeatures
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test
import org.lwjgl.vulkan.VK10.VK_NULL_HANDLE

@Tag("integration")
class KVkInstanceCreationTest : VulkanTestBase() {

    @Test
    fun `instance handle is valid`() {
        assertThat(instance!!.address()).isNotEqualTo(VK_NULL_HANDLE)
    }

    @Test
    fun `enumerate physical devices returns at least one GPU`() {
        stackPush {
            val physicalDevices = instance!!.getPhysicalDevices()

            var count = 0
            physicalDevices.iterate(instance!!) { _ -> count++ }

            assertThat(count).isGreaterThan(0)
        }
    }

    @Test
    fun `select physical device with graphics queue and create logical device`() {
        var selectedDevice: KVkPhysicalDevice? = null
        var graphicsFamily: Int? = null

        stackPush {
            val physicalDevices = instance!!.getPhysicalDevices()
            physicalDevices.iterate(instance!!) { device ->
                val families = device.queueFamilyProperties
                for (i in 0 until families.capacity) {
                    if (families[i].queueFlags.graphics) {
                        selectedDevice = device
                        graphicsFamily = i
                        return@iterate
                    }
                }
            }
        }

        assertThat(selectedDevice).isNotNull()
        assertThat(graphicsFamily).isNotNull()

        val device = selectedDevice!!
        val family = graphicsFamily!!

        var logicalDevice: KVkDevice? = null

        try {
            stackPush {
                val createInfo = KVkDeviceCreateInfo.vkDeviceCreateInfo {
                    pQueueCreateInfos = KVkDeviceQueueCreateInfoArray.vkDeviceQueueCreateInfoArray {
                        add {
                            queueFamilyIndex = family
                            pQueuePriorities = floats(1.0f)
                        }
                    }
                    pEnabledFeatures = KVkPhysicalDeviceFeatures.vkPhysicalDeviceFeatures()
                }

                logicalDevice = KVkDevice.vkCreateDevice(device, createInfo)
                val graphicsQueue = KVkQueue.vkGetDeviceQueue(logicalDevice, family)

                assertThat(logicalDevice.struct.address()).isNotEqualTo(VK_NULL_HANDLE)
                assertThat(graphicsQueue.struct.address()).isNotEqualTo(VK_NULL_HANDLE)

                // Verify queue belongs to the correct device
                assertThat(graphicsQueue.device.struct.address())
                    .isEqualTo(logicalDevice.struct.address())
            }
        } finally {
            logicalDevice?.destroy()
        }
    }
}
