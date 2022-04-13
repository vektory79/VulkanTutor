package me.vektory79.vulkan.kotlin

import org.lwjgl.PointerBuffer
import org.lwjgl.system.MemoryStack
import org.lwjgl.system.Struct
import org.lwjgl.vulkan.VK10
import org.lwjgl.vulkan.VkApplicationInfo
import org.lwjgl.vulkan.VkInstanceCreateInfo
import java.nio.ByteBuffer


// Use calloc to initialize the structs with 0s. Otherwise, the program can crash due to random values
inline fun <T : Struct> calloc(stack: MemoryStack, create: (stack: MemoryStack) -> T, init: T.() -> Unit): T =
    create(stack).apply { init() }

fun createVkApplicationInfo(stack: MemoryStack, init: VkApplicationInfo.() -> Unit): VkApplicationInfo =
    calloc(stack, VkApplicationInfo::calloc, init).
    apply { sType(VK10.VK_STRUCTURE_TYPE_APPLICATION_INFO) }
var VkApplicationInfo.pApplicationName: ByteBuffer?
    get() = pApplicationName()
    set(value) { pApplicationName(value) }
val VkApplicationInfo.applicationName: String?
    get() = pApplicationNameString()
var VkApplicationInfo.applicationVersion: Int
    get() = applicationVersion()
    set(value) { applicationVersion(value) }
var VkApplicationInfo.pEngineName: ByteBuffer?
    get() = pEngineName()
    set(value) { pEngineName(value) }
val VkApplicationInfo.engineName: String?
    get() = pEngineNameString()
var VkApplicationInfo.engineVersion: Int
    get() = engineVersion()
    set(value) { engineVersion(value) }
var VkApplicationInfo.apiVersion: Int
    get() = apiVersion()
    set(value) { apiVersion(value) }


fun createVkInstanceCreateInfo(stack: MemoryStack, init: VkInstanceCreateInfo.() -> Unit): VkInstanceCreateInfo =
    calloc(stack, VkInstanceCreateInfo::calloc, init).
    apply { sType(VK10.VK_STRUCTURE_TYPE_INSTANCE_CREATE_INFO) }
var VkInstanceCreateInfo.pApplicationInfo: VkApplicationInfo?
    get() = pApplicationInfo()
    set(value) { pApplicationInfo(value) }
var VkInstanceCreateInfo.ppEnabledLayerNames: PointerBuffer?
    get() = ppEnabledLayerNames()
    set(value) { ppEnabledLayerNames(value) }
var VkInstanceCreateInfo.ppEnabledExtensionNames: PointerBuffer?
    get() = ppEnabledExtensionNames()
    set(value) { ppEnabledExtensionNames(value) }
