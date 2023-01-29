/**
 * Copyright (C) 2023. ExodusEnt Corp. All rights reserved.
 * You must have prior written permission to read this file.
 * @author Kim min gyu <mingue0605@myloveidol.com>
 * Description:
 *
 * */

package com.example.remote.mapper

interface RemoteMapper<T, E> {
    fun T.toData(): E
    fun E.fromData(): T
}