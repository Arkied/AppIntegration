package com.galaxy.appintegration.ui.alumnos

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.galaxy.appintegration.data.RoomRepository

class ListAlumnoViewModelFactory(private val repository: RoomRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ListAlumnoViewModel(repository) as T
    }
}