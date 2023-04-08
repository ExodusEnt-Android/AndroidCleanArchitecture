package org.techtown.data.mapper

interface NewsDataMapper<T, E> {
    fun T.toData(): E
    fun E.fromData(): T
}