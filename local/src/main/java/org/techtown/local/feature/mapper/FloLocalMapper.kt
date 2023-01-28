package org.techtown.local.feature.mapper


/**
 * @see
 * */

interface FloLocalMapper<T, E> {
    fun T.toFloData(): E
    fun E.fromFloData(): T
}