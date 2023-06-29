package com.example.question2

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DisplayActivity: AppCompatActivity() {
    private lateinit var listViewTransactions: ListView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_data)

        var name: TextView=findViewById(R.id.text_name)
        var dob: TextView=findViewById(R.id.text_dob)
        var address: TextView=findViewById(R.id.text_address)
        var dot: TextView=findViewById(R.id.text_dot)
        var amount: TextView=findViewById(R.id.text_amount)
        listViewTransactions = findViewById(R.id.listViewTransactions)
        var context=this
        var db =DatabaseHandler(context);

        var userid:EditText = findViewById(R.id.edit_userid)
        var displayEvent:Button=findViewById(R.id.btn_submit)

        displayEvent.setOnClickListener{
            var userId= userid.text.toString().toInt();


            val user: User? = db.getUserById(userId)
            val userTransactions: List<UserTransaction> = db.getUserTransactions(userId)

            user?.let {
                name.text = "User Name: ${user.name}"
                dob.text = "Date of Birth: ${user.dob}"
                address.text = "Address: ${user.address}"
            }

        }
        fun displayUserTransactions(transactions: List<UserTransaction>) {
            val transactionAmounts = transactions.map { "Date: ${it.dot}, Amount: ${it.amount}" }
            val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, transactionAmounts)
            listViewTransactions.adapter = adapter
        }


    }
}