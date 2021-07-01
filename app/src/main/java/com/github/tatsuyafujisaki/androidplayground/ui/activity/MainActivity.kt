package com.github.tatsuyafujisaki.androidplayground.ui.activity

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.activity.addCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.github.tatsuyafujisaki.androidplayground.R
import com.github.tatsuyafujisaki.androidplayground.WebViewContainer
import com.github.tatsuyafujisaki.androidplayground.databinding.ActivityMainBinding
import com.github.tatsuyafujisaki.androidplayground.network.RetrofitClient
import com.github.tatsuyafujisaki.androidplayground.util.ContextUtil.toast
import com.github.tatsuyafujisaki.androidplayground.util.NavigationUtil.currentFragment
import kotlinx.coroutines.launch

// @AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navHostFragment: NavHostFragment
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        // https://developer.android.com/guide/navigation/navigation-custom-back
        onBackPressedDispatcher.addCallback(this) {
            Log.d(TAG, "Back button is pressed.")

            (navHostFragment.childFragmentManager.primaryNavigationFragment as? WebViewContainer)?.run {
                if (canGoBack()) {
                    goBack()
                } else if (!navController.popBackStack()) {
                    finish()
                }
            }
        }

        binding.bottomNavigationView.setupWithNavController(navController)
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.mainFragment,
                R.id.exploreFragment,
                R.id.accountFragment
            )
        )
        binding.toolbar.setupWithNavController(navController, appBarConfiguration)
        setSupportActionBar(binding.toolbar)

        binding.fab.setOnClickListener {
            lifecycleScope.launch {
                runCatching {
                    RetrofitClient.googleApiService.getBooks("The Little Prince")
                }.fold({
                    this@MainActivity.toast(it.toString())
                }, {
                    this@MainActivity.toast(it.toString())
                })
            }
        }

        lifecycleScope.launch {
            Log.d(TAG, "This is a demonstration of lifecycleScope.")
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_options, menu)
        return true
    }

    /**
     * You must call [AppCompatActivity.setSupportActionBar] in [AppCompatActivity.onCreate]
     * to make tapping the Up button call [AppCompatActivity.onOptionsItemSelected]
     * even when there is no other menu item.
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home ->
                (navHostFragment.currentFragment as? WebViewContainer)?.run {
                    if (canGoBack()) {
                        goBack()
                    } else {
                        onBackPressed()
                    }
                    return true
                } ?: navController.navigateUp()
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}
