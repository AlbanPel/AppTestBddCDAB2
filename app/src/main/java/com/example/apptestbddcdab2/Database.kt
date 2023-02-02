package com.example.apptestbddcdab2


import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.DatabaseUtils
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class Database(context: Context) :
    SQLiteOpenHelper(context, "data.db", null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE users(id INTEGER PRIMARY KEY, name TEXT, age INTEGER, gender TEXT)")
    }

    //p1: oldversion
    //p2: newVersion
    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
    }

    fun createUser(user: User) {
        val values = ContentValues()
        values.put("name", user.name)
        values.put("age", user.age)
        values.put("gender", user.gender)
        writableDatabase.insert("users", null, values)
    }

    @SuppressLint("Range")
    fun getAllUsers() : MutableList<User> {
        val users = mutableListOf<User>()
        readableDatabase.rawQuery("SELECT * FROM users", null).use { cursor ->
            while (cursor.moveToNext()) {
                val user = User(
                    cursor.getString(cursor.getColumnIndex("name")),
                    cursor.getInt(cursor.getColumnIndex("age")),
                    cursor.getString(cursor.getColumnIndex("gender"))
                )
                users.add(user)
            }
        }
        return users
    }
    fun getUsersCount() : Int = DatabaseUtils.queryNumEntries(readableDatabase, "users", null).toInt()

}
