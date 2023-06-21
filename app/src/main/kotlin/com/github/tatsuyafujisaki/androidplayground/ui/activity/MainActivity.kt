package com.github.tatsuyafujisaki.androidplayground.ui.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.github.tatsuyafujisaki.androidplayground.R
import com.github.tatsuyafujisaki.androidplayground.databinding.ActivityMainBinding
import com.github.tatsuyafujisaki.androidplayground.network.RetrofitClient
import com.github.tatsuyafujisaki.androidplayground.ui.viewmodel.MyActivityViewModel
import com.github.tatsuyafujisaki.androidplayground.util.ContextUtil.toast
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private val navController get() = navHostFragment.navController
    private lateinit var binding: ActivityMainBinding
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navHostFragment: NavHostFragment
    private val viewModel: MyActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment

        binding.bottomNavigationView.setupWithNavController(navController)
        appBarConfiguration = AppBarConfiguration(
            setOf(R.id.main_fragment, R.id.second_fragment, R.id.third_fragment)
        )
        binding.toolbar.setupWithNavController(navController, appBarConfiguration)
        setSupportActionBar(binding.toolbar)

        binding.fab.setOnClickListener {
            lifecycleScope.launch {
                runCatching {
                    RetrofitClient.getGoogleApiService()
                        .getBooks("The Little Prince")
                }.fold({
                    this@MainActivity.toast(it.toString())
                }, {
                    this@MainActivity.toast(it.toString())
                })
            }
        }

        viewModel.setOrientation(resources.configuration.orientation)
    }
}
