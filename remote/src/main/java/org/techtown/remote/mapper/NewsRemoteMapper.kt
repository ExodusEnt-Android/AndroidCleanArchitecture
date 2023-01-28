package org.techtown.remote.mapper


/**
 * @see
 * */

interface NewsRemoteMapper<T, E> {
    fun T.toData(): E
    fun E.fromData(): T
}