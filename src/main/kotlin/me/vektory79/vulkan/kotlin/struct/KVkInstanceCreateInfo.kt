package me.vektory79.vulkan.kotlin.struct

import me.vektory79.vulkan.kotlin.KVkStruct
import me.vektory79.vulkan.kotlin.VkStruct
import me.vektory79.vulkan.kotlin.calloc
import org.lwjgl.PointerBuffer
import org.lwjgl.system.MemoryStack
import org.lwjgl.vulkan.VK10
import org.lwjgl.vulkan.VkInstanceCreateInfo

context(MemoryStack)
@VkStruct
fun vkInstanceCreateInfo(init: KVkInstanceCreateInfo.() -> Unit): KVkInstanceCreateInfo =
    calloc(init) {
        KVkInstanceCreateInfo(
            VkInstanceCreateInfo.calloc(this@MemoryStack)
                .apply { sType(VK10.VK_STRUCTURE_TYPE_INSTANCE_CREATE_INFO) })
    }

@VkStruct
@JvmInline
value class KVkInstanceCreateInfo(override val struct: VkInstanceCreateInfo) : KVkStruct<VkInstanceCreateInfo> {
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
