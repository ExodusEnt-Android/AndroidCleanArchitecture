package com.example.presentation.Activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.example.presentation.PreferenceUtil
import com.example.presentation.R
import com.example.presentation.databinding.ActivityLoginBinding

class LoginActivity : BaseActivity<ActivityLoginBinding>(R.layout.activity_login) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding.btnLogin.setOnClickListener {
            if(!PreferenceUtil.getPreference(this, mBinding.etId.text.toString()).isNullOrEmpty()){
                if(PreferenceUtil.getPreference(this, mBinding.etId.text.toString()) == mBinding.etPwd.text.toString()){
                    PreferenceUtil.setPreference(this, "Login", true)
                    startActivity(Intent(this, MainActivity::class.java))
                }
            }else{
                Toast.makeText(this, "아이디 혹은 비밀번호를 잘못 입력하였습니다. 다시 한번 확인 해주세요", Toast.LENGTH_SHORT).show()
            }
        }
    }
}