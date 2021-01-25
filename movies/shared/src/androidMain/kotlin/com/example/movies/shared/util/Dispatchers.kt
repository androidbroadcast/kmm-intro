package com.example.movies.shared.util

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext


actual val ioDispatcher: CoroutineContext = Dispatchers.IO

actual val uiDispatcher: CoroutineContext = Dispatchers.Main

actual fun ktorScope(block: suspend () -> Unit) {
    GlobalScope.launch(uiDispatcher) { block() }
}