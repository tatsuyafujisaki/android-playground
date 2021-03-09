package com.github.tatsuyafujisaki.androidplayground.ui.activity

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.activity.ComponentActivity
import androidx.activity.OnBackPressedDispatcher
import androidx.activity.addCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.github.tatsuyafujisaki.androidplayground.MainApplication
import com.github.tatsuyafujisaki.androidplayground.R
import com.github.tatsuyafujisaki.androidplayground.WebViewContainer
import com.github.tatsuyafujisaki.androidplayground.databinding.ActivityMainBinding
import com.github.tatsuyafujisaki.androidplayground.di.MainActivityComponent
import com.github.tatsuyafujisaki.androidplayground.network.RetrofitClient
import com.github.tatsuyafujisaki.androidplayground.util.ContextUtil.toast
import com.github.tatsuyafujisaki.androidplayground.util.FragmentUtil.currentFragment
import com.github.tatsuyafujisaki.androidplayground.util.viewBindings
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(R.layout.activity_main) {
    private val binding by viewBindings(ActivityMainBinding::bind)
    private lateinit var mainActivityComponent: MainActivityComponent
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navHostFragment: NavHostFragment
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainActivityComponent = (applicationContext as MainApplication)
            .applicationComponent
            .mainComponent()
            .create().apply {
                inject(this@MainActivity)
            }

        Log.d(TAG, object {}.javaClass.enclosingMethod!!.name)

        navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment

        // https://developer.android.com/guide/navigation/navigation-custom-back
        onBackPressedDispatcher.addCallback(this) {
            Log.d(TAG, "Back button is pressed.")

            (navHostFragment.currentFragment as? WebViewContainer)?.run {
                if (canGoBack()) {
                    goBack()
                } else if (!navController.popBackStack()) {
                    finish()
                }
            }
        }

        navController = navHostFragment.navController

        binding.bottomNavigationView.setupWithNavController(navController)
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.destination_home,
                R.id.destination_explore,
                R.id.destination_account
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

    override fun onStart() {
        super.onStart()
        Log.d(TAG, object {}.javaClass.enclosingMethod!!.name)
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, object {}.javaClass.enclosingMethod!!.name)
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, object {}.javaClass.enclosingMethod!!.name)
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, object {}.javaClass.enclosingMethod!!.name)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, object {}.javaClass.enclosingMethod!!.name)
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

    override fun onBackPressed() {
        Log.d(TAG, object {}.javaClass.enclosingMethod!!.name)
        /**
         * If you override [ComponentActivity.onBackPressed] but does not call super.onBackPressed() in it,
         * [OnBackPressedDispatcher]'s callbacks will not be called.
         */
        super.onBackPressed()
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}
