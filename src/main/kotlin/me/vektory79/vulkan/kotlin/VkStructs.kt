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
    inline fun <S : Struct, K : KVkStruct<S>> calloc(init: K.() -> Unit, create: context(MemoryStack) () -> K): S =
    create(this@MemoryStack).apply {
        init()
    }.struct
