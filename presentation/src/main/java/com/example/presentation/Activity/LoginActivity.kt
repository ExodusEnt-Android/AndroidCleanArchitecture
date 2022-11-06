package com.example.presentation.Activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.presentation.databinding.ActivityLoginBinding
import com.example.presentation.enum.UserMokData

class LoginActivity : AppCompatActivity() {
    private lateinit var mBinding : ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        mBinding.btnLogin.setOnClickListener {
            if(loginCheck(mBinding.etId.text.toString(), mBinding.etPwd.text.toString())){
                startActivity(Intent(this, MainActivity::class.java))
            }else{
                Toast.makeText(this, "nonono",Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, MainActivity::class.java))
            }
        }
    }

    private fun loginCheck(email : String, pwd : String) : Boolean{
        return UserMokData.UserModel.values().any{it.email == email && it.pwd == pwd}
    }
}