package cc.kafuu.bilidownload.common.jni

import android.graphics.Bitmap
import android.graphics.Color
import androidx.core.graphics.createBitmap
import androidx.core.graphics.set

object QrCodeNativeLib {
    fun generateQrBitmap(text: String, scale: Int = 10): Bitmap {
        val (data, width) = NativeLib.generateQrCode(text)
        val bitmapSize = width * scale
        val bitmap = createBitmap(bitmapSize, bitmapSize)
        for (y in 0 until width) {
            for (x in 0 until width) {
                val index = y * width + x
                val color = if (data[index]) Color.BLACK else Color.WHITE
                for (dy in 0 until scale) {
                    for (dx in 0 until scale) {
                        bitmap[x * scale + dx, y * scale + dy] = color
                    }
                }
            }
        }
        return bitmap
    }
}