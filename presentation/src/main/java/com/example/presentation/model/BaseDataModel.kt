package com.example.presentation.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class BaseDataModel<T>(
  val status: String,
  val totalResults: Int = 0,
  val articles: @RawValue List<T>? = null
) : Parcelable
