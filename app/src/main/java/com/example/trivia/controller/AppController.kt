package com.example.trivia.controller

import android.app.Application
import android.content.Context
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley

class AppController: Application() {
    companion object {
        @Volatile
        private var INSTANCE: AppController? = null

        fun getInstance() =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: AppController().also {
                    INSTANCE = it
                }
            }
    }

    val requestQueue: RequestQueue by lazy {
        Volley.newRequestQueue(applicationContext)
    }

    fun <T> addToRequestQueue(req: Request<T>) {
        requestQueue.add(req)
    }

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
    }

}