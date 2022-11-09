package com.example.presentation.Activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.presentation.Base.BaseActivity
import com.example.presentation.LoginData
import com.example.presentation.Room.AppDB
import com.example.presentation.databinding.ActivityLoginBinding
import com.example.presentation.enum.UserMokData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginActivity : BaseActivity() {
    private lateinit var mBinding : ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        mBinding.btnLogin.setOnClickListener {
            if(loginCheck(mBinding.etId.text.toString(), mBinding.etPwd.text.toString())){
                CoroutineScope(Dispatchers.IO).launch {
                    val loginData = LoginData(mBinding.etId.text.toString(), mBinding.etPwd.text.toString())
                    AppDB.getInstance(this@LoginActivity)?.loginDao()?.insert(loginData)
                    startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                }
            }else{
                Toast.makeText(this, "nonono",Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun loginCheck(email : String, pwd : String) : Boolean{
        return UserMokData.UserModel.values().any{it.email == email && it.pwd == pwd}
    }
}