package cc.kafuu.bilidownload.feature.compose.viewmodel.qrlolgin

import android.graphics.Bitmap

sealed class QrLoginUiState {
    data object None : QrLoginUiState()
    data class QrCode(val bitmap: Bitmap) : QrLoginUiState()
}