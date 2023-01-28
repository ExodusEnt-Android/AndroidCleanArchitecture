package org.techtown.presentation.mapper

/**
 * @see
 * */

interface NewPresentationMapper<T, E> {
    fun T.toData(): E
    fun E.fromData(): T
}
