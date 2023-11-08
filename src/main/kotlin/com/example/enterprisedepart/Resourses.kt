package com.example.enterprisedepart

import java.net.URL

object Resourses {
    fun get(path:String): URL? {
        return HelloApplication::class.java.getResource(path)
    }
}