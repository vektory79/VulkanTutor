package me.vektory79.vulkan.kotlin

import me.vektory79.vulkan.kotlin.struct.KVkDebugUtilsMessengerCreateInfoEXT
import me.vektory79.vulkan.kotlin.struct.KVkInstanceCreateInfo
import org.lwjgl.PointerBuffer
import org.lwjgl.system.MemoryStack
import org.lwjgl.system.MemoryUtil
import org.lwjgl.vulkan.EXTDebugUtils
import org.lwjgl.vulkan.VK10.VK_ERROR_EXTENSION_NOT_PRESENT
import org.lwjgl.vulkan.VK10.VK_NULL_HANDLE
import org.lwjgl.vulkan.VK10.VK_SUCCESS
import org.lwjgl.vulkan.VK10.vkCreateInstance
import org.lwjgl.vulkan.VK10.vkDestroyInstance
import org.lwjgl.vulkan.VK10.vkEnumerateInstanceLayerProperties
import org.lwjgl.vulkan.VK10.vkEnumeratePhysicalDevices
import org.lwjgl.vulkan.VK10.vkGetInstanceProcAddr
import org.lwjgl.vulkan.VkAllocationCallbacks
import org.lwjgl.vulkan.VkInstance
import org.lwjgl.vulkan.VkInstanceCreateInfo
import org.lwjgl.vulkan.VkLayerProperties
import java.nio.LongBuffer

/**
 * Vulkan-инстанс — первая и обязательная точка входа в API.
 *
 * Инстанс управляет общими для всех устройств ресурсами: расширениями, слоями,
 * отладочными мессенджерами. Через него перечисляются физические устройства
 * ([getPhysicalDevices]) и создаются поверхности.
 *
 * Наследует [org.lwjgl.vulkan.VkInstance], добавляя Kotlin-обёртки над ключевыми
 * методами и проверку результатов вызовов.
 *
 * @property allocator колбэки для аллокации памяти. По умолчанию `null`.
 */
class KVkInstance(handle: Long, ci: VkInstanceCreateInfo, val allocator: VkAllocationCallbacks? = null) :
    VkInstance(handle, ci) {
    private fun checkedExtensionCall(funcName: String, call: () -> Int) {
        checkVkResult(
            if (vkGetInstanceProcAddr(
                    this,
                    funcName
                ) != MemoryUtil.NULL
            ) {
                call()
            } else VK_ERROR_EXTENSION_NOT_PRESENT
        )
    }

    /**
     * Уничтожает Vulkan-инстанс и освобождает все связанные с ним ресурсы.
     *
     * Вызывает ``vkDestroyInstance``. После вызова все устройства, поверхности и другие
     * ресурсы, созданные через этот инстанс, становятся невалидными.
     */
    fun destroy() {
        vkDestroyInstance(this, allocator)
    }

    /**
     * Создаёт мессенджер для отладочных сообщений (расширение EXT_debug_utils).
     *
     * Вызывает ``vkCreateDebugUtilsMessengerEXT``, если расширение доступно.
     *
     * @param createInfo параметры создания мессенджера.
     * @return нативный хэндл мессенджера.
     */
    context(stack: MemoryStack)
    fun createDebugUtilsMessenger(createInfo: KVkDebugUtilsMessengerCreateInfoEXT): Long {
        val pDebugMessenger: LongBuffer = stack.longs(VK_NULL_HANDLE)
        checkedExtensionCall("vkCreateDebugUtilsMessengerEXT") {
            EXTDebugUtils.vkCreateDebugUtilsMessengerEXT(
                this,
                createInfo.struct,
                allocator,
                pDebugMessenger
            )
        }
        return pDebugMessenger[0]
    }

    /**
     * Уничтожает мессенджер отладочных сообщений.
     *
     * Вызывает ``vkDestroyDebugUtilsMessengerEXT``, если расширение доступно.
     *
     * @param debugMessenger нативный хэндл мессенджера, созданный через [createDebugUtilsMessenger].
     */
    fun destroyDebugUtilsMessenger(debugMessenger: Long) {
        checkedExtensionCall("vkDestroyDebugUtilsMessengerEXT") {
            EXTDebugUtils.vkDestroyDebugUtilsMessengerEXT(this, debugMessenger, allocator)
            VK_SUCCESS
        }
    }

    /**
     * Перечисляет доступные физические устройства (GPU), поддерживающие Vulkan.
     *
     * Вызывает ``vkEnumeratePhysicalDevices``. Если GPU не найдено, бросает исключение.
     *
     * @return коллекция физических устройств.
     */
    context(stack: MemoryStack)
    fun getPhysicalDevices(): PhysicalDevices {
        val deviceCount = stack.ints(0)
        checkVkResult(vkEnumeratePhysicalDevices(this, deviceCount, null))
        if (deviceCount[0] == 0) {
            throw RuntimeException("Failed to find GPUs with Vulkan support")
        }
        val ppPhysicalDevices: PointerBuffer = stack.mallocPointer(deviceCount[0])
        vkEnumeratePhysicalDevices(this, deviceCount, ppPhysicalDevices)
        return PhysicalDevices(ppPhysicalDevices)
    }

    companion object {
        /**
         * Создаёт Vulkan-инстанс — первую точку входа в API.
         *
         * Вызывает ``vkCreateInstance`` с параметрами из [init]. Инстанс необходим
         * для создания всех последующих Vulkan-объектов.
         *
         * @param init лямбда, возвращающая [KVkInstanceCreateInfo] с параметрами создания.
         * @return созданный инстанс.
         */
        context(stack: MemoryStack)
        fun vkCreateInstance(
            init: context(MemoryStack) () -> KVkInstanceCreateInfo
        ): KVkInstance {
            val createInfo = init()
            // We need to retrieve the pointer of the created instance
            val instancePtr = stack.mallocPointer(1)
            checkVkResult(vkCreateInstance(createInfo.struct, null, instancePtr))
            return KVkInstance(instancePtr[0], createInfo.struct)
        }
    }
}

/**
 * Перечисляет все слои, доступные для Vulkan-инстанса.
 *
 * Вызывает ``vkEnumerateInstanceLayerProperties``.
 *
 * @return буфер со свойствами доступных слоёв.
 */
context(stack: MemoryStack)
fun vkGetInstanceLayerProperties(): VkLayerProperties.Buffer {
    val layerCount = stack.ints(0)
    vkEnumerateInstanceLayerProperties(layerCount, null)
    val availableLayers: VkLayerProperties.Buffer = VkLayerProperties.malloc(layerCount[0], stack)
    vkEnumerateInstanceLayerProperties(layerCount, availableLayers)
    return availableLayers
}

/**
 * Коллекция физических устройств, полученная через ``vkEnumeratePhysicalDevices``.
 *
 * @property ppPhysicalDevices буфер нативных хэндлов физических устройств.
 */
@JvmInline
value class PhysicalDevices(val ppPhysicalDevices: PointerBuffer) {
    /**
     * Проходит по всем устройствам, создавая [KVkPhysicalDevice] для каждого.
     *
     * @param instance Vulkan-инстанс, к которому привязаны устройства.
     * @param processor обработчик, вызываемый для каждого устройства.
     */
    inline fun iterate(instance: VkInstance, processor: (KVkPhysicalDevice) -> Unit) {
        for (i in 0 until ppPhysicalDevices.capacity()) {
            val device = KVkPhysicalDevice(ppPhysicalDevices[i], instance)
            processor(device)
        }
    }
}
