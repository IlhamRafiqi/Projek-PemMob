package com.example.sejenakapps

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.sejenakapps.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupClickListeners()
    }

    private fun setupClickListeners() {
        // Back button
        binding.btnBack.setOnClickListener {
            finish()
        }

        // Register button
        binding.registerButton.setOnClickListener {
            val name = binding.nameEditText.text.toString()
            val email = binding.emailEditText.text.toString()
            val password = binding.passwordEditText.text.toString()
            val confirmPassword = binding.confirmPasswordEditText.text.toString()

            if (validateInput(name, email, password, confirmPassword)) {
                // Perform registration logic here
                val intent = Intent(this, SejenakActivity::class.java)
                startActivity(intent)
                finish()
            }
        }

        // Login link
        binding.loginTextView.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun validateInput(
        name: String,
        email: String,
        password: String,
        confirmPassword: String
    ): Boolean {
        if (name.isEmpty()) {
            binding.nameEditText.error = "Nama tidak boleh kosong"
            return false
        }

        if (email.isEmpty()) {
            binding.emailEditText.error = "Email tidak boleh kosong"
            return false
        }

        if (password.isEmpty()) {
            binding.passwordEditText.error = "Password tidak boleh kosong"
            return false
        }

        if (password.length < 6) {
            binding.passwordEditText.error = "Password minimal 6 karakter"
            return false
        }

        if (confirmPassword != password) {
            binding.confirmPasswordEditText.error = "Konfirmasi password tidak cocok"
            return false
        }

        return true
    }
}