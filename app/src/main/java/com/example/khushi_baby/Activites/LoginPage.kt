package com.example.khushi_baby.Activites

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.example.khushi_baby.R

class LoginPage : AppCompatActivity() {

    lateinit var mContext: Context


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_page)

        var actionBar: ActionBar? = supportActionBar
        actionBar?.hide()		//hide actionbar
        mContext = this			//set context
    }
}