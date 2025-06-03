package cc.kafuu.bilidownload.feature.compose.viewmodel.qrlolgin

import cc.kafuu.bilidownload.common.core.compose.CoreCompViewModel
import cc.kafuu.bilidownload.common.core.compose.UiIntentObserver
import cc.kafuu.bilidownload.common.jni.QrCodeNativeLib

class QrLoginViewModel : CoreCompViewModel<QrLoginUiIntent, QrLoginUiState>(QrLoginUiState.None) {
    @UiIntentObserver(QrLoginUiIntent.CreateQr::class)
    fun onCreateOr() {
        QrLoginUiState.QrCode(
            bitmap = QrCodeNativeLib.generateQrBitmap("Nyan Nyan")
        ).setup()
    }
}