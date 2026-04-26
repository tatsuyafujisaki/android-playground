package com.github.tatsuyafujisaki.androidplayground.ui.activity

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.lifecycleScope
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.ui.NavDisplay
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
            val backStack = remember { mutableStateListOf<Screen>(Screen.Main) }
            val currentDestination = backStack.last()

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
                                    text = when (currentDestination) {
                                        Screen.Main -> stringResource(R.string.main)
                                        Screen.Second -> stringResource(R.string.second)
                                        Screen.Third -> stringResource(R.string.third)
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
                                    selected = currentDestination == screen,
                                    onClick = {
                                        if (currentDestination != screen) {
                                            backStack.clear()
                                            backStack.add(screen)
                                        }
                                    }
                                )
                            }
                        }
                    }
                ) { innerPadding ->
                    NavDisplay(
                        backStack = backStack,
                        modifier = Modifier.padding(innerPadding),
                        onBack = {
                            if (backStack.size > 1) {
                                backStack.removeAt(backStack.size - 1)
                            } else {
                                finish()
                            }
                        },
                        entryProvider = { screen ->
                            NavEntry(key = screen) {
                                when (screen) {
                                    Screen.Main -> {
                                        MainScreen {
                                            backStack.add(Screen.Third)
                                        }
                                    }
                                    Screen.Second -> SecondScreen()
                                    Screen.Third -> ThirdScreen()
                                }
                            }
                        }
                    )
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
