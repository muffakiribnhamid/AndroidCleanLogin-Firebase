package com.muffakir.regiesterapp

import android.app.Activity
import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.muffakir.regiesterapp.databinding.ActivitySignUpBinding

class SignUp : AppCompatActivity() {
    private lateinit var mAuth : FirebaseAuth
    private lateinit var binding: ActivitySignUpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mAuth = FirebaseAuth.getInstance()

        binding.btnSignUp.setOnClickListener {
        var email = binding.etEmailSignUp.text.toString()
        var password = binding.etPasswordSignUp.text.toString()
            validateUser(email,password)
            regiester(email,password)
        }
    }

    fun regiester(email : String, password : String) {
        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(ContentValues.TAG, "createUserWithEmail:success")
                    val user = mAuth.currentUser
                    updateUi(SignIn())
                    var intent = Intent()

                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(ContentValues.TAG, "createUserWithEmail:failure", task.exception)
                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                }
            }
    }

    fun updateUi(activity : Activity) {
        val intent = Intent(this,activity::class.java)
        startActivity(intent)
        finish()
    }

    private fun validateUser(email: String, password: String) {
        if (email.isNullOrEmpty() && password.isNullOrEmpty()) {
            Toast.makeText(this, "Enter Correct Details", Toast.LENGTH_SHORT).show()
        }

    }

}