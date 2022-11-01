package org.techtown.presentation.login

import android.content.Intent
import android.widget.Toast
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.techtown.presentation.MainActivity
import org.techtown.presentation.R
import org.techtown.presentation.base.BaseActivity
import org.techtown.presentation.database.database.AppDatabase
import org.techtown.presentation.databinding.ActivityLoginBinding
import org.techtown.presentation.enum.User
import org.techtown.presentation.model.LoginModel

class LoginActivity : BaseActivity<ActivityLoginBinding>(R.layout.activity_login) {

    private lateinit var database: AppDatabase

    override fun ActivityLoginBinding.onCreate() {
        initSet()
        setListenerEvent()
    }

    private fun initSet() {
        database = AppDatabase.getInstance(applicationContext)
    }

    private fun setListenerEvent() {
        binding.btnLogin.setOnClickListener {
            if ((binding.edtId.text.toString() == User.TESTUSER.userId && binding.edtPass.text.toString() == User.TESTUSER.userPasswd)
                || (binding.edtId.text.toString() == User.TESTUSER1.userId && binding.edtPass.text.toString() == User.TESTUSER1.userPasswd)
            ) {
                CoroutineScope(Dispatchers.IO).launch {//백그라운드로 로그인 정보를 저장합니다.
                    //유저아이디, 로그인 여부를 저장합니다.
                    database.loginDao().insertLoginStatus(
                        LoginModel(
                            isLogined = true,
                            user_id = binding.edtId.text.toString()
                        )
                    )
                    runOnUiThread {
                        goMainActivity()
                    }
                }

            } else {
                Toast.makeText(this@LoginActivity, "아이디 비번을 확인해주세요", Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun goMainActivity() {
        startActivity(Intent(this@LoginActivity, MainActivity::class.java))
    }
}