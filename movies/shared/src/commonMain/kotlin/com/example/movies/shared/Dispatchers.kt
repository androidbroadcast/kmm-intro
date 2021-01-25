package com.example.movies.shared

import kotlin.coroutines.CoroutineContext


expect val uiDispatcher: CoroutineContext
expect val ioDispatcher: CoroutineContext
