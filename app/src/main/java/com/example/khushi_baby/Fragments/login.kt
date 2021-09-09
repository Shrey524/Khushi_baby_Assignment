package com.example.khushi_baby.Fragments

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.khushi_baby.Activites.HomePage
import com.example.khushi_baby.Database.DatabseHelper
import com.example.khushi_baby.R
import kotlinx.android.synthetic.main.fragment_login.*


class login : Fragment() {

    lateinit var sharedPreferences: SharedPreferences
    var isRemember = false
    private lateinit var databaseHelper: DatabseHelper

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        super.onCreate(savedInstanceState)
        login_button.setOnClickListener {
            verifyFromSQLite()
        }
        SignUp.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_login2_to_signup2)
        }
    }

    private fun verifyFromSQLite() {
        if (databaseHelper!!.checkUser(username_ET!!.text.toString().trim { it <= ' ' }, password_ET!!.text.toString().trim { it <= ' ' })) {
            val userName: String = username_ET.text.toString()
            val password: String = password_ET.text.toString()
            var isChecked: Boolean = checkBox.isChecked

            val editor: SharedPreferences.Editor = sharedPreferences.edit()
            editor.putString("USERNAME", userName)
            editor.putString("PASSWORD", password)
            editor.putBoolean("CHECKBOX", isChecked)
            editor.apply()

            Toast.makeText(requireContext(),"Welcome", Toast.LENGTH_LONG).show()
            val intent = Intent(requireContext(), HomePage::class.java)
            emptyInputEditText()
            startActivity(intent)
        } else {
            Toast.makeText(requireContext(),"No User Found Please Register yourself first", Toast.LENGTH_LONG).show()
        }

    }

    private fun emptyInputEditText() {
        username_ET!!.text = null
        password_ET!!.text = null
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        sharedPreferences = this.requireActivity().getSharedPreferences("SHARED_PREF", Context.MODE_PRIVATE)
        isRemember = sharedPreferences.getBoolean("CHECKBOX", false)

        if(isRemember){
            val intent = Intent(requireContext(), HomePage::class.java)
            startActivity(intent)
        }
        initObjects()
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    private fun initObjects() {
        databaseHelper = DatabseHelper((activity), "UserManager.db",1)
    }


}