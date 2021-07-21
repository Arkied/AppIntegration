package com.galaxy.appintegration.ui.login

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import com.galaxy.appintegration.databinding.ActivityMainBinding
import com.galaxy.appintegration.gonnaToActivity
import com.galaxy.appintegration.showMessage
import com.galaxy.appintegration.models.Constants.PREFIX_LASTNAME
import com.galaxy.appintegration.models.Constants.PREFIX_NAME
import com.galaxy.appintegration.models.Constants.SESSION
import com.galaxy.appintegration.models.User
import com.galaxy.appintegration.ui.alumnos.ListAlumnoActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var sharedPreference: SharedPreferences
    private val sharedPreferencesEncrypted by lazy {
        EncryptedSharedPreferences.create(
            "AppIntegrationEncrypted",
            MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC),
            this,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sharedPreference = getSharedPreferences("AppIntegration", 0)
        validateIfExistSession()
    }

    override fun onResume() {
        super.onResume()
        events()
    }

    private fun validateIfExistSession() {
        val session = sharedPreferencesEncrypted.getBoolean(SESSION, false)
        if (session) processIntent()
    }

    private fun events() {
        with(binding) {
            textViewForgotPassword.setOnClickListener {

            }
            buttonLogin.setOnClickListener {
                processLogin()
            }
        }
    }

    private fun processLogin() {
        with(binding) {
            val user = textInputEditTextEmail.text.toString()
            val pass = textInputEditTextPassword.text.toString()

            if (validateTextFieldIfEmpty(user, pass)) {
                showMessage("Por favor ingrese su email o contraseña!")
            } else if (validateIfUserIsCorrect(user, pass)) {
                processInformationEncrypted()
                processIntent()
            } else {
                showMessage("El usuario y contraseña son incorrectos!")
            }
        }
    }

    private fun processInformation() {
        val user = User("edmundo@gmail.com", "Edmundo", "Prado")
        sharedPreference.edit().apply {
            putBoolean(SESSION, true)
            putString(PREFIX_NAME, user.name)
            putString(PREFIX_LASTNAME, user.lastName)
        }.commit()
    }

    private fun processInformationEncrypted() {
        val user = User("edmundo@gmail.com", "Edmundo", "Prado")
        sharedPreferencesEncrypted.edit().apply {
            putBoolean(SESSION, true)
            putString(PREFIX_NAME, user.name)
            putString(PREFIX_LASTNAME, user.lastName)
        }.apply()
    }

    private fun processIntent() {
        gonnaToActivity(ListAlumnoActivity::class.java)
    }

    private fun validateIfUserIsCorrect(user: String, password: String): Boolean {
        return user.equals("edmundo") && password.equals("123")
    }

    private fun validateTextFieldIfEmpty(user: String, password: String): Boolean {
        if (user.isEmpty()) {
            return true
        } else if (password.isEmpty()) {
            return true
        }
        return false
    }
}