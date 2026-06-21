package me.vektory79.vulkan.kotlin

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test
import org.lwjgl.vulkan.VK10.VK_SUCCESS
import org.lwjgl.vulkan.VK10.VK_ERROR_INITIALIZATION_FAILED
import org.lwjgl.vulkan.VK10.VK_ERROR_OUT_OF_HOST_MEMORY
import org.lwjgl.vulkan.VK10.VK_ERROR_OUT_OF_DEVICE_MEMORY
import org.lwjgl.vulkan.VK10.VK_ERROR_LAYER_NOT_PRESENT
import org.lwjgl.vulkan.VK10.VK_ERROR_EXTENSION_NOT_PRESENT
import org.lwjgl.vulkan.VK10.VK_ERROR_FEATURE_NOT_PRESENT
import org.lwjgl.vulkan.VK10.VK_ERROR_DEVICE_LOST
import org.lwjgl.vulkan.VK10.VK_NOT_READY
import org.lwjgl.vulkan.VK10.VK_TIMEOUT
import org.lwjgl.vulkan.VK10.VK_INCOMPLETE

@Tag("unit")
class VkResultCodeTest {

    @Test
    fun `describeResultCode maps known Vulkan result codes`() {
        assertThat(describeResultCode(VK_SUCCESS).code).isEqualTo(VK_SUCCESS)
        assertThat(describeResultCode(VK_NOT_READY).code).isEqualTo(VK_NOT_READY)
        assertThat(describeResultCode(VK_TIMEOUT).code).isEqualTo(VK_TIMEOUT)
        assertThat(describeResultCode(VK_INCOMPLETE).code).isEqualTo(VK_INCOMPLETE)
        assertThat(describeResultCode(VK_ERROR_INITIALIZATION_FAILED).code).isEqualTo(VK_ERROR_INITIALIZATION_FAILED)
        assertThat(describeResultCode(VK_ERROR_OUT_OF_HOST_MEMORY).code).isEqualTo(VK_ERROR_OUT_OF_HOST_MEMORY)
        assertThat(describeResultCode(VK_ERROR_OUT_OF_DEVICE_MEMORY).code).isEqualTo(VK_ERROR_OUT_OF_DEVICE_MEMORY)
        assertThat(describeResultCode(VK_ERROR_LAYER_NOT_PRESENT).code).isEqualTo(VK_ERROR_LAYER_NOT_PRESENT)
        assertThat(describeResultCode(VK_ERROR_EXTENSION_NOT_PRESENT).code).isEqualTo(VK_ERROR_EXTENSION_NOT_PRESENT)
        assertThat(describeResultCode(VK_ERROR_FEATURE_NOT_PRESENT).code).isEqualTo(VK_ERROR_FEATURE_NOT_PRESENT)
        assertThat(describeResultCode(VK_ERROR_DEVICE_LOST).code).isEqualTo(VK_ERROR_DEVICE_LOST)
    }

    @Test
    fun `describeResultCode throws for unknown code`() {
        assertThatThrownBy { describeResultCode(-99999) }
            .isInstanceOf(IllegalStateException::class.java)
    }

    @Test
    fun `checkVkResult succeeds for VK_SUCCESS`() {
        checkVkResult(VK_SUCCESS)
    }

    @Test
    fun `checkVkResult throws VkException for error code`() {
        try {
            checkVkResult(VK_ERROR_INITIALIZATION_FAILED)
            error("Expected VkException")
        } catch (ex: VkException) {
            assertThat(ex.code).isNotNull()
            assertThat(ex.message).isNotBlank()
        }
    }

    @Test
    fun `VkException message corresponds to result code`() {
        val ex = VkException(VK_ERROR_OUT_OF_HOST_MEMORY)
        assertThat(ex.message!!.lowercase()).contains("host memory")
    }
}
