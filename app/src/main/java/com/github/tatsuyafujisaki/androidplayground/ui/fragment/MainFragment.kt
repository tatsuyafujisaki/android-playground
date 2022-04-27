package com.github.tatsuyafujisaki.androidplayground.ui.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.github.tatsuyafujisaki.androidplayground.MainViewModel
import com.github.tatsuyafujisaki.androidplayground.R
import com.github.tatsuyafujisaki.androidplayground.WebViewContainer
import com.github.tatsuyafujisaki.androidplayground.databinding.FragmentMainBinding
import kotlinx.coroutines.launch

class MainFragment : Fragment(), WebViewContainer {
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private val mainViewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)

        viewLifecycleOwner.lifecycleScope.launch {
            Log.d(TAG, "This is a demonstration of viewLifecycleOwner.lifecycleScope.")
        }

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            Log.d(TAG, object {}.javaClass.enclosingMethod!!.name)
            if (!findNavController().popBackStack()) {
                // Finish the Activity if it has nothing in the back stack.
                requireActivity().finish()
            }
        }

        return binding.root
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            clearButton.setOnClickListener {
                mainViewModel.setData(null)
            }

            navigateToSampleFragmentButton.setOnClickListener(
                Navigation.createNavigateOnClickListener(R.id.action_main_fragment_to_third_fragment)
            )

            mainViewModel.liveData.observe(viewLifecycleOwner) {
                editText.setText(it)
            }

            webView.settings.javaScriptEnabled = true
            webView.webViewClient = object : WebViewClient() {
                override fun shouldOverrideUrlLoading(
                    view: WebView?,
                    request: WebResourceRequest?
                ): Boolean {
                    if (true) {
                        view?.loadUrl("https://example.com")
                        return true
                    }
                    return super.shouldOverrideUrlLoading(view, request)
                }

                override fun onPageFinished(view: WebView, url: String) {
                    // Print the whole HTML.
                    view.evaluateJavascript("document.documentElement.outerHTML") {
                        val html = it.replace("\\u003C", "<")
                        Log.d(TAG, html)
                    }
                }
            }

            webView.loadUrl("https://www.google.com")
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
