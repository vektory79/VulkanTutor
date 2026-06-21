package me.vektory79.vulkan.kotlin

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test

@Tag("integration")
class KVkPhysicalDeviceTest : VulkanTestBase() {

    @Test
    fun `each physical device has at least one queue family`() {
        stackPush {
            val physicalDevices = instance!!.getPhysicalDevices()
            physicalDevices.iterate(instance!!) { device ->
                val families = device.queueFamilyProperties
                assertThat(families.capacity).isGreaterThan(0)
            }
        }
    }

    @Test
    fun `find graphics queue family on suitable device`() {
        var foundGraphicsFamily: Int? = null

        stackPush {
            val physicalDevices = instance!!.getPhysicalDevices()
            physicalDevices.iterate(instance!!) { device ->
                val families = device.queueFamilyProperties
                for (i in 0 until families.capacity) {
                    if (families[i].queueFlags.graphics) {
                        foundGraphicsFamily = i
                        return@iterate
                    }
                }
            }
        }

        assertThat(foundGraphicsFamily).isNotNull()
    }

    @Test
    fun `queue family has correct queue count`() {
        stackPush {
            val physicalDevices = instance!!.getPhysicalDevices()
            physicalDevices.iterate(instance!!) { device ->
                val families = device.queueFamilyProperties
                for (i in 0 until families.capacity) {
                    assertThat(families[i].queueCount.toInt()).isGreaterThan(0)
                }
            }
        }
    }

    @Test
    fun `physical device has available extensions`() {
        stackPush {
            val physicalDevices = instance!!.getPhysicalDevices()
            physicalDevices.iterate(instance!!) { device ->
                val extensions = device.availableExtensions
                assertThat(extensions.capacity).isGreaterThan(0)
            }
        }
    }

    @Test
    fun `extension names are readable and include swapchain`() {
        var foundSwapchain = false

        stackPush {
            val physicalDevices = instance!!.getPhysicalDevices()
            physicalDevices.iterate(instance!!) { device ->
                val extensions = device.availableExtensions
                for (i in 0 until extensions.capacity) {
                    val name = extensions[i].extensionNameString
                    assertThat(name).isNotBlank()
                    if (name.contains("KHR_swapchain")) {
                        foundSwapchain = true
                    }
                }
            }
        }

        assertThat(foundSwapchain).isTrue()
    }
}
