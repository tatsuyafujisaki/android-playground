package com.github.tatsuyafujisaki.androidplayground.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.github.tatsuyafujisaki.androidplayground.MainViewModel
import com.github.tatsuyafujisaki.androidplayground.R
import com.github.tatsuyafujisaki.androidplayground.WebViewContainer
import com.github.tatsuyafujisaki.androidplayground.databinding.FragmentHomeBinding
import com.github.tatsuyafujisaki.androidplayground.util.WebViewUtil.enableJavaScript
import kotlinx.coroutines.launch

class HomeFragment : Fragment(), WebViewContainer {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val mainViewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        viewLifecycleOwner.lifecycleScope.launch {
            Log.d(TAG, "This is a demonstration of viewLifecycleOwner.lifecycleScope.")
        }

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                Log.d(TAG, object {}.javaClass.enclosingMethod!!.name)
                if (!findNavController().popBackStack()) {
                    // Finish the Activity if it has nothing in the back stack.
                    requireActivity().finish()
                }
            }
        })

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            clearButton.setOnClickListener {
                mainViewModel.setData(null)
            }

            navigateToSampleFragmentButton.setOnClickListener(
                Navigation.createNavigateOnClickListener(R.id.action_homeFragment_to_sampleFragment)
            )

            mainViewModel.liveData.observe(viewLifecycleOwner) {
                editText.setText(it)
            }

            with(webView) {
                enableJavaScript()
                webViewClient = object : WebViewClient() {
                    // Enable page transitions inside the WebView instead of opening a browser.
                    override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                        view.loadUrl(url)
                        return true
                    }
                }

                loadUrl("https://www.google.com/")
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun canGoBack() = binding.webView.canGoBack()
    override fun goBack() = binding.webView.goBack()

    companion object {
        private const val TAG = "HomeFragment"
    }
}
