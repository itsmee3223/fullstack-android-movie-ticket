package com.ramanda.ticketmovie.sign.signup

import android.app.ProgressDialog
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.karumi.dexter.Dexter
import com.ramanda.ticketmovie.R
import com.ramanda.ticketmovie.databinding.ActivitySignPhotoScreenBinding
import com.ramanda.ticketmovie.sign.User
import com.ramanda.ticketmovie.utils.Preferences
import com.ramanda.ticketmovie.HomeScreenActivity
import java.util.*

class SignUpPhotoscreenActivity : AppCompatActivity() {
    val REQUEST_IMAGE_CAPTURE = 1
    private var statusAdd:Boolean = false
    private lateinit var filePath:Uri
    private lateinit var user: User

    private lateinit var storage: FirebaseStorage
    private lateinit var storageReference: StorageReference
    private lateinit var preferences: Preferences

    private lateinit var mFirebaseDatabase: DatabaseReference
    private lateinit var mFirebaseInstance: FirebaseDatabase

    private lateinit var binding: ActivitySignPhotoScreenBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignPhotoScreenBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        preferences = Preferences(this)
        storage = FirebaseStorage.getInstance()
        storageReference = storage.getReference("")

        mFirebaseInstance = FirebaseDatabase.getInstance()
        mFirebaseDatabase = mFirebaseInstance.getReference("User")

        val user = intent.getParcelableExtra<User>("nama")
        binding.tvWelcome.text = "Selamat datang \n" + user.nama

        binding.ivAdd.setOnClickListener {
            if(statusAdd) {
                statusAdd = false
                binding.btnSave.visibility = View.VISIBLE
                binding.ivAdd.setImageResource(R.drawable.ic_add_circle)
                binding.ivProfile.setImageResource(R.drawable.user_pic)
            } else {
                Dexter.withActivity(this)
                    .withPermission(android.Manifest.permission.CAMERA)
                    .withListener(this)
                    .check()

                ImagePicker.with(this)
                    .cameraOnly()
                    .start()

            }
        }

        binding.btnHome.setOnClickListener {
            finishAffinity()
            startActivity(Intent(this@SignUpPhotoscreenActivity, HomeScreenActivity::class.java))
        }

        binding.btnSave.setOnClickListener {
            val progresDialog = ProgressDialog(this)
            progresDialog.setTitle("Uploading.....")
            progresDialog.show()

            val ref = storageReference.child("images/" + UUID.randomUUID().toString())
            ref.putFile(filePath)
                .addOnSuccessListener {
                    progresDialog.dismiss()
                    Toast.makeText(this@SignUpPhotoscreenActivity, "Uploaded", Toast.LENGTH_LONG).show()
                    ref.downloadUrl.addOnSuccessListener {
                        saveToFirebase(it.toString())
                    }
                }
                .addOnFailureListener { e ->
                    progresDialog.dismiss()
                    Toast.makeText(this@SignUpPhotoscreenActivity, "Failed" + e.message, Toast.LENGTH_LONG).show()
                }
                .addOnProgressListener { taskSnapshot ->
                    val progress = 100.0 * taskSnapshot.bytesTransferred / taskSnapshot.totalByteCount
                    progresDialog.setMessage("Uploaded" + progress.toInt() + " %")
                }
        }

    }

    private fun saveToFirebase(url: String) {
        mFirebaseDatabase.child(user.username!!).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dBase: DataSnapshot) {
                user.url = url
                mFirebaseDatabase.child(user.username!!).setValue(user)

                preferences.setValues("nama", user.nama.toString())
                preferences.setValues("user", user.username.toString())
                preferences.setValues("saldo", "")
                preferences.setValues("url", url)
                preferences.setValues("email", user.email.toString())
                preferences.setValues("status", "1")

                finishAffinity()
                startActivity(Intent(this@SignUpPhotoscreenActivity, HomeScreenActivity::class.java))
            }

            override fun onCancelled(err: DatabaseError) {
                Toast.makeText(this@SignUpPhotoscreenActivity, err.message, Toast.LENGTH_LONG).show()
            }

        })
    }
}