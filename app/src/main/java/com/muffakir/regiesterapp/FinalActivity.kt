package com.muffakir.regiesterapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.muffakir.regiesterapp.databinding.ActivityFinalBinding

class FinalActivity : AppCompatActivity() {
    private lateinit var mAuth : FirebaseAuth
    private lateinit var binding : ActivityFinalBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFinalBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mAuth = FirebaseAuth.getInstance()

        binding.root.setOnClickListener{
            mAuth.signOut()
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()

        }
    }
}