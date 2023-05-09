package com.github.tatsuyafujisaki.androidplayground.util

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

object LifecycleUtil {
    object RepeatOnLifecycle {
        private fun launchWithStartAndCompletion(
            lifecycleOwner: LifecycleOwner,
            lifecycleState: Lifecycle.State,
            onStart: suspend () -> Unit,
            onCompletion: suspend () -> Unit
        ) = lifecycleOwner.lifecycleScope.launch {
            lifecycleOwner.repeatOnLifecycle(lifecycleState) {
                MutableSharedFlow<Nothing>() // Use SharedFlow, which never completes, unless the CoroutineScope is canceled.
                    .onStart { onStart() }
                    .onCompletion { onCompletion() }
                    .launchIn(this)
            }
        }

        fun launchBetweenStartAndStop(
            lifecycleOwner: LifecycleOwner,
            onStart: suspend () -> Unit,
            onStop: suspend () -> Unit
        ) {
            launchWithStartAndCompletion(
                lifecycleOwner = lifecycleOwner,
                lifecycleState = Lifecycle.State.STARTED,
                onStart = onStart,
                onCompletion = onStop
            )
        }

        fun launchBetweenResumeAndPause(
            lifecycleOwner: LifecycleOwner,
            onResume: suspend () -> Unit,
            onPause: suspend () -> Unit
        ) {
            launchWithStartAndCompletion(
                lifecycleOwner = lifecycleOwner,
                lifecycleState = Lifecycle.State.RESUMED,
                onStart = onResume,
                onCompletion = onPause
            )
        }
    }

    private val defaultLifecycleObserver
        get() = object : DefaultLifecycleObserver {
            override fun onCreate(owner: LifecycleOwner) {}
            override fun onStart(owner: LifecycleOwner) {}
            override fun onResume(owner: LifecycleOwner) {}
            override fun onPause(owner: LifecycleOwner) {}
            override fun onStop(owner: LifecycleOwner) {}
            override fun onDestroy(owner: LifecycleOwner) {}
        }

    private val lifecycleEventObserver
        get() = LifecycleEventObserver { source, event ->
            Log.d(source::class.java.simpleName, event.name)
            when (event) {
                Lifecycle.Event.ON_CREATE -> {}
                Lifecycle.Event.ON_START -> {}
                Lifecycle.Event.ON_RESUME -> {}
                Lifecycle.Event.ON_PAUSE -> {}
                Lifecycle.Event.ON_STOP -> {}
                Lifecycle.Event.ON_DESTROY -> {}
                Lifecycle.Event.ON_ANY -> {}
            }
        }

    fun onViewCreated(fragment: Fragment) {
        fragment.viewLifecycleOwner.lifecycle.addObserver(
            object : DefaultLifecycleObserver {
                override fun onDestroy(owner: LifecycleOwner) {
                    println("Fragment.onDestroyView() is called.")
                }
            }
        )
        fragment.lifecycle.addObserver(
            object : DefaultLifecycleObserver {
                override fun onDestroy(owner: LifecycleOwner) {
                    println("Fragment.onDestroy() is called.")
                }
            }
        )
    }
}
