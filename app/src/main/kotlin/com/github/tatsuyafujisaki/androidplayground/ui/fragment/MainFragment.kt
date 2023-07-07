package com.github.tatsuyafujisaki.androidplayground.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.asLiveData
import com.github.tatsuyafujisaki.androidplayground.ui.compose.screen.MainScreen
import com.github.tatsuyafujisaki.androidplayground.ui.viewmodel.MainViewModel
import com.github.tatsuyafujisaki.androidplayground.ui.viewmodel.MyActivityViewModel
import com.github.tatsuyafujisaki.androidplayground.util.ActivityUtil.OssLicenses.startOssLicensesMenuActivity

class MainFragment : Fragment() {
    private val activityViewModel: MyActivityViewModel by activityViewModels()
    private val viewModel: MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ) = ComposeView(requireContext()).apply {
        setViewCompositionStrategy(DisposeOnViewTreeLifecycleDestroyed)
        setContent {
            MaterialTheme {
                MainScreen {
                    startOssLicensesMenuActivity(requireContext())
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        activityViewModel.orientation2.asLiveData().observe(viewLifecycleOwner) {
            Log.d(TAG, it.toString())
        }
        activityViewModel.orientation4.asLiveData().observe(viewLifecycleOwner) {
            Log.d(TAG, it.toString())
        }
    }

    companion object {
        private const val TAG = "MainFragment"
    }
}
