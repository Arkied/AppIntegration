package com.galaxy.appintegration.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "Alumno")
data class Alumno(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "Id")
    val id: Int?,
    @ColumnInfo(name = "Name")
    val name: String,
    @ColumnInfo(name = "Nota")
    val nota: Int,
    @ColumnInfo(name = "Grado")
    val grado: String,
    @ColumnInfo(name = "IsFree")
    val isFree: Boolean?
) : Serializable