package com.github.tatsuyafujisaki.androidplayground

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.Observer
import com.github.tatsuyafujisaki.androidplayground.dataClass.Sample
import com.github.tatsuyafujisaki.androidplayground.databinding.FragmentHomeBinding
import dagger.android.support.DaggerFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel
import javax.inject.Inject

class HomeFragment : DaggerFragment(), CoroutineScope by MainScope(), WebViewContainer {
    @Inject
    lateinit var sample: Sample

    private val logTag = this::class.java.simpleName
    private lateinit var binding: FragmentHomeBinding
    private val mainViewModel: MainViewModel by activityViewModels()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d(logTag, object {}.javaClass.enclosingMethod!!.name)
        lifecycle.addObserver(LifecycleEventObserver { _, event ->
            Log.d(logTag, event.toString())
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(logTag, object {}.javaClass.enclosingMethod!!.name)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(logTag, object {}.javaClass.enclosingMethod!!.name)
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(logTag, object {}.javaClass.enclosingMethod!!.name)

        binding.run {
            viewModel = mainViewModel

            clearButton.setOnClickListener {
                mainViewModel.liveData.value = null
            }

            mainViewModel.liveData.observe(viewLifecycleOwner, Observer {
                if (it != null) {
                    Log.d(logTag, it)
                }
            })

            /**
             * Without setting lifecycleOwner, updating liveData.value does not update EditText.
             * On the other hand, updating EditText updates liveData.value
             * regardless of setting lifecycleOwner.
             */
            lifecycleOwner = this@HomeFragment

            webView.run {
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

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.d(logTag, object {}.javaClass.enclosingMethod!!.name)
    }

    override fun onStart() {
        super.onStart()
        Log.d(logTag, object {}.javaClass.enclosingMethod!!.name)
    }

    override fun onResume() {
        super.onResume()
        Log.d(logTag, object {}.javaClass.enclosingMethod!!.name)
    }

    override fun onPause() {
        super.onPause()
        Log.d(logTag, object {}.javaClass.enclosingMethod!!.name)
    }

    override fun onStop() {
        super.onStop()
        Log.d(logTag, object {}.javaClass.enclosingMethod!!.name)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d(logTag, object {}.javaClass.enclosingMethod!!.name)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d(logTag, object {}.javaClass.enclosingMethod!!.name)
        cancel()
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(logTag, object {}.javaClass.enclosingMethod!!.name)
    }

    override fun onDetach() {
        super.onDetach()
        Log.d(logTag, object {}.javaClass.enclosingMethod!!.name)
    }

    override fun canGoBack() = binding.webView.canGoBack()
    override fun goBack() = binding.webView.goBack()
}
