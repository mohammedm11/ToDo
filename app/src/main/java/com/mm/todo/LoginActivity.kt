package com.mm.todo

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.mm.todo.databinding.ActivityLoginBinding

import java.util.concurrent.Executor
class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var executor: Executor
    private lateinit var biometricPrompt: androidx.biometric.BiometricPrompt
    private lateinit var promptInfo: androidx.biometric.BiometricPrompt.PromptInfo
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        executor = ContextCompat.getMainExecutor(this)
        biometricPrompt =androidx.biometric.BiometricPrompt(this@LoginActivity,executor,object:androidx.biometric.BiometricPrompt.AuthenticationCallback(){
            @SuppressLint("SetTextI18n")

            override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                super.onAuthenticationError(errorCode, errString)
                
                toastError()
            }
            @SuppressLint("SetTextI18n")
            override fun onAuthenticationSucceeded(result: androidx.biometric.BiometricPrompt.AuthenticationResult) {
                super.onAuthenticationSucceeded(result)

                intent()
            }
            @SuppressLint("SetTextI18n")
            override fun onAuthenticationFailed() {
                super.onAuthenticationFailed()
                toastFailed()
            }
        })
        promptInfo= androidx.biometric.BiometricPrompt.PromptInfo.Builder()
            .setTitle("Biometric Authentication")
            .setSubtitle("Login using fingerprint")
            .setNegativeButtonText("Cancel")
            .build()
        binding.Authbtn.setOnClickListener {
            biometricPrompt.authenticate(promptInfo)
        }
    }
    fun intent(){
        val intent = Intent(this@LoginActivity,MainActivity::class.java)
        startActivity(intent)
    }
    fun toastError(){
        Toast.makeText(this,"please LOGIN using fingerprint",Toast.LENGTH_LONG).show()
    }
    fun toastFailed(){
        Toast.makeText(this,"Authentication Failed",Toast.LENGTH_LONG).show()
    }
}