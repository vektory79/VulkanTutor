package me.vektory79.vulkan.kotlin.struct

import me.vektory79.vulkan.kotlin.KVkStruct
import me.vektory79.vulkan.kotlin.VkStruct
import me.vektory79.vulkan.kotlin.calloc
import org.lwjgl.system.MemoryStack
import org.lwjgl.vulkan.VK10
import org.lwjgl.vulkan.VkApplicationInfo
import java.nio.ByteBuffer

/**
 * Kotlin-обёртка над [VkApplicationInfo].
 *
 * Содержит информацию о приложении и движке, которая передаётся при создании
 * Vulkan-инстанса. Используется драйвером для оптимизации и отладки.
 *
 * ```kotlin
 * val appInfo = stackPush { vkApplicationInfo {
 *     applicationName = "MyApp"
 *     applicationVersion = VK_MAKE_VERSION(1, 0, 0)
 *     engineName = "MyEngine"
 *     engineVersion = VK_MAKE_VERSION(1, 0, 0)
 *     apiVersion = VK_MAKE_VERSION(1, 2, 0)
 * }}
 * ```
 *
 * @property struct LWJGL-структура [VkApplicationInfo].
 */
@VkStruct
@JvmInline
value class KVkApplicationInfo(override val struct: VkApplicationInfo) : KVkStruct<VkApplicationInfo> {
    /**
     * Указатель на имя приложения (ByteBuffer).
     */
    var pApplicationName: ByteBuffer?
        get() = struct.pApplicationName()
        set(value) {
            struct.pApplicationName(value)
        }

    /**
     * Имя приложения как строка. Используется для идентификации приложения драйвером.
     */
    val applicationName: String?
        get() = struct.pApplicationNameString()

    /**
     * Версия приложения в формате VK_MAKE_VERSION(major, minor, patch).
     * Используется драйвером для оптимизации под конкретные версии приложений.
     */
    var applicationVersion: Int
        get() = struct.applicationVersion()
        set(value) {
            struct.applicationVersion(value)
        }

    /**
     * Указатель на имя движка (ByteBuffer).
     */
    var pEngineName: ByteBuffer?
        get() = struct.pEngineName()
        set(value) {
            struct.pEngineName(value)
        }

    /**
     * Имя движка как строка. Может быть null, если приложение не использует движок.
     */
    val engineName: String?
        get() = struct.pEngineNameString()

    /**
     * Версия движка в формате VK_MAKE_VERSION(major, minor, patch).
     */
    var engineVersion: Int
        get() = struct.engineVersion()
        set(value) {
            struct.engineVersion(value)
        }

    /**
     * Версия Vulkan API, которую приложение намерено использовать.
     * Должна быть <= максимальной версии, поддерживаемой драйвером.
     */
    var apiVersion: Int
        get() = struct.apiVersion()
        set(value) {
            struct.apiVersion(value)
        }

    companion object {
        /**
         * Создаёт и инициализирует [KVkApplicationInfo] на [MemoryStack].
         *
         * Устанавливает sType в [VK_STRUCTURE_TYPE_APPLICATION_INFO].
         *
         * @param init лямбда для инициализации полей структуры.
         * @return инициализированная структура.
         */
        context(stack: MemoryStack)
        @VkStruct
        fun vkApplicationInfo(init: KVkApplicationInfo.() -> Unit): KVkApplicationInfo =
            calloc(init) {
                KVkApplicationInfo(
                    VkApplicationInfo.calloc(stack)
                        .apply { sType(VK10.VK_STRUCTURE_TYPE_APPLICATION_INFO) })
            }
    }
}
