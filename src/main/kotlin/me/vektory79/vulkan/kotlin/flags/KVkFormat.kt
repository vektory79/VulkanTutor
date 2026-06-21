package me.vektory79.vulkan.kotlin.flags

import org.lwjgl.vulkan.VK10.*
import java.rmi.UnexpectedException

/**
 * Форматы пикселей Vulkan, соответствующие `VkFormat`.
 *
 * Определяют организацию компонент и тип данных для изображений, буферов и других
 * графических ресурсов. Включает форматы от простых (R8_UNORM) до сжатых (BC*, ETC*, ASTC*).
 *
 * @property format целочисленное значение константы VK_FORMAT_*.
 */
enum class KVkFormat(val format: Int) {
    /* ===== Multi-component formats ===== */

    /**
     * Неопределённый формат. Используется для инициализации или когда формат не имеет значения.
     */
    UNDEFINED(VK_FORMAT_UNDEFINED),

    /* ===== Packed pixel formats ===== */

    /** Два 4-битных канала RG, UNORM, упакованы в 8 бит. */
    R4G4_UNORM_PACK8(VK_FORMAT_R4G4_UNORM_PACK8),
    /** Четыре 4-битных канала RGBA, UNORM, упакованы в 16 бит. */
    R4G4B4A4_UNORM_PACK16(VK_FORMAT_R4G4B4A4_UNORM_PACK16),
    /** Четыре 4-битных канала BGRA, UNORM, упакованы в 16 бит. */
    B4G4R4A4_UNORM_PACK16(VK_FORMAT_B4G4R4A4_UNORM_PACK16),
    /** 5-6-5 бит RGB, UNORM, упакованы в 16 бит. */
    R5G6B5_UNORM_PACK16(VK_FORMAT_R5G6B5_UNORM_PACK16),
    /** 5-6-5 бит BGR, UNORM, упакованы в 16 бит. */
    B5G6R5_UNORM_PACK16(VK_FORMAT_B5G6R5_UNORM_PACK16),
    /** 5-5-5-1 бит RGBA, UNORM, упакованы в 16 бит. */
    R5G5B5A1_UNORM_PACK16(VK_FORMAT_R5G5B5A1_UNORM_PACK16),
    /** 5-5-5-1 бит BGRA, UNORM, упакованы в 16 бит. */
    B5G5R5A1_UNORM_PACK16(VK_FORMAT_B5G5R5A1_UNORM_PACK16),
    /** 1-5-5-5 бит ARGB, UNORM, упакованы в 16 бит. */
    A1R5G5B5_UNORM_PACK16(VK_FORMAT_A1R5G5B5_UNORM_PACK16),

    /* ===== 8-bit multi-component formats ===== */

    /** Один 8-битный канал R, беззнаковая нормализация [0, 1]. */
    R8_UNORM(VK_FORMAT_R8_UNORM),
    /** Один 8-битный канал R, знаковая нормализация [-1, 1]. */
    R8_SNORM(VK_FORMAT_R8_SNORM),
    /** Один 8-битный канал R, беззнаковое масштабирование. */
    R8_USCALED(VK_FORMAT_R8_USCALED),
    /** Один 8-битный канал R, знаковое масштабирование. */
    R8_SSCALED(VK_FORMAT_R8_SSCALED),
    /** Один 8-битный канал R, беззнаковое целое. */
    R8_UINT(VK_FORMAT_R8_UINT),
    /** Один 8-битный канал R, знаковое целое. */
    R8_SINT(VK_FORMAT_R8_SINT),
    /** Один 8-битный канал R, sRGB цветовое пространство. */
    R8_SRGB(VK_FORMAT_R8_SRGB),

    /** Два 8-битных канала RG, беззнаковая нормализация. */
    R8G8_UNORM(VK_FORMAT_R8G8_UNORM),
    /** Два 8-битных канала RG, знаковая нормализация. */
    R8G8_SNORM(VK_FORMAT_R8G8_SNORM),
    /** Два 8-битных канала RG, беззнаковое масштабирование. */
    R8G8_USCALED(VK_FORMAT_R8G8_USCALED),
    /** Два 8-битных канала RG, знаковое масштабирование. */
    R8G8_SSCALED(VK_FORMAT_R8G8_SSCALED),
    /** Два 8-битных канала RG, беззнаковые целые. */
    R8G8_UINT(VK_FORMAT_R8G8_UINT),
    /** Два 8-битных канала RG, знаковые целые. */
    R8G8_SINT(VK_FORMAT_R8G8_SINT),
    /** Два 8-битных канала RG, sRGB. */
    R8G8_SRGB(VK_FORMAT_R8G8_SRGB),

    /** Три 8-битных канала RGB, беззнаковая нормализация. */
    R8G8B8_UNORM(VK_FORMAT_R8G8B8_UNORM),
    /** Три 8-битных канала RGB, знаковая нормализация. */
    R8G8B8_SNORM(VK_FORMAT_R8G8B8_SNORM),
    /** Три 8-битных канала RGB, беззнаковое масштабирование. */
    R8G8B8_USCALED(VK_FORMAT_R8G8B8_USCALED),
    /** Три 8-битных канала RGB, знаковое масштабирование. */
    R8G8B8_SSCALED(VK_FORMAT_R8G8B8_SSCALED),
    /** Три 8-битных канала RGB, беззнаковые целые. */
    R8G8B8_UINT(VK_FORMAT_R8G8B8_UINT),
    /** Три 8-битных канала RGB, знаковые целые. */
    R8G8B8_SINT(VK_FORMAT_R8G8B8_SINT),
    /** Три 8-битных канала RGB, sRGB. */
    R8G8B8_SRGB(VK_FORMAT_R8G8B8_SRGB),

    /** Три 8-битных канала BGR, беззнаковая нормализация. */
    B8G8R8_UNORM(VK_FORMAT_B8G8R8_UNORM),
    /** Три 8-битных канала BGR, знаковая нормализация. */
    B8G8R8_SNORM(VK_FORMAT_B8G8R8_SNORM),
    /** Три 8-битных канала BGR, беззнаковое масштабирование. */
    B8G8R8_USCALED(VK_FORMAT_B8G8R8_USCALED),
    /** Три 8-битных канала BGR, знаковое масштабирование. */
    B8G8R8_SSCALED(VK_FORMAT_B8G8R8_SSCALED),
    /** Три 8-битных канала BGR, беззнаковые целые. */
    B8G8R8_UINT(VK_FORMAT_B8G8R8_UINT),
    /** Три 8-битных канала BGR, знаковые целые. */
    B8G8R8_SINT(VK_FORMAT_B8G8R8_SINT),
    /** Три 8-битных канала BGR, sRGB. */
    B8G8R8_SRGB(VK_FORMAT_B8G8R8_SRGB),

    /** Четыре 8-битных канала RGBA, беззнаковая нормализация. */
    R8G8B8A8_UNORM(VK_FORMAT_R8G8B8A8_UNORM),
    /** Четыре 8-битных канала RGBA, знаковая нормализация. */
    R8G8B8A8_SNORM(VK_FORMAT_R8G8B8A8_SNORM),
    /** Четыре 8-битных канала RGBA, беззнаковое масштабирование. */
    R8G8B8A8_USCALED(VK_FORMAT_R8G8B8A8_USCALED),
    /** Четыре 8-битных канала RGBA, знаковое масштабирование. */
    R8G8B8A8_SSCALED(VK_FORMAT_R8G8B8A8_SSCALED),
    /** Четыре 8-битных канала RGBA, беззнаковые целые. */
    R8G8B8A8_UINT(VK_FORMAT_R8G8B8A8_UINT),
    /** Четыре 8-битных канала RGBA, знаковые целые. */
    R8G8B8A8_SINT(VK_FORMAT_R8G8B8A8_SINT),
    /** Четыре 8-битных канала RGBA, sRGB. */
    R8G8B8A8_SRGB(VK_FORMAT_R8G8B8A8_SRGB),

    /** Четыре 8-битных канала BGRA, беззнаковая нормализация. */
    B8G8R8A8_UNORM(VK_FORMAT_B8G8R8A8_UNORM),
    /** Четыре 8-битных канала BGRA, знаковая нормализация. */
    B8G8R8A8_SNORM(VK_FORMAT_B8G8R8A8_SNORM),
    /** Четыре 8-битных канала BGRA, беззнаковое масштабирование. */
    B8G8R8A8_USCALED(VK_FORMAT_B8G8R8A8_USCALED),
    /** Четыре 8-битных канала BGRA, знаковое масштабирование. */
    B8G8R8A8_SSCALED(VK_FORMAT_B8G8R8A8_SSCALED),
    /** Четыре 8-битных канала BGRA, беззнаковые целые. */
    B8G8R8A8_UINT(VK_FORMAT_B8G8R8A8_UINT),
    /** Четыре 8-битных канала BGRA, знаковые целые. */
    B8G8R8A8_SINT(VK_FORMAT_B8G8R8A8_SINT),
    /** Четыре 8-битных канала BGRA, sRGB. */
    B8G8R8A8_SRGB(VK_FORMAT_B8G8R8A8_SRGB),

    /** 8-8-8-8 бит BGRA, упакованы в 32 бита (A — старший байт), беззнаковая нормализация. */
    A8B8G8R8_UNORM_PACK32(VK_FORMAT_A8B8G8R8_UNORM_PACK32),
    /** 8-8-8-8 бит BGRA, PACK32, знаковая нормализация. */
    A8B8G8R8_SNORM_PACK32(VK_FORMAT_A8B8G8R8_SNORM_PACK32),
    /** 8-8-8-8 бит BGRA, PACK32, беззнаковое масштабирование. */
    A8B8G8R8_USCALED_PACK32(VK_FORMAT_A8B8G8R8_USCALED_PACK32),
    /** 8-8-8-8 бит BGRA, PACK32, знаковое масштабирование. */
    A8B8G8R8_SSCALED_PACK32(VK_FORMAT_A8B8G8R8_SSCALED_PACK32),
    /** 8-8-8-8 бит BGRA, PACK32, беззнаковые целые. */
    A8B8G8R8_UINT_PACK32(VK_FORMAT_A8B8G8R8_UINT_PACK32),
    /** 8-8-8-8 бит BGRA, PACK32, знаковые целые. */
    A8B8G8R8_SINT_PACK32(VK_FORMAT_A8B8G8R8_SINT_PACK32),
    /** 8-8-8-8 бит BGRA, PACK32, sRGB. */
    A8B8G8R8_SRGB_PACK32(VK_FORMAT_A8B8G8R8_SRGB_PACK32),

    /** 2-10-10-10 бит RGBA, упакованы в 32 бита, беззнаковая нормализация. */
    A2R10G10B10_UNORM_PACK32(VK_FORMAT_A2R10G10B10_UNORM_PACK32),
    /** 2-10-10-10 бит RGBA, PACK32, знаковая нормализация. */
    A2R10G10B10_SNORM_PACK32(VK_FORMAT_A2R10G10B10_SNORM_PACK32),
    /** 2-10-10-10 бит RGBA, PACK32, беззнаковое масштабирование. */
    A2R10G10B10_USCALED_PACK32(VK_FORMAT_A2R10G10B10_USCALED_PACK32),
    /** 2-10-10-10 бит RGBA, PACK32, знаковое масштабирование. */
    A2R10G10B10_SSCALED_PACK32(VK_FORMAT_A2R10G10B10_SSCALED_PACK32),
    /** 2-10-10-10 бит RGBA, PACK32, беззнаковые целые. */
    A2R10G10B10_UINT_PACK32(VK_FORMAT_A2R10G10B10_UINT_PACK32),
    /** 2-10-10-10 бит RGBA, PACK32, знаковые целые. */
    A2R10G10B10_SINT_PACK32(VK_FORMAT_A2R10G10B10_SINT_PACK32),

    /** 2-10-10-10 бит BGRA, PACK32, беззнаковая нормализация. */
    A2B10G10R10_UNORM_PACK32(VK_FORMAT_A2B10G10R10_UNORM_PACK32),
    /** 2-10-10-10 бит BGRA, PACK32, знаковая нормализация. */
    A2B10G10R10_SNORM_PACK32(VK_FORMAT_A2B10G10R10_SNORM_PACK32),
    /** 2-10-10-10 бит BGRA, PACK32, беззнаковое масштабирование. */
    A2B10G10R10_USCALED_PACK32(VK_FORMAT_A2B10G10R10_USCALED_PACK32),
    /** 2-10-10-10 бит BGRA, PACK32, знаковое масштабирование. */
    A2B10G10R10_SSCALED_PACK32(VK_FORMAT_A2B10G10R10_SSCALED_PACK32),
    /** 2-10-10-10 бит BGRA, PACK32, беззнаковые целые. */
    A2B10G10R10_UINT_PACK32(VK_FORMAT_A2B10G10R10_UINT_PACK32),
    /** 2-10-10-10 бит BGRA, PACK32, знаковые целые. */
    A2B10G10R10_SINT_PACK32(VK_FORMAT_A2B10G10R10_SINT_PACK32),

    /* ===== 16-bit multi-component formats ===== */

    /** Один 16-битный канал R, беззнаковая нормализация. */
    R16_UNORM(VK_FORMAT_R16_UNORM),
    /** Один 16-битный канал R, знаковая нормализация. */
    R16_SNORM(VK_FORMAT_R16_SNORM),
    /** Один 16-битный канал R, беззнаковое масштабирование. */
    R16_USCALED(VK_FORMAT_R16_USCALED),
    /** Один 16-битный канал R, знаковое масштабирование. */
    R16_SSCALED(VK_FORMAT_R16_SSCALED),
    /** Один 16-битный канал R, беззнаковое целое. */
    R16_UINT(VK_FORMAT_R16_UINT),
    /** Один 16-битный канал R, знаковое целое. */
    R16_SINT(VK_FORMAT_R16_SINT),
    /** Один 16-битный канал R, половинная точность float (IEEE 754). */
    R16_SFLOAT(VK_FORMAT_R16_SFLOAT),

    /** Два 16-битных канала RG, беззнаковая нормализация. */
    R16G16_UNORM(VK_FORMAT_R16G16_UNORM),
    /** Два 16-битных канала RG, знаковая нормализация. */
    R16G16_SNORM(VK_FORMAT_R16G16_SNORM),
    /** Два 16-битных канала RG, беззнаковое масштабирование. */
    R16G16_USCALED(VK_FORMAT_R16G16_USCALED),
    /** Два 16-битных канала RG, знаковое масштабирование. */
    R16G16_SSCALED(VK_FORMAT_R16G16_SSCALED),
    /** Два 16-битных канала RG, беззнаковые целые. */
    R16G16_UINT(VK_FORMAT_R16G16_UINT),
    /** Два 16-битных канала RG, знаковые целые. */
    R16G16_SINT(VK_FORMAT_R16G16_SINT),
    /** Два 16-битных канала RG, половинная точность float. */
    R16G16_SFLOAT(VK_FORMAT_R16G16_SFLOAT),

    /** Три 16-битных канала RGB, беззнаковая нормализация. */
    R16G16B16_UNORM(VK_FORMAT_R16G16B16_UNORM),
    /** Три 16-битных канала RGB, знаковая нормализация. */
    R16G16B16_SNORM(VK_FORMAT_R16G16B16_SNORM),
    /** Три 16-битных канала RGB, беззнаковое масштабирование. */
    R16G16B16_USCALED(VK_FORMAT_R16G16B16_USCALED),
    /** Три 16-битных канала RGB, знаковое масштабирование. */
    R16G16B16_SSCALED(VK_FORMAT_R16G16B16_SSCALED),
    /** Три 16-битных канала RGB, беззнаковые целые. */
    R16G16B16_UINT(VK_FORMAT_R16G16B16_UINT),
    /** Три 16-битных канала RGB, знаковые целые. */
    R16G16B16_SINT(VK_FORMAT_R16G16B16_SINT),
    /** Три 16-битных канала RGB, половинная точность float. */
    R16G16B16_SFLOAT(VK_FORMAT_R16G16B16_SFLOAT),

    /** Четыре 16-битных канала RGBA, беззнаковая нормализация. */
    R16G16B16A16_UNORM(VK_FORMAT_R16G16B16A16_UNORM),
    /** Четыре 16-битных канала RGBA, знаковая нормализация. */
    R16G16B16A16_SNORM(VK_FORMAT_R16G16B16A16_SNORM),
    /** Четыре 16-битных канала RGBA, беззнаковое масштабирование. */
    R16G16B16A16_USCALED(VK_FORMAT_R16G16B16A16_USCALED),
    /** Четыре 16-битных канала RGBA, знаковое масштабирование. */
    R16G16B16A16_SSCALED(VK_FORMAT_R16G16B16A16_SSCALED),
    /** Четыре 16-битных канала RGBA, беззнаковые целые. */
    R16G16B16A16_UINT(VK_FORMAT_R16G16B16A16_UINT),
    /** Четыре 16-битных канала RGBA, знаковые целые. */
    R16G16B16A16_SINT(VK_FORMAT_R16G16B16A16_SINT),
    /** Четыре 16-битных канала RGBA, половинная точность float. */
    R16G16B16A16_SFLOAT(VK_FORMAT_R16G16B16A16_SFLOAT),

    /* ===== 32-bit multi-component formats ===== */

    /** Один 32-битный канал R, беззнаковое целое. */
    R32_UINT(VK_FORMAT_R32_UINT),
    /** Один 32-битный канал R, знаковое целое. */
    R32_SINT(VK_FORMAT_R32_SINT),
    /** Один 32-битный канал R, float полной точности (IEEE 754). */
    R32_SFLOAT(VK_FORMAT_R32_SFLOAT),

    /** Два 32-битных канала RG, беззнаковые целые. */
    R32G32_UINT(VK_FORMAT_R32G32_UINT),
    /** Два 32-битных канала RG, знаковые целые. */
    R32G32_SINT(VK_FORMAT_R32G32_SINT),
    /** Два 32-битных канала RG, float полной точности. */
    R32G32_SFLOAT(VK_FORMAT_R32G32_SFLOAT),

    /** Три 32-битных канала RGB, беззнаковые целые. */
    R32G32B32_UINT(VK_FORMAT_R32G32B32_UINT),
    /** Три 32-битных канала RGB, знаковые целые. */
    R32G32B32_SINT(VK_FORMAT_R32G32B32_SINT),
    /** Три 32-битных канала RGB, float полной точности. */
    R32G32B32_SFLOAT(VK_FORMAT_R32G32B32_SFLOAT),

    /** Четыре 32-битных канала RGBA, беззнаковые целые. */
    R32G32B32A32_UINT(VK_FORMAT_R32G32B32A32_UINT),
    /** Четыре 32-битных канала RGBA, знаковые целые. */
    R32G32B32A32_SINT(VK_FORMAT_R32G32B32A32_SINT),
    /** Четыре 32-битных канала RGBA, float полной точности. */
    R32G32B32A32_SFLOAT(VK_FORMAT_R32G32B32A32_SFLOAT),

    /* ===== 64-bit multi-component formats ===== */

    /** Один 64-битный канал R, беззнаковое целое. */
    R64_UINT(VK_FORMAT_R64_UINT),
    /** Один 64-битный канал R, знаковое целое. */
    R64_SINT(VK_FORMAT_R64_SINT),
    /** Один 64-битный канал R, double (IEEE 754). */
    R64_SFLOAT(VK_FORMAT_R64_SFLOAT),

    /** Два 64-битных канала RG, беззнаковые целые. */
    R64G64_UINT(VK_FORMAT_R64G64_UINT),
    /** Два 64-битных канала RG, знаковые целые. */
    R64G64_SINT(VK_FORMAT_R64G64_SINT),
    /** Два 64-битных канала RG, double. */
    R64G64_SFLOAT(VK_FORMAT_R64G64_SFLOAT),

    /** Три 64-битных канала RGB, беззнаковые целые. */
    R64G64B64_UINT(VK_FORMAT_R64G64B64_UINT),
    /** Три 64-битных канала RGB, знаковые целые. */
    R64G64B64_SINT(VK_FORMAT_R64G64B64_SINT),
    /** Три 64-битных канала RGB, double. */
    R64G64B64_SFLOAT(VK_FORMAT_R64G64B64_SFLOAT),

    /** Четыре 64-битных канала RGBA, беззнаковые целые. */
    R64G64B64A64_UINT(VK_FORMAT_R64G64B64A64_UINT),
    /** Четыре 64-битных канала RGBA, знаковые целые. */
    R64G64B64A64_SINT(VK_FORMAT_R64G64B64A64_SINT),
    /** Четыре 64-битных канала RGBA, double. */
    R64G64B64A64_SFLOAT(VK_FORMAT_R64G64B64A64_SFLOAT),

    /* ===== Packed float formats ===== */

    /** 10-11-11 бит BGR, беззнаковый float, упакованы в 32 бита. HDR-формат. */
    B10G11R11_UFLOAT_PACK32(VK_FORMAT_B10G11R11_UFLOAT_PACK32),
    /** 9-9-9-5 бит BGRA, беззнаковый float с общим экспонентом, упакованы в 32 бита. */
    E5B9G9R9_UFLOAT_PACK32(VK_FORMAT_E5B9G9R9_UFLOAT_PACK32),

    /* ===== Depth and stencil formats ===== */

    /** 16-битный канал глубины, беззнаковая нормализация. */
    D16_UNORM(VK_FORMAT_D16_UNORM),
    /** 24-битный канал глубины + 8 бит заполнителя, беззнаковая нормализация. */
    X8_D24_UNORM_PACK32(VK_FORMAT_X8_D24_UNORM_PACK32),
    /** 32-битный канал глубины, float полной точности. */
    D32_SFLOAT(VK_FORMAT_D32_SFLOAT),
    /** 8-битный канал шаблонa, беззнаковое целое. */
    S8_UINT(VK_FORMAT_S8_UINT),
    /** 16-битная глубина + 8-битный шаблон. */
    D16_UNORM_S8_UINT(VK_FORMAT_D16_UNORM_S8_UINT),
    /** 24-битная глубина + 8-битный шаблон. */
    D24_UNORM_S8_UINT(VK_FORMAT_D24_UNORM_S8_UINT),
    /** 32-битная глубина (float) + 8-битный шаблон. */
    D32_SFLOAT_S8_UINT(VK_FORMAT_D32_SFLOAT_S8_UINT),

    /* ===== Compressed formats: BC (S3TC / Texture Compression) ===== */

    /** BC1 (DXT1): сжатый RGB или RGBA (1 бит), 64 бита на блок 4×4. */
    BC1_RGB_UNORM_BLOCK(VK_FORMAT_BC1_RGB_UNORM_BLOCK),
    /** BC1 (DXT1): сжатый RGB или RGBA, sRGB. */
    BC1_RGB_SRGB_BLOCK(VK_FORMAT_BC1_RGB_SRGB_BLOCK),
    /** BC1 (DXT1): сжатый RGBA, 64 бита на блок 4×4. */
    BC1_RGBA_UNORM_BLOCK(VK_FORMAT_BC1_RGBA_UNORM_BLOCK),
    /** BC1 (DXT1): сжатый RGBA, sRGB. */
    BC1_RGBA_SRGB_BLOCK(VK_FORMAT_BC1_RGBA_SRGB_BLOCK),

    /** BC2 (DXT3): сжатый RGBA, 128 бит на блок 4×4. */
    BC2_UNORM_BLOCK(VK_FORMAT_BC2_UNORM_BLOCK),
    /** BC2 (DXT3): сжатый RGBA, sRGB. */
    BC2_SRGB_BLOCK(VK_FORMAT_BC2_SRGB_BLOCK),

    /** BC3 (DXT5): сжатый RGBA, 128 бит на блок 4×4. */
    BC3_UNORM_BLOCK(VK_FORMAT_BC3_UNORM_BLOCK),
    /** BC3 (DXT5): сжатый RGBA, sRGB. */
    BC3_SRGB_BLOCK(VK_FORMAT_BC3_SRGB_BLOCK),

    /** BC4 (RGTC1): сжатый R-канал, 64 бита на блок 4×4. */
    BC4_UNORM_BLOCK(VK_FORMAT_BC4_UNORM_BLOCK),
    /** BC4 (RGTC1): сжатый R-канал, знаковая нормализация. */
    BC4_SNORM_BLOCK(VK_FORMAT_BC4_SNORM_BLOCK),

    /** BC5 (RGTC2): сжатый RG-канал, 64 бита на блок 4×4. */
    BC5_UNORM_BLOCK(VK_FORMAT_BC5_UNORM_BLOCK),
    /** BC5 (RGTC2): сжатый RG-канал, знаковая нормализация. */
    BC5_SNORM_BLOCK(VK_FORMAT_BC5_SNORM_BLOCK),

    /** BC6H: сжатый RGB, беззнаковый float, 128 бит на блок 4×4. HDR. */
    BC6H_UFLOAT_BLOCK(VK_FORMAT_BC6H_UFLOAT_BLOCK),
    /** BC6H: сжатый RGB, знаковый float, 128 бит на блок 4×4. HDR. */
    BC6H_SFLOAT_BLOCK(VK_FORMAT_BC6H_SFLOAT_BLOCK),

    /** BC7 (BPTC): сжатый RGBA, 128 бит на блок 4×4. */
    BC7_UNORM_BLOCK(VK_FORMAT_BC7_UNORM_BLOCK),
    /** BC7 (BPTC): сжатый RGBA, sRGB. */
    BC7_SRGB_BLOCK(VK_FORMAT_BC7_SRGB_BLOCK),

    /* ===== Compressed formats: ETC2/EAC (Ericsson) ===== */

    /** ETC2: сжатый RGB, 8 бит на канал, 64 бита на блок 4×4. */
    ETC2_R8G8B8_UNORM_BLOCK(VK_FORMAT_ETC2_R8G8B8_UNORM_BLOCK),
    /** ETC2: сжатый RGB, sRGB. */
    ETC2_R8G8B8_SRGB_BLOCK(VK_FORMAT_ETC2_R8G8B8_SRGB_BLOCK),
    /** ETC2: сжатый RGBA (1 бит альфа), 64 бита на блок 4×4. */
    ETC2_R8G8B8A1_UNORM_BLOCK(VK_FORMAT_ETC2_R8G8B8A1_UNORM_BLOCK),
    /** ETC2: сжатый RGBA (1 бит альфа), sRGB. */
    ETC2_R8G8B8A1_SRGB_BLOCK(VK_FORMAT_ETC2_R8G8B8A1_SRGB_BLOCK),
    /** ETC2: сжатый RGBA, 64 бита на блок 4×4. */
    ETC2_R8G8B8A8_UNORM_BLOCK(VK_FORMAT_ETC2_R8G8B8A8_UNORM_BLOCK),
    /** ETC2: сжатый RGBA, sRGB. */
    ETC2_R8G8B8A8_SRGB_BLOCK(VK_FORMAT_ETC2_R8G8B8A8_SRGB_BLOCK),

    /** EAC: сжатый R-канал, 11 бит точности, 64 бита на блок 4×4. */
    EAC_R11_UNORM_BLOCK(VK_FORMAT_EAC_R11_UNORM_BLOCK),
    /** EAC: сжатый R-канал, знаковая нормализация. */
    EAC_R11_SNORM_BLOCK(VK_FORMAT_EAC_R11_SNORM_BLOCK),
    /** EAC: сжатый RG-канал, 11 бит на канал, 64 бита на блок 4×4. */
    EAC_R11G11_UNORM_BLOCK(VK_FORMAT_EAC_R11G11_UNORM_BLOCK),
    /** EAC: сжатый RG-канал, знаковая нормализация. */
    EAC_R11G11_SNORM_BLOCK(VK_FORMAT_EAC_R11G11_SNORM_BLOCK),

    /* ===== Compressed formats: ASTC (ARM Adaptive Scalable Texture Compression) ===== */

    /** ASTC: сжатый RGBA, блок 4×4. */
    ASTC_4x4_UNORM_BLOCK(VK_FORMAT_ASTC_4x4_UNORM_BLOCK),
    /** ASTC: сжатый RGBA, блок 4×4, sRGB. */
    ASTC_4x4_SRGB_BLOCK(VK_FORMAT_ASTC_4x4_SRGB_BLOCK),

    /** ASTC: сжатый RGBA, блок 5×4. */
    ASTC_5x4_UNORM_BLOCK(VK_FORMAT_ASTC_5x4_UNORM_BLOCK),
    /** ASTC: сжатый RGBA, блок 5×4, sRGB. */
    ASTC_5x4_SRGB_BLOCK(VK_FORMAT_ASTC_5x4_SRGB_BLOCK),

    /** ASTC: сжатый RGBA, блок 5×5. */
    ASTC_5x5_UNORM_BLOCK(VK_FORMAT_ASTC_5x5_UNORM_BLOCK),
    /** ASTC: сжатый RGBA, блок 5×5, sRGB. */
    ASTC_5x5_SRGB_BLOCK(VK_FORMAT_ASTC_5x5_SRGB_BLOCK),

    /** ASTC: сжатый RGBA, блок 6×5. */
    ASTC_6x5_UNORM_BLOCK(VK_FORMAT_ASTC_6x5_UNORM_BLOCK),
    /** ASTC: сжатый RGBA, блок 6×5, sRGB. */
    ASTC_6x5_SRGB_BLOCK(VK_FORMAT_ASTC_6x5_SRGB_BLOCK),

    /** ASTC: сжатый RGBA, блок 6×6. */
    ASTC_6x6_UNORM_BLOCK(VK_FORMAT_ASTC_6x6_UNORM_BLOCK),
    /** ASTC: сжатый RGBA, блок 6×6, sRGB. */
    ASTC_6x6_SRGB_BLOCK(VK_FORMAT_ASTC_6x6_SRGB_BLOCK),

    /** ASTC: сжатый RGBA, блок 8×5. */
    ASTC_8x5_UNORM_BLOCK(VK_FORMAT_ASTC_8x5_UNORM_BLOCK),
    /** ASTC: сжатый RGBA, блок 8×5, sRGB. */
    ASTC_8x5_SRGB_BLOCK(VK_FORMAT_ASTC_8x5_SRGB_BLOCK),

    /** ASTC: сжатый RGBA, блок 8×6. */
    ASTC_8x6_UNORM_BLOCK(VK_FORMAT_ASTC_8x6_UNORM_BLOCK),
    /** ASTC: сжатый RGBA, блок 8×6, sRGB. */
    ASTC_8x6_SRGB_BLOCK(VK_FORMAT_ASTC_8x6_SRGB_BLOCK),

    /** ASTC: сжатый RGBA, блок 8×8. */
    ASTC_8x8_UNORM_BLOCK(VK_FORMAT_ASTC_8x8_UNORM_BLOCK),
    /** ASTC: сжатый RGBA, блок 8×8, sRGB. */
    ASTC_8x8_SRGB_BLOCK(VK_FORMAT_ASTC_8x8_SRGB_BLOCK),

    /** ASTC: сжатый RGBA, блок 10×5. */
    ASTC_10x5_UNORM_BLOCK(VK_FORMAT_ASTC_10x5_UNORM_BLOCK),
    /** ASTC: сжатый RGBA, блок 10×5, sRGB. */
    ASTC_10x5_SRGB_BLOCK(VK_FORMAT_ASTC_10x5_SRGB_BLOCK),

    /** ASTC: сжатый RGBA, блок 10×6. */
    ASTC_10x6_UNORM_BLOCK(VK_FORMAT_ASTC_10x6_UNORM_BLOCK),
    /** ASTC: сжатый RGBA, блок 10×6, sRGB. */
    ASTC_10x6_SRGB_BLOCK(VK_FORMAT_ASTC_10x6_SRGB_BLOCK),

    /** ASTC: сжатый RGBA, блок 10×8. */
    ASTC_10x8_UNORM_BLOCK(VK_FORMAT_ASTC_10x8_UNORM_BLOCK),
    /** ASTC: сжатый RGBA, блок 10×8, sRGB. */
    ASTC_10x8_SRGB_BLOCK(VK_FORMAT_ASTC_10x8_SRGB_BLOCK),

    /** ASTC: сжатый RGBA, блок 10×10. */
    ASTC_10x10_UNORM_BLOCK(VK_FORMAT_ASTC_10x10_UNORM_BLOCK),
    /** ASTC: сжатый RGBA, блок 10×10, sRGB. */
    ASTC_10x10_SRGB_BLOCK(VK_FORMAT_ASTC_10x10_SRGB_BLOCK),

    /** ASTC: сжатый RGBA, блок 12×10. */
    ASTC_12x10_UNORM_BLOCK(VK_FORMAT_ASTC_12x10_UNORM_BLOCK),
    /** ASTC: сжатый RGBA, блок 12×10, sRGB. */
    ASTC_12x10_SRGB_BLOCK(VK_FORMAT_ASTC_12x10_SRGB_BLOCK),

    /** ASTC: сжатый RGBA, блок 12×12. */
    ASTC_12x12_UNORM_BLOCK(VK_FORMAT_ASTC_12x12_UNORM_BLOCK),
    /** ASTC: сжатый RGBA, блок 12×12, sRGB. */
    ASTC_12x12_SRGB_BLOCK(VK_FORMAT_ASTC_12x12_SRGB_BLOCK);

    companion object {
        /**
         * Преобразует целочисленное значение формата в соответствующий элемент перечисления.
         *
         * @param format целочисленное значение (VK_FORMAT_*).
         * @return соответствующий формат.
         * @throws UnexpectedException если значение не соответствует ни одному известному формату.
         */
        fun toEnum(format: Int): KVkFormat {
            return when (format) {
                VK_FORMAT_UNDEFINED -> UNDEFINED
                VK_FORMAT_R4G4_UNORM_PACK8 -> R4G4_UNORM_PACK8
                VK_FORMAT_R4G4B4A4_UNORM_PACK16 -> R4G4B4A4_UNORM_PACK16
                VK_FORMAT_B4G4R4A4_UNORM_PACK16 -> B4G4R4A4_UNORM_PACK16
                VK_FORMAT_R5G6B5_UNORM_PACK16 -> R5G6B5_UNORM_PACK16
                VK_FORMAT_B5G6R5_UNORM_PACK16 -> B5G6R5_UNORM_PACK16
                VK_FORMAT_R5G5B5A1_UNORM_PACK16 -> R5G5B5A1_UNORM_PACK16
                VK_FORMAT_B5G5R5A1_UNORM_PACK16 -> B5G5R5A1_UNORM_PACK16
                VK_FORMAT_A1R5G5B5_UNORM_PACK16 -> A1R5G5B5_UNORM_PACK16
                VK_FORMAT_R8_UNORM -> R8_UNORM
                VK_FORMAT_R8_SNORM -> R8_SNORM
                VK_FORMAT_R8_USCALED -> R8_USCALED
                VK_FORMAT_R8_SSCALED -> R8_SSCALED
                VK_FORMAT_R8_UINT -> R8_UINT
                VK_FORMAT_R8_SINT -> R8_SINT
                VK_FORMAT_R8_SRGB -> R8_SRGB
                VK_FORMAT_R8G8_UNORM -> R8G8_UNORM
                VK_FORMAT_R8G8_SNORM -> R8G8_SNORM
                VK_FORMAT_R8G8_USCALED -> R8G8_USCALED
                VK_FORMAT_R8G8_SSCALED -> R8G8_SSCALED
                VK_FORMAT_R8G8_UINT -> R8G8_UINT
                VK_FORMAT_R8G8_SINT -> R8G8_SINT
                VK_FORMAT_R8G8_SRGB -> R8G8_SRGB
                VK_FORMAT_R8G8B8_UNORM -> R8G8B8_UNORM
                VK_FORMAT_R8G8B8_SNORM -> R8G8B8_SNORM
                VK_FORMAT_R8G8B8_USCALED -> R8G8B8_USCALED
                VK_FORMAT_R8G8B8_SSCALED -> R8G8B8_SSCALED
                VK_FORMAT_R8G8B8_UINT -> R8G8B8_UINT
                VK_FORMAT_R8G8B8_SINT -> R8G8B8_SINT
                VK_FORMAT_R8G8B8_SRGB -> R8G8B8_SRGB
                VK_FORMAT_B8G8R8_UNORM -> B8G8R8_UNORM
                VK_FORMAT_B8G8R8_SNORM -> B8G8R8_SNORM
                VK_FORMAT_B8G8R8_USCALED -> B8G8R8_USCALED
                VK_FORMAT_B8G8R8_SSCALED -> B8G8R8_SSCALED
                VK_FORMAT_B8G8R8_UINT -> B8G8R8_UINT
                VK_FORMAT_B8G8R8_SINT -> B8G8R8_SINT
                VK_FORMAT_B8G8R8_SRGB -> B8G8R8_SRGB
                VK_FORMAT_R8G8B8A8_UNORM -> R8G8B8A8_UNORM
                VK_FORMAT_R8G8B8A8_SNORM -> R8G8B8A8_SNORM
                VK_FORMAT_R8G8B8A8_USCALED -> R8G8B8A8_USCALED
                VK_FORMAT_R8G8B8A8_SSCALED -> R8G8B8A8_SSCALED
                VK_FORMAT_R8G8B8A8_UINT -> R8G8B8A8_UINT
                VK_FORMAT_R8G8B8A8_SINT -> R8G8B8A8_SINT
                VK_FORMAT_R8G8B8A8_SRGB -> R8G8B8A8_SRGB
                VK_FORMAT_B8G8R8A8_UNORM -> B8G8R8A8_UNORM
                VK_FORMAT_B8G8R8A8_SNORM -> B8G8R8A8_SNORM
                VK_FORMAT_B8G8R8A8_USCALED -> B8G8R8A8_USCALED
                VK_FORMAT_B8G8R8A8_SSCALED -> B8G8R8A8_SSCALED
                VK_FORMAT_B8G8R8A8_UINT -> B8G8R8A8_UINT
                VK_FORMAT_B8G8R8A8_SINT -> B8G8R8A8_SINT
                VK_FORMAT_B8G8R8A8_SRGB -> B8G8R8A8_SRGB
                VK_FORMAT_A8B8G8R8_UNORM_PACK32 -> A8B8G8R8_UNORM_PACK32
                VK_FORMAT_A8B8G8R8_SNORM_PACK32 -> A8B8G8R8_SNORM_PACK32
                VK_FORMAT_A8B8G8R8_USCALED_PACK32 -> A8B8G8R8_USCALED_PACK32
                VK_FORMAT_A8B8G8R8_SSCALED_PACK32 -> A8B8G8R8_SSCALED_PACK32
                VK_FORMAT_A8B8G8R8_UINT_PACK32 -> A8B8G8R8_UINT_PACK32
                VK_FORMAT_A8B8G8R8_SINT_PACK32 -> A8B8G8R8_SINT_PACK32
                VK_FORMAT_A8B8G8R8_SRGB_PACK32 -> A8B8G8R8_SRGB_PACK32
                VK_FORMAT_A2R10G10B10_UNORM_PACK32 -> A2R10G10B10_UNORM_PACK32
                VK_FORMAT_A2R10G10B10_SNORM_PACK32 -> A2R10G10B10_SNORM_PACK32
                VK_FORMAT_A2R10G10B10_USCALED_PACK32 -> A2R10G10B10_USCALED_PACK32
                VK_FORMAT_A2R10G10B10_SSCALED_PACK32 -> A2R10G10B10_SSCALED_PACK32
                VK_FORMAT_A2R10G10B10_UINT_PACK32 -> A2R10G10B10_UINT_PACK32
                VK_FORMAT_A2R10G10B10_SINT_PACK32 -> A2R10G10B10_SINT_PACK32
                VK_FORMAT_A2B10G10R10_UNORM_PACK32 -> A2B10G10R10_UNORM_PACK32
                VK_FORMAT_A2B10G10R10_SNORM_PACK32 -> A2B10G10R10_SNORM_PACK32
                VK_FORMAT_A2B10G10R10_USCALED_PACK32 -> A2B10G10R10_USCALED_PACK32
                VK_FORMAT_A2B10G10R10_SSCALED_PACK32 -> A2B10G10R10_SSCALED_PACK32
                VK_FORMAT_A2B10G10R10_UINT_PACK32 -> A2B10G10R10_UINT_PACK32
                VK_FORMAT_A2B10G10R10_SINT_PACK32 -> A2B10G10R10_SINT_PACK32
                VK_FORMAT_R16_UNORM -> R16_UNORM
                VK_FORMAT_R16_SNORM -> R16_SNORM
                VK_FORMAT_R16_USCALED -> R16_USCALED
                VK_FORMAT_R16_SSCALED -> R16_SSCALED
                VK_FORMAT_R16_UINT -> R16_UINT
                VK_FORMAT_R16_SINT -> R16_SINT
                VK_FORMAT_R16_SFLOAT -> R16_SFLOAT
                VK_FORMAT_R16G16_UNORM -> R16G16_UNORM
                VK_FORMAT_R16G16_SNORM -> R16G16_SNORM
                VK_FORMAT_R16G16_USCALED -> R16G16_USCALED
                VK_FORMAT_R16G16_SSCALED -> R16G16_SSCALED
                VK_FORMAT_R16G16_UINT -> R16G16_UINT
                VK_FORMAT_R16G16_SINT -> R16G16_SINT
                VK_FORMAT_R16G16_SFLOAT -> R16G16_SFLOAT
                VK_FORMAT_R16G16B16_UNORM -> R16G16B16_UNORM
                VK_FORMAT_R16G16B16_SNORM -> R16G16B16_SNORM
                VK_FORMAT_R16G16B16_USCALED -> R16G16B16_USCALED
                VK_FORMAT_R16G16B16_SSCALED -> R16G16B16_SSCALED
                VK_FORMAT_R16G16B16_UINT -> R16G16B16_UINT
                VK_FORMAT_R16G16B16_SINT -> R16G16B16_SINT
                VK_FORMAT_R16G16B16_SFLOAT -> R16G16B16_SFLOAT
                VK_FORMAT_R16G16B16A16_UNORM -> R16G16B16A16_UNORM
                VK_FORMAT_R16G16B16A16_SNORM -> R16G16B16A16_SNORM
                VK_FORMAT_R16G16B16A16_USCALED -> R16G16B16A16_USCALED
                VK_FORMAT_R16G16B16A16_SSCALED -> R16G16B16A16_SSCALED
                VK_FORMAT_R16G16B16A16_UINT -> R16G16B16A16_UINT
                VK_FORMAT_R16G16B16A16_SINT -> R16G16B16A16_SINT
                VK_FORMAT_R16G16B16A16_SFLOAT -> R16G16B16A16_SFLOAT
                VK_FORMAT_R32_UINT -> R32_UINT
                VK_FORMAT_R32_SINT -> R32_SINT
                VK_FORMAT_R32_SFLOAT -> R32_SFLOAT
                VK_FORMAT_R32G32_UINT -> R32G32_UINT
                VK_FORMAT_R32G32_SINT -> R32G32_SINT
                VK_FORMAT_R32G32_SFLOAT -> R32G32_SFLOAT
                VK_FORMAT_R32G32B32_UINT -> R32G32B32_UINT
                VK_FORMAT_R32G32B32_SINT -> R32G32B32_SINT
                VK_FORMAT_R32G32B32_SFLOAT -> R32G32B32_SFLOAT
                VK_FORMAT_R32G32B32A32_UINT -> R32G32B32A32_UINT
                VK_FORMAT_R32G32B32A32_SINT -> R32G32B32A32_SINT
                VK_FORMAT_R32G32B32A32_SFLOAT -> R32G32B32A32_SFLOAT
                VK_FORMAT_R64_UINT -> R64_UINT
                VK_FORMAT_R64_SINT -> R64_SINT
                VK_FORMAT_R64_SFLOAT -> R64_SFLOAT
                VK_FORMAT_R64G64_UINT -> R64G64_UINT
                VK_FORMAT_R64G64_SINT -> R64G64_SINT
                VK_FORMAT_R64G64_SFLOAT -> R64G64_SFLOAT
                VK_FORMAT_R64G64B64_UINT -> R64G64B64_UINT
                VK_FORMAT_R64G64B64_SINT -> R64G64B64_SINT
                VK_FORMAT_R64G64B64_SFLOAT -> R64G64B64_SFLOAT
                VK_FORMAT_R64G64B64A64_UINT -> R64G64B64A64_UINT
                VK_FORMAT_R64G64B64A64_SINT -> R64G64B64A64_SINT
                VK_FORMAT_R64G64B64A64_SFLOAT -> R64G64B64A64_SFLOAT
                VK_FORMAT_B10G11R11_UFLOAT_PACK32 -> B10G11R11_UFLOAT_PACK32
                VK_FORMAT_E5B9G9R9_UFLOAT_PACK32 -> E5B9G9R9_UFLOAT_PACK32
                VK_FORMAT_D16_UNORM -> D16_UNORM
                VK_FORMAT_X8_D24_UNORM_PACK32 -> X8_D24_UNORM_PACK32
                VK_FORMAT_D32_SFLOAT -> D32_SFLOAT
                VK_FORMAT_S8_UINT -> S8_UINT
                VK_FORMAT_D16_UNORM_S8_UINT -> D16_UNORM_S8_UINT
                VK_FORMAT_D24_UNORM_S8_UINT -> D24_UNORM_S8_UINT
                VK_FORMAT_D32_SFLOAT_S8_UINT -> D32_SFLOAT_S8_UINT
                VK_FORMAT_BC1_RGB_UNORM_BLOCK -> BC1_RGB_UNORM_BLOCK
                VK_FORMAT_BC1_RGB_SRGB_BLOCK -> BC1_RGB_SRGB_BLOCK
                VK_FORMAT_BC1_RGBA_UNORM_BLOCK -> BC1_RGBA_UNORM_BLOCK
                VK_FORMAT_BC1_RGBA_SRGB_BLOCK -> BC1_RGBA_SRGB_BLOCK
                VK_FORMAT_BC2_UNORM_BLOCK -> BC2_UNORM_BLOCK
                VK_FORMAT_BC2_SRGB_BLOCK -> BC2_SRGB_BLOCK
                VK_FORMAT_BC3_UNORM_BLOCK -> BC3_UNORM_BLOCK
                VK_FORMAT_BC3_SRGB_BLOCK -> BC3_SRGB_BLOCK
                VK_FORMAT_BC4_UNORM_BLOCK -> BC4_UNORM_BLOCK
                VK_FORMAT_BC4_SNORM_BLOCK -> BC4_SNORM_BLOCK
                VK_FORMAT_BC5_UNORM_BLOCK -> BC5_UNORM_BLOCK
                VK_FORMAT_BC5_SNORM_BLOCK -> BC5_SNORM_BLOCK
                VK_FORMAT_BC6H_UFLOAT_BLOCK -> BC6H_UFLOAT_BLOCK
                VK_FORMAT_BC6H_SFLOAT_BLOCK -> BC6H_SFLOAT_BLOCK
                VK_FORMAT_BC7_UNORM_BLOCK -> BC7_UNORM_BLOCK
                VK_FORMAT_BC7_SRGB_BLOCK -> BC7_SRGB_BLOCK
                VK_FORMAT_ETC2_R8G8B8_UNORM_BLOCK -> ETC2_R8G8B8_UNORM_BLOCK
                VK_FORMAT_ETC2_R8G8B8_SRGB_BLOCK -> ETC2_R8G8B8_SRGB_BLOCK
                VK_FORMAT_ETC2_R8G8B8A1_UNORM_BLOCK -> ETC2_R8G8B8A1_UNORM_BLOCK
                VK_FORMAT_ETC2_R8G8B8A1_SRGB_BLOCK -> ETC2_R8G8B8A1_SRGB_BLOCK
                VK_FORMAT_ETC2_R8G8B8A8_UNORM_BLOCK -> ETC2_R8G8B8A8_UNORM_BLOCK
                VK_FORMAT_ETC2_R8G8B8A8_SRGB_BLOCK -> ETC2_R8G8B8A8_SRGB_BLOCK
                VK_FORMAT_EAC_R11_UNORM_BLOCK -> EAC_R11_UNORM_BLOCK
                VK_FORMAT_EAC_R11_SNORM_BLOCK -> EAC_R11_SNORM_BLOCK
                VK_FORMAT_EAC_R11G11_UNORM_BLOCK -> EAC_R11G11_UNORM_BLOCK
                VK_FORMAT_EAC_R11G11_SNORM_BLOCK -> EAC_R11G11_SNORM_BLOCK
                VK_FORMAT_ASTC_4x4_UNORM_BLOCK -> ASTC_4x4_UNORM_BLOCK
                VK_FORMAT_ASTC_4x4_SRGB_BLOCK -> ASTC_4x4_SRGB_BLOCK
                VK_FORMAT_ASTC_5x4_UNORM_BLOCK -> ASTC_5x4_UNORM_BLOCK
                VK_FORMAT_ASTC_5x4_SRGB_BLOCK -> ASTC_5x4_SRGB_BLOCK
                VK_FORMAT_ASTC_5x5_UNORM_BLOCK -> ASTC_5x5_UNORM_BLOCK
                VK_FORMAT_ASTC_5x5_SRGB_BLOCK -> ASTC_5x5_SRGB_BLOCK
                VK_FORMAT_ASTC_6x5_UNORM_BLOCK -> ASTC_6x5_UNORM_BLOCK
                VK_FORMAT_ASTC_6x5_SRGB_BLOCK -> ASTC_6x5_SRGB_BLOCK
                VK_FORMAT_ASTC_6x6_UNORM_BLOCK -> ASTC_6x6_UNORM_BLOCK
                VK_FORMAT_ASTC_6x6_SRGB_BLOCK -> ASTC_6x6_SRGB_BLOCK
                VK_FORMAT_ASTC_8x5_UNORM_BLOCK -> ASTC_8x5_UNORM_BLOCK
                VK_FORMAT_ASTC_8x5_SRGB_BLOCK -> ASTC_8x5_SRGB_BLOCK
                VK_FORMAT_ASTC_8x6_UNORM_BLOCK -> ASTC_8x6_UNORM_BLOCK
                VK_FORMAT_ASTC_8x6_SRGB_BLOCK -> ASTC_8x6_SRGB_BLOCK
                VK_FORMAT_ASTC_8x8_UNORM_BLOCK -> ASTC_8x8_UNORM_BLOCK
                VK_FORMAT_ASTC_8x8_SRGB_BLOCK -> ASTC_8x8_SRGB_BLOCK
                VK_FORMAT_ASTC_10x5_UNORM_BLOCK -> ASTC_10x5_UNORM_BLOCK
                VK_FORMAT_ASTC_10x5_SRGB_BLOCK -> ASTC_10x5_SRGB_BLOCK
                VK_FORMAT_ASTC_10x6_UNORM_BLOCK -> ASTC_10x6_UNORM_BLOCK
                VK_FORMAT_ASTC_10x6_SRGB_BLOCK -> ASTC_10x6_SRGB_BLOCK
                VK_FORMAT_ASTC_10x8_UNORM_BLOCK -> ASTC_10x8_UNORM_BLOCK
                VK_FORMAT_ASTC_10x8_SRGB_BLOCK -> ASTC_10x8_SRGB_BLOCK
                VK_FORMAT_ASTC_10x10_UNORM_BLOCK -> ASTC_10x10_UNORM_BLOCK
                VK_FORMAT_ASTC_10x10_SRGB_BLOCK -> ASTC_10x10_SRGB_BLOCK
                VK_FORMAT_ASTC_12x10_UNORM_BLOCK -> ASTC_12x10_UNORM_BLOCK
                VK_FORMAT_ASTC_12x10_SRGB_BLOCK -> ASTC_12x10_SRGB_BLOCK
                VK_FORMAT_ASTC_12x12_UNORM_BLOCK -> ASTC_12x12_UNORM_BLOCK
                VK_FORMAT_ASTC_12x12_SRGB_BLOCK -> ASTC_12x12_SRGB_BLOCK
                else -> throw UnexpectedException("Unexpected format constant")
            }
        }
    }
}
