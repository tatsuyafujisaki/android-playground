package com.github.tatsuyafujisaki.androidplayground.ui.compose

import android.util.Log
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFontFamilyResolver
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.createFontFamilyResolver
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.googlefonts.GoogleFont
import androidx.compose.ui.tooling.preview.Preview
import com.github.tatsuyafujisaki.androidplayground.R
import kotlinx.coroutines.CoroutineExceptionHandler

/**
 * Google Fonts
 * https://developer.android.com/develop/ui/compose/text/fonts
 */
@Preview(showBackground = true)
@Composable
fun FontExample() {
    // NB: Google Font is not applied in a preview.
    val fontName = GoogleFont("Noto Sans")

    val fontProvider = GoogleFont.Provider(
        providerAuthority = "com.google.android.gms.fonts",
        providerPackage = "com.google.android.gms",
        certificates = R.array.com_google_android_gms_fonts_certs
    )

    val fontFamily = FontFamily(
        Font(
            googleFont = fontName, fontProvider = fontProvider
        )
    )

    val handler = CoroutineExceptionHandler { _, throwable ->
        // process the Throwable
        Log.e("👀", "There has been an issue: ", throwable)
    }

    CompositionLocalProvider(
        LocalFontFamilyResolver provides createFontFamilyResolver(LocalContext.current, handler)
    ) {
        Text(
            text = "人類社会のすべての構成員の固有の尊厳と平等で譲ることのできない権利とを承認することは",
            fontFamily = fontFamily,
        )
    }
}
