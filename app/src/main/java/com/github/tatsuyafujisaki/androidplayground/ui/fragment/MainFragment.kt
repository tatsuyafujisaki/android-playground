package com.github.tatsuyafujisaki.androidplayground.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.github.tatsuyafujisaki.androidplayground.MainViewModel

class MainFragment : Fragment() {
    private val viewModel: MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ) = ComposeView(requireContext()).apply {
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
