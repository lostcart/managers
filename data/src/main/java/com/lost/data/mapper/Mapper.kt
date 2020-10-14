package com.lost.data.mapper

abstract class Mapper<in From, To> {

    abstract fun mapFrom(from: From): To
}