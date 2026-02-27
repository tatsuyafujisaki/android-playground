package com.github.tatsuyafujisaki.androidplayground.ui.activity

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.github.tatsuyafujisaki.androidplayground.R
import com.github.tatsuyafujisaki.androidplayground.data.MyRemoteConfig
import com.github.tatsuyafujisaki.androidplayground.network.RetrofitClient
import com.github.tatsuyafujisaki.androidplayground.ui.compose.screen.MainScreen
import com.github.tatsuyafujisaki.androidplayground.ui.compose.screen.SecondScreen
import com.github.tatsuyafujisaki.androidplayground.ui.compose.screen.ThirdScreen
import com.github.tatsuyafujisaki.androidplayground.ui.viewmodel.MyActivityViewModel
import com.google.firebase.Firebase
import com.google.firebase.remoteconfig.get
import com.google.firebase.remoteconfig.remoteConfig
import com.google.firebase.remoteconfig.remoteConfigSettings
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var json: Json

    private val viewModel: MyActivityViewModel by viewModels()

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentDestination = navBackStackEntry?.destination

            val items = listOf(
                Screen.Main,
                Screen.Second,
                Screen.Third
            )

            MaterialTheme {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = {
                                Text(
                                    text = when (currentDestination?.route) {
                                        Screen.Main.route -> stringResource(R.string.main)
                                        Screen.Second.route -> stringResource(R.string.second)
                                        Screen.Third.route -> stringResource(R.string.third)
                                        else -> stringResource(R.string.app_name)
                                    }
                                )
                            },
                            colors = TopAppBarDefaults.topAppBarColors(
                                containerColor = MaterialTheme.colorScheme.primary,
                                titleContentColor = MaterialTheme.colorScheme.onPrimary
                            )
                        )
                    },
                    bottomBar = {
                        NavigationBar {
                            items.forEach { screen ->
                                NavigationBarItem(
                                    icon = {
                                        Icon(
                                            painter = painterResource(id = screen.iconId),
                                            contentDescription = null
                                        )
                                    },
                                    label = { Text(stringResource(screen.resourceId)) },
                                    selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                                    onClick = {
                                        navController.navigate(screen.route) {
                                            popUpTo(navController.graph.findStartDestination().id) {
                                                saveState = true
                                            }
                                            launchSingleTop = true
                                            restoreState = true
                                        }
                                    }
                                )
                            }
                        }
                    }
                ) { innerPadding ->
                    NavHost(
                        navController,
                        startDestination = Screen.Main.route,
                        Modifier.padding(innerPadding)
                    ) {
                        composable(Screen.Main.route) {
                            MainScreen {
                                navController.navigate(Screen.Third.route)
                            }
                        }
                        composable(Screen.Second.route) { SecondScreen() }
                        composable(Screen.Third.route) { ThirdScreen() }
                    }
                }
            }
        }

        lifecycleScope.launch {
            val service = RetrofitClient.createJsonPlaceholderService()
            service.deleteAlbum(id = 1)
        }

        doRemoteConfigStuff()
    }

    private fun doRemoteConfigStuff() {
        val remoteConfig = Firebase.remoteConfig

        remoteConfig.setConfigSettingsAsync(
            remoteConfigSettings {
                minimumFetchIntervalInSeconds = 0
            },
        )

        remoteConfig.setDefaultsAsync(R.xml.remote_config_defaults)

        remoteConfig.fetchAndActivate().addOnCompleteListener {
            if (it.isSuccessful) {
                val myRemoteConfig =
                    json.decodeFromString<MyRemoteConfig>(remoteConfig["my_key"].asString())
                println(myRemoteConfig)
            }
        }
    }

    sealed class Screen(val route: String, val resourceId: Int, val iconId: Int) {
        object Main : Screen("main", R.string.main, R.drawable.baseline_home_24)
        object Second : Screen("second", R.string.second, R.drawable.baseline_explore_24)
        object Third : Screen("third", R.string.third, R.drawable.baseline_account_circle_24)
    }
}
