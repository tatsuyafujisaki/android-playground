package com.github.tatsuyafujisaki.androidplayground.compose

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.ui.StyledPlayerView

@Composable
fun ComposableExoPlayer(uri: String) {
    AndroidView(
        factory = {
            StyledPlayerView(it).apply {
                player = ExoPlayer.Builder(context).build().apply {
                    setMediaItem(MediaItem.fromUri(uri))
                }
            }
        }
    )
}

@Preview
@Composable
private fun PreviewComposableExoPlayer() {
    ComposableExoPlayer("https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4")
}
