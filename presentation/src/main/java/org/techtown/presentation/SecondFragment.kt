package org.techtown.presentation

import android.os.Bundle
import android.util.Log
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import org.techtown.presentation.base.BaseFragment
import org.techtown.presentation.databinding.FragmentSecondBinding
import org.techtown.presentation.ext.navigateWithAnim

class SecondFragment : BaseFragment<FragmentSecondBinding>(R.layout.fragment_second) {

    private lateinit var navController: NavController
    private lateinit var navHost: NavHostFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun FragmentSecondBinding.onCreateView() {
        initSet()
        setListenerEvent()
    }

    private fun initSet() {
        navHost =
            requireActivity().supportFragmentManager.findFragmentById(R.id.main_nav_host_fragment) as NavHostFragment
        navController = navHost.findNavController()

    }

    private fun setListenerEvent() {

        //비지니스 기사.
        binding.btnBusiness.setOnClickListener {
            navController.navigateWithAnim(R.id.category_detail, Bundle().apply {
                putString("category_detail", binding.btnBusiness.text.toString())
            }
            )
        }

        //엔터테인먼트 기사.
        binding.btnEntertainment.setOnClickListener {
            Log.d("Category", "엔터")
            navController.navigateWithAnim(R.id.category_detail, Bundle().apply {
                putString("category_detail", binding.btnEntertainment.text.toString())
            }
            )
        }

        //일반 기사.
        binding.btnGeneral.setOnClickListener {
            navController.navigateWithAnim(R.id.category_detail, Bundle().apply {
                putString("category_detail", binding.btnGeneral.text.toString())
            }
            )
        }

        //건강 , 헬스케어 기사.
        binding.btnHealth.setOnClickListener {
            navController.navigateWithAnim(R.id.category_detail, Bundle().apply {
                putString("category_detail", binding.btnHealth.text.toString())
            }
            )
        }

        //과학 관련 기사.
        binding.btnScience.setOnClickListener {
            navController.navigateWithAnim(R.id.category_detail, Bundle().apply {
                putString("category_detail", binding.btnScience.text.toString())
            }
            )
        }

        //스포츠 기사.
        binding.btnScience.setOnClickListener {
            navController.navigateWithAnim(R.id.category_detail, Bundle().apply {
                putString("category_detail", binding.btnSports.text.toString())
            }
            )
        }

        //테크놀로지 기사.
        binding.btnScience.setOnClickListener {
            navController.navigateWithAnim(R.id.category_detail, Bundle().apply {
                putString("category_detail", binding.btnTechnology.text.toString())
            }
            )
        }

    }
}