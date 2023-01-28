package org.techtown.presentation.mapper

/**
 * @see
 * */

interface FloPresentationMapper<T, E> {
    fun T.toFloData(): E
    fun E.fromFloData(): T
}
