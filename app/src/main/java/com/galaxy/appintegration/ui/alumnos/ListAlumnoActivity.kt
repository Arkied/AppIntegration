package com.galaxy.appintegration.ui.alumnos

import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
//import com.galaxy.appintegration.data.AlumnoDatabase
import com.galaxy.appintegration.data.RoomRepository
import com.galaxy.appintegration.databinding.ActivityListAlumnoBinding
import com.galaxy.appintegration.gonnaToActivity
import com.galaxy.appintegration.models.Constants.PREFIX_NAME
import com.galaxy.appintegration.models.Alumno
import com.galaxy.appintegration.ui.detail.DetailAlumnoActivity


public class ListAlumnoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityListAlumnoBinding
    private lateinit var sharedPreferences: SharedPreferences
    private val sharedPreferencesEncrypted by lazy {
        EncryptedSharedPreferences.create(
            "AppIntegrationEncrypted",
            MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC),
            this,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }
    private lateinit var adapterMain: AlumnoAdapter
    private lateinit var viewModel: ListAlumnoViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListAlumnoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpSharedPreference()
        setInformationView()
        setUpViewModel()
        setUpRecyclerView()
        setUpViewModelObservers()
    }

    private fun setUpSharedPreference() {
        sharedPreferences = getSharedPreferences("AppIntegration", 0)
    }

    private fun setInformationView() {
       binding.textViewNameUser.text = sharedPreferencesEncrypted.getString(PREFIX_NAME, null)
    }

    private fun setUpRecyclerView() {
        with(binding.recyclerViewAlumno) {
            adapterMain = AlumnoAdapter()
            adapter = adapterMain
            layoutManager = LinearLayoutManager(this@ListAlumnoActivity)
        }
    }

    private fun setUpViewModel() {
        val factory = ListAlumnoViewModelFactory(RoomRepository())
        viewModel = ViewModelProviders.of(this, factory)[ListAlumnoViewModel::class.java]
    }

    private fun setUpViewModelObservers() {
        with(viewModel) {
            alumnos.observe(this@ListAlumnoActivity, {
                it?.let {
                    adapterMain.list = it
                    adapterMain.notifyDataSetChanged()
                }
            })
        }
    }

    fun passDataToDetail(alu: Alumno) {
        gonnaToActivity(DetailAlumnoActivity::class.java, DetailAlumnoActivity.ALUMNO, alu)
    }

}