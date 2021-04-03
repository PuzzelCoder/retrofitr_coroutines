package com.example.coroutineretrofit1

import android.widget.ProgressBar

class MyThread(name: String, val progressBar: ProgressBar) : Thread(name) {
    var isExit: Boolean = false

    fun setMyExit(setExit:Boolean){
        isExit=setExit
    }

    override fun run() {
        super.run()
        var i = 0
        while (true) {
            if(isExit){
                break
            }

            println("$name: $i")
            try {
                i++
                i = i % 15
                progressBar.progress = i
                sleep(100)
            } catch (e: InterruptedException) {
                println("Caught:$e")
            }
        }
        println("$name Stopped.")
    }
}
