package org.techtown.local.feature.mapper


/**
 * @see
 * */

interface NewsLocalMapper<T, E> {
    fun T.toData(): E
    fun E.fromData(): T
}