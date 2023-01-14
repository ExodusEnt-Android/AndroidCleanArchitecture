package org.techtown.presentation.feature.login

import android.content.Intent
import android.widget.Toast
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.techtown.androidcleanarchitecturecoroutine.R
import org.techtown.androidcleanarchitecturecoroutine.databinding.ActivityLoginBinding
import org.techtown.presentation.feature.main.MainActivity
import org.techtown.presentation.base.BaseActivity
import org.techtown.presentation.feature.splash.SplashActivity

class LoginActivity : BaseActivity<ActivityLoginBinding>(R.layout.activity_login) {

    override fun ActivityLoginBinding.onCreate() {
        setListenerEvent()
    }

    private fun setListenerEvent() {

        binding.btnLogin.setOnClickListener {

            if (binding.edtId.text.toString() == MEMBER1 && binding.edtPass.text.toString() == SplashActivity.prefs.getString(
                    MEMBER1,
                    ""
                )
                || binding.edtId.text.toString() == MEMBER2 && binding.edtPass.text.toString() == SplashActivity.prefs.getString(
                    MEMBER2,
                    ""
                )
            ) {
                CoroutineScope(Dispatchers.IO).launch {
                    //로그인 여부 저장 및 아이디 저장해줍니다.
                    SplashActivity.prefs.setBoolean(LOGIN_STATUS, true)
                    SplashActivity.prefs.setString(LOGIN_ID, binding.edtId.text.toString())

                    runOnUiThread {
                        goMainActivity()
                    }
                }
            } else {
                Toast.makeText(this, "아이디와 비밀번호를 확인해주세요", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun goMainActivity() {
        startActivity(Intent(this@LoginActivity, MainActivity::class.java))
    }


    companion object {
        const val MEMBER1 = "member1"
        const val MEMBER2 = "member2"
        const val LOGIN_STATUS = "login_status"
        const val LOGIN_ID = "login_id"
    }


}