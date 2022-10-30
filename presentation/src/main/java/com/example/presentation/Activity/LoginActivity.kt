package com.example.presentation.Activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.presentation.Room.UserDB
import com.example.presentation.Room.UserModel
import com.example.presentation.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var mBinding : ActivityLoginBinding

    var userModel:UserModel?= null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        userModel = UserDB.getInstance(this)?.userDao()?.getChatMember("mingue0605@naver.com")
        mBinding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        mBinding.btnLogin.setOnClickListener {
            loginCheck(mBinding.etId.toString()){ userModel ->
                if(userModel.password == mBinding.etPwd.toString()){
                    Log.d("mingue login id not null --> ", userModel.toString())
                    startActivity(Intent(this, MainActivity::class.java))
                }
                else{
                    Log.d("mingue login id null --> ", userModel.toString())
                    startActivity(Intent(this, MainActivity::class.java))
                }
            }
        }

    }
    private fun loginCheck(email : String, callback : ((UserModel) -> Unit)?){
        val r = Runnable {
            val userModel = UserDB.getInstance(this)?.userDao()?.getChatMember(email)
            if (userModel != null) {
                Log.d("mingue -> ", userModel.toString())
                callback?.invoke(userModel)
            }
            else{
                Log.d("mingue --> ", userModel.toString())
                callback?.invoke(UserModel("mingue0605","asdasdasd"))
            }
        }
        val thread = Thread(r)
        thread.start()
    }

//    private fun loginCheck(email : String, callback : ((UserModel) -> Unit)?){
//        val r = Runnable {
//
//
//            if (userModel != null) {
//                Log.d("mingue userModel not null -> ", userModel.toString())
//                callback?.invoke(userModel!!)
//            }
//            else{
//                Log.d("mingue userModel null--> ", userModel.toString())
//
//                runOnUiThread {
//                    Toast.makeText(this,"문제",Toast.LENGTH_SHORT).show()
//                }
//            }
//        }
//        val thread = Thread(r)
//        thread.start()
//    }

}