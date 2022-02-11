package org.techtown.presentation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import org.techtown.presentation.adapter.UserListAdapter
import org.techtown.presentation.model.UserRootModel
import org.techtown.presentation.retorfit.RetrofitBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        getFirstUserInfo()
    }

    private fun getFirstUserInfo() {
        RetrofitBuilder.api.getUserInfo("hello").enqueue(object : Callback<UserRootModel> {
            override fun onResponse(call: Call<UserRootModel>, response: Response<UserRootModel>) {
                if(response.isSuccessful){
                    if(response.code() == 200){

                        val userList = response.body()!!.items

                        val intent = Intent(this@SplashActivity, MainActivity::class.java)
                        intent.putExtra("user_list", userList)
                        startActivity(intent)

                        Toast.makeText(this@SplashActivity, "데이터를 불러오는데 성공하였습니다." , Toast.LENGTH_LONG).show()


                    }
                }
            }

            override fun onFailure(call: Call<UserRootModel>, t: Throwable) {
                Toast.makeText(this@SplashActivity, "데이터 불러오기 실패", Toast.LENGTH_LONG).show()
            }
        })
    }
}