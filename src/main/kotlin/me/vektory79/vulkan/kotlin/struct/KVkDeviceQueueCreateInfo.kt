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

/**
 * Kotlin-обёртка над [VkDeviceQueueCreateInfo].
 *
 * Описывает очередь, которую приложение запрашивает при создании логического устройства.
 * Содержит индекс семейства очередей, количество очередей и их приоритеты.
 *
 * @property struct LWJGL-структура [VkDeviceQueueCreateInfo].
 */
@VkStruct
@JvmInline
value class KVkDeviceQueueCreateInfo(override val struct: VkDeviceQueueCreateInfo) :
    KVkStruct<VkDeviceQueueCreateInfo> {
    /**
     * Указатель на следующую структуру в цепочке.
     */
    var pNext: Long
        get() = struct.pNext()
        set(value) {
            struct.pNext(value)
        }

    /**
     * Устанавливает pNext на [VkDeviceQueueGlobalPriorityCreateInfoEXT].
     */
    fun pNext(value: VkDeviceQueueGlobalPriorityCreateInfoEXT) = struct.pNext(value)

    /**
     * Устанавливает pNext на [VkDeviceQueueGlobalPriorityCreateInfoKHR].
     */
    fun pNext(value: VkDeviceQueueGlobalPriorityCreateInfoKHR) = struct.pNext(value)

    /**
     * Флаги создания очереди (зарезервировано для будущего использования).
     */
    var flags: Int
        get() = struct.flags()
        set(value) {
            struct.flags(value)
        }

    /**
     * Индекс семейства очередей GPU, из которого создаются очереди.
     */
    var queueFamilyIndex: Int
        get() = struct.queueFamilyIndex()
        set(value) {
            struct.queueFamilyIndex(value)
        }

    /**
     * Количество очередей в этом семействе.
     */
    val queueCount: Int
        get() = struct.queueCount()

    /**
     * Массив приоритетов для каждой очереди (0.0 — низший, 1.0 — высший).
     */
    var pQueuePriorities: FloatBuffer
        get() = struct.pQueuePriorities()
        set(value) {
            struct.pQueuePriorities(value)
        }

    companion object {
        /**
         * Создаёт и инициализирует [KVkDeviceQueueCreateInfo] на [MemoryStack].
         *
         * Устанавливает sType в [VK_STRUCTURE_TYPE_DEVICE_QUEUE_CREATE_INFO].
         *
         * @param init лямбда для инициализации полей структуры.
         * @return инициализированная структура.
         */
        context(stack: MemoryStack)
        fun vkDeviceQueueCreateInfo(init: KVkDeviceQueueCreateInfo.() -> Unit): KVkDeviceQueueCreateInfo =
            calloc(init) {
                KVkDeviceQueueCreateInfo(
                    VkDeviceQueueCreateInfo.calloc(stack)
                        .apply { sType(VK_STRUCTURE_TYPE_DEVICE_QUEUE_CREATE_INFO) })
            }
    }
}

/**
 * Коллектор инициализаторов для массива [KVkDeviceQueueCreateInfo].
 *
 * Используется с [KVkDeviceQueueCreateInfoArray.vkDeviceQueueCreateInfoArray]
 * для поэлементной инициализации массива структур создания очередей.
 */
class KVkDeviceQueueCreateInfoInitCollector : KVkStructArrayInitCollector<
    VkDeviceQueueCreateInfo,
    VkDeviceQueueCreateInfo.Buffer,
    KVkDeviceQueueCreateInfo,
    KVkDeviceQueueCreateInfoArray
    >()

/**
 * Массив структур [KVkDeviceQueueCreateInfo] для создания нескольких семейств очередей.
 *
 * Оборачивает [VkDeviceQueueCreateInfo.Buffer] и предоставляет доступ к элементам
 * через Kotlin-обёртки.
 *
 * @property struct LWJGL-буфер [VkDeviceQueueCreateInfo.Buffer].
 */
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

    /**
     * Указатель на следующую структуру в цепочке (для текущего элемента по позиции).
     */
    var pNext: Long
        get() = struct.pNext()
        set(value) {
            struct.pNext(value)
        }

    /**
     * Устанавливает pNext на [VkDeviceQueueGlobalPriorityCreateInfoEXT].
     */
    fun pNext(value: VkDeviceQueueGlobalPriorityCreateInfoEXT) = struct.pNext(value)

    /**
     * Устанавливает pNext на [VkDeviceQueueGlobalPriorityCreateInfoKHR].
     */
    fun pNext(value: VkDeviceQueueGlobalPriorityCreateInfoKHR) = struct.pNext(value)

    /**
     * Флаги создания очереди.
     */
    var flags: Int
        get() = struct.flags()
        set(value) {
            struct.flags(value)
        }

    /**
     * Индекс семейства очередей.
     */
    var queueFamilyIndex: Int
        get() = struct.queueFamilyIndex()
        set(value) {
            struct.queueFamilyIndex(value)
        }

    /**
     * Количество очередей в семействе.
     */
    val queueCount: Int
        get() = struct.queueCount()

    /**
     * Массив приоритетов очередей.
     */
    var pQueuePriorities: FloatBuffer
        get() = struct.pQueuePriorities()
        set(value) {
            struct.pQueuePriorities(value)
        }

    companion object {
        /**
         * Создаёт массив [KVkDeviceQueueCreateInfo] на [MemoryStack].
         *
         * Каждый элемент инициализируется через [init] коллектор.
         *
         * @param init лямбда для добавления элементов в коллектор.
         * @return заполненный массив структур.
         */
        context(stack: MemoryStack)
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
