package me.vektory79.vulkan.kotlin

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test
import org.lwjgl.vulkan.VK10.VK_NULL_HANDLE

@Tag("integration")
class KVkSurfaceTest : VulkanTestBase() {

    private var surface: KVkSurface? = null

    @BeforeEach
    override fun createWindowAndInstance() {
        super.createWindowAndInstance()
        stackPush {
            surface = KVkSurface.glfwCreateWindowSurface(instance!!, window)
        }
    }

    @AfterEach
    override fun destroyWindowAndInstance() {
        surface?.destroy()
        super.destroyWindowAndInstance()
    }

    @Test
    fun `surface handle is valid`() {
        assertThat(surface!!.surface).isNotEqualTo(VK_NULL_HANDLE)
    }

    @Test
    fun `physical device reports surface capabilities`() {
        stackPush {
            val physicalDevices = instance!!.getPhysicalDevices()
            physicalDevices.iterate(instance!!) { device ->
                val capabilities = device.getSurfaceCapabilities(surface!!)
                assertThat(capabilities.minImageCount.toInt()).isGreaterThan(0)
            }
        }
    }

    @Test
    fun `physical device reports surface formats`() {
        stackPush {
            val physicalDevices = instance!!.getPhysicalDevices()
            physicalDevices.iterate(instance!!) { device ->
                val formats = device.getSurfaceFormats(surface!!)
                assertThat(formats).isNotNull()
                assertThat(formats!!.capacity).isGreaterThan(0)
            }
        }
    }

    @Test
    fun `surface format has valid format and color space`() {
        stackPush {
            val physicalDevices = instance!!.getPhysicalDevices()
            physicalDevices.iterate(instance!!) { device ->
                val formats = device.getSurfaceFormats(surface!!)
                if (formats != null) {
                    for (i in 0 until formats.capacity) {
                        assertThat(formats[i].format).isNotNull()
                        assertThat(formats[i].colorSpace).isNotNull()
                    }
                }
            }
        }
    }

    @Test
    fun `surfaceSupport returns boolean for each queue family`() {
        stackPush {
            val physicalDevices = instance!!.getPhysicalDevices()
            physicalDevices.iterate(instance!!) { device ->
                val families = device.queueFamilyProperties
                for (i in 0 until families.capacity) {
                    device.surfaceSupport(i, surface!!)
                }
            }
        }
    }
}
