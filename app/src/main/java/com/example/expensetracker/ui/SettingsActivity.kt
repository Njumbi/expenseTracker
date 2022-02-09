package com.example.expensetracker.ui


import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.expensetracker.R
import kotlinx.android.synthetic.main.activity_settings.*
import java.util.concurrent.Executor
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SettingsActivity : AppCompatActivity() {
    private lateinit var executor: Executor
    private lateinit var biometricPrompt: BiometricPrompt
    private lateinit var promptInfo: BiometricPrompt.PromptInfo
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        executor = ContextCompat.getMainExecutor(this)
        biometricPrompt = BiometricPrompt(this, executor,
            object : BiometricPrompt.AuthenticationCallback() {
                override fun onAuthenticationError(errorCode: Int,
                                                   errString: CharSequence) {
                    super.onAuthenticationError(errorCode, errString)
                    Toast.makeText(applicationContext,
                        "Authentication error: $errString", Toast.LENGTH_SHORT)
                        .show()
                }

                override fun onAuthenticationSucceeded(
                    result: BiometricPrompt.AuthenticationResult) {
                    super.onAuthenticationSucceeded(result)
                    Toast.makeText(applicationContext,
                        "Authentication succeeded!", Toast.LENGTH_SHORT)
                        .show()

                }

                override fun onAuthenticationFailed() {
                    super.onAuthenticationFailed()
                    Toast.makeText(applicationContext, "Authentication failed",
                        Toast.LENGTH_SHORT)
                        .show()
                }

            })
        promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle("Biometric login for my app")
            .setSubtitle("Log in using your biometric credential")
            .setNegativeButtonText("Use account password")
            .build()

        checkBox.setOnClickListener  {
            biometricPrompt.authenticate(promptInfo)
            val sharedPref = baseContext.getSharedPreferences("mypref", Context.MODE_PRIVATE)
            val editor = sharedPref.edit()
            editor.putBoolean("checked", checkBox.isChecked)
            editor.commit()
Log.d("------>", checkBox.isChecked.toString())
    }
}

    override fun onDestroy() {
        super.onDestroy()
        val sharedPref: SharedPreferences =
            this.getSharedPreferences("mypref", Context.MODE_PRIVATE)
        val isChecked = sharedPref.getBoolean("checked", true)
        checkBox.isChecked = isChecked
    }

    override fun onResume() {
        super.onResume()
        val sharedPref: SharedPreferences =
            this.getSharedPreferences("mypref", Context.MODE_PRIVATE)
        val isChecked = sharedPref.getBoolean("checked", true)
        checkBox.isChecked = isChecked
    }
}