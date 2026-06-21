package me.vektory79.vulkan.kotlin.struct

import me.vektory79.vulkan.kotlin.KVkStruct
import me.vektory79.vulkan.kotlin.VkStruct
import me.vektory79.vulkan.kotlin.calloc
import org.lwjgl.PointerBuffer
import org.lwjgl.system.MemoryStack
import org.lwjgl.vulkan.VK10
import org.lwjgl.vulkan.VkInstanceCreateInfo

/**
 * Kotlin-обёртка над [VkInstanceCreateInfo].
 *
 * Управляет созданием Vulkan-инстанса через `vkCreateInstance`. Содержит информацию
 * о приложении, включаемых слоях, расширениях и флагах создания.
 *
 * @property struct LWJGL-структура [VkInstanceCreateInfo].
 */
@VkStruct
@JvmInline
value class KVkInstanceCreateInfo(override val struct: VkInstanceCreateInfo) : KVkStruct<VkInstanceCreateInfo> {
    /**
     * Информация о приложении и движке. Может быть null.
     */
    var pApplicationInfo: KVkApplicationInfo?
        get() {
            val result = struct.pApplicationInfo()
            return if (result != null)
                KVkApplicationInfo(result)
            else
                null
        }
        set(value) {
            struct.pApplicationInfo(value?.struct)
        }

    /**
     * Массив указателей на имена включаемых слоёв VK.
     */
    var ppEnabledLayerNames: PointerBuffer?
        get() = struct.ppEnabledLayerNames()
        set(value) {
            struct.ppEnabledLayerNames(value)
        }

    /**
     * Массив указателей на имена включаемых расширений VK.
     */
    var ppEnabledExtensionNames: PointerBuffer?
        get() = struct.ppEnabledExtensionNames()
        set(value) {
            struct.ppEnabledExtensionNames(value)
        }

    companion object {
        /**
         * Создаёт и инициализирует [KVkInstanceCreateInfo] на [MemoryStack].
         *
         * Устанавливает sType в [VK_STRUCTURE_TYPE_INSTANCE_CREATE_INFO].
         *
         * @param init лямбда для инициализации полей структуры.
         * @return инициализированная структура.
         */
        context(stack: MemoryStack)
        fun vkInstanceCreateInfo(init: KVkInstanceCreateInfo.() -> Unit): KVkInstanceCreateInfo =
            calloc(init) {
                KVkInstanceCreateInfo(
                    VkInstanceCreateInfo.calloc(stack)
                        .apply { sType(VK10.VK_STRUCTURE_TYPE_INSTANCE_CREATE_INFO) })
            }
    }
}
