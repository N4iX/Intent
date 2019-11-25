package com.example.messageprac

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val message = intent.getStringExtra(MainActivity.KEY)
        val luckyNum = intent.getIntExtra(MainActivity.KEY_2, 0)
        textViewMessage.text = String.format("%s %s \n %s %d", getString(R.string.message), message, getString(R.string.lucky_num), luckyNum)

        buttonDone.setOnClickListener{
            //intent.putExtra(MESSAGE, message)
            //intent.putExtra(LUCKYNUM, luckyNum)
            //startActivity(intent)
            if(editTextReply.text.isNotEmpty()) {
                val reply = editTextReply.text.toString()
                val intent = getIntent() //return the MainActivity intent
                intent.putExtra(MainActivity.REPLY, reply)

                //inform the MainActivity that everything is okay
                setResult(Activity.RESULT_OK, intent)
            }else{
                setResult(Activity.RESULT_CANCELED)
            }
            finish()
        }
    }
}
