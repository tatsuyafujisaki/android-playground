package com.github.tatsuyafujisaki.androidplayground.ui.fragment

import android.content.Context
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
import androidx.lifecycle.*
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.github.tatsuyafujisaki.androidplayground.MainViewModel
import com.github.tatsuyafujisaki.androidplayground.R
import com.github.tatsuyafujisaki.androidplayground.WebViewContainer
import com.github.tatsuyafujisaki.androidplayground.databinding.FragmentHomeBinding
import com.github.tatsuyafujisaki.androidplayground.util.WebViewUtil.enableJavaScript
import kotlinx.coroutines.launch

class HomeFragment : Fragment(), WebViewContainer {
    private val logTagForView = this::class.java.simpleName + ".view"
    private lateinit var binding: FragmentHomeBinding
    private val mainViewModel: MainViewModel by activityViewModels()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d(TAG, object {}.javaClass.enclosingMethod!!.name)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, object {}.javaClass.enclosingMethod!!.name)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.d(TAG, object {}.javaClass.enclosingMethod!!.name)
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        viewLifecycleOwner.lifecycleScope.launch {
            Log.d(TAG, "This is a demonstration of viewLifecycleOwner.lifecycleScope.")
        }

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                Log.d(TAG, object {}.javaClass.enclosingMethod!!.name)
                if(!findNavController().popBackStack()) {
                    // Finish the Activity if it has nothing in the back stack.
                    requireActivity().finish()
                }
            }
        })

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, object {}.javaClass.enclosingMethod!!.name)

        with(binding) {
            /**
             * Without setting lifecycleOwner, updating liveData.value does not update EditText.
             * On the other hand, updating EditText updates liveData.value
             * regardless of setting lifecycleOwner.
             */
            lifecycleOwner = this@HomeFragment

            viewModel = mainViewModel

            clearButton.setOnClickListener {
                mainViewModel.liveData.value = null
            }

            navigateToSampleFragmentButton.setOnClickListener(
                Navigation.createNavigateOnClickListener(R.id.action_from_home_to_sample)
            )

            mainViewModel.liveData.observe(viewLifecycleOwner) {
                if (it != null) {
                    Log.d(TAG, it)
                }
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

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d(TAG, object {}.javaClass.enclosingMethod!!.name)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d(TAG, object {}.javaClass.enclosingMethod!!.name)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, object {}.javaClass.enclosingMethod!!.name)
    }

    override fun onDetach() {
        super.onDetach()
        Log.d(TAG, object {}.javaClass.enclosingMethod!!.name)
    }

    override fun canGoBack() = binding.webView.canGoBack()
    override fun goBack() = binding.webView.goBack()

    companion object {
        private const val TAG = "HomeFragment"
    }
}
