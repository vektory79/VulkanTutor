package me.vektory79.vulkan.kotlin.struct

import me.vektory79.vulkan.kotlin.KVkStruct
import me.vektory79.vulkan.kotlin.VkStruct
import me.vektory79.vulkan.kotlin.calloc
import org.lwjgl.system.MemoryStack
import org.lwjgl.vulkan.VkPhysicalDeviceFeatures

/**
 * Kotlin-обёртка над [VkPhysicalDeviceFeatures].
 *
 * Описывает базовые возможности физического устройства Vulkan, которые приложение
 * может запросить при создании логического устройства. Каждая возможность — это
 * boolean-флаг: `true` означает, что возможность должна быть доступна.
 *
 * Получается через `vkGetPhysicalDeviceFeatures` и передаётся в [org.lwjgl.vulkan.VkDeviceCreateInfo].
 *
 * @property struct LWJGL-структура [VkPhysicalDeviceFeatures].
 */
class KVkPhysicalDeviceFeatures(override val struct: VkPhysicalDeviceFeatures) :
    KVkStruct<VkPhysicalDeviceFeatures> {

    /** Доступ к буферам за пределами заданных границ возвращает нули вместо UB. */
    var robustBufferAccess: Boolean
        get() = struct.robustBufferAccess()
        set(value) {
            struct.robustBufferAccess(value)
        }

    /** Поддержка индексов вершин до UINT32_MAX в командах отрисовки. */
    var fullDrawIndexUint32: Boolean
        get() = struct.fullDrawIndexUint32()
        set(value) {
            struct.fullDrawIndexUint32(value)
        }

    /** Независимые уравнения смешивания для каждого color attachment. */
    var independentBlend: Boolean
        get() = struct.independentBlend()
        set(value) {
            struct.independentBlend(value)
        }

    /** Поддержка геометрических шейдеров. */
    var geometryShader: Boolean
        get() = struct.geometryShader()
        set(value) {
            struct.geometryShader(value)
        }

    /** Поддержка шейдеров тесселляции. */
    var tessellationShader: Boolean
        get() = struct.tessellationShader()
        set(value) {
            struct.tessellationShader(value)
        }

    /** Шейдинг на основе количества сэмплов на пиксель. */
    var sampleRateShading: Boolean
        get() = struct.sampleRateShading()
        set(value) {
            struct.sampleRateShading(value)
        }

    /** Два источника смешивания в фрагментном шейдере. */
    var dualSrcBlend: Boolean
        get() = struct.dualSrcBlend()
        set(value) {
            struct.dualSrcBlend(value)
        }

    /** Логические операции (AND, OR, XOR) в цвете прикрепления. */
    var logicOp: Boolean
        get() = struct.logicOp()
        set(value) {
            struct.logicOp(value)
        }

    /** Многократные вызовы отрисовки из одного буфера команд. */
    var multiDrawIndirect: Boolean
        get() = struct.multiDrawIndirect()
        set(value) {
            struct.multiDrawIndirect(value)
        }

    /** Поддержка firstInstance в параметрах косвенной отрисовки. */
    var drawIndirectFirstInstance: Boolean
        get() = struct.drawIndirectFirstInstance()
        set(value) {
            struct.drawIndirectFirstInstance(value)
        }

    /** Ограничение глубины вне диапазона [0, 1]. */
    var depthClamp: Boolean
        get() = struct.depthClamp()
        set(value) {
            struct.depthClamp(value)
        }

    /** Ограничение смещения глубины. */
    var depthBiasClamp: Boolean
        get() = struct.depthBiasClamp()
        set(value) {
            struct.depthBiasClamp(value)
        }

    /** Режимы заполнения point и line. */
    var fillModeNonSolid: Boolean
        get() = struct.fillModeNonSolid()
        set(value) {
            struct.fillModeNonSolid(value)
        }

    /** Тестирование границ глубины. */
    var depthBounds: Boolean
        get() = struct.depthBounds()
        set(value) {
            struct.depthBounds(value)
        }

    /** Линии шириной больше 1. */
    var wideLines: Boolean
        get() = struct.wideLines()
        set(value) {
            struct.wideLines(value)
        }

    /** Точки диаметром больше 8. */
    var largePoints: Boolean
        get() = struct.largePoints()
        set(value) {
            struct.largePoints(value)
        }

    /** Преобразование прозрачности alpha=0 в alpha=1. */
    var alphaToOne: Boolean
        get() = struct.alphaToOne()
        set(value) {
            struct.alphaToOne(value)
        }

    /** Несколько viewports в пайплайне. */
    var multiViewport: Boolean
        get() = struct.multiViewport()
        set(value) {
            struct.multiViewport(value)
        }

    /** Анизотропная фильтрация сэмплера. */
    var samplerAnisotropy: Boolean
        get() = struct.samplerAnisotropy()
        set(value) {
            struct.samplerAnisotropy(value)
        }

    /** Сжатие текстур ETC2. */
    var textureCompressionETC2: Boolean
        get() = struct.textureCompressionETC2()
        set(value) {
            struct.textureCompressionETC2(value)
        }

    /** Сжатие текстур ASTC LDR. */
    var textureCompressionASTC_LDR: Boolean
        get() = struct.textureCompressionASTC_LDR()
        set(value) {
            struct.textureCompressionASTC_LDR(value)
        }

    /** Сжатие текстур BC (S3TC). */
    var textureCompressionBC: Boolean
        get() = struct.textureCompressionBC()
        set(value) {
            struct.textureCompressionBC(value)
        }

    /** Точные запросы окклюзии. */
    var occlusionQueryPrecise: Boolean
        get() = struct.occlusionQueryPrecise()
        set(value) {
            struct.occlusionQueryPrecise(value)
        }

    /** Запросы статистики пайплайна. */
    var pipelineStatisticsQuery: Boolean
        get() = struct.pipelineStatisticsQuery()
        set(value) {
            struct.pipelineStatisticsQuery(value)
        }

    /** Атомарные операции в вершинном пайплайне. */
    var vertexPipelineStoresAndAtomics: Boolean
        get() = struct.vertexPipelineStoresAndAtomics()
        set(value) {
            struct.vertexPipelineStoresAndAtomics(value)
        }

    /** Атомарные операции во фрагментном пайплайне. */
    var fragmentStoresAndAtomics: Boolean
        get() = struct.fragmentStoresAndAtomics()
        set(value) {
            struct.fragmentStoresAndAtomics(value)
        }

    /**PointSize/gl_PointSize в тесселляционных и геометрических шейдерах. */
    var shaderTessellationAndGeometryPointSize: Boolean
        get() = struct.shaderTessellationAndGeometryPointSize()
        set(value) {
            struct.shaderTessellationAndGeometryPointSize(value)
        }

    /** imageGather* для форматов с числами с плавающей запятой. */
    var shaderImageGatherExtended: Boolean
        get() = struct.shaderImageGatherExtended()
        set(value) {
            struct.shaderImageGatherExtended(value)
        }

    /** Storage image для расширенных форматов. */
    var shaderStorageImageExtendedFormats: Boolean
        get() = struct.shaderStorageImageExtendedFormats()
        set(value) {
            struct.shaderStorageImageExtendedFormats(value)
        }

    /** Мультиканальные storage image. */
    var shaderStorageImageMultisample: Boolean
        get() = struct.shaderStorageImageMultisample()
        set(value) {
            struct.shaderStorageImageMultisample(value)
        }

    /** Чтение storage image без формата. */
    var shaderStorageImageReadWithoutFormat: Boolean
        get() = struct.shaderStorageImageReadWithoutFormat()
        set(value) {
            struct.shaderStorageImageReadWithoutFormat(value)
        }

    /** Запись в storage image без формата. */
    var shaderStorageImageWriteWithoutFormat: Boolean
        get() = struct.shaderStorageImageWriteWithoutFormat()
        set(value) {
            struct.shaderStorageImageWriteWithoutFormat(value)
        }

    /** Динамическая индексация uniform buffer array. */
    var shaderUniformBufferArrayDynamicIndexing: Boolean
        get() = struct.shaderUniformBufferArrayDynamicIndexing()
        set(value) {
            struct.shaderUniformBufferArrayDynamicIndexing(value)
        }

    /** Динамическая индексация sampled image array. */
    var shaderSampledImageArrayDynamicIndexing: Boolean
        get() = struct.shaderSampledImageArrayDynamicIndexing()
        set(value) {
            struct.shaderSampledImageArrayDynamicIndexing(value)
        }

    /** Динамическая индексация storage buffer array. */
    var shaderStorageBufferArrayDynamicIndexing: Boolean
        get() = struct.shaderStorageBufferArrayDynamicIndexing()
        set(value) {
            struct.shaderStorageBufferArrayDynamicIndexing(value)
        }

    /** Динамическая индексация storage image array. */
    var shaderStorageImageArrayDynamicIndexing: Boolean
        get() = struct.shaderStorageImageArrayDynamicIndexing()
        set(value) {
            struct.shaderStorageImageArrayDynamicIndexing(value)
        }

    /** gl_ClipDistance в шейдерах. */
    var shaderClipDistance: Boolean
        get() = struct.shaderClipDistance()
        set(value) {
            struct.shaderClipDistance(value)
        }

    /** gl_CullDistance в шейдерах. */
    var shaderCullDistance: Boolean
        get() = struct.shaderCullDistance()
        set(value) {
            struct.shaderCullDistance(value)
        }

    /** 64-битные числа с плавающей запятой в шейдерах. */
    var shaderFloat64: Boolean
        get() = struct.shaderFloat64()
        set(value) {
            struct.shaderFloat64(value)
        }

    /** 64-битные целые числа в шейдерах. */
    var shaderInt64: Boolean
        get() = struct.shaderInt64()
        set(value) {
            struct.shaderInt64(value)
        }

    /** 16-битные целые числа в шейдерах. */
    var shaderInt16: Boolean
        get() = struct.shaderInt16()
        set(value) {
            struct.shaderInt16(value)
        }

    /** Min LOD в шейдерах для текстур. */
    var shaderResourceMinLod: Boolean
        get() = struct.shaderResourceMinLod()
        set(value) {
            struct.shaderResourceMinLod(value)
        }

    /** Сparse-биндинг для изображений и буферов. */
    var sparseBinding: Boolean
        get() = struct.sparseBinding()
        set(value) {
            struct.sparseBinding(value)
        }

    /** Сparse-резиденция для буферов. */
    var sparseResidencyBuffer: Boolean
        get() = struct.sparseResidencyBuffer()
        set(value) {
            struct.sparseResidencyBuffer(value)
        }

    /** Сparse-резиденция для 2D-изображений. */
    var sparseResidencyImage2D: Boolean
        get() = struct.sparseResidencyImage2D()
        set(value) {
            struct.sparseResidencyImage2D(value)
        }

    /** Сparse-резиденция для 3D-изображений. */
    var sparseResidencyImage3D: Boolean
        get() = struct.sparseResidencyImage3D()
        set(value) {
            struct.sparseResidencyImage3D(value)
        }

    /** Сparse-резиденция для 2 сэмплов. */
    var sparseResidency2Samples: Boolean
        get() = struct.sparseResidency2Samples()
        set(value) {
            struct.sparseResidency2Samples(value)
        }

    /** Сparse-резиденция для 4 сэмплов. */
    var sparseResidency4Samples: Boolean
        get() = struct.sparseResidency4Samples()
        set(value) {
            struct.sparseResidency4Samples(value)
        }

    /** Сparse-резиденция для 8 сэмплов. */
    var sparseResidency8Samples: Boolean
        get() = struct.sparseResidency8Samples()
        set(value) {
            struct.sparseResidency8Samples(value)
        }

    /** Сparse-резиденция для 16 сэмплов. */
    var sparseResidency16Samples: Boolean
        get() = struct.sparseResidency16Samples()
        set(value) {
            struct.sparseResidency16Samples(value)
        }

    /** Алиасирование sparse-резиденции. */
    var sparseResidencyAliased: Boolean
        get() = struct.sparseResidencyAliased()
        set(value) {
            struct.sparseResidencyAliased(value)
        }

    /** Разная скорость мультисэмплинга в разных прикреплениях. */
    var variableMultisampleRate: Boolean
        get() = struct.variableMultisampleRate()
        set(value) {
            struct.variableMultisampleRate(value)
        }

    /** Наследуемые запросы во вторичных буферах команд. */
    var inheritedQueries: Boolean
        get() = struct.inheritedQueries()
        set(value) {
            struct.inheritedQueries(value)
        }

    companion object {
        /**
         * Создаёт и инициализирует [KVkPhysicalDeviceFeatures] на [MemoryStack].
         *
         * @param init лямбда для инициализации полей структуры.
         * @return инициализированная структура.
         */
        context(stack: MemoryStack)
        fun vkPhysicalDeviceFeatures(init: KVkPhysicalDeviceFeatures.() -> Unit = {}): KVkPhysicalDeviceFeatures =
            calloc(init) {
                KVkPhysicalDeviceFeatures(
                    VkPhysicalDeviceFeatures.calloc(stack)
                )
            }
    }
}
