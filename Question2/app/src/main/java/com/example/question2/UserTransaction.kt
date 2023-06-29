package com.example.question2

class UserTransaction {
    var uid:Int=0;
    var dot : String ="";
    var amount: String =""
    constructor(uid: Int, dot: String, amount:String){
        this.dot= dot
        this.amount=amount
        this.uid=uid
    }
    constructor(){}
}