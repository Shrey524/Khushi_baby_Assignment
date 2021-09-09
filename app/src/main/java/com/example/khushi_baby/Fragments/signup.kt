package com.example.khushi_baby.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.khushi_baby.Database.DatabseHelper
import com.example.khushi_baby.Database.UserData
import com.example.khushi_baby.R
import com.example.khushi_baby.Utils.validation
import kotlinx.android.synthetic.main.fragment_signup.*


class signup : Fragment() {


    private lateinit var databaseHelper: DatabseHelper
    private lateinit var inputValidation: validation

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        initObjects()
        return inflater.inflate(R.layout.fragment_signup, container, false)
    }

    private fun initObjects() {
        databaseHelper = DatabseHelper(activity, "UserManager.db",1)
        inputValidation = validation(requireContext())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        SignUp.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_signup2_to_login2)
        }
        login_button.setOnClickListener {
            val navigate: Boolean = postDataToSQLite()
            if(navigate){
                Navigation.findNavController(view).navigate(R.id.action_signup2_to_login2)
            }
        }
    }

    private fun postDataToSQLite(): Boolean {
        if (!inputValidation!!.isInputEditTextEmail(username_ET)){
            Toast.makeText(requireContext(),"Invalid E-mail", Toast.LENGTH_LONG).show()
            return false
        }
        if (!inputValidation!!.phoneNumberValidation(password_ET)) {
            Toast.makeText(requireContext(),"Invalid Phone Number", Toast.LENGTH_LONG).show()
            return false
        }
        if (!databaseHelper!!.checkUser(username_ET!!.text.toString().trim())) {
            var user = UserData(name = Name_ET!!.text.toString().trim(),
                e_mail = username_ET!!.text.toString().trim(),
                contact = password_ET!!.text.toString().trim())
            databaseHelper!!.addUser(user)
            Toast.makeText(requireContext(),"Please Login with your account", Toast.LENGTH_LONG).show()
            return true
        } else {
            Toast.makeText(requireContext(),"User already exists", Toast.LENGTH_LONG).show()
            return false
        }
    }

}