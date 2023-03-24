package me.vektory79.vulkan.kotlin.struct

import me.vektory79.vulkan.kotlin.KVkStruct
import me.vektory79.vulkan.kotlin.VkStruct
import me.vektory79.vulkan.kotlin.calloc
import org.lwjgl.system.MemoryStack
import org.lwjgl.vulkan.EXTDebugUtils
import org.lwjgl.vulkan.VkDebugUtilsMessengerCallbackEXTI
import org.lwjgl.vulkan.VkDebugUtilsMessengerCreateInfoEXT

context(MemoryStack)
    @VkStruct
fun vkDebugUtilsMessengerCreateInfoEXT(init: KVkDebugUtilsMessengerCreateInfoEXT.() -> Unit): KVkDebugUtilsMessengerCreateInfoEXT =
    calloc(init) {
        KVkDebugUtilsMessengerCreateInfoEXT(
            VkDebugUtilsMessengerCreateInfoEXT.calloc(this@MemoryStack)
                .apply { sType(EXTDebugUtils.VK_STRUCTURE_TYPE_DEBUG_UTILS_MESSENGER_CREATE_INFO_EXT) })
    }

@VkStruct
@JvmInline
value class KVkDebugUtilsMessengerCreateInfoEXT(override val struct: VkDebugUtilsMessengerCreateInfoEXT) :
    KVkStruct<VkDebugUtilsMessengerCreateInfoEXT> {
    var pNext: Long
        get() = struct.pNext()
        set(value) {
            struct.pNext(value)
        }

    var flags: Int
        get() = struct.flags()
        set(value) {
            struct.flags(value)
        }

    var messageSeverity: Int
        get() = struct.messageSeverity()
        set(value) {
            struct.messageSeverity(value)
        }

    var messageType: Int
        get() = struct.messageType()
        set(value) {
            struct.messageType(value)
        }

    var pfnUserCallback: VkDebugUtilsMessengerCallbackEXTI
        get() = struct.pfnUserCallback()
        set(value) {
            struct.pfnUserCallback(value)
        }

    var pUserData: Long
        get() = struct.pUserData()
        set(value) {
            struct.pUserData(value)
        }
}
