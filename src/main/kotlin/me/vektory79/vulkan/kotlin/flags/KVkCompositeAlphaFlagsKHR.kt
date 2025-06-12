package me.vektory79.vulkan.kotlin.flags

import me.vektory79.vulkan.kotlin.KVkBits
import me.vektory79.vulkan.kotlin.KVkFlags
import org.lwjgl.vulkan.KHRSurface.VK_COMPOSITE_ALPHA_INHERIT_BIT_KHR
import org.lwjgl.vulkan.KHRSurface.VK_COMPOSITE_ALPHA_OPAQUE_BIT_KHR
import org.lwjgl.vulkan.KHRSurface.VK_COMPOSITE_ALPHA_POST_MULTIPLIED_BIT_KHR
import org.lwjgl.vulkan.KHRSurface.VK_COMPOSITE_ALPHA_PRE_MULTIPLIED_BIT_KHR
import java.rmi.UnexpectedException

enum class KVkCompositeAlphaFlagsBitsKHR(override val value: Int) : KVkBits {
    OPAQUE(VK_COMPOSITE_ALPHA_OPAQUE_BIT_KHR),
    PRE_MULTIPLIED(VK_COMPOSITE_ALPHA_PRE_MULTIPLIED_BIT_KHR),
    POST_MULTIPLIED(VK_COMPOSITE_ALPHA_POST_MULTIPLIED_BIT_KHR),
    INHERIT(VK_COMPOSITE_ALPHA_INHERIT_BIT_KHR);

    companion object {
        fun toEnum(value: Int): KVkCompositeAlphaFlagsBitsKHR {
            return when(value) {
                VK_COMPOSITE_ALPHA_OPAQUE_BIT_KHR -> OPAQUE
                VK_COMPOSITE_ALPHA_PRE_MULTIPLIED_BIT_KHR -> PRE_MULTIPLIED
                VK_COMPOSITE_ALPHA_POST_MULTIPLIED_BIT_KHR -> POST_MULTIPLIED
                VK_COMPOSITE_ALPHA_INHERIT_BIT_KHR -> INHERIT
                else -> throw UnexpectedException("Unexpected composite alpha flags")
            }
        }
    }
}

@JvmInline
value class KVkCompositeAlphaFlagsKHR(override val flags: Int) : KVkFlags {
    val opaque get() = KVkCompositeAlphaFlagsBitsKHR.OPAQUE.check(flags)
    val preMultiplied get() = KVkCompositeAlphaFlagsBitsKHR.PRE_MULTIPLIED.check(flags)
    val postMultiplied get() = KVkCompositeAlphaFlagsBitsKHR.POST_MULTIPLIED.check(flags)
    val inherit get() = KVkCompositeAlphaFlagsBitsKHR.INHERIT.check(flags)
}