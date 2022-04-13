package me.vektory79.vulkan.tutor

import org.lwjgl.system.Configuration.DEBUG

const val WIDTH = 800
const val HEIGHT = 600

val ENABLE_VALIDATION_LAYERS: Boolean = DEBUG.get(true)

val VALIDATION_LAYERS: Set<String> =
    if (ENABLE_VALIDATION_LAYERS) setOf("VK_LAYER_KHRONOS_validation")
    // We are not going to use it, so we don't create it
    else setOf()
