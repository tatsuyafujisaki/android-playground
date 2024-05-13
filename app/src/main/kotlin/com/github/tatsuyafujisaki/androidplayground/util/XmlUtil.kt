package com.github.tatsuyafujisaki.androidplayground.util

import android.content.res.Resources
import android.util.Xml
import androidx.annotation.XmlRes
import org.xmlpull.v1.XmlPullParser

object XmlUtil {
    fun readXmlTag(resources: Resources, @XmlRes xmlResId: Int, tagToRead: String) =
        readXmlTag(parser = resources.getXml(xmlResId), tagToRead = tagToRead)

    fun readXmlTag(xml: String, tagToRead: String) = readXmlTag(
        parser = Xml.newPullParser().apply {
            setInput(xml.byteInputStream(), null)
        },
        tagToRead = tagToRead,
    )

    private fun readXmlTag(parser: XmlPullParser, tagToRead: String): String {
        do {
            val eventType = parser.next()
            if (eventType == XmlPullParser.START_TAG && parser.name == tagToRead) {
                return parser.nextText()
            }
        } while (eventType != XmlPullParser.END_DOCUMENT)

        return ""
    }
}
