package com.galaxy.appintegration.ui.alumnos

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.galaxy.appintegration.R
import com.galaxy.appintegration.databinding.ItemAlumnoBinding
import com.galaxy.appintegration.models.Alumno

class AlumnoAdapter() : RecyclerView.Adapter<AlumnoAdapter.ViewHolder>() {
    var list = emptyList<Alumno>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlumnoAdapter.ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_alumno, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: AlumnoAdapter.ViewHolder, position: Int) {
        val item = list[position]
        holder.bind(item)

    }

    override fun getItemCount() = list.size


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemAlumnoBinding.bind(view)

        fun bind(alu: Alumno) {
            with(binding) {
                textViewAlumno.text = alu.name
                textViewNota.text = alu.nota.toString()
                textViewGrado.text = alu.grado
            }
        }
    }

}