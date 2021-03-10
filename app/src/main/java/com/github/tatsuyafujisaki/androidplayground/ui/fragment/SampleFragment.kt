package com.github.tatsuyafujisaki.androidplayground.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.tatsuyafujisaki.androidplayground.R
import com.github.tatsuyafujisaki.androidplayground.databinding.FragmentSampleBinding

class SampleFragment : Fragment(R.layout.fragment_sample) {
    private var _binding: FragmentSampleBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.d(TAG, object {}.javaClass.enclosingMethod!!.name)
        _binding = FragmentSampleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d(TAG, object {}.javaClass.enclosingMethod!!.name)
        _binding = null
    }

    companion object {
        private const val TAG = "SampleFragment"
    }
}
