package me.vektory79.vulkan.kotlin.struct

import me.vektory79.vulkan.kotlin.KVkStruct
import me.vektory79.vulkan.kotlin.VkStruct
import me.vektory79.vulkan.kotlin.calloc
import org.lwjgl.system.MemoryStack
import org.lwjgl.vulkan.EXTDebugUtils
import org.lwjgl.vulkan.VkDebugUtilsMessengerCallbackEXTI
import org.lwjgl.vulkan.VkDebugUtilsMessengerCreateInfoEXT

/**
 * Kotlin-обёртка над [VkDebugUtilsMessengerCreateInfoEXT].
 *
 * Управляет созданием мессенджера отладочных сообщений через расширение VK_EXT_debug_utils.
 * Позволяет настраивать уровень серьёзности, тип сообщений и пользовательский колбэк.
 *
 * @property struct LWJGL-структура [VkDebugUtilsMessengerCreateInfoEXT].
 */
@VkStruct
@JvmInline
value class KVkDebugUtilsMessengerCreateInfoEXT(override val struct: VkDebugUtilsMessengerCreateInfoEXT) :
    KVkStruct<VkDebugUtilsMessengerCreateInfoEXT> {
    /**
     * Указатель на следующую структуру в цепочке.
     */
    var pNext: Long
        get() = struct.pNext()
        set(value) {
            struct.pNext(value)
        }

    /**
     * Флаги создания (зарезервировано для будущего использования).
     */
    var flags: Int
        get() = struct.flags()
        set(value) {
            struct.flags(value)
        }

    /**
     * Битовая маска уровней серьёзности сообщений (VK_DEBUG_UTILS_MESSAGE_SEVERITY_*).
     */
    var messageSeverity: Int
        get() = struct.messageSeverity()
        set(value) {
            struct.messageSeverity(value)
        }

    /**
     * Битовая маска типов сообщений (VK_DEBUG_UTILS_MESSAGE_TYPE_*).
     */
    var messageType: Int
        get() = struct.messageType()
        set(value) {
            struct.messageType(value)
        }

    /**
     * Пользовательский колбэк, вызываемый при получении отладочного сообщения.
     */
    var pfnUserCallback: VkDebugUtilsMessengerCallbackEXTI
        get() = struct.pfnUserCallback()
        set(value) {
            struct.pfnUserCallback(value)
        }

    /**
     * Указатель на пользовательские данные, передаваемые в колбэк.
     */
    var pUserData: Long
        get() = struct.pUserData()
        set(value) {
            struct.pUserData(value)
        }

    companion object {
        /**
         * Создаёт и инициализирует [KVkDebugUtilsMessengerCreateInfoEXT] на [MemoryStack].
         *
         * Устанавливает sType в [VK_STRUCTURE_TYPE_DEBUG_UTILS_MESSENGER_CREATE_INFO_EXT].
         *
         * @param init лямбда для инициализации полей структуры.
         * @return инициализированная структура.
         */
        context(stack: MemoryStack)
        fun vkDebugUtilsMessengerCreateInfoEXT(init: KVkDebugUtilsMessengerCreateInfoEXT.() -> Unit): KVkDebugUtilsMessengerCreateInfoEXT =
            calloc(init) {
                KVkDebugUtilsMessengerCreateInfoEXT(
                    VkDebugUtilsMessengerCreateInfoEXT.calloc(stack)
                        .apply { sType(EXTDebugUtils.VK_STRUCTURE_TYPE_DEBUG_UTILS_MESSENGER_CREATE_INFO_EXT) })
            }
    }
}
