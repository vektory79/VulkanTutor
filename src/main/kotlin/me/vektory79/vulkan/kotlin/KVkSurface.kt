package me.vektory79.vulkan.kotlin

import org.lwjgl.glfw.GLFWVulkan.glfwCreateWindowSurface
import org.lwjgl.system.MemoryStack
import org.lwjgl.vulkan.KHRSurface.vkDestroySurfaceKHR
import org.lwjgl.vulkan.VK10.VK_NULL_HANDLE
import org.lwjgl.vulkan.VK10.VK_SUCCESS

/**
 * Vulkan-поверхность для вывода на окно GLFW.
 *
 * Связывает Vulkan с системой оконного менеджера, позволяя презентовать изображения
 * swap chain в окно. Создаётся через GLFW-интеграцию и уничтожается через
 * ``vkDestroySurfaceKHR``.
 *
 * @property surface нативный хэндл ``VkSurfaceKHR``.
 */
class KVkSurface(private val instance: KVkInstance, val surface: Long) {
    /**
     * Уничтожает поверхность и освобождает связанные ресурсы.
     *
     * Вызывает ``vkDestroySurfaceKHR``. После вызова презентация на эту поверхность
     * становится невозможной.
     */
    fun destroy() {
        vkDestroySurfaceKHR(instance, surface, null)
    }

    companion object {
        /**
         * Создаёт поверхность для окна GLFW.
         *
         * Вызывает `glfwCreateWindowSurface` для связывания Vulkan-инстанса с окном.
         *
         * @param instance Vulkan-инстанс.
         * @param window нативный хэндл GLFW-окна.
         * @return созданная поверхность.
         * @throws RuntimeException если создание поверхности не удалось.
         */
        context(stack: MemoryStack)
        fun glfwCreateWindowSurface(instance: KVkInstance, window: Long): KVkSurface {
            val pSurface = stack.longs(VK_NULL_HANDLE)
            if (glfwCreateWindowSurface(instance, window, null, pSurface) != VK_SUCCESS) {
                throw RuntimeException("Failed to create window surface")
            }
            return KVkSurface(instance, pSurface[0])
        }
    }
}
