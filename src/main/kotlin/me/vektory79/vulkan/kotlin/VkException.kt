package me.vektory79.vulkan.kotlin

import org.lwjgl.vulkan.EXTBufferDeviceAddress.VK_ERROR_INVALID_DEVICE_ADDRESS_EXT
import org.lwjgl.vulkan.EXTFullScreenExclusive.VK_ERROR_FULL_SCREEN_EXCLUSIVE_MODE_LOST_EXT
import org.lwjgl.vulkan.KHRDeferredHostOperations.VK_OPERATION_DEFERRED_KHR
import org.lwjgl.vulkan.KHRDeferredHostOperations.VK_OPERATION_NOT_DEFERRED_KHR
import org.lwjgl.vulkan.KHRDeferredHostOperations.VK_THREAD_DONE_KHR
import org.lwjgl.vulkan.KHRDeferredHostOperations.VK_THREAD_IDLE_KHR
import org.lwjgl.vulkan.KHRDisplaySwapchain.VK_ERROR_INCOMPATIBLE_DISPLAY_KHR
import org.lwjgl.vulkan.KHRSurface.VK_ERROR_NATIVE_WINDOW_IN_USE_KHR
import org.lwjgl.vulkan.KHRSurface.VK_ERROR_SURFACE_LOST_KHR
import org.lwjgl.vulkan.KHRSwapchain.VK_ERROR_OUT_OF_DATE_KHR
import org.lwjgl.vulkan.KHRSwapchain.VK_SUBOPTIMAL_KHR
import org.lwjgl.vulkan.NVGLSLShader.VK_ERROR_INVALID_SHADER_NV
import org.lwjgl.vulkan.VK10.VK_ERROR_DEVICE_LOST
import org.lwjgl.vulkan.VK10.VK_ERROR_EXTENSION_NOT_PRESENT
import org.lwjgl.vulkan.VK10.VK_ERROR_FEATURE_NOT_PRESENT
import org.lwjgl.vulkan.VK10.VK_ERROR_FORMAT_NOT_SUPPORTED
import org.lwjgl.vulkan.VK10.VK_ERROR_FRAGMENTED_POOL
import org.lwjgl.vulkan.VK10.VK_ERROR_INCOMPATIBLE_DRIVER
import org.lwjgl.vulkan.VK10.VK_ERROR_INITIALIZATION_FAILED
import org.lwjgl.vulkan.VK10.VK_ERROR_LAYER_NOT_PRESENT
import org.lwjgl.vulkan.VK10.VK_ERROR_MEMORY_MAP_FAILED
import org.lwjgl.vulkan.VK10.VK_ERROR_OUT_OF_DEVICE_MEMORY
import org.lwjgl.vulkan.VK10.VK_ERROR_OUT_OF_HOST_MEMORY
import org.lwjgl.vulkan.VK10.VK_ERROR_TOO_MANY_OBJECTS
import org.lwjgl.vulkan.VK10.VK_ERROR_UNKNOWN
import org.lwjgl.vulkan.VK10.VK_EVENT_RESET
import org.lwjgl.vulkan.VK10.VK_EVENT_SET
import org.lwjgl.vulkan.VK10.VK_INCOMPLETE
import org.lwjgl.vulkan.VK10.VK_NOT_READY
import org.lwjgl.vulkan.VK10.VK_SUCCESS
import org.lwjgl.vulkan.VK10.VK_TIMEOUT
import org.lwjgl.vulkan.VK11.VK_ERROR_INVALID_EXTERNAL_HANDLE
import org.lwjgl.vulkan.VK11.VK_ERROR_OUT_OF_POOL_MEMORY
import org.lwjgl.vulkan.VK12.VK_ERROR_FRAGMENTATION
import org.lwjgl.vulkan.VK12.VK_ERROR_INVALID_OPAQUE_CAPTURE_ADDRESS
import org.lwjgl.vulkan.VK13.VK_PIPELINE_COMPILE_REQUIRED

enum class VkResultCode(val code: Int, val message: String) {
    SUCCESS(VK_SUCCESS, "Command successfully completed"),
    NOT_READY(VK_NOT_READY, "A fence or query has not yet completed"),
    TIMEOUT(VK_TIMEOUT, "A wait operation has not completed in the specified time"),
    EVENT_SET(VK_EVENT_SET, "An event is signaled"),
    EVENT_RESET(VK_EVENT_RESET, "An event is unsignaled"),
    INCOMPLETE(VK_INCOMPLETE, "A return array was too small for the result"),
    SUBOPTIMAL_KHR(
        VK_SUBOPTIMAL_KHR,
        "A swapchain no longer matches the surface properties exactly, but can still be used to present to the surface successfully."
    ),
    THREAD_IDLE_KHR(
        VK_THREAD_IDLE_KHR,
        "A deferred operation is not complete but there is currently no work for this thread to do at the time of this call."
    ),
    THREAD_DONE_KHR(
        VK_THREAD_DONE_KHR,
        "A deferred operation is not complete but there is no work remaining to assign to additional threads."
    ),
    OPERATION_DEFERRED_KHR(
        VK_OPERATION_DEFERRED_KHR,
        "A deferred operation was requested and at least some of the work was deferred."
    ),
    OPERATION_NOT_DEFERRED_KHR(
        VK_OPERATION_NOT_DEFERRED_KHR,
        "A deferred operation was requested and no operations were deferred."
    ),
    PIPELINE_COMPILE_REQUIRED(
        VK_PIPELINE_COMPILE_REQUIRED,
        "A requested pipeline creation would have required compilation, but the application requested compilation to not be performed."
    ),

    ERROR_OUT_OF_HOST_MEMORY(VK_ERROR_OUT_OF_HOST_MEMORY, "A host memory allocation has failed."),
    ERROR_OUT_OF_DEVICE_MEMORY(VK_ERROR_OUT_OF_DEVICE_MEMORY, "A device memory allocation has failed."),
    ERROR_INITIALIZATION_FAILED(
        VK_ERROR_INITIALIZATION_FAILED,
        "Initialization of an object could not be completed for implementation-specific reasons."
    ),
    ERROR_DEVICE_LOST(
        VK_ERROR_DEVICE_LOST,
        "The logical or physical device has been lost. See Lost Device: https://www.khronos.org/registry/vulkan/specs/1.3-extensions/html/vkspec.html#devsandqueues-lost-device"
    ),
    ERROR_MEMORY_MAP_FAILED(VK_ERROR_MEMORY_MAP_FAILED, "Mapping of a memory object has failed."),
    ERROR_LAYER_NOT_PRESENT(VK_ERROR_LAYER_NOT_PRESENT, "A requested layer is not present or could not be loaded."),
    ERROR_EXTENSION_NOT_PRESENT(VK_ERROR_EXTENSION_NOT_PRESENT, "A requested extension is not supported."),
    ERROR_FEATURE_NOT_PRESENT(VK_ERROR_FEATURE_NOT_PRESENT, "A requested feature is not supported."),
    ERROR_INCOMPATIBLE_DRIVER(
        VK_ERROR_INCOMPATIBLE_DRIVER,
        "The requested version of Vulkan is not supported by the driver or is otherwise incompatible for implementation-specific reasons."
    ),
    ERROR_TOO_MANY_OBJECTS(VK_ERROR_TOO_MANY_OBJECTS, "Too many objects of the type have already been created."),
    ERROR_FORMAT_NOT_SUPPORTED(VK_ERROR_FORMAT_NOT_SUPPORTED, "A requested format is not supported on this device."),
    ERROR_FRAGMENTED_POOL(
        VK_ERROR_FRAGMENTED_POOL,
        "A pool allocation has failed due to fragmentation of the pool’s memory. This must only be returned if no attempt to allocate host or device memory was made to accommodate the new allocation. This should be returned in preference to ERROR_OUT_OF_POOL_MEMORY, but only if the implementation is certain that the pool allocation failure was due to fragmentation."
    ),
    ERROR_SURFACE_LOST_KHR(VK_ERROR_SURFACE_LOST_KHR, "A surface is no longer available."),
    ERROR_NATIVE_WINDOW_IN_USE_KHR(
        VK_ERROR_NATIVE_WINDOW_IN_USE_KHR,
        "The requested window is already in use by Vulkan or another API in a manner which prevents it from being used again."
    ),
    ERROR_OUT_OF_DATE_KHR(
        VK_ERROR_OUT_OF_DATE_KHR,
        "A surface has changed in such a way that it is no longer compatible with the swapchain, and further presentation requests using the swapchain will fail. Applications must query the new surface properties and recreate their swapchain if they wish to continue presenting to the surface."
    ),
    ERROR_INCOMPATIBLE_DISPLAY_KHR(
        VK_ERROR_INCOMPATIBLE_DISPLAY_KHR,
        "The display used by a swapchain does not use the same presentable image layout, or is incompatible in a way that prevents sharing an image."
    ),
    ERROR_INVALID_SHADER_NV(
        VK_ERROR_INVALID_SHADER_NV,
        "One or more shaders failed to compile or link. More details are reported back to the application via VK_EXT_debug_report if enabled."
    ),
    ERROR_OUT_OF_POOL_MEMORY(
        VK_ERROR_OUT_OF_POOL_MEMORY,
        "A pool memory allocation has failed. This must only be returned if no attempt to allocate host or device memory was made to accommodate the new allocation. If the failure was definitely due to fragmentation of the pool, ERROR_FRAGMENTED_POOL should be returned instead."
    ),
    ERROR_INVALID_EXTERNAL_HANDLE(
        VK_ERROR_INVALID_EXTERNAL_HANDLE,
        "An external handle is not a valid handle of the specified type."
    ),
    ERROR_FRAGMENTATION(VK_ERROR_FRAGMENTATION, "A descriptor pool creation has failed due to fragmentation."),
    ERROR_INVALID_DEVICE_ADDRESS_EXT(
        VK_ERROR_INVALID_DEVICE_ADDRESS_EXT,
        "A buffer creation failed because the requested address is not available."
    ),
    ERROR_INVALID_OPAQUE_CAPTURE_ADDRESS(
        VK_ERROR_INVALID_OPAQUE_CAPTURE_ADDRESS,
        "A buffer creation or memory allocation failed because the requested address is not available. A shader group handle assignment failed because the requested shader group handle information is no longer valid."
    ),
    ERROR_FULL_SCREEN_EXCLUSIVE_MODE_LOST_EXT(
        VK_ERROR_FULL_SCREEN_EXCLUSIVE_MODE_LOST_EXT,
        "An operation on a swapchain created with FULL_SCREEN_EXCLUSIVE_APPLICATION_CONTROLLED_EXT failed as it did not have exlusive full-screen access. This may occur due to implementation-dependent reasons, outside of the application’s control."
    ),
    ERROR_UNKNOWN(
        VK_ERROR_UNKNOWN,
        "An unknown error has occurred; either the application has provided invalid input, or an implementation failure has occurred."
    )
}

fun describeResultCode(code: Int): VkResultCode =
    when (code) {
        VK_SUCCESS -> VkResultCode.SUCCESS
        VK_NOT_READY -> VkResultCode.NOT_READY
        VK_TIMEOUT -> VkResultCode.TIMEOUT
        VK_EVENT_SET -> VkResultCode.EVENT_SET
        VK_EVENT_RESET -> VkResultCode.EVENT_RESET
        VK_INCOMPLETE -> VkResultCode.INCOMPLETE
        VK_SUBOPTIMAL_KHR -> VkResultCode.SUBOPTIMAL_KHR
        VK_THREAD_IDLE_KHR -> VkResultCode.THREAD_IDLE_KHR
        VK_THREAD_DONE_KHR -> VkResultCode.THREAD_DONE_KHR
        VK_OPERATION_DEFERRED_KHR -> VkResultCode.OPERATION_DEFERRED_KHR
        VK_OPERATION_NOT_DEFERRED_KHR -> VkResultCode.OPERATION_NOT_DEFERRED_KHR
        VK_PIPELINE_COMPILE_REQUIRED -> VkResultCode.PIPELINE_COMPILE_REQUIRED
        VK_ERROR_OUT_OF_HOST_MEMORY -> VkResultCode.ERROR_OUT_OF_HOST_MEMORY
        VK_ERROR_OUT_OF_DEVICE_MEMORY -> VkResultCode.ERROR_OUT_OF_DEVICE_MEMORY
        VK_ERROR_INITIALIZATION_FAILED -> VkResultCode.ERROR_INITIALIZATION_FAILED
        VK_ERROR_DEVICE_LOST -> VkResultCode.ERROR_DEVICE_LOST
        VK_ERROR_MEMORY_MAP_FAILED -> VkResultCode.ERROR_MEMORY_MAP_FAILED
        VK_ERROR_LAYER_NOT_PRESENT -> VkResultCode.ERROR_LAYER_NOT_PRESENT
        VK_ERROR_EXTENSION_NOT_PRESENT -> VkResultCode.ERROR_EXTENSION_NOT_PRESENT
        VK_ERROR_FEATURE_NOT_PRESENT -> VkResultCode.ERROR_FEATURE_NOT_PRESENT
        VK_ERROR_INCOMPATIBLE_DRIVER -> VkResultCode.ERROR_INCOMPATIBLE_DRIVER
        VK_ERROR_TOO_MANY_OBJECTS -> VkResultCode.ERROR_TOO_MANY_OBJECTS
        VK_ERROR_FORMAT_NOT_SUPPORTED -> VkResultCode.ERROR_FORMAT_NOT_SUPPORTED
        VK_ERROR_FRAGMENTED_POOL -> VkResultCode.ERROR_FRAGMENTED_POOL
        VK_ERROR_SURFACE_LOST_KHR -> VkResultCode.ERROR_SURFACE_LOST_KHR
        VK_ERROR_NATIVE_WINDOW_IN_USE_KHR -> VkResultCode.ERROR_NATIVE_WINDOW_IN_USE_KHR
        VK_ERROR_OUT_OF_DATE_KHR -> VkResultCode.ERROR_OUT_OF_DATE_KHR
        VK_ERROR_INCOMPATIBLE_DISPLAY_KHR -> VkResultCode.ERROR_INCOMPATIBLE_DISPLAY_KHR
        VK_ERROR_INVALID_SHADER_NV -> VkResultCode.ERROR_INVALID_SHADER_NV
        VK_ERROR_OUT_OF_POOL_MEMORY -> VkResultCode.ERROR_OUT_OF_POOL_MEMORY
        VK_ERROR_INVALID_EXTERNAL_HANDLE -> VkResultCode.ERROR_INVALID_EXTERNAL_HANDLE
        VK_ERROR_FRAGMENTATION -> VkResultCode.ERROR_FRAGMENTATION
        VK_ERROR_INVALID_DEVICE_ADDRESS_EXT -> VkResultCode.ERROR_INVALID_DEVICE_ADDRESS_EXT
        VK_ERROR_INVALID_OPAQUE_CAPTURE_ADDRESS -> VkResultCode.ERROR_INVALID_OPAQUE_CAPTURE_ADDRESS
        VK_ERROR_FULL_SCREEN_EXCLUSIVE_MODE_LOST_EXT -> VkResultCode.ERROR_FULL_SCREEN_EXCLUSIVE_MODE_LOST_EXT
        VK_ERROR_UNKNOWN -> VkResultCode.ERROR_UNKNOWN
        else -> throw IllegalStateException("Unknown Vulkan result code")
    }

class VkException(code: Int) : RuntimeException() {
    val code = describeResultCode(code)
    override val message: String = this.code.message
}

fun checkVkResult(code: Int) {
    if (code != VK_SUCCESS) {
        throw VkException(code)
    }
}
