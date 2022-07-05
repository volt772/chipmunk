package com.apx6.domain.repository

sealed class Resource<T> {

    class Success<T>(val data: T): Resource<T>()

    class Failed<T>(val message: String): Resource<T>()

}