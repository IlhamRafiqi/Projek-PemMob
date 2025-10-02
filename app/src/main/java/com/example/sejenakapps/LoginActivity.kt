package com.example.sejenakapps

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.sejenakapps.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupClickListeners()
        setupTabSelection()
    }

    private fun setupTabSelection() {
        // Set initial state
        selectLoginTab()

        binding.tvLoginTab.setOnClickListener {
            selectLoginTab()
        }

        binding.tvSignUpTab.setOnClickListener {
            selectSignUpTab()
        }
    }

    private fun selectLoginTab() {
        binding.
        tvLoginTab.setBackgroundResource(R.drawable.bg_toggle_selected)
        binding.tvLoginTab.setTextColor(getColor(android.R.color.white))

        binding.tvSignUpTab.setBackgroundResource(R.drawable.bg_toggle_unselected)
        binding.tvSignUpTab.setTextColor(getColor(R.color.primary_blue))

        // Update UI for login
        binding.btnLogin.text = "Log In"
        // Hide confirm password if showing
    }

    private fun selectSignUpTab() {
        binding.tvSignUpTab.setBackgroundResource(R.drawable.bg_toggle_selected)
        binding.tvSignUpTab.setTextColor(getColor(android.R.color.white))

        binding.tvLoginTab.setBackgroundResource(R.drawable.bg_toggle_unselected)
        binding.tvLoginTab.setTextColor(getColor(R.color.primary_blue))

        // Update UI for sign up
        binding.btnLogin.text = "Sign Up"
        // Show confirm password field if needed
    }

    private fun setupClickListeners() {
        // Login/Sign Up button
        binding.btnLogin.setOnClickListener {
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()

            if (validateInput(email, password)) {
                if (binding.tvLoginTab.background.constantState ==
                    resources.getDrawable(R.drawable.bg_toggle_selected, null).constantState) {
                    // Login logic
                    performLogin(email, password)
                } else {
                    // Sign up logic
                    performSignUp(email, password)
                }
            }
        }

        // Forgot password
        binding.tvForgotPassword.setOnClickListener {
            // Navigate to forgot password screen
            val intent = Intent(this, ForgotPasswordActivity::class.java)
            startActivity(intent)
        }

        // Google login
        binding.btnGoogle.setOnClickListener {
            // Implement Google login
            performGoogleLogin()
        }

        // Facebook login
        binding.btnFacebook.setOnClickListener {
            // Implement Facebook login
            performFacebookLogin()
        }
    }

    private fun validateInput(email: String, password: String): Boolean {
        if (email.isEmpty()) {
            binding.etEmail.error = "Email tidak boleh kosong"
            return false
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.etEmail.error = "Format email tidak valid"
            return false
        }

        if (password.isEmpty()) {
            binding.etPassword.error = "Password tidak boleh kosong"
            return false
        }

        if (password.length < 6) {
            binding.etPassword.error = "Password minimal 6 karakter"
            return false
        }

        return true
    }

    private fun performLogin(email: String, password: String) {
        // Implement your login logic here
        val intent = Intent(this, SejenakActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun performSignUp(email: String, password: String) {
        // Implement your sign up logic here
        val intent = Intent(this, SejenakActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun performGoogleLogin() {
        // Implement Google login
    }

    private fun performFacebookLogin() {
        // Implement Facebook login
    }
}