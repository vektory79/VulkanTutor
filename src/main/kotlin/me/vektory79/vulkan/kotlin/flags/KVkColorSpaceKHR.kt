package me.vektory79.vulkan.kotlin.flags

import org.lwjgl.vulkan.EXTSwapchainColorspace.VK_COLOR_SPACE_ADOBERGB_LINEAR_EXT
import org.lwjgl.vulkan.EXTSwapchainColorspace.VK_COLOR_SPACE_ADOBERGB_NONLINEAR_EXT
import org.lwjgl.vulkan.EXTSwapchainColorspace.VK_COLOR_SPACE_BT2020_LINEAR_EXT
import org.lwjgl.vulkan.EXTSwapchainColorspace.VK_COLOR_SPACE_BT709_LINEAR_EXT
import org.lwjgl.vulkan.EXTSwapchainColorspace.VK_COLOR_SPACE_BT709_NONLINEAR_EXT
import org.lwjgl.vulkan.EXTSwapchainColorspace.VK_COLOR_SPACE_DCI_P3_LINEAR_EXT
import org.lwjgl.vulkan.EXTSwapchainColorspace.VK_COLOR_SPACE_DCI_P3_NONLINEAR_EXT
import org.lwjgl.vulkan.EXTSwapchainColorspace.VK_COLOR_SPACE_DISPLAY_P3_LINEAR_EXT
import org.lwjgl.vulkan.EXTSwapchainColorspace.VK_COLOR_SPACE_DISPLAY_P3_NONLINEAR_EXT
import org.lwjgl.vulkan.EXTSwapchainColorspace.VK_COLOR_SPACE_DOLBYVISION_EXT
import org.lwjgl.vulkan.EXTSwapchainColorspace.VK_COLOR_SPACE_EXTENDED_SRGB_LINEAR_EXT
import org.lwjgl.vulkan.EXTSwapchainColorspace.VK_COLOR_SPACE_EXTENDED_SRGB_NONLINEAR_EXT
import org.lwjgl.vulkan.EXTSwapchainColorspace.VK_COLOR_SPACE_HDR10_HLG_EXT
import org.lwjgl.vulkan.EXTSwapchainColorspace.VK_COLOR_SPACE_HDR10_ST2084_EXT
import org.lwjgl.vulkan.EXTSwapchainColorspace.VK_COLOR_SPACE_PASS_THROUGH_EXT
import org.lwjgl.vulkan.KHRSurface.VK_COLOR_SPACE_SRGB_NONLINEAR_KHR
import java.rmi.UnexpectedException

/**
 * Цветовые пространства Vulkan, соответствующие `VkColorSpaceKHR`.
 *
 * Определяют цветовое пространство изображений swap chain. Включает стандартные
 * sRGB, расширенные пространства (Display P3, DCI-P3, BT.709, BT.2020) и HDR-форматы.
 *
 * @property value целочисленное значение константы VK_COLOR_SPACE_*.
 */
enum class KVkColorSpaceKHR(val value: Int) {
    /** Стандартное sRGB цветовое пространство с нелинейной гаммой. */
    SRGB_NONLINEAR(VK_COLOR_SPACE_SRGB_NONLINEAR_KHR),
    /** Apple Display P3 с нелинейной гаммой. */
    DISPLAY_P3_NONLINEAR(VK_COLOR_SPACE_DISPLAY_P3_NONLINEAR_EXT),
    /** Расширенный sRGB с линейной гаммой. */
    EXTENDED_SRGB_LINEAR(VK_COLOR_SPACE_EXTENDED_SRGB_LINEAR_EXT),
    /** Apple Display P3 с линейной гаммой. */
    DISPLAY_P3_LINEAR(VK_COLOR_SPACE_DISPLAY_P3_LINEAR_EXT),
    /** DCI-P3 с нелинейной гаммой. */
    DCI_P3_NONLINEAR(VK_COLOR_SPACE_DCI_P3_NONLINEAR_EXT),
    /** BT.709 с линейной гаммой. */
    BT709_LINEAR(VK_COLOR_SPACE_BT709_LINEAR_EXT),
    /** BT.709 с нелинейной гаммой. */
    BT709_NONLINEAR(VK_COLOR_SPACE_BT709_NONLINEAR_EXT),
    /** BT.2020 с линейной гаммой. */
    BT2020_LINEAR(VK_COLOR_SPACE_BT2020_LINEAR_EXT),
    /** HDR10 со шкалой ST.2084 (PQ). */
    HDR10_ST2084(VK_COLOR_SPACE_HDR10_ST2084_EXT),
    /** Dolby Vision. */
    DOLBYVISION(VK_COLOR_SPACE_DOLBYVISION_EXT),
    /** HDR со шкалой HLG (Hybrid Log-Gamma). */
    HDR10_HLG(VK_COLOR_SPACE_HDR10_HLG_EXT),
    /** Adobe RGB с линейной гаммой. */
    ADOBERGB_LINEAR(VK_COLOR_SPACE_ADOBERGB_LINEAR_EXT),
    /** Adobe RGB с нелинейной гаммой. */
    ADOBERGB_NONLINEAR(VK_COLOR_SPACE_ADOBERGB_NONLINEAR_EXT),
    /** Прямая передача без преобразования цветового пространства. */
    PASS_THROUGH(VK_COLOR_SPACE_PASS_THROUGH_EXT),
    /** Расширенный sRGB с нелинейной гаммой. */
    EXTENDED_SRGB_NONLINEAR(VK_COLOR_SPACE_EXTENDED_SRGB_NONLINEAR_EXT),
    /** DCI-P3 с линейной гаммой. */
    DCI_P3_LINEAR(VK_COLOR_SPACE_DCI_P3_LINEAR_EXT);

    companion object {
        /**
         * Преобразует целочисленное значение в соответствующий элемент перечисления.
         *
         * @param value целочисленное значение (VK_COLOR_SPACE_*).
         * @return соответствующее цветовое пространство.
         * @throws UnexpectedException если значение не соответствует ни одному известному цветовому пространству.
         */
        fun toEnum(value: Int): KVkColorSpaceKHR {
            return when(value) {
                VK_COLOR_SPACE_SRGB_NONLINEAR_KHR -> SRGB_NONLINEAR
                VK_COLOR_SPACE_DISPLAY_P3_NONLINEAR_EXT -> DISPLAY_P3_NONLINEAR
                VK_COLOR_SPACE_EXTENDED_SRGB_LINEAR_EXT -> EXTENDED_SRGB_LINEAR
                VK_COLOR_SPACE_DISPLAY_P3_LINEAR_EXT -> DISPLAY_P3_LINEAR
                VK_COLOR_SPACE_DCI_P3_NONLINEAR_EXT -> DCI_P3_NONLINEAR
                VK_COLOR_SPACE_BT709_LINEAR_EXT -> BT709_LINEAR
                VK_COLOR_SPACE_BT709_NONLINEAR_EXT -> BT709_NONLINEAR
                VK_COLOR_SPACE_BT2020_LINEAR_EXT -> BT2020_LINEAR
                VK_COLOR_SPACE_HDR10_ST2084_EXT -> HDR10_ST2084
                VK_COLOR_SPACE_DOLBYVISION_EXT -> DOLBYVISION
                VK_COLOR_SPACE_HDR10_HLG_EXT -> HDR10_HLG
                VK_COLOR_SPACE_ADOBERGB_LINEAR_EXT -> ADOBERGB_LINEAR
                VK_COLOR_SPACE_ADOBERGB_NONLINEAR_EXT -> ADOBERGB_NONLINEAR
                VK_COLOR_SPACE_PASS_THROUGH_EXT -> PASS_THROUGH
                VK_COLOR_SPACE_EXTENDED_SRGB_NONLINEAR_EXT -> EXTENDED_SRGB_NONLINEAR
                VK_COLOR_SPACE_DCI_P3_LINEAR_EXT -> DCI_P3_LINEAR
                else -> throw UnexpectedException("Unexpected color space")
            }
        }
    }
}
