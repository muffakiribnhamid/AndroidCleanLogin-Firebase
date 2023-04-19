package com.muffakir.regiesterapp

import android.app.Activity
import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.muffakir.regiesterapp.databinding.ActivitySignInBinding
import com.muffakir.regiesterapp.databinding.ActivitySignUpBinding

class SignIn : AppCompatActivity() {
    private lateinit var mAuth : FirebaseAuth
    private lateinit var binding: ActivitySignInBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)



        mAuth = FirebaseAuth.getInstance()
        
        binding.btnSignIn.setOnClickListener { 
            var email = binding.etEmailLogin.text.toString()
            var password = binding.etPasswordLogin.text.toString()

            validateUser(email,password)
            
            LogIn(email,password)
        }


    }

    fun LogIn(email : String, password: String) {
        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithEmail:success")
                    val user = mAuth.currentUser
                    updateUI(FinalActivity())
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithEmail:failure", task.exception)
                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun updateUI(user: Activity) {
        var intent = Intent(this,user::class.java)
        startActivity(intent)
        finish()
        

    }
    
    private fun validateUser(email: String, password: String) {
        if (email.isNullOrEmpty() && password.isNullOrEmpty()) {
            Toast.makeText(this, "Enter Correct Details", Toast.LENGTH_SHORT).show()
        }

    }
}
