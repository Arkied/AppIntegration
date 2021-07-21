package com.galaxy.appintegration.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.galaxy.appintegration.databinding.ActivityDetailAlumnoBinding
import com.galaxy.appintegration.models.Alumno

class DetailAlumnoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailAlumnoBinding
    private var product: Alumno? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailAlumnoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        recoverProduct()
        setValueViews(product)
    }

    private fun recoverProduct() {
        product = intent.extras?.getSerializable(ALUMNO) as Alumno
    }

    private fun setValueViews(alumno: Alumno?) {
        with(binding) {
            textViewNameProduct.text = alumno?.name
            textViewNamePrice.text = alumno?.grado.toString()
        }
    }

    companion object {
        const val ALUMNO = "ALUMNO"
    }
}