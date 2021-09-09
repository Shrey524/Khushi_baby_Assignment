package com.example.khushi_baby.Activites

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.AsyncTask
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.khushi_baby.Adapter.HomePageAdapter
import com.example.khushi_baby.Database.PaitentData
import com.example.khushi_baby.Database.PaitentDataBaseHelper
import com.example.khushi_baby.R
import kotlinx.android.synthetic.main.activity_homepage.*

class HomePage: AppCompatActivity() {
    private lateinit var adapter: HomePageAdapter
    lateinit var sharedPreferences: SharedPreferences
    private val activity = this@HomePage
    lateinit var mContext: Context
    lateinit var listUsers: ArrayList<PaitentData>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_homepage)
        sharedPreferences = this.activity.getSharedPreferences("SHARED_PREF", Context.MODE_PRIVATE)

        var actionBar: ActionBar? = supportActionBar
        actionBar?.hide()		//hide actionbar
        mContext = this			//set context

        RegisterPaitent.setOnClickListener {
            val intent = Intent(mContext, PatientRegistration::class.java)
            startActivity(intent)
        }

        Logout.setOnClickListener {
            val editor: SharedPreferences.Editor = sharedPreferences.edit()
            editor.putBoolean("CHECKBOX", false)
            editor.apply()
            val intent = Intent(mContext, LoginPage::class.java)
            startActivity(intent)
        }

        if(listUsers.size==0){
            Toast.makeText(mContext,"You haven't registered any patient yet", Toast.LENGTH_LONG).show()
        }
        initObjects()
    }

    private fun initObjects() {
        listUsers = ArrayList()
        adapter = HomePageAdapter(activity, listUsers)
        val mLayoutManager = LinearLayoutManager(applicationContext)
        paitentlist.layoutManager = mLayoutManager
        paitentlist.itemAnimator = DefaultItemAnimator()
        paitentlist.setHasFixedSize(true)
        paitentlist.adapter = adapter
        databaseHelper = PaitentDataBaseHelper(activity,"PaitentManager.db",1)
        var getDataFromSQLite = GetDataFromSQLite()
        getDataFromSQLite.execute()
    }

    inner class GetDataFromSQLite : AsyncTask<Void, Void, List<PaitentData>>() {
        override fun doInBackground(vararg p0: Void?): List<PaitentData> {
            return databaseHelper.getAllUser()
        }
        override fun onPostExecute(result: List<PaitentData>?) {
            super.onPostExecute(result)
            listUsers.clear()
            listUsers.addAll(result!!)
            runOnUiThread {
                addDataToRecyclerView()
            }
        }
        private fun addDataToRecyclerView(){
            adapter?.notifyDataSetChanged()
        }
    }

    companion object{
        lateinit var databaseHelper: PaitentDataBaseHelper
        private var instance: HomePage? = null

    }

}
