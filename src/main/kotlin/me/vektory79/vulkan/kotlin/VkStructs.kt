package me.vektory79.vulkan.kotlin

import org.lwjgl.PointerBuffer
import org.lwjgl.system.MemoryStack
import org.lwjgl.system.Struct
import org.lwjgl.vulkan.EXTDebugUtils.VK_STRUCTURE_TYPE_DEBUG_UTILS_MESSENGER_CREATE_INFO_EXT
import org.lwjgl.vulkan.VK10
import org.lwjgl.vulkan.VkApplicationInfo
import org.lwjgl.vulkan.VkDebugUtilsMessengerCallbackEXTI
import org.lwjgl.vulkan.VkDebugUtilsMessengerCreateInfoEXT
import org.lwjgl.vulkan.VkInstanceCreateInfo
import java.nio.ByteBuffer

@DslMarker
annotation class VkStruct

interface KVkStruct<T : Struct> {
    val struct: T
}

// Use calloc to initialize the structs with 0s. Otherwise, the program can crash due to random values
context(MemoryStack)
    inline fun <S : Struct, K : KVkStruct<S>> calloc(create: context(MemoryStack) () -> K, init: K.() -> Unit): S =
    create(this@MemoryStack).apply {
        init()
    }.struct


context(MemoryStack)
    @VkStruct
fun vkApplicationInfo(init: KVkApplicationInfo.() -> Unit): VkApplicationInfo =
    calloc(
        {
            KVkApplicationInfo(
                VkApplicationInfo.calloc(this@MemoryStack).apply { sType(VK10.VK_STRUCTURE_TYPE_APPLICATION_INFO) })
        },
        init
    )

@VkStruct
@JvmInline
value class KVkApplicationInfo(override val struct: VkApplicationInfo) : KVkStruct<VkApplicationInfo> {
    var pApplicationName: ByteBuffer?
        get() = struct.pApplicationName()
        set(value) {
            struct.pApplicationName(value)
        }
    val applicationName: String?
        get() = struct.pApplicationNameString()

    var applicationVersion: Int
        get() = struct.applicationVersion()
        set(value) {
            struct.applicationVersion(value)
        }

    var pEngineName: ByteBuffer?
        get() = struct.pEngineName()
        set(value) {
            struct.pEngineName(value)
        }
    val engineName: String?
        get() = struct.pEngineNameString()

    var engineVersion: Int
        get() = struct.engineVersion()
        set(value) {
            struct.engineVersion(value)
        }

    var apiVersion: Int
        get() = struct.apiVersion()
        set(value) {
            struct.apiVersion(value)
        }
}

context(MemoryStack)
    @VkStruct
fun vkInstanceCreateInfo(init: KVkInstanceCreateInfo.() -> Unit): VkInstanceCreateInfo =
    calloc(
        {
            KVkInstanceCreateInfo(
                VkInstanceCreateInfo.calloc(this@MemoryStack)
                    .apply { sType(VK10.VK_STRUCTURE_TYPE_INSTANCE_CREATE_INFO) })
        },
        init
    )

@VkStruct
@JvmInline
value class KVkInstanceCreateInfo(override val struct: VkInstanceCreateInfo) : KVkStruct<VkInstanceCreateInfo> {
    var pApplicationInfo: VkApplicationInfo?
        get() = struct.pApplicationInfo()
        set(value) {
            struct.pApplicationInfo(value)
        }

    var ppEnabledLayerNames: PointerBuffer?
        get() = struct.ppEnabledLayerNames()
        set(value) {
            struct.ppEnabledLayerNames(value)
        }

    var ppEnabledExtensionNames: PointerBuffer?
        get() = struct.ppEnabledExtensionNames()
        set(value) {
            struct.ppEnabledExtensionNames(value)
        }
}

context(MemoryStack)
    @VkStruct
fun vkDebugUtilsMessengerCreateInfoEXT(init: KVkDebugUtilsMessengerCreateInfoEXT.() -> Unit): VkDebugUtilsMessengerCreateInfoEXT =
    calloc(
        {
            KVkDebugUtilsMessengerCreateInfoEXT(
                VkDebugUtilsMessengerCreateInfoEXT.calloc(this@MemoryStack)
                    .apply { sType(VK_STRUCTURE_TYPE_DEBUG_UTILS_MESSENGER_CREATE_INFO_EXT) })
        },
        init
    )

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
