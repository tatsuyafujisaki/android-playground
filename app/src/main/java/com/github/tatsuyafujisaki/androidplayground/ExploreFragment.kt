package com.github.tatsuyafujisaki.androidplayground

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleEventObserver
import com.github.tatsuyafujisaki.androidplayground.databinding.FragmentExploreBinding
import com.github.tatsuyafujisaki.androidplayground.util.viewBindings

class ExploreFragment : Fragment(R.layout.fragment_explore) {
    private val logTag = this::class.java.simpleName
    private val binding by viewBindings(FragmentExploreBinding::bind)

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d(logTag, object {}.javaClass.enclosingMethod!!.name)
        lifecycle.addObserver(LifecycleEventObserver { _, event ->
            Log.d(logTag, event.toString())
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(logTag, object {}.javaClass.enclosingMethod!!.name)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d(logTag, object {}.javaClass.enclosingMethod!!.name)
    }
}
