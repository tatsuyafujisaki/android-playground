package com.github.tatsuyafujisaki.androidplayground.compose

import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.LifecycleOwner
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.ui.StyledPlayerView
import kotlinx.coroutines.delay

private fun Player.autoPlay() {
    playWhenReady = true
    prepare()
}

@Composable
fun ComposableExoPlayer(
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current,
    uri: String,
    positionMs: Long,
    onPositionChange: (Long) -> Unit
) {
    lateinit var styledPlayerView: StyledPlayerView

    AndroidView(
        factory = {
            StyledPlayerView(it).apply {
                styledPlayerView = this
                player = ExoPlayer.Builder(it).build().apply {
                    seekTo(positionMs)
                    setMediaItem(MediaItem.fromUri(uri))
                    autoPlay()
                }
            }
        },
        modifier = Modifier.background(Color.Black)
    )

    LaunchedEffect(uri) {
        while (true) {
            onPositionChange(styledPlayerView.player?.currentPosition ?: 0)
            delay(1000)
        }
    }

    DisposableEffect(lifecycleOwner) {
        onDispose {
            // Stop the audio of the video when you leave the screen.
            styledPlayerView.player?.release()
        }
    }
}

@Preview
@Composable
private fun PreviewComposableExoPlayer() {
    ComposableExoPlayer(
        uri = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4",
        positionMs = 0,
        onPositionChange = {}
    )
}