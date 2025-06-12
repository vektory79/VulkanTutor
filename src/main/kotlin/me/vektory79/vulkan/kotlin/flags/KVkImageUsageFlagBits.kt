package me.vektory79.vulkan.kotlin.flags

import me.vektory79.vulkan.kotlin.KVkBits
import me.vektory79.vulkan.kotlin.KVkFlags
import org.lwjgl.vulkan.VK10.VK_IMAGE_USAGE_COLOR_ATTACHMENT_BIT
import org.lwjgl.vulkan.VK10.VK_IMAGE_USAGE_DEPTH_STENCIL_ATTACHMENT_BIT
import org.lwjgl.vulkan.VK10.VK_IMAGE_USAGE_INPUT_ATTACHMENT_BIT
import org.lwjgl.vulkan.VK10.VK_IMAGE_USAGE_SAMPLED_BIT
import org.lwjgl.vulkan.VK10.VK_IMAGE_USAGE_STORAGE_BIT
import org.lwjgl.vulkan.VK10.VK_IMAGE_USAGE_TRANSFER_DST_BIT
import org.lwjgl.vulkan.VK10.VK_IMAGE_USAGE_TRANSFER_SRC_BIT
import org.lwjgl.vulkan.VK10.VK_IMAGE_USAGE_TRANSIENT_ATTACHMENT_BIT
import java.rmi.UnexpectedException

enum class KVkImageUsageFlagBits(override val value: Int) : KVkBits {
    TRANSFER_SRC(VK_IMAGE_USAGE_TRANSFER_SRC_BIT),
    TRANSFER_DST(VK_IMAGE_USAGE_TRANSFER_DST_BIT),
    SAMPLED(VK_IMAGE_USAGE_SAMPLED_BIT),
    STORAGE(VK_IMAGE_USAGE_STORAGE_BIT),
    COLOR_ATTACHMENT(VK_IMAGE_USAGE_COLOR_ATTACHMENT_BIT),
    DEPTH_STENCIL_ATTACHMENT(VK_IMAGE_USAGE_DEPTH_STENCIL_ATTACHMENT_BIT),
    TRANSIENT_ATTACHMENT(VK_IMAGE_USAGE_TRANSIENT_ATTACHMENT_BIT),
    INPUT_ATTACHMENT(VK_IMAGE_USAGE_INPUT_ATTACHMENT_BIT);

    companion object {
        fun toEnum(value: Int): KVkImageUsageFlagBits {
            return when(value) {
                VK_IMAGE_USAGE_TRANSFER_SRC_BIT -> TRANSFER_SRC
                VK_IMAGE_USAGE_TRANSFER_DST_BIT -> TRANSFER_DST
                VK_IMAGE_USAGE_SAMPLED_BIT -> SAMPLED
                VK_IMAGE_USAGE_STORAGE_BIT -> STORAGE
                VK_IMAGE_USAGE_COLOR_ATTACHMENT_BIT -> COLOR_ATTACHMENT
                VK_IMAGE_USAGE_DEPTH_STENCIL_ATTACHMENT_BIT -> DEPTH_STENCIL_ATTACHMENT
                VK_IMAGE_USAGE_TRANSIENT_ATTACHMENT_BIT -> TRANSIENT_ATTACHMENT
                VK_IMAGE_USAGE_INPUT_ATTACHMENT_BIT -> INPUT_ATTACHMENT
                else -> throw UnexpectedException("Unexpected image usage flags")
            }
        }
    }
}

@JvmInline
value class KVkImageUsageFlags(override val flags: Int) : KVkFlags {
    val transferSrc get() = KVkImageUsageFlagBits.TRANSFER_SRC.check(flags)
    val transferDst get() = KVkImageUsageFlagBits.TRANSFER_DST.check(flags)
    val sampled get() = KVkImageUsageFlagBits.SAMPLED.check(flags)
    val storage get() = KVkImageUsageFlagBits.STORAGE.check(flags)
    val colorAttachment get() = KVkImageUsageFlagBits.COLOR_ATTACHMENT.check(flags)
    val depthStencilAttachment get() = KVkImageUsageFlagBits.DEPTH_STENCIL_ATTACHMENT.check(flags)
    val transientAttachment get() = KVkImageUsageFlagBits.TRANSIENT_ATTACHMENT.check(flags)
    val inputAttachment get() = KVkImageUsageFlagBits.INPUT_ATTACHMENT.check(flags)
}
