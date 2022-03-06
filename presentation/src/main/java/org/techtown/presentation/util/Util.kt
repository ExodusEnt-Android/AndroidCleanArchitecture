package org.techtown.presentation.util

import android.app.ProgressDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import org.techtown.presentation.R

object Util {

    private var progressDialog: ProgressDialog? = null

    //프로그래스 보여줌.
    fun showProgress(context: Context) {
        if (progressDialog != null && progressDialog!!.isShowing) {
            return
        }
        progressDialog = ProgressDialog.show(context, null, null, true, false)
        progressDialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        progressDialog?.setContentView(R.layout.progress_layout)
    }

    //프로그래스 닫아줌.
    fun closeProgress() {
        if (progressDialog != null && progressDialog!!.isShowing) {
            progressDialog!!.dismiss()
        }
    }

}