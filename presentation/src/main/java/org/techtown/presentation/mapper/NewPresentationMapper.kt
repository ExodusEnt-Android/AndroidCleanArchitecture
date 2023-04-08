package org.techtown.presentation.mapper

/**
 * @see
 * */

interface NewPresentationMapper<T, E> {
    fun T.toEntity(): E
    fun E.fromEntity(): T
}
