package me.vektory79.vulkan.kotlin.struct

import me.vektory79.vulkan.kotlin.KVkStruct
import me.vektory79.vulkan.kotlin.VkStruct
import me.vektory79.vulkan.kotlin.calloc
import org.lwjgl.PointerBuffer
import org.lwjgl.system.MemoryStack
import org.lwjgl.vulkan.VK10.VK_STRUCTURE_TYPE_DEVICE_CREATE_INFO
import org.lwjgl.vulkan.VkDeviceCreateInfo

context(MemoryStack)
@VkStruct
fun vkDeviceCreateInfo(init: KVkDeviceCreateInfo.() -> Unit): KVkDeviceCreateInfo =
    calloc(init) {
        KVkDeviceCreateInfo(
            VkDeviceCreateInfo.calloc(this@MemoryStack)
                .apply { sType(VK_STRUCTURE_TYPE_DEVICE_CREATE_INFO) })
    }

@VkStruct
@JvmInline
value class KVkDeviceCreateInfo(override val struct: VkDeviceCreateInfo) :
    KVkStruct<VkDeviceCreateInfo> {
    var flags: Int
        get() = struct.flags()
        set(value) {
            struct.flags(value)
        }

    var pQueueCreateInfos: KVkDeviceQueueCreateInfoArray
        get() = KVkDeviceQueueCreateInfoArray(struct.pQueueCreateInfos())
        set(value) {
            struct.pQueueCreateInfos(value.struct)
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
}
