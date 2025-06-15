package me.vektory79.vulkan.kotlin.struct

import me.vektory79.vulkan.kotlin.KVkStruct
import me.vektory79.vulkan.kotlin.KVkStructArray
import me.vektory79.vulkan.kotlin.KVkStructArrayInitCollector
import me.vektory79.vulkan.kotlin.VkStruct
import me.vektory79.vulkan.kotlin.calloc
import me.vektory79.vulkan.kotlin.callocArray
import org.lwjgl.system.MemoryStack
import org.lwjgl.vulkan.VK10.VK_STRUCTURE_TYPE_DEVICE_QUEUE_CREATE_INFO
import org.lwjgl.vulkan.VkDeviceQueueCreateInfo
import org.lwjgl.vulkan.VkDeviceQueueGlobalPriorityCreateInfoEXT
import org.lwjgl.vulkan.VkDeviceQueueGlobalPriorityCreateInfoKHR
import java.nio.FloatBuffer

@VkStruct
@JvmInline
value class KVkDeviceQueueCreateInfo(override val struct: VkDeviceQueueCreateInfo) :
    KVkStruct<VkDeviceQueueCreateInfo> {
    var pNext: Long
        get() = struct.pNext()
        set(value) {
            struct.pNext(value)
        }
    fun pNext(value: VkDeviceQueueGlobalPriorityCreateInfoEXT) = struct.pNext(value)

    fun pNext(value: VkDeviceQueueGlobalPriorityCreateInfoKHR) = struct.pNext(value)
    var flags: Int
        get() = struct.flags()
        set(value) {
            struct.flags(value)
        }

    var queueFamilyIndex: Int
        get() = struct.queueFamilyIndex()
        set(value) {
            struct.queueFamilyIndex(value)
        }

    val queueCount: Int
        get() = struct.queueCount()

    var pQueuePriorities: FloatBuffer
        get() = struct.pQueuePriorities()
        set(value) {
            struct.pQueuePriorities(value)
        }

    companion object {
        context(stack: MemoryStack)
        @VkStruct
        fun vkDeviceQueueCreateInfo(init: KVkDeviceQueueCreateInfo.() -> Unit): KVkDeviceQueueCreateInfo =
            calloc(init) {
                KVkDeviceQueueCreateInfo(
                    VkDeviceQueueCreateInfo.calloc(stack)
                        .apply { sType(VK_STRUCTURE_TYPE_DEVICE_QUEUE_CREATE_INFO) })
            }
    }
}

class KVkDeviceQueueCreateInfoInitCollector : KVkStructArrayInitCollector<
    VkDeviceQueueCreateInfo,
    VkDeviceQueueCreateInfo.Buffer,
    KVkDeviceQueueCreateInfo,
    KVkDeviceQueueCreateInfoArray
    >()

@VkStruct
@JvmInline
value class KVkDeviceQueueCreateInfoArray(override val struct: VkDeviceQueueCreateInfo.Buffer) :
    KVkStructArray<VkDeviceQueueCreateInfo, VkDeviceQueueCreateInfo.Buffer, KVkDeviceQueueCreateInfo> {

    override var sType: Int
        get() = struct.sType()
        set(value) {
            struct.sType(value)
        }

    override fun get(i: Int): KVkDeviceQueueCreateInfo = KVkDeviceQueueCreateInfo(struct.get(i))

    override fun wrap(struct: VkDeviceQueueCreateInfo): KVkDeviceQueueCreateInfo = KVkDeviceQueueCreateInfo(struct)

    var pNext: Long
        get() = struct.pNext()
        set(value) {
            struct.pNext(value)
        }

    fun pNext(value: VkDeviceQueueGlobalPriorityCreateInfoEXT) = struct.pNext(value)
    fun pNext(value: VkDeviceQueueGlobalPriorityCreateInfoKHR) = struct.pNext(value)

    var flags: Int
        get() = struct.flags()
        set(value) {
            struct.flags(value)
        }

    var queueFamilyIndex: Int
        get() = struct.queueFamilyIndex()
        set(value) {
            struct.queueFamilyIndex(value)
        }

    val queueCount: Int
        get() = struct.queueCount()

    var pQueuePriorities: FloatBuffer
        get() = struct.pQueuePriorities()
        set(value) {
            struct.pQueuePriorities(value)
        }

    companion object {
        context(stack: MemoryStack)
        @VkStruct
        fun vkDeviceQueueCreateInfoArray(
            init: KVkDeviceQueueCreateInfoInitCollector.() -> Unit
        ): KVkDeviceQueueCreateInfoArray {
            val collector = KVkDeviceQueueCreateInfoInitCollector()
            collector.init()
            return callocArray(
                VK_STRUCTURE_TYPE_DEVICE_QUEUE_CREATE_INFO,
                collector
            ) {
                KVkDeviceQueueCreateInfoArray(
                    VkDeviceQueueCreateInfo
                        .calloc(collector.size, stack)
                )
            }
        }
    }
}
