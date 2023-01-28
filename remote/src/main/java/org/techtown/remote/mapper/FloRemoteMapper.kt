package org.techtown.remote.mapper


/**
 * @see
 * */

interface FloRemoteMapper<T, E> {
    fun T.toFloData(): E
    fun E.fromFloData(): T
}