package me.vektory79.vulkan.kotlin.struct

import me.vektory79.vulkan.kotlin.KVkStruct
import me.vektory79.vulkan.kotlin.VkStruct
import me.vektory79.vulkan.kotlin.calloc
import org.lwjgl.system.MemoryStack
import org.lwjgl.vulkan.VK10
import org.lwjgl.vulkan.VkApplicationInfo
import java.nio.ByteBuffer

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

    companion object {
        context(stack: MemoryStack)
        @VkStruct
        fun vkApplicationInfo(init: KVkApplicationInfo.() -> Unit): KVkApplicationInfo =
            calloc(init) {
                KVkApplicationInfo(
                    VkApplicationInfo.calloc(stack).apply { sType(VK10.VK_STRUCTURE_TYPE_APPLICATION_INFO) })
            }
    }
}
