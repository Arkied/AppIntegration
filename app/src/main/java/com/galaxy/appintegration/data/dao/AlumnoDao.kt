package com.galaxy.appintegration.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.galaxy.appintegration.models.Alumno

@Dao
interface AlumnoDao {

    @Query("SELECT * FROM ALUMNO")
    fun getALlAlumno(): LiveData<List<Alumno>>

    @Query("DELETE FROM ALUMNO WHERE Id =:idAlumno")
    fun delete(idAlumno: Int?)

    @Insert
    fun registerProduct(alumno: Alumno)

    @Delete
    fun removeProduct(alumno: Alumno)
}
