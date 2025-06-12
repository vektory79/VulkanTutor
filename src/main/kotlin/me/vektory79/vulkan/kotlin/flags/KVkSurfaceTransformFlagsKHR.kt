package me.vektory79.vulkan.kotlin.flags

import me.vektory79.vulkan.kotlin.KVkBits
import me.vektory79.vulkan.kotlin.KVkFlags
import org.lwjgl.vulkan.KHRSurface.VK_SURFACE_TRANSFORM_HORIZONTAL_MIRROR_BIT_KHR
import org.lwjgl.vulkan.KHRSurface.VK_SURFACE_TRANSFORM_HORIZONTAL_MIRROR_ROTATE_180_BIT_KHR
import org.lwjgl.vulkan.KHRSurface.VK_SURFACE_TRANSFORM_HORIZONTAL_MIRROR_ROTATE_270_BIT_KHR
import org.lwjgl.vulkan.KHRSurface.VK_SURFACE_TRANSFORM_HORIZONTAL_MIRROR_ROTATE_90_BIT_KHR
import org.lwjgl.vulkan.KHRSurface.VK_SURFACE_TRANSFORM_IDENTITY_BIT_KHR
import org.lwjgl.vulkan.KHRSurface.VK_SURFACE_TRANSFORM_INHERIT_BIT_KHR
import org.lwjgl.vulkan.KHRSurface.VK_SURFACE_TRANSFORM_ROTATE_180_BIT_KHR
import org.lwjgl.vulkan.KHRSurface.VK_SURFACE_TRANSFORM_ROTATE_270_BIT_KHR
import org.lwjgl.vulkan.KHRSurface.VK_SURFACE_TRANSFORM_ROTATE_90_BIT_KHR
import java.rmi.UnexpectedException

enum class KVkSurfaceTransformFlagsBitsKHR(override val value: Int) : KVkBits {
    IDENTITY(VK_SURFACE_TRANSFORM_IDENTITY_BIT_KHR),
    ROTATE_90(VK_SURFACE_TRANSFORM_ROTATE_90_BIT_KHR),
    ROTATE_180(VK_SURFACE_TRANSFORM_ROTATE_180_BIT_KHR),
    ROTATE_270(VK_SURFACE_TRANSFORM_ROTATE_270_BIT_KHR),
    HORIZONTAL_MIRROR(VK_SURFACE_TRANSFORM_HORIZONTAL_MIRROR_BIT_KHR),
    HORIZONTAL_MIRROR_ROTATE_90(VK_SURFACE_TRANSFORM_HORIZONTAL_MIRROR_ROTATE_90_BIT_KHR),
    HORIZONTAL_MIRROR_ROTATE_180(VK_SURFACE_TRANSFORM_HORIZONTAL_MIRROR_ROTATE_180_BIT_KHR),
    HORIZONTAL_MIRROR_ROTATE_270(VK_SURFACE_TRANSFORM_HORIZONTAL_MIRROR_ROTATE_270_BIT_KHR),
    INHERIT(VK_SURFACE_TRANSFORM_INHERIT_BIT_KHR);

    companion object {
        fun toEnum(value: Int): KVkSurfaceTransformFlagsBitsKHR {
            return when(value) {
                VK_SURFACE_TRANSFORM_IDENTITY_BIT_KHR -> IDENTITY
                VK_SURFACE_TRANSFORM_ROTATE_90_BIT_KHR -> ROTATE_90
                VK_SURFACE_TRANSFORM_ROTATE_180_BIT_KHR -> ROTATE_180
                VK_SURFACE_TRANSFORM_ROTATE_270_BIT_KHR -> ROTATE_270
                VK_SURFACE_TRANSFORM_HORIZONTAL_MIRROR_BIT_KHR -> HORIZONTAL_MIRROR
                VK_SURFACE_TRANSFORM_HORIZONTAL_MIRROR_ROTATE_90_BIT_KHR -> HORIZONTAL_MIRROR_ROTATE_90
                VK_SURFACE_TRANSFORM_HORIZONTAL_MIRROR_ROTATE_180_BIT_KHR -> HORIZONTAL_MIRROR_ROTATE_180
                VK_SURFACE_TRANSFORM_HORIZONTAL_MIRROR_ROTATE_270_BIT_KHR -> HORIZONTAL_MIRROR_ROTATE_270
                VK_SURFACE_TRANSFORM_INHERIT_BIT_KHR -> INHERIT
                else -> throw UnexpectedException("Unexpected surface transform mode")
            }
        }
    }
}

@JvmInline
value class KVkSurfaceTransformFlagsKHR(override val flags: Int) : KVkFlags {
    val identity get() = KVkSurfaceTransformFlagsBitsKHR.IDENTITY.check(flags)
    val rotate90 get() = KVkSurfaceTransformFlagsBitsKHR.ROTATE_90.check(flags)
    val rotate180 get() = KVkSurfaceTransformFlagsBitsKHR.ROTATE_180.check(flags)
    val rotate270 get() = KVkSurfaceTransformFlagsBitsKHR.ROTATE_270.check(flags)
    val horizontalMirror get() = KVkSurfaceTransformFlagsBitsKHR.HORIZONTAL_MIRROR.check(flags)
    val horizontalMirrorRotate90 get() = KVkSurfaceTransformFlagsBitsKHR.HORIZONTAL_MIRROR_ROTATE_90.check(flags)
    val horizontalMirrorRotate180 get() = KVkSurfaceTransformFlagsBitsKHR.HORIZONTAL_MIRROR_ROTATE_180.check(flags)
    val horizontalMirrorRotate270 get() = KVkSurfaceTransformFlagsBitsKHR.HORIZONTAL_MIRROR_ROTATE_270.check(flags)
    val inherit get() = KVkSurfaceTransformFlagsBitsKHR.INHERIT.check(flags)
}
