package com.example.question2

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class TransactionActivity:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insert_transaction)

        var useridText: TextView = findViewById(R.id.edit_userid)
        var dotText: TextView = findViewById(R.id.edit_dot)
        var amountText: TextView = findViewById(R.id.edit_amount)
        var db = DatabaseHandler(applicationContext)
        var tInsert: Button = findViewById(R.id.btn_t_insert)

        val buttonNavigate= findViewById<Button>(R.id.btn_user)
        buttonNavigate.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        val buttonDisplay= findViewById<Button>(R.id.btn_display2)
        buttonDisplay.setOnClickListener {
            val intent = Intent(this, DisplayActivity::class.java)
            startActivity(intent)
        }




        tInsert.setOnClickListener {
            if (useridText.text.toString().length > 0 && dotText.text.toString().length > 0 &&
                amountText.text.toString().length > 0
            ) {
                var usert = UserTransaction(
                    useridText.text.toString().toInt(),
                    dotText.text.toString().toString(),
                    amountText.text.toString()
                )
                db.insertTransactionData(usert)

            } else {
                Toast.makeText(applicationContext, "Please fill", Toast.LENGTH_SHORT).show();
            }
        }

    }


}

