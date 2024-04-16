package com.shivani.khatabook

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBhelper(context: Context): SQLiteOpenHelper(context,"Khata",null,1) {

    override fun onCreate(p0: SQLiteDatabase?) {
        val query =
            "CREATE TABLE Khatabook(id INTEGER PRIMARY KEY AUTOINCREMENT,title TEXT, amount TEXT,note TEXT, data TEXT,time TEXT,catogery TEXT)"
        val queryCategory = "CREATE TABLE Catogery(id INTEGER PRIMARY KEY AUTOINCREMENT,name TEXT)"
        p0!!.apply {
            execSQL(query)
            execSQL(queryCategory)
        }

    }

    fun addCategory(name: String) {
        var db = writableDatabase
        var cn = ContentValues()
        cn.put("name", name)
        db.insert("Category", null, cn)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
    }

    @SuppressLint("Range")
    fun getCategory(): ArrayList<Catogery_modelclass> {
        var list = arrayListOf<Catogery_modelclass>()
        val db = readableDatabase
        val query = "SELECT * FROM Category"
        var cursor = db.rawQuery(query, null)

        if (cursor.moveToFirst()) {
            do {
                var id = cursor.getString(cursor.getColumnIndex("id"))
                var name = cursor.getString(cursor.getColumnIndex("name"))
                var model = Catogery_modelclass(id, name)
                list.add(model)
            } while (cursor.moveToNext())

        }
        return list
    }

        fun addIncomeExpense(model: IncomeExpense_model) {
            val db = writableDatabase
            val cn = ContentValues()
            cn.put("title", model.title)
            cn.put("amount", model.amount)
            cn.put("date", model.date)
            cn.put("id", model.id)
            cn.put("time", model.time)
            cn.put("category",model.category)
            db.insert("IncomeExpense",null,cn)
        }
        fun updateIncomeExpense(model: IncomeExpense_model) {
            val db = writableDatabase
            val cn = ContentValues()
            cn.put("title", model.title)
            cn.put("amount", model.amount)
            cn.put("date", model.date)
            cn.put("id", model.id)
            cn.put("time", model.time)
            cn.put("category",model.category)
            db.update("IncomeExpense", cn, "id=?", arrayOf(model.id))
        }

        fun deleteIncomeExpense(id: String) {
            val db = writableDatabase
            db.delete("IncomeExpense", "id=?", arrayOf(id))
        }

        @SuppressLint("Range")
        fun getIncomeExpense(): ArrayList<IncomeExpense_model> {

            var db = readableDatabase
            var query = "SELECT * FROM IncomeExpense"
            var cursor = db.rawQuery(query, null)
            var datalist = ArrayList<IncomeExpense_model>()

            if (cursor.moveToFirst()) {

            do {
                var id = cursor.getString(cursor.getColumnIndex("id"))
                var title = cursor.getString(cursor.getColumnIndex("title"))
                var amount = cursor.getString(cursor.getColumnIndex("amount"))
                var date = cursor.getString(cursor.getColumnIndex("date"))
                var time = cursor.getString(cursor.getColumnIndex("time"))
                var note = cursor.getString(cursor.getColumnIndex("note"))
                var category = cursor.getString(cursor.getColumnIndex("category"))

                var model = IncomeExpense_model(id, title, time, amount, note, date, category)
                datalist.add(model)

            } while (cursor.moveToNext())
        }

        return datalist

    }

}
