package com.example.trychat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    private lateinit var edtemail:EditText
    private lateinit var edtpassword:EditText
    private lateinit var loginbtn:Button
    private lateinit var signupbtn:Button
    private lateinit var mAuth: FirebaseAuth

        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
            supportActionBar?.hide()

            mAuth = FirebaseAuth.getInstance()
            edtpassword = findViewById<EditText>(R.id.password_login)
            edtemail = findViewById<EditText>(R.id.email_login)
            loginbtn = findViewById<Button>(R.id.login_btn)
            signupbtn = findViewById<Button>(R.id.signup_btn)

            signupbtn.setOnClickListener{
                val intent = Intent(this@LoginActivity,SignUpActivity::class.java)
                startActivity(intent)
            }

            loginbtn.setOnClickListener{
                val email = edtemail.text.toString()
                val password = edtpassword.text.toString()

                login(email,password)
            }

    }
    private fun login(email:String,password:String){
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this){
            task ->
            if(task.isSuccessful){
                //code for home
                val intent = Intent(this@LoginActivity,MainActivity::class.java)
                finish()
                startActivity(intent)
            }
            else{
                Toast.makeText(this@LoginActivity, "user doesn't exists", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

