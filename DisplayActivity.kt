package com.mrdheerajpurohit.kotlinproject
/**
 * Dheeraj Purohit Kotlin Demo for Android.
 */

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Typeface
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast

class DisplayActivity : AppCompatActivity() {

    var prefs: SharedPreferences? = null
    val PREFS_FILENAME = "com.omegasolutions.kotlinproject.prefs"
    internal var titleTypeFace: Typeface? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display)
        val text: TextView = findViewById(R.id.result) as TextView
        val detailstext: TextView = findViewById(R.id.details) as TextView
        val mydetailstext: TextView = findViewById(R.id.mydetails) as TextView


        prefs = this.getSharedPreferences(PREFS_FILENAME, 0)
        titleTypeFace = Typeface.createFromAsset(assets, "SourceSansPro-Regular.ttf")
        text?.typeface = titleTypeFace
        detailstext?.typeface = titleTypeFace
        mydetailstext?.typeface = titleTypeFace

        val i = intent
        val count = prefs!!.getInt("count",0);


        text.setText(i.getStringExtra("firstName")+" "+i.getStringExtra("lastName")+"\nYou are successfully register!!!")
        toast("Count : "+count.toString())
    }
    fun Context.toast(message: CharSequence) =
            Toast.makeText(this, message, Toast.LENGTH_LONG).show()

}
