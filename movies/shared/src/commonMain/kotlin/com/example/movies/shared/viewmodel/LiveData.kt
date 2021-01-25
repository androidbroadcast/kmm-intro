package com.example.movies.shared.viewmodel

typealias Listener<T> = (T?)->Unit

class LiveData<T> {
    var value:T? = null
    set(value) {
        listener?.invoke(value)
        field = value
    }
    var listener: Listener<T>? = null

    constructor(){}
    constructor(v:T?):this() {
        this.value = v
    }

    fun bind(listener: Listener<T>?) {
        this.listener = listener
    }
}