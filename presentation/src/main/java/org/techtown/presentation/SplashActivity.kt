package org.techtown.presentation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Toast
import kotlinx.coroutines.*
import org.techtown.presentation.databinding.ActivitySplashBinding
import org.techtown.presentation.model.UserModel
import org.techtown.presentation.model.UserRootModel
import org.techtown.presentation.retorfit.RetrofitBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.collections.ArrayList

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    //3초 카운트다운 세기위해서 추가.
    private var count = 3
    private var countDownTimer:CountDownTimer? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //처음 들어갔을때 유저 목록 가져오기.
        getFirstUserInfo()
    }

    override fun onDestroy() {
        super.onDestroy()
        //액티비티 없어지면 타이머도 취소 및 없애줌.
        try{
            countDownTimer!!.cancel()
        }catch (e: Exception){
            e.printStackTrace()
        }

        countDownTimer = null

    }

    private fun countDownTimer(userList: ArrayList<UserModel>) {
        countDownTimer = object : CountDownTimer(3000L, 1000L){
            override fun onTick(p0: Long) {
                binding.tvCount.text = "$count"
                count --
                if(count == 0){ //0일될떄는 3초가 다 지났으므로 다음화면으로 넘어가줍니다.
                    val intent = Intent(this@SplashActivity, MainActivity::class.java)
                    intent.putExtra("user_list", userList)
                    startActivity(intent)
                }
            }

            override fun onFinish() {
                binding.tvCount.text = "카운트종료"
            }

        }
    }

    private fun getFirstUserInfo() {
        RetrofitBuilder.api.getUserInfo("hello").enqueue(object : Callback<UserRootModel> {
            override fun onResponse(call: Call<UserRootModel>, response: Response<UserRootModel>) {
                if(response.isSuccessful){
                    if(response.code() == 200){

                        val userList = response.body()!!.items

                        countDownTimer(userList)
                        countDownTimer!!.start()

                        Toast.makeText(this@SplashActivity, "데이터를 불러오는데 성공하였습니다." , Toast.LENGTH_LONG).show()


                    }
                }
            }

            override fun onFailure(call: Call<UserRootModel>, t: Throwable) {
                Toast.makeText(this@SplashActivity, "데이터 불러오기 실패", Toast.LENGTH_LONG).show()
            }
        })
    }

    fun getCurrentTime(): String? {
        val time = System.currentTimeMillis()
        val dayTime = SimpleDateFormat("yyyy-mm-dd hh:mm:ss")
        return dayTime.format(Date(time))
    }
}