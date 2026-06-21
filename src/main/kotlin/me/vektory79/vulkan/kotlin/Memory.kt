package me.vektory79.vulkan.kotlin

import org.lwjgl.system.MemoryStack
import org.lwjgl.system.MemoryStack.stackGet
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

/**
 * Выполняет операцию [operation] внутри фрейма [MemoryStack].
 *
 * Получает текущий стек через [stackGet], создаёт новый фрейм ([push]),
 * выполняет операцию и автоматически очищает фрейм после завершения.
 *
 * ```kotlin
 * stackPush {
 *     val buf = stack.malloc(256)
 *     // buf автоматически освобождается при выходе из блока
 * }
 * ```
 *
 * @param R тип возвращаемого значения операции.
 * @param operation лямбда, выполняемая в контексте [MemoryStack].
 */
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
