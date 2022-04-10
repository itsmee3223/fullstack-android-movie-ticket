package com.ramanda.ticketmovie

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.FirebaseDatabase
import com.ramanda.ticketmovie.databinding.ActivitySignInBinding


class SignInActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignInBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.btnHome.setOnClickListener {
            // Write a message to the database
            // Write a message to the database
            val database = FirebaseDatabase.getInstance("https://ticket-movie-9ccff-default-rtdb.firebaseio.com/")
            val myRef = database.getReference("message")

            myRef.setValue("Hello, World!")
        }
    }
}
