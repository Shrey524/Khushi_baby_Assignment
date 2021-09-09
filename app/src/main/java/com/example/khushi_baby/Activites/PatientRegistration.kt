package com.example.khushi_baby.Activites

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.example.khushi_baby.R
import kotlinx.android.synthetic.main.activity_paitent_registration.*

class PatientRegistration : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_paitent_registration)
        var actionBar: ActionBar? = supportActionBar
        actionBar?.hide()		//hide actionbar

        register2.setOnClickListener {
//            postDataToSQLite()
            val intent = Intent(this, ActivityPatient::class.java)
            startActivity(intent)
        }
    }

}