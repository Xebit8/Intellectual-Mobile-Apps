package ru.mirea.firsovav.firebaseauth

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.ComponentActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import ru.mirea.firsovav.firebaseauth.databinding.ActivityMainBinding
import java.lang.String.valueOf
import java.util.Objects


class MainActivity : ComponentActivity() {

    private val TAG = MainActivity::class.simpleName
    private lateinit var binding: ActivityMainBinding
    private lateinit var mAuth: FirebaseAuth
    private var isVerified = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mAuth = FirebaseAuth.getInstance()
        updateUI(null)

        binding.createAccount.setOnClickListener {
            val email: String = valueOf(binding.email.text)
            val password: String = valueOf(binding.password.text)
            createAccount(email, password)
        }

        binding.signIn.setOnClickListener {
            val email: String = valueOf(binding.email.text)
            val password: String = valueOf(binding.password.text)
            signIn(email, password)
        }
        binding.signOut.setOnClickListener {
            signOut()
        }

        binding.verifyEmail.setOnClickListener {
            sendEmailVerification()
        }
    }

    private fun updateUI(user: FirebaseUser?) {
        if (user != null) {
            binding.emailAndPassword.visibility = View.VISIBLE
            binding.statusTextView.visibility = View.GONE

            binding.email.visibility = View.GONE
            binding.password.visibility = View.GONE

            binding.signedOutButtons.visibility = View.GONE

            binding.signedInButtons.visibility = View.VISIBLE
        } else {
            binding.emailAndPassword.visibility = View.GONE
            binding.statusTextView.visibility = View.VISIBLE

            binding.email.visibility = View.VISIBLE
            binding.password.visibility = View.VISIBLE

            binding.signedOutButtons.visibility = View.VISIBLE

            binding.signedInButtons.visibility = View.GONE

        }
    }

    private fun createAccount(email: String, password: String) {
        Log.d(ContentValues.TAG, "createAccount:$email")
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
                    binding.emailAndPassword.text =
                        "Email User: ${email} (verified: $isVerified)\nFirebase UID: ${mAuth.uid}"
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
        Log.d(ContentValues.TAG, "signIn:$email")
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
                    binding.emailAndPassword.text =
                        "Email User: ${email} (verified: $isVerified)\nFirebase UID: ${mAuth.uid}"
                } else {
                    Toast.makeText(
                        this, "Sign in failed",
                        Toast.LENGTH_SHORT
                    ).show()
                    updateUI(null)
                }
            }
    }

    private fun signOut() {
        mAuth.signOut()
        updateUI(null)
    }

    private fun sendEmailVerification() {
        binding.verifyEmail.isEnabled = false
        val user = mAuth.currentUser
        if (user != null) {
            Objects.requireNonNull(user).sendEmailVerification()
                .addOnCompleteListener {
                    binding.verifyEmail.isEnabled = true

                    if (it.isSuccessful) {
                        Toast.makeText(
                            this,
                            "Verification email sent to " + user.email,
                            Toast.LENGTH_SHORT
                        ).show()

                    } else {
                        Log.e(TAG, "sendEmailVerification", it.exception)
                        Toast.makeText(
                            this,
                            "Failed to send verification email.",
                            Toast.LENGTH_SHORT
                        ).show()

                    }
                }
        }
    }

}