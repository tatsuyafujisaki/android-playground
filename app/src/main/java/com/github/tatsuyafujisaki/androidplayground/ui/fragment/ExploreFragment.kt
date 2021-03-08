package com.github.tatsuyafujisaki.androidplayground.ui.fragment

import android.content.Context
import android.util.Log
import androidx.fragment.app.Fragment
import com.github.tatsuyafujisaki.androidplayground.R
import com.github.tatsuyafujisaki.androidplayground.databinding.FragmentExploreBinding
import com.github.tatsuyafujisaki.androidplayground.util.viewBindings

class ExploreFragment : Fragment(R.layout.fragment_explore) {
    private val binding by viewBindings(FragmentExploreBinding::bind)

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d(TAG, object {}.javaClass.enclosingMethod!!.name)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d(TAG, object {}.javaClass.enclosingMethod!!.name)
    }

    companion object {
        private const val TAG = "ExploreFragment"
    }
}
