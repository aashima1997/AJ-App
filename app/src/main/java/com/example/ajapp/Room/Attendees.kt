package com.example.ajapp.Room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.aj.DataRepository.dateConverter
import java.util.*
@Entity(tableName="table_Register")
data class Attendees (

    @PrimaryKey(autoGenerate = true)
    val id:Int,
    @ColumnInfo(name="Name") val Name:String,
    @ColumnInfo(name="Present") val Present:String,
   @TypeConverters(dateConverter::class) val remember: Date

    )
