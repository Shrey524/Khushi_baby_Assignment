package com.example.khushi_baby.Database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class PaitentDataBaseHelper(context: Context?, DATABASE_NAME: String?, DATABASE_VERSION: Int) : SQLiteOpenHelper(context, DataBaseName, null, DataBaseVersion) {

	private lateinit var mContext: Context
	private val CREATE_USER_TABLE = ("CREATE TABLE " + TABLE_PAITENT + "("
			+ COLUMN_PAITENT_ID  + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_PAITENT_NAME + " TEXT,"
			+ COLUMN_PAITENT_EMAIL + " TEXT," + COLUMN_PAITENT_AGE + " TEXT," + COLUMN_PAITENT_AGE + " TEXT,"
			+ COLUMN_PAITENT_GENDER + " TEXT," + COLUMN_PAITENT_ADDRESS + " TEXT," + COLUMN_PAITENT_FNAME + " TEXT," + COLUMN_PAITENT_OCCUPATION + " TEXT" + ")")
	private val DROP_USER_TABLE = "DROP TABLE IF EXISTS $TABLE_PAITENT"
	override fun onCreate(db: SQLiteDatabase) {
		db.execSQL(CREATE_USER_TABLE)
	}
	override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
		db.execSQL(DROP_USER_TABLE)
		onCreate(db)
	}

	fun getAllUser(): ArrayList<PaitentData> {
		val columns = arrayOf(COLUMN_PAITENT_ID, COLUMN_PAITENT_NAME, COLUMN_PAITENT_EMAIL, COLUMN_PAITENT_AGE, COLUMN_PAITENT_GENDER, COLUMN_PAITENT_OCCUPATION, COLUMN_PAITENT_ADDRESS, COLUMN_PAITENT_FNAME)
		val sortOrder = "$COLUMN_PAITENT_ID ASC"
		val paitentList = ArrayList<PaitentData>()
		val db = this.readableDatabase
		val cursor = db.query(TABLE_PAITENT, //Table to query
				columns,            //columns to return
				null,     //columns for the WHERE clause
				null,  //The values for the WHERE clause
				null,      //group the rows
				null,       //filter by row groups
				sortOrder)         //The sort order
		if (cursor.moveToFirst()) {
			do {
				val paitent = PaitentData(id = cursor.getString(cursor.getColumnIndex(COLUMN_PAITENT_ID)).toInt(),
						name = cursor.getString(cursor.getColumnIndex(COLUMN_PAITENT_NAME)),
						e_mail = cursor.getString(cursor.getColumnIndex(COLUMN_PAITENT_EMAIL)),
						age = cursor.getString(cursor.getColumnIndex(COLUMN_PAITENT_AGE)),
						gender = cursor.getString(cursor.getColumnIndex(COLUMN_PAITENT_GENDER)),
						occupation = cursor.getString(cursor.getColumnIndex(COLUMN_PAITENT_OCCUPATION)),
						address = cursor.getString(cursor.getColumnIndex(COLUMN_PAITENT_ADDRESS)),
						Fname = cursor.getString(cursor.getColumnIndex(COLUMN_PAITENT_FNAME)),)
				paitentList.add(paitent)
			} while (cursor.moveToNext())
		}
		cursor.close()
		db.close()
		return paitentList
	}

	fun addUser(paitent: PaitentData) {
		val db = this.writableDatabase
		val values = ContentValues()
		values.put(COLUMN_PAITENT_NAME, paitent.name)
		values.put(COLUMN_PAITENT_EMAIL, paitent.e_mail)
		values.put(COLUMN_PAITENT_AGE, paitent.age)
		values.put(COLUMN_PAITENT_GENDER, paitent.gender)
		values.put(COLUMN_PAITENT_OCCUPATION, paitent.occupation)
		values.put(COLUMN_PAITENT_ADDRESS, paitent.address)
		values.put(COLUMN_PAITENT_FNAME, paitent.Fname)
		db.insert(TABLE_PAITENT, null, values)
		db.close()
	}



	fun deleteUser(name: String) {
		val db = this.writableDatabase
		db.delete(TABLE_PAITENT, "$COLUMN_PAITENT_NAME = ?",
				arrayOf(name))
		db.close()
	}
	companion object {
		val DataBaseName = "UserManager.db"
		private val DataBaseVersion =  1
		private val TABLE_PAITENT = "paitent_data"
		private val COLUMN_PAITENT_ID = "paitent_id"
		private val COLUMN_PAITENT_NAME = "paitent_name"
		private val COLUMN_PAITENT_EMAIL = "paitent_email"
		private val COLUMN_PAITENT_AGE = "paitent_age"
		private val COLUMN_PAITENT_GENDER = "paitent_gender"
		private val COLUMN_PAITENT_OCCUPATION = "paitent_occupation"
		private val COLUMN_PAITENT_ADDRESS = "paitent_address"
		private val COLUMN_PAITENT_FNAME = "paitent_Fname"

	}
}