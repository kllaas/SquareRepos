package com.example.squarerepos.utils.ext

import android.view.View
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


fun <T> Observable<T>.applySchedulers(): Observable<T> {
    return subscribeOn(Schedulers.newThread())
        .observeOn(AndroidSchedulers.mainThread())
}

fun View.toggleVisibility(visible: Boolean) {
    visibility = if (visible) {
        View.VISIBLE
    } else {
        View.GONE
    }
}