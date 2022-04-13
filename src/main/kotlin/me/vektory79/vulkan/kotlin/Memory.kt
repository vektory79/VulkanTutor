package me.vektory79.vulkan.kotlin

import org.lwjgl.system.MemoryStack
import org.lwjgl.system.MemoryStack.stackGet
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

@Suppress("OPT_IN_IS_NOT_ENABLED")
@OptIn(ExperimentalContracts::class)
inline fun <R> stackPush(operation: MemoryStack.() -> R) {
    contract {
        callsInPlace(operation, InvocationKind.EXACTLY_ONCE)
    }
    stackGet().use { stack ->
        stack.push()
        stack.operation()
    }
}
