package com.example.ajapp.Room

import android.graphics.Bitmap
import androidx.room.*
import java.util.*

@Entity(tableName="tableLogin")
data class User(

        @PrimaryKey(autoGenerate = true)
        val id:Int,
@ColumnInfo (name="Username") val Username:String,
        @ColumnInfo (name="Password") val Password:String,

        )
