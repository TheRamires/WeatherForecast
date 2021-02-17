package com.mvcage.retrofit

public class Message {

    private val id: Long = 0
    private val time: Long = 0
    private val text: String? = null
    private val image: String? = null

    fun messageInfo() : String
    {
        return "$id $time $text"
    }
}