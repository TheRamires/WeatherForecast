package com.mvcage.retrofit

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.math.log


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://rawgit.com/startandroid/data/master/messages/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val messagesApi: IMessagesApi = retrofit.create(IMessagesApi::class.java)

        val messages: Call<List<Message>> = messagesApi.messages()

        messages.enqueue(object :
            Callback<List<Message>> {
            override fun onResponse(call: Call<List<Message>>, response: Response<List<Message>>) {
                val fuck : List<Message>? = response.body()
                Log.i("response ", response.body()?.size.toString())

                for(message in fuck!!)
                {
                    Log.i("response", message.messageInfo())
                }
            }

            override fun onFailure(call: Call<List<Message>>, t: Throwable?) {
                Log.i("response ", "fail")
            }
        })
    }
}
