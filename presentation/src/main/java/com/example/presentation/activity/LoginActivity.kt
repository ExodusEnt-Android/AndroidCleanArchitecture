package com.example.presentation.activity

import android.content.Intent
import com.example.presentation.R
import com.example.presentation.base.BaseActivity
import com.example.presentation.const.Const.PARAM_IS_LOGIN_ID
import com.example.presentation.const.Const.PARAM_IS_LOGIN_SUCCESS
import com.example.presentation.databinding.ActivityLoginBinding
import com.example.presentation.enum.MockUser
import com.example.presentation.util.PreferenceManager

class LoginActivity:BaseActivity<ActivityLoginBinding>(R.layout.activity_login) {

    override fun ActivityLoginBinding.onCreate() {
        setListenerEvent()
    }


    //리스너 이벤트 모음
    private fun setListenerEvent(){

        //로그인 버튼 클릭시
        binding.btnLogin.setOnClickListener {
            if(isLoginInfoValid()){//로그인 정보가 모두 맞는 경우
                goToMainScreen()
                finish()
            }else{
                showToast("로그인 정보가 틀렸습니다.")
            }
        }
    }

    //로그인 정보 유효한지 체크
    private fun isLoginInfoValid():Boolean{
        val writtenId = binding.editLoginId.text.toString()
        val writtenPwd = binding.editLoginPwd.text.toString()
        return MockUser.values().any { it.userID == writtenId && it.password == writtenPwd }
    }

    //메인으로 가기
    private fun goToMainScreen(){

        //로그인 성공했다는 정보 저장
        PreferenceManager.setPreference(this,PARAM_IS_LOGIN_SUCCESS,true)
        PreferenceManager.setPreference(this,PARAM_IS_LOGIN_ID,binding.editLoginId.text.toString())
        startActivity(Intent(this, MainActivity::class.java))
    }
}