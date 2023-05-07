package com.example.trychat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SignUpActivity : AppCompatActivity() {

    private lateinit var edtemail: EditText
    private lateinit var edtname: EditText
    private lateinit var edtpassword: EditText
    private lateinit var signupbtn: Button
    private lateinit var mAuth: FirebaseAuth
    private lateinit var mDbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        supportActionBar?.hide()

        mAuth = FirebaseAuth.getInstance()
        edtname = findViewById<EditText>(R.id.username_signup)
        edtpassword = findViewById<EditText>(R.id.password_signup)
        edtemail = findViewById<EditText>(R.id.email_signup)
        signupbtn = findViewById<Button>(R.id.signup_btn)

        signupbtn.setOnClickListener {
            val name = edtname.text.toString()
            val email = edtemail.text.toString()
            val password = edtpassword.text.toString()

            signup(name,email,password)
        }

    }
    private fun signup(name:String,email:String,password:String){
        //logic of signup
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this) {task ->
            if (task.isSuccessful){
                //code for home
                addUserToDatabase(name,email,mAuth.currentUser?.uid!!)
                val intent = Intent(this@SignUpActivity,MainActivity::class.java)
                finish()
                startActivity(intent)
            }
            else{
                Toast.makeText(this@SignUpActivity, "error occured", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun addUserToDatabase(name: String, email: String, uid: String){
        mDbRef = FirebaseDatabase.getInstance().reference
        mDbRef.child("user").child(uid).setValue(User(name,email,uid))
    }
}

