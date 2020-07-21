package com.github.tatsuyafujisaki.androidplayground

import android.content.Context
import android.util.Log
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

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d(logTag, object {}.javaClass.enclosingMethod!!.name)
    }
}
