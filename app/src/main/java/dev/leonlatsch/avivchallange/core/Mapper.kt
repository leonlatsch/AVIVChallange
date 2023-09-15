package dev.leonlatsch.avivchallange.core

interface Mapper<From, To> {
    fun map(from: From): To
}