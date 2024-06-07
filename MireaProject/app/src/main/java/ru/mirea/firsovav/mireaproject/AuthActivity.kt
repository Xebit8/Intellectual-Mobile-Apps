package ru.mirea.firsovav.mireaproject

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import ru.mirea.firsovav.mireaproject.databinding.ActivityAuthBinding
import java.lang.String.valueOf

class AuthActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAuthBinding
    private lateinit var mAuth: FirebaseAuth

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mAuth = FirebaseAuth.getInstance()

        binding.createAccount.setOnClickListener {
            val email: String = valueOf(binding.email.text)
            val password: String = valueOf(binding.password.text)
            createAccount(email, password)
        }

        binding.signedInButtons.setOnClickListener {
            val email: String = valueOf(binding.email.text)
            val password: String = valueOf(binding.password.text)
            signIn(email, password)
        }
    }

    private fun updateUI(user: FirebaseUser?) {
        if (user != null) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        } else {
            binding.createAccount.visibility = View.VISIBLE
            binding.email.visibility = View.VISIBLE
            binding.password.visibility = View.VISIBLE
            binding.signedInButtons.visibility = View.VISIBLE
        }
    }

    private fun createAccount(email: String, password: String) {
        Log.d(TAG, "createAccount:$email")
        if (email.isEmpty() or password.isEmpty()) {
            Toast.makeText(
                this, "Email and/or password fields are not filled in",
                Toast.LENGTH_SHORT
            ).show()
            return
        }
        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = mAuth.currentUser
                    Toast.makeText(
                        this, "Signed up successfully",
                        Toast.LENGTH_SHORT
                    ).show()
                    updateUI(user)
                } else {
                    Toast.makeText(
                        this, "Sign up failed",
                        Toast.LENGTH_SHORT
                    ).show()
                    updateUI(null)
                }
            }
    }

    private fun signIn(email: String, password: String) {
        Log.d(TAG, "signIn:$email")
        if (email.isEmpty() or password.isEmpty()) {
            Toast.makeText(
                this, "Email and/or password fields are not filled in",
                Toast.LENGTH_SHORT
            ).show()
            return
        }
        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = mAuth.currentUser
                    Toast.makeText(
                        this, "Signed in successfully",
                        Toast.LENGTH_SHORT
                    ).show()
                    updateUI(user)
                } else {
                    Toast.makeText(
                        this, "Sign in failed",
                        Toast.LENGTH_SHORT
                    ).show()
                    updateUI(null)
                }
            }
    }
}
