package com.galaxy.appintegration.data

import com.galaxy.appintegration.models.Alumno

class RoomRepository {
    fun returnList(): List<Alumno> {
        val listAlumno = arrayListOf<Alumno>()
        listAlumno.add(Alumno(1, "Miguel", 14,"4 Grado",false))
        listAlumno.add(Alumno(2, "Angel", 15,"5 Grado",false))
        listAlumno.add(Alumno(3, "Polo", 13,"3 Grado",false))
        listAlumno.add(Alumno(4, "Maria", 16,"5 Grado",false))
        listAlumno.add(Alumno(5, "Luis", 12,"2 Grado",false))
        listAlumno.add(Alumno(6, "Mathias", 14,"4 Grado",false))
        listAlumno.add(Alumno(7, "Andre", 15,"4 Grado",false))
        listAlumno.add(Alumno(8, "Ana", 17,"5 Grado",false))
        listAlumno.add(Alumno(9, "Julia", 15,"5 Grado",false))
        listAlumno.add(Alumno(10, "Anastasio", 16,"5 Grado",false))
        return listAlumno
    }
}