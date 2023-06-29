package com.example.question2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        var insert: Button= findViewById(R.id.btn_insert)
        var nameText: TextView= findViewById(R.id.edit_name)
        var dobText: TextView= findViewById(R.id.edit_dob)
        var addressText: TextView= findViewById(R.id.edit_address)
        val context = this
        var db = DatabaseHandler(context)

//        for transition

        val buttonNavigate= findViewById<Button>(R.id.btn_transaction)
        buttonNavigate.setOnClickListener {
            val intent = Intent(this, TransactionActivity::class.java)
            startActivity(intent)
        }
//        ____________________________________________________

        val buttonDisplay= findViewById<Button>(R.id.btn_display1)
        buttonDisplay.setOnClickListener {
            val intent = Intent(this, DisplayActivity::class.java)
            startActivity(intent)
        }

        insert.setOnClickListener {
            if (nameText.text.toString().length > 0 &&
                dobText.text.toString().length > 0 &&
                addressText.text.toString().length > 0
            ) {
                var user = User(
                    nameText.text.toString(),
                    dobText.text.toString().toString(),
                    addressText.text.toString()
                )

                db.insertData(user)

            } else {
                Toast.makeText(context, "Please fill", Toast.LENGTH_SHORT).show();
            }
        }


    }
}