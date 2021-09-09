package com.example.khushi_baby.Database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class ExternalDataBaseHelper(context: Context?, DATABASE_NAME: String?, DATABASE_VERSION: Int) : SQLiteOpenHelper(context, ExternalDataBaseHelper.DataBaseName , null, ExternalDataBaseHelper.DataBaseVersion)  {

	private val CREATE_TABLE_SYMPTOMS = ("CREATE TABLE " + TABLE_SYMPTOMS + "("
			+ COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_DESC + " TEXT" + ")")

	private val CREATE_TABLE_DIAGNOSIS = ("CREATE TABLE " + TABLE_DIAGNOSIS + "("
			+ COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_DESC + " TEXT" + ")")

	private val CREATE_TABLE_PRESCRIPTION = ("CREATE TABLE " + TABLE_PRESCRIPTION + "("
			+ COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_DESC + " TEXT" + ")")


	override fun onCreate(db: SQLiteDatabase?) {
		db?.execSQL(CREATE_TABLE_SYMPTOMS)
		db?.execSQL(CREATE_TABLE_DIAGNOSIS)
		db?.execSQL(CREATE_TABLE_PRESCRIPTION)

	}

	override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
	}

	fun getSymptoms():ArrayList<ExternalDataBase>{
		val columns = arrayOf(
			COLUMN_ID,
			COLUMN_DESC
		)
		val sortOrder = "${COLUMN_ID} ASC"
		val symptomList = ArrayList<ExternalDataBase>()
		val db = this.readableDatabase
		val cursor = db.query(
			TABLE_SYMPTOMS, //Table to query
			columns,            //columns to return
			null,     //columns for the WHERE clause
			null,  //The values for the WHERE clause
			null,      //group the rows
			null,       //filter by row groups
			sortOrder)         //The sort order
		if (cursor.moveToFirst()) {
			do {
				val symptom = ExternalDataBase(id = cursor.getString(cursor.getColumnIndex(COLUMN_ID)).toInt(),
					desc = cursor.getString(cursor.getColumnIndex(COLUMN_DESC)))
				symptomList.add(symptom)
			} while (cursor.moveToNext())
		}
		cursor.close()
		db.close()
		return symptomList
	}

	fun getDiagnosis():ArrayList<ExternalDataBase>{
		val columns = arrayOf(
			COLUMN_ID,
			COLUMN_DESC
		)
		val sortOrder = "${COLUMN_ID} ASC"
		val diagnosisList = ArrayList<ExternalDataBase>()
		val db = this.readableDatabase
		val cursor = db.query(
			TABLE_DIAGNOSIS, //Table to query
			columns,            //columns to return
			null,     //columns for the WHERE clause
			null,  //The values for the WHERE clause
			null,      //group the rows
			null,       //filter by row groups
			sortOrder)         //The sort order
		if (cursor.moveToFirst()) {
			do {
				val daig = ExternalDataBase(id = cursor.getString(cursor.getColumnIndex(COLUMN_ID)).toInt(),
					desc = cursor.getString(cursor.getColumnIndex(COLUMN_DESC)))
				diagnosisList.add(daig)
			} while (cursor.moveToNext())
		}
		cursor.close()
		db.close()
		return diagnosisList
	}

	fun getprescription():ArrayList<ExternalDataBase>{
		val columns = arrayOf(
			COLUMN_ID,
			COLUMN_DESC
		)
		val sortOrder = "${COLUMN_ID} ASC"
		val prescriptionList = ArrayList<ExternalDataBase>()
		val db = this.readableDatabase
		val cursor = db.query(
			TABLE_PRESCRIPTION, //Table to query
			columns,            //columns to return
			null,     //columns for the WHERE clause
			null,  //The values for the WHERE clause
			null,      //group the rows
			null,       //filter by row groups
			sortOrder)         //The sort order
		if (cursor.moveToFirst()) {
			do {
				val presc = ExternalDataBase(id = cursor.getString(cursor.getColumnIndex(COLUMN_ID)).toInt(),
					desc = cursor.getString(cursor.getColumnIndex(COLUMN_DESC)))
				prescriptionList.add(presc)
			} while (cursor.moveToNext())
		}
		cursor.close()
		db.close()
		return prescriptionList
	}


	companion object {
		val DataBaseName = "external.db"
		private val DataBaseVersion =  1
		private val TABLE_SYMPTOMS = "user_data"
		private val TABLE_DIAGNOSIS = "user_diagnosis"
		private val TABLE_PRESCRIPTION = "user_prescription"
		private val COLUMN_ID = "id"
		private val COLUMN_DESC = "desc"
	}
}