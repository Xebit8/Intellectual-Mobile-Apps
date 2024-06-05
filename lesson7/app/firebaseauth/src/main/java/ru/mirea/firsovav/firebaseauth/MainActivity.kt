package ru.mirea.firsovav.firebaseauth

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.ComponentActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import ru.mirea.firsovav.firebaseauth.databinding.ActivityMainBinding
import java.util.Objects


class MainActivity : ComponentActivity() {

    private val TAG = MainActivity::class.simpleName
    private lateinit var binding: ActivityMainBinding
    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mAuth = FirebaseAuth.getInstance()
    }

    override fun onStart() {
        super.onStart()
        val currentUser = mAuth.currentUser
        if (currentUser != null) {
            updateUI(currentUser)
        }
    }

    private fun updateUI(user: FirebaseUser?) {

        if (user != null) {
            binding.statusTextView.text = getString(R.string.emailpassword_status_fmt, user.email, user.isEmailVerified)
        }

        if (user != null) {
            binding.detailTextView.text = getString(R.string.firebase_status_fmt, user.uid)
        }

        binding.emailPasswordButtons.visibility = View.GONE
        binding.emailPasswordFields.visibility = View.GONE
        binding.signedInButtons.visibility = View.VISIBLE
        if (user != null) {
            binding.verifyEmailButton.isEnabled = !user.isEmailVerified
        }
    }

    private fun createAccount(email: String, password: String) {
        Log.d(TAG, "createAccount:$email");
        if (binding.emaiField.text.isEmpty() || binding.passwordField.text.isEmpty()) {
            return
        }

        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) {
                if (it.isSuccessful) {
                    Log.d(TAG, "createUserWithEmail:success");
                    val user = mAuth.currentUser;
                    if (user != null) {
                        updateUI(user)
                    }
                } else {
                    Log.w(
                        TAG, "createUserWithEmail:failure",
                        it.exception
                    )
                    Toast.makeText(MainActivity(), "Authentication failed.", Toast.LENGTH_SHORT)
                        .show();
                    updateUI(null);
                }
            }
    }

    private fun signIn(email: String, password: String) {
        Log.d(TAG, "signIn:$email")
        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) {
                if (it.isSuccessful) {
                    Log.d(TAG, "signInWithEmail:success");
                    val user = mAuth.currentUser;
                    updateUI(user);
                } else {
                    Log.w(
                        TAG, "signInWithEmail:failure", it.exception
                    )
                    Toast.makeText(MainActivity(), "Authentication failed .", Toast.LENGTH_SHORT).show()
                    updateUI(null);
                }
                if (!it.isSuccessful) {
                    binding.statusTextView.setText(R.string.auth_failed);
                }
            }
    }

    private fun signOut() {
        mAuth.signOut()
        updateUI(null)
    }

    private fun sendEmailVerification() {
        binding.verifyEmailButton.isEnabled = false;
        val user = mAuth.currentUser;
        Objects.requireNonNull(user)?.sendEmailVerification()
            ?.addOnCompleteListener(this) {
                binding.verifyEmailButton.isEnabled = true;

                if (it.isSuccessful) {
                    if (user != null) {
                        Toast.makeText(MainActivity(),"Verification email sent to " + user.email, Toast.LENGTH_SHORT).show()
                    }
                } else {

                    Log.e(TAG, "sendEmailVerification", it.exception)
                    Toast.makeText(MainActivity(), "Failed to send verification email.", Toast.LENGTH_SHORT).show()

                }
            }
    }
}