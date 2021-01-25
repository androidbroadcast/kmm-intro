package com.example.movies.shared.viewmodel

class LiveData<T> {
    var listener: Listener<T>? = null

    constructor() {}
    constructor(v: T?) : this() {
        value = v
    }

    fun bind(listener: Listener<T>?) {
        this.listener = listener
    }

    fun bindAndFire(listener: Listener<T>?) {
        this.listener = listener
        listener?.invoke(value)
    }

    var value: T? = null
    set(value) {
        listener?.invoke(value)
        field = value
    }
}

typealias Listener<T> = (T?) -> Unit