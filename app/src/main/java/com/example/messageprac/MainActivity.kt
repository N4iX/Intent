package com.example.messageprac

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonSend.setOnClickListener{
            sendMessage()
        }

        buttonCallMe.setOnClickListener{
            callMe()
        }
    }

    private fun callMe() {
        val intent = Intent(Intent.ACTION_VIEW)
        val phone: String = "tel:0123456789"

        //Check package manager for app to handle an intent
        intent.setData(Uri.parse(phone))
        if(intent.resolveActivity(packageManager) != null){
            startActivity(intent)
        }
    }

    private fun sendMessage() {
        val intent = Intent(this,
            SecondActivity::class.java)

        val message = editTextMessage.text.toString()
        val luckyNum = editTextLuckyNum.text.toString().toInt()

        intent.putExtra(KEY, message)
        intent.putExtra(KEY_2, luckyNum)

        //startActivity(intent)
        startActivityForResult(intent, REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(requestCode == REQUEST_CODE){
            if(resultCode == Activity.RESULT_OK){
                val reply = data?.getStringArrayExtra(REPLY).toString()
                textViewReply.text = String.format("%s %s", getString(R.string.reply), reply)
            }
        }
    }

    companion object{
        const val KEY = "com.example.messageprac.KEY"
        const val KEY_2 = "com.example.messageprac.KEY_2"
        const val REPLY = "com.example.messageprac.REPLY"
        const val REQUEST_CODE = 1
    }
}
