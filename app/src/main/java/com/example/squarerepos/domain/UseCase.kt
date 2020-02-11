package com.example.squarerepos.domain

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class UseCase<in T, R> {

    private val compositeDisposable = CompositeDisposable()

    fun Disposable.addToDisposables() {
        compositeDisposable.add(this)
    }

    fun dispose() {
        compositeDisposable.dispose()
    }

    abstract fun execute(
        params: T,
        onExecuted: (UseCaseResult<R>) -> Unit
    )
}