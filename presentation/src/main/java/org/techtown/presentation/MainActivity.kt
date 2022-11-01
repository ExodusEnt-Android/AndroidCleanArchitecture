package org.techtown.presentation


import android.view.View
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.techtown.presentation.base.BaseActivity
import org.techtown.presentation.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    private lateinit var navHostFragment: NavHostFragment
    private lateinit var navController: NavController

    override fun ActivityMainBinding.onCreate() {
        initSet()
    }

    private fun initSet() {

        navHostFragment = supportFragmentManager.findFragmentById(R.id.main_nav_host_fragment) as NavHostFragment ?: return
        navController = navHostFragment.findNavController()


        navController.addOnDestinationChangedListener(navDestinationChangedListener)

        setUpBottomNavMenu(navController)
    }

    private val navDestinationChangedListener = NavController.OnDestinationChangedListener{ _, destination , _ ->

        when(destination.label) {
            "topNews" -> {
                binding.toolbarMain.visibility = View.VISIBLE
                binding.tvToolbarTitle.text = "topNews"
            }
            "category" -> {
                binding.toolbarMain.visibility = View.VISIBLE
                binding.tvToolbarTitle.text = "category"
            }
            "saved" -> {
                binding.toolbarMain.visibility = View.VISIBLE
                binding.tvToolbarTitle.text = "saved"
            }
        }
    }

    private fun setUpBottomNavMenu(navController: NavController) {
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_nav_view)
        bottomNav?.setupWithNavController(navController)
    }
}