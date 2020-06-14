package com.github.tatsuyafujisaki.androidplayground

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.activity.addCallback
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.github.tatsuyafujisaki.androidplayground.dataClass.Sample
import com.github.tatsuyafujisaki.androidplayground.databinding.ActivityMainBinding
import com.github.tatsuyafujisaki.androidplayground.network.RetrofitClient
import com.github.tatsuyafujisaki.androidplayground.util.ContextUtil
import com.github.tatsuyafujisaki.androidplayground.util.FragmentUtil
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {
    @Inject
    lateinit var sample: Sample

    private val tag = this::class.java.simpleName

    private lateinit var binding: ActivityMainBinding
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navHostFragment: NavHostFragment
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(tag, object {}.javaClass.enclosingMethod!!.name)

        lifecycle.addObserver(LifecycleEventObserver { _, event ->
            Log.d(tag, event.toString())
        })

        binding = ActivityMainBinding.inflate(layoutInflater).apply {
            setContentView(root)
        }

//        if (savedInstanceState == null) {
//            supportFragmentManager.commit {
//                replace(R.id.fragment_container_view, HomeFragment())
//            }
//        }

        navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment

        // https://developer.android.com/guide/navigation/navigation-custom-back
        onBackPressedDispatcher.addCallback(this) {
            Log.d(tag, "Back button is pressed.")

            (FragmentUtil.getCurrentFragment(navHostFragment) as? WebViewContainer)?.run {
                if (canGoBack()) {
                    goBack()
                } else {
                    if (isEnabled) {
                        /**
                         * Disable callbacks for the Back button.
                         * Otherwise, this callback will be called recursively
                         * because onBackPressed() below calls this callback again.
                         */
                        isEnabled = false

                        onBackPressed()
                    }
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

        binding.fab.setOnClickListener {
            lifecycleScope.launch {
                runCatching {
                    RetrofitClient.googleApiService.getBooks("The Little Prince")
                }.fold({
                    ContextUtil.toast(this@MainActivity, it.toString())
                }, {
                    ContextUtil.toast(this@MainActivity, it.toString())
                })
            }
        }

        lifecycleScope.launch {
            Log.d(tag, "This is a demonstration of lifecycleScope.")
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d(tag, object {}.javaClass.enclosingMethod!!.name)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        Log.d(tag, object {}.javaClass.enclosingMethod!!.name)
    }

    override fun onResume() {
        super.onResume()
        Log.d(tag, object {}.javaClass.enclosingMethod!!.name)
    }

    override fun onPause() {
        super.onPause()
        Log.d(tag, object {}.javaClass.enclosingMethod!!.name)
    }

    override fun onStop() {
        super.onStop()
        Log.d(tag, object {}.javaClass.enclosingMethod!!.name)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d(tag, object {}.javaClass.enclosingMethod!!.name)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(tag, object {}.javaClass.enclosingMethod!!.name)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_options, menu)
        return true
    }

    /**
     * You must call setSupportActionBar(...) in Activity.onCreate()
     * to make tapping the Up button call onOptionsItemSelected()
     * even when there is no other menu item.
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home ->
                (FragmentUtil.getCurrentFragment(navHostFragment) as? WebViewContainer)?.run {
                    if (canGoBack()) {
                        goBack()
                    } else {
                        onBackPressed()
                    }
                    return true
                } ?: false
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
