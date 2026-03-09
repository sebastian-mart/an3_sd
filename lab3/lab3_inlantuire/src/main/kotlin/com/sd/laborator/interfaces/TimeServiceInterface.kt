package com.sd.laborator.interfaces


//adaugata injectare dependinta + interfata pentru respectarea dependentei inverse
interface TimeServiceInterface {
    fun getCurrentTime():String
}