package com.lazy.androidjetpackdemo.Paging

import java.util.concurrent.Executors

private val IO_EXECUTOR = Executors.newSingleThreadExecutor()

/**
 * Utility method to run blocks on a dedicated background thread, used for io/database work.
 * 在专用后台线程上运行块的实用程序方法，用于io /数据库工作。
 */
fun ioThread(f : () -> Unit) {
    IO_EXECUTOR.execute(f)
}