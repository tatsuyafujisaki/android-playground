package com.github.tatsuyafujisaki.androidplayground.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.github.tatsuyafujisaki.androidplayground.ui.compose.screen.MainScreen
import com.github.tatsuyafujisaki.androidplayground.ui.viewmodel.MainViewModel
import com.github.tatsuyafujisaki.androidplayground.ui.viewmodel.MyActivityViewModel

@Suppress("unused")
class MainFragment : Fragment() {
    private val activityViewModel: MyActivityViewModel by activityViewModels()
    private val viewModel: MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ) = ComposeView(requireContext()).apply {
        setViewCompositionStrategy(strategy = DisposeOnViewTreeLifecycleDestroyed)
        setContent {
            MaterialTheme {
                MainScreen {
                }
            }
        }
    }
}
