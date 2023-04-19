package com.muffakir.regiesterapp

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.view.ContentInfoCompat.Flags
import com.google.firebase.auth.FirebaseAuth
import com.muffakir.regiesterapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var mAuth  : FirebaseAuth
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mAuth = FirebaseAuth.getInstance()




        binding.btnSignIn.setOnClickListener {
            val intent = Intent(this@MainActivity,SignIn::class.java)
            startActivity(intent)
        }

        binding.btnSignUp.setOnClickListener {
            val intent = Intent(this@MainActivity,SignUp::class.java)
            startActivity(intent)
        }
    }

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = mAuth.currentUser
        if(currentUser != null){
            val intent = Intent(this,FinalActivity::class.java)
            startActivity(intent)
            finish()

        }
    }



}