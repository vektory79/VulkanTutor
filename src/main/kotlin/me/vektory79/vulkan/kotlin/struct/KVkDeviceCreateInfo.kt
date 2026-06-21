package me.vektory79.vulkan.kotlin.struct

import me.vektory79.vulkan.kotlin.KVkStruct
import me.vektory79.vulkan.kotlin.VkStruct
import me.vektory79.vulkan.kotlin.calloc
import org.lwjgl.PointerBuffer
import org.lwjgl.system.MemoryStack
import org.lwjgl.vulkan.VK10.VK_STRUCTURE_TYPE_DEVICE_CREATE_INFO
import org.lwjgl.vulkan.VkDeviceCreateInfo
import org.lwjgl.vulkan.VkDeviceQueueCreateInfo

/**
 * Kotlin-обёртка над [VkDeviceCreateInfo].
 *
 * Управляет созданием логического устройства Vulkan через `vkCreateDevice`.
 * Содержит информацию о семействах очередей, слоях, расширениях и включаемых возможностях.
 *
 * @property struct LWJGL-структура [VkDeviceCreateInfo].
 */
@VkStruct
@JvmInline
value class KVkDeviceCreateInfo(override val struct: VkDeviceCreateInfo) :
    KVkStruct<VkDeviceCreateInfo> {
    /**
     * Флаги создания устройства (зарезервировано для будущего использования).
     */
    var flags: Int
        get() = struct.flags()
        set(value) {
            struct.flags(value)
        }

    /**
     * Массив структур [KVkDeviceQueueCreateInfo], описывающих запрашиваемые очереди.
     */
    var pQueueCreateInfos: KVkDeviceQueueCreateInfoArray?
        get() {
            val buf = struct.pQueueCreateInfos()
            return if (buf != null) KVkDeviceQueueCreateInfoArray(buf) else null
        }
        set(value) {
            // Workaround for Kotlin 2.3 + JSpecify nullability conflict:
            // value class .struct is inferred as Buffer? due to boxing, while LWJGL 3.4
            // uses JSpecify @Nullable on setter param. Two nullability domains conflict.
            val buf = value?.struct
            if (buf != null) {
                VkDeviceCreateInfo.npQueueCreateInfos(struct.address(), buf)
            }
        }

    /**
     * Массив указателей на имена включаемых слоёв устройства.
     */
    var ppEnabledLayerNames: PointerBuffer?
        get() = struct.ppEnabledLayerNames()
        set(value) {
            struct.ppEnabledLayerNames(value)
        }

    /**
     * Массив указателей на имена включаемых расширений устройства.
     */
    var ppEnabledExtensionNames: PointerBuffer?
        get() = struct.ppEnabledExtensionNames()
        set(value) {
            struct.ppEnabledExtensionNames(value)
        }

    /**
     * Структура [KVkPhysicalDeviceFeatures] с включаемыми возможностями устройства.
     */
    var pEnabledFeatures: KVkPhysicalDeviceFeatures?
        get() {
            val result = struct.pEnabledFeatures()
            return if (result != null)
                KVkPhysicalDeviceFeatures(result)
            else
                null
        }
        set(value) {
            struct.pEnabledFeatures(value?.struct)
        }

    companion object {
        /**
         * Создаёт и инициализирует [KVkDeviceCreateInfo] на [MemoryStack].
         *
         * Устанавливает sType в [VK_STRUCTURE_TYPE_DEVICE_CREATE_INFO].
         *
         * @param init лямбда для инициализации полей структуры.
         * @return инициализированная структура.
         */
        context(stack: MemoryStack)
        @VkStruct
        fun vkDeviceCreateInfo(init: KVkDeviceCreateInfo.() -> Unit): KVkDeviceCreateInfo =
            calloc(init) {
                KVkDeviceCreateInfo(
                    VkDeviceCreateInfo.calloc(stack)
                        .apply { sType(VK_STRUCTURE_TYPE_DEVICE_CREATE_INFO) })
            }
    }
}
