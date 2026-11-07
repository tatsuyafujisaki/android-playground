package com.github.tatsuyafujisaki.androidplayground.ui.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.github.tatsuyafujisaki.androidplayground.R
import com.mikepenz.aboutlibraries.ui.compose.android.produceLibraries
import com.mikepenz.aboutlibraries.ui.compose.m3.LibrariesContainer

@Preview
@Composable
private fun AboutLibrariesExample() {
    val libraries by produceLibraries(R.raw.aboutlibraries)
    Scaffold {
        LibrariesContainer(
            libraries = libraries,
            modifier = Modifier
                .background(color = Color.White)
                .fillMaxSize()
                .padding(paddingValues = it),
        )
    }
}
