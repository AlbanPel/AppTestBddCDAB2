package com.example.apptestbddcdab2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val database = Database(this)

        //faker
        if (database.getUsersCount() == 0 ) {
            Log.i("MainActivity", "JE SUIS DANS LE IF")
            database.createUser(User("Fabien", 25, "Other"))
            database.createUser(User("Hugo", 21, "M"))
            database.createUser(User("Ken", 26, "M"))
            database.createUser(User("Marin", 25, "M"))
            database.createUser(User("Maxime", 24, "M"))
            database.createUser(User("Julie", 28, "F"))
            database.createUser(User("Celia", 28, "F"))
        }

        val users = database.getAllUsers()
        for (user in users) {
            Log.i("MainActivity", "Database USER: " + user)
        }
    }
}