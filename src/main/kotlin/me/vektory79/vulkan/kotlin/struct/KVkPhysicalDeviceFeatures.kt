package me.vektory79.vulkan.kotlin.struct

import me.vektory79.vulkan.kotlin.KVkStruct
import me.vektory79.vulkan.kotlin.VkStruct
import me.vektory79.vulkan.kotlin.calloc
import org.lwjgl.system.MemoryStack
import org.lwjgl.vulkan.VkPhysicalDeviceFeatures

context(MemoryStack)
@VkStruct
fun vkPhysicalDeviceFeatures(init: KVkPhysicalDeviceFeatures.() -> Unit = {}): KVkPhysicalDeviceFeatures =
    calloc(init) {
        KVkPhysicalDeviceFeatures(
            VkPhysicalDeviceFeatures.calloc(this@MemoryStack)
        )
    }

class KVkPhysicalDeviceFeatures(override val struct: VkPhysicalDeviceFeatures) :
    KVkStruct<VkPhysicalDeviceFeatures> {

    var robustBufferAccess: Boolean
        get() = struct.robustBufferAccess()
        set(value) {
            struct.robustBufferAccess(value)
        }

    var fullDrawIndexUint32: Boolean
        get() = struct.fullDrawIndexUint32()
        set(value) {
            struct.fullDrawIndexUint32(value)
        }

    var independentBlend: Boolean
        get() = struct.independentBlend()
        set(value) {
            struct.independentBlend(value)
        }

    var geometryShader: Boolean
        get() = struct.geometryShader()
        set(value) {
            struct.geometryShader(value)
        }

    var tessellationShader: Boolean
        get() = struct.tessellationShader()
        set(value) {
            struct.tessellationShader(value)
        }

    var sampleRateShading: Boolean
        get() = struct.sampleRateShading()
        set(value) {
            struct.sampleRateShading(value)
        }

    var dualSrcBlend: Boolean
        get() = struct.dualSrcBlend()
        set(value) {
            struct.dualSrcBlend(value)
        }

    var logicOp: Boolean
        get() = struct.logicOp()
        set(value) {
            struct.logicOp(value)
        }

    var multiDrawIndirect: Boolean
        get() = struct.multiDrawIndirect()
        set(value) {
            struct.multiDrawIndirect(value)
        }

    var drawIndirectFirstInstance: Boolean
        get() = struct.drawIndirectFirstInstance()
        set(value) {
            struct.drawIndirectFirstInstance(value)
        }

    var depthClamp: Boolean
        get() = struct.depthClamp()
        set(value) {
            struct.depthClamp(value)
        }

    var depthBiasClamp: Boolean
        get() = struct.depthBiasClamp()
        set(value) {
            struct.depthBiasClamp(value)
        }

    var fillModeNonSolid: Boolean
        get() = struct.fillModeNonSolid()
        set(value) {
            struct.fillModeNonSolid(value)
        }

    var depthBounds: Boolean
        get() = struct.depthBounds()
        set(value) {
            struct.depthBounds(value)
        }

    var wideLines: Boolean
        get() = struct.wideLines()
        set(value) {
            struct.wideLines(value)
        }

    var largePoints: Boolean
        get() = struct.largePoints()
        set(value) {
            struct.largePoints(value)
        }

    var alphaToOne: Boolean
        get() = struct.alphaToOne()
        set(value) {
            struct.alphaToOne(value)
        }

    var multiViewport: Boolean
        get() = struct.multiViewport()
        set(value) {
            struct.multiViewport(value)
        }

    var samplerAnisotropy: Boolean
        get() = struct.samplerAnisotropy()
        set(value) {
            struct.samplerAnisotropy(value)
        }

    var textureCompressionETC2: Boolean
        get() = struct.textureCompressionETC2()
        set(value) {
            struct.textureCompressionETC2(value)
        }

    var textureCompressionASTC_LDR: Boolean
        get() = struct.textureCompressionASTC_LDR()
        set(value) {
            struct.textureCompressionASTC_LDR(value)
        }

    var textureCompressionBC: Boolean
        get() = struct.textureCompressionBC()
        set(value) {
            struct.textureCompressionBC(value)
        }

    var occlusionQueryPrecise: Boolean
        get() = struct.occlusionQueryPrecise()
        set(value) {
            struct.occlusionQueryPrecise(value)
        }

    var pipelineStatisticsQuery: Boolean
        get() = struct.pipelineStatisticsQuery()
        set(value) {
            struct.pipelineStatisticsQuery(value)
        }

    var vertexPipelineStoresAndAtomics: Boolean
        get() = struct.vertexPipelineStoresAndAtomics()
        set(value) {
            struct.vertexPipelineStoresAndAtomics(value)
        }

    var fragmentStoresAndAtomics: Boolean
        get() = struct.fragmentStoresAndAtomics()
        set(value) {
            struct.fragmentStoresAndAtomics(value)
        }

    var shaderTessellationAndGeometryPointSize: Boolean
        get() = struct.shaderTessellationAndGeometryPointSize()
        set(value) {
            struct.shaderTessellationAndGeometryPointSize(value)
        }

    var shaderImageGatherExtended: Boolean
        get() = struct.shaderImageGatherExtended()
        set(value) {
            struct.shaderImageGatherExtended(value)
        }

    var shaderStorageImageExtendedFormats: Boolean
        get() = struct.shaderStorageImageExtendedFormats()
        set(value) {
            struct.shaderStorageImageExtendedFormats(value)
        }

    var shaderStorageImageMultisample: Boolean
        get() = struct.shaderStorageImageMultisample()
        set(value) {
            struct.shaderStorageImageMultisample(value)
        }

    var shaderStorageImageReadWithoutFormat: Boolean
        get() = struct.shaderStorageImageReadWithoutFormat()
        set(value) {
            struct.shaderStorageImageReadWithoutFormat(value)
        }

    var shaderStorageImageWriteWithoutFormat: Boolean
        get() = struct.shaderStorageImageWriteWithoutFormat()
        set(value) {
            struct.shaderStorageImageWriteWithoutFormat(value)
        }

    var shaderUniformBufferArrayDynamicIndexing: Boolean
        get() = struct.shaderUniformBufferArrayDynamicIndexing()
        set(value) {
            struct.shaderUniformBufferArrayDynamicIndexing(value)
        }

    var shaderSampledImageArrayDynamicIndexing: Boolean
        get() = struct.shaderSampledImageArrayDynamicIndexing()
        set(value) {
            struct.shaderSampledImageArrayDynamicIndexing(value)
        }

    var shaderStorageBufferArrayDynamicIndexing: Boolean
        get() = struct.shaderStorageBufferArrayDynamicIndexing()
        set(value) {
            struct.shaderStorageBufferArrayDynamicIndexing(value)
        }

    var shaderStorageImageArrayDynamicIndexing: Boolean
        get() = struct.shaderStorageImageArrayDynamicIndexing()
        set(value) {
            struct.shaderStorageImageArrayDynamicIndexing(value)
        }

    var shaderClipDistance: Boolean
        get() = struct.shaderClipDistance()
        set(value) {
            struct.shaderClipDistance(value)
        }

    var shaderCullDistance: Boolean
        get() = struct.shaderCullDistance()
        set(value) {
            struct.shaderCullDistance(value)
        }

    var shaderFloat64: Boolean
        get() = struct.shaderFloat64()
        set(value) {
            struct.shaderFloat64(value)
        }

    var shaderInt64: Boolean
        get() = struct.shaderInt64()
        set(value) {
            struct.shaderInt64(value)
        }

    var shaderInt16: Boolean
        get() = struct.shaderInt16()
        set(value) {
            struct.shaderInt16(value)
        }

    var shaderResourceMinLod: Boolean
        get() = struct.shaderResourceMinLod()
        set(value) {
            struct.shaderResourceMinLod(value)
        }

    var sparseBinding: Boolean
        get() = struct.sparseBinding()
        set(value) {
            struct.sparseBinding(value)
        }

    var sparseResidencyBuffer: Boolean
        get() = struct.sparseResidencyBuffer()
        set(value) {
            struct.sparseResidencyBuffer(value)
        }

    var sparseResidencyImage2D: Boolean
        get() = struct.sparseResidencyImage2D()
        set(value) {
            struct.sparseResidencyImage2D(value)
        }

    var sparseResidencyImage3D: Boolean
        get() = struct.sparseResidencyImage3D()
        set(value) {
            struct.sparseResidencyImage3D(value)
        }

    var sparseResidency2Samples: Boolean
        get() = struct.sparseResidency2Samples()
        set(value) {
            struct.sparseResidency2Samples(value)
        }

    var sparseResidency4Samples: Boolean
        get() = struct.sparseResidency4Samples()
        set(value) {
            struct.sparseResidency4Samples(value)
        }

    var sparseResidency8Samples: Boolean
        get() = struct.sparseResidency8Samples()
        set(value) {
            struct.sparseResidency8Samples(value)
        }

    var sparseResidency16Samples: Boolean
        get() = struct.sparseResidency16Samples()
        set(value) {
            struct.sparseResidency16Samples(value)
        }

    var sparseResidencyAliased: Boolean
        get() = struct.sparseResidencyAliased()
        set(value) {
            struct.sparseResidencyAliased(value)
        }

    var variableMultisampleRate: Boolean
        get() = struct.variableMultisampleRate()
        set(value) {
            struct.variableMultisampleRate(value)
        }

    var inheritedQueries: Boolean
        get() = struct.inheritedQueries()
        set(value) {
            struct.inheritedQueries(value)
        }
}
