package com.example.japance

data class Thing(
    val id: String?,
    val name: String? = "___",
    val score: Int? = 0
){
    constructor() : this("if you see this there is error",
        "if you see this there is error",404
    )
}