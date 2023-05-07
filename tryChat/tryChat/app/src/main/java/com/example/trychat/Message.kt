package com.example.trychat

class Message {
    var message: String? = null
    var senderId: String? =null

    constructor(){}

    constructor(message: String?, senderId: String?){
        this.message = message
        this.senderId = senderId
    }
}