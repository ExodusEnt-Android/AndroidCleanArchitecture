package org.techtown.presentation.feature.main

import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import org.techtown.presentation.R
import org.techtown.presentation.base.BaseActivity
import org.techtown.presentation.databinding.ActivityMainBinding

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    private lateinit var navHostFragment: NavHostFragment
    private lateinit var navController: NavController

    override fun ActivityMainBinding.onCreate() {
        initSet()
    }

    private fun initSet() {

        navHostFragment =
            supportFragmentManager.findFragmentById(R.id.main_nav_host_fragment) as NavHostFragment
        navController = navHostFragment.findNavController()


        navController.addOnDestinationChangedListener(navDestinationChangedListener)

        setUpBottomNavMenu(navController)
    }

    private val navDestinationChangedListener =
        NavController.OnDestinationChangedListener { _, destination, _ ->

            when (destination.label) {
                "topNews" -> {
                    binding.tvToolbarTitle.text = getString(R.string.top_news)
                }
                "topNewsDetail" -> {
                    binding.tvToolbarTitle.text = getString(R.string.top_news_detail)
                }
                "category" -> {
                    binding.tvToolbarTitle.text = getString(R.string.category)
                }
                "categoryDetail" -> {
                    binding.tvToolbarTitle.text = getString(R.string.category_detail)
                }
                "categoryTopNewsDetail" -> {
                    binding.tvToolbarTitle.text = getString(R.string.category_top_news_detail)
                }
                "saved" -> {
                    binding.tvToolbarTitle.text = getString(R.string.saved)
                }
                "savedTopNewsDetail" -> {
                    binding.tvToolbarTitle.text = getString(R.string.saved_top_news_detail)
                }
            }
        }

    private fun setUpBottomNavMenu(navController: NavController) {
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_nav_view)
        bottomNav?.setupWithNavController(navController)
    }
}