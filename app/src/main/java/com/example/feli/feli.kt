package com.example.feli

import android.graphics.drawable.Drawable
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Persona(var Nombre:String, var Edad:String, var curso:String,var descripcion:String, var url: String):Parcelable