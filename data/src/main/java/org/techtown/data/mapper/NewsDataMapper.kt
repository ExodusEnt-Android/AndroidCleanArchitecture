package org.techtown.data.mapper

interface NewsDataMapper<T, E> {
    fun T.toEntity(): E
    fun E.fromEntity(): T
}