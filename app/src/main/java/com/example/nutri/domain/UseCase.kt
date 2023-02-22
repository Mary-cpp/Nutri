package com.example.nutri.domain

abstract class UseCase {

    abstract suspend fun run()

    operator fun invoke(){
        // body
    }
}