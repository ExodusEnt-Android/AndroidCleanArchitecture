package org.techtown.presentation.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import org.techtown.presentation.databinding.ActivityUserDetailBinding
import org.techtown.presentation.model.UserModel

class UserDetailActivity : AppCompatActivity() {

    //인텐트로 가져온 유저 모델.
    private lateinit var user: UserModel

    //뷰바인딩.
    private lateinit var bindnig : ActivityUserDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindnig = ActivityUserDetailBinding.inflate(layoutInflater)
        setContentView(bindnig.root)

        initSet()
    }

    private fun initSet() {
        user = intent.getParcelableExtra<UserModel>("user_model") as UserModel


        //이름 , 스코어만 가져와줌.
        bindnig.tvUserName.text = user.login
        bindnig.tvScore.text = user.score.toString()

        //유저 아바타사진.
        Glide.with(this).load(user.avatar_url).into(bindnig.ivUserAvatar)
    }
}