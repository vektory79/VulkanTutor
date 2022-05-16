package me.vektory79.vulkan.kotlin.struct

import me.vektory79.vulkan.kotlin.KVkStruct
import me.vektory79.vulkan.kotlin.VkStruct
import me.vektory79.vulkan.kotlin.calloc
import org.lwjgl.PointerBuffer
import org.lwjgl.system.MemoryStack
import org.lwjgl.vulkan.VK10
import org.lwjgl.vulkan.VkApplicationInfo
import org.lwjgl.vulkan.VkInstanceCreateInfo

context(MemoryStack)
    @VkStruct
fun vkInstanceCreateInfo(init: KVkInstanceCreateInfo.() -> Unit): VkInstanceCreateInfo =
    calloc(init) {
        KVkInstanceCreateInfo(
            VkInstanceCreateInfo.calloc(this@MemoryStack)
                .apply { sType(VK10.VK_STRUCTURE_TYPE_INSTANCE_CREATE_INFO) })
    }

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
