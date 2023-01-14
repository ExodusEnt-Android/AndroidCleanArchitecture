package org.techtown.presentation.ext

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import org.techtown.androidcleanarchitecturecoroutine.R

fun NavController.navigateWithAnim(destinationId: Int, bundle: Bundle){
    val options = NavOptions.Builder()
        .setEnterAnim(R.anim.slide_from_right)
        .setExitAnim(R.anim.stationary)
        .setPopEnterAnim(R.anim.stationary)
        .setPopExitAnim(R.anim.slide_to_right)
        .build()
    this.navigate(destinationId,bundle,options)
}