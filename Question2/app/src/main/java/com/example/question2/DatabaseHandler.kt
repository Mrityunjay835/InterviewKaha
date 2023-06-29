package com.example.question2

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast



class DatabaseHandler (var context: Context):SQLiteOpenHelper(context, DATABASE_NAME,null,1) {

    companion object{
        private const val DATABASE_NAME="MyDB"

//        table users
        private const val TABLE_NAME="users"
        private const  val COL_ID="id"
        private const val COL_NAME="name"
        private const  val COL_DOB="dob"
        private const val COL_ADDRESS="address"

//        table user transaction
        private const val TABLE_TNAME="UserTransaction"
        private const  val COL_UID="uid"
        private const val COL_TDOT="dot"
        private const  val COL_AMOUNT="amount"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createUserTransactionTable = "CREATE TABLE $TABLE_TNAME( $COL_UID INTEGER NOT NULL, $COL_TDOT TEXT NOT NULL, $COL_AMOUNT TEXT NOT NULL);"

        val createUserTable = "CREATE TABLE $TABLE_NAME( $COL_ID INTEGER PRIMARY KEY AUTOINCREMENT, $COL_NAME TEXT NOT NULL, $COL_DOB TEXT NOT NULL, $COL_ADDRESS TEXT NOT NULL);"
        db?.execSQL(createUserTransactionTable)
        db?.execSQL(createUserTable)

        Toast.makeText(context, "tABLE ",Toast.LENGTH_SHORT).show()

    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME");
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_TNAME");
        onCreate(db)

    }

    fun insertData(user:User){
        val db=this.writableDatabase
        val cv= ContentValues()
        cv.put(COL_NAME,user.name)
        cv.put( COL_DOB,user.dob)
        cv.put(COL_ADDRESS,user.address)
        val result=db.insert(TABLE_NAME,null,cv)
        if(result == -1.toLong()){
            Toast.makeText(context, "Failed",Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(context, "Success",Toast.LENGTH_SHORT).show()
        }
    }

    fun insertTransactionData(userTransaction:UserTransaction){
        val db1=this.writableDatabase
        val cv1= ContentValues()
        cv1.put(COL_UID,userTransaction.uid)
        cv1.put( COL_TDOT,userTransaction.dot)
        cv1.put(COL_AMOUNT,userTransaction.amount)
        val result=db1.insert(TABLE_TNAME ,null,cv1)
        if(result == -1.toLong()){
            Toast.makeText(context, "Failed...",Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(context, "Success",Toast.LENGTH_SHORT).show()
        }

    }

    fun getUserTransactions(userId: Int):MutableList<UserTransaction>{
        val list: MutableList<UserTransaction> = ArrayList()

        val db =this.readableDatabase
        val query= "SELECT * FROM $TABLE_TNAME WHERE $COL_ID=$userId"

        val result= db.rawQuery(query,null)

        if(result.moveToFirst()){
            do{
                val usert=UserTransaction()
                usert.uid= result.getString(0).toInt()
                usert.dot=result.getString(1).toString()
                usert.amount=result.getString(2).toString()
                list.add(usert)
            }while(result.moveToNext())
        }

        result.close()
        db.close()
        return list
    }

//    fun getUserById(userId: Int):MutableList<User>{
//        val list: MutableList<User> = ArrayList()
//
//        val db =this.readableDatabase
//        val query= "SELECT * FROM $TABLE_NAME WHERE $COL_ID = $userId"
//        val result= db.rawQuery(query,null)
//
//        if(result.moveToFirst()){
//            do{
//                val user=User()
//                user.id= result.getString(0).toInt()
//                user.name=result.getString(1).toString()
//                user.dob=result.getString(2).toString()
//                user.address=result.getString(3).toString()
//                list.add(user)
//            }while(result.moveToNext())
//        }
//
//        result.close()
//        db.close()
//        return list
//    }
    fun getUserById(userId: Int): User? {
        val db = readableDatabase

        val query = "SELECT * $TABLE_NAME WHERE $COL_ID = $userId"
        val cursor: Cursor = db.rawQuery(query, null)

        return if (cursor.moveToFirst()) {
            val id = cursor.getInt(0)
            val name = cursor.getString(1)
            val bod = cursor.getString(2)
            val address = cursor.getString(3)

            User(name, bod, address)
        } else {
            null
        }
    }

//    fun getUserTransactions(userId: Int): List<UserTransaction> {
//        val db = readableDatabase
//
//        val query = "SELECT * FROM $TABLE_TNAME WHERE $COL_UID = $userId"
//        val cursor: Cursor = db.rawQuery(query, null)
//
//        val transactions = mutableListOf<UserTransaction>()
//        if (cursor.moveToFirst()) {
//            do {
//                val date = cursor.getString(0)
//                val amount = cursor.getString(1)
//
//                transactions.add(UserTransaction(userId, date, amount))
//            } while (cursor.moveToNext())
//        }
//
//        return transactions
//    }
}