package com.github.tatsuyafujisaki.androidplayground.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import com.github.tatsuyafujisaki.androidplayground.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
class MainFragment : Fragment() {
    private val viewModel: MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setViewCompositionStrategy(DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                MaterialTheme {
                    Scaffold {
                        Box(
                            modifier = Modifier
                                .padding(it)
                                .fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text("Hello Compose!")
                        }
                    }
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewLifecycleOwner.lifecycle.addObserver(
            object : DefaultLifecycleObserver {
                override fun onCreate(owner: LifecycleOwner) {}
                override fun onStart(owner: LifecycleOwner) {}
                override fun onResume(owner: LifecycleOwner) {}
                override fun onPause(owner: LifecycleOwner) {}
                override fun onStop(owner: LifecycleOwner) {}
                override fun onDestroy(owner: LifecycleOwner) {}
            }
        )
        viewLifecycleOwner.lifecycle.addObserver(
            LifecycleEventObserver { source, event ->
                when (event) {
                    Lifecycle.Event.ON_CREATE -> {}
                    Lifecycle.Event.ON_START -> {}
                    Lifecycle.Event.ON_RESUME -> {}
                    Lifecycle.Event.ON_PAUSE -> {}
                    Lifecycle.Event.ON_STOP -> {}
                    Lifecycle.Event.ON_DESTROY -> {}
                    Lifecycle.Event.ON_ANY -> {}
                }
            }
        )
    }
}
