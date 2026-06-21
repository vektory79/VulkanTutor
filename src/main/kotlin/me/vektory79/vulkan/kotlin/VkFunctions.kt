package me.vektory79.vulkan.kotlin

import java.nio.ByteBuffer
import org.lwjgl.system.MemoryStack

/**
 * Кодирует строку в null-terminated UTF-8 ByteBuffer, аллоцированный на [MemoryStack].
 *
 * Используется для передачи строк в Vulkan API (имена приложений, extensions, layers и т.д.).
 *
 * ```kotlin
 * stackPush {
 *     pApplicationName = "Hello Triangle".utf8
 * }
 * ```
 *
 * Или в методе с `context(stack: MemoryStack)`:
 *
 * ```kotlin
 * context(stack: MemoryStack) {
 *     layers.map { it.utf8 }.forEach(buffer::put)
 * }
 * ```
 */
context(stack: MemoryStack)
val String.utf8: ByteBuffer
    get() = stack.UTF8(this)



