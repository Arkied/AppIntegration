package com.galaxy.appintegration.ui.alumnos

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.galaxy.appintegration.data.RoomRepository
import com.galaxy.appintegration.models.Alumno
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ListAlumnoViewModel(private val repository: RoomRepository) : ViewModel() {
    private var _alumno = MutableLiveData<List<Alumno>>()
    val alumnos: LiveData<List<Alumno>> get() = _alumno

    init{
        _alumno.value = repository.returnList()
    }

}