package com.example.speaktotext

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.RecognizerIntent
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity()
{
    private val REQUESTCODE = 100
    lateinit var textView: TextView
///sdafjlasdjflasjd
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        title = "KotlinApp"
        textView = findViewById(R.id.textView)

        val speakImageView: ImageView = findViewById(R.id.speakImageView)

        speakImageView.setOnClickListener {

            val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)

            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                    RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)

            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())

            intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Need to Speak")

            startActivityForResult(intent, REQUESTCODE)

            Toast.makeText(applicationContext, "Sorry your device not supported", Toast.LENGTH_SHORT).show()
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?)
    {
        super.onActivityResult(requestCode, resultCode, data)
        
        if (resultCode == Activity.RESULT_OK && null != data)
        {
            val result: ArrayList<String> = data.getStringArrayListExtra(RecognizerIntent .EXTRA_RESULTS) as ArrayList<String>
            textView.text = result[0]
        }
    }


}