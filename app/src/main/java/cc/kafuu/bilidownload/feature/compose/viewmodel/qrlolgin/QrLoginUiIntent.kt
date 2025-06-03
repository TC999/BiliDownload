package cc.kafuu.bilidownload.feature.compose.viewmodel.qrlolgin

sealed class QrLoginUiIntent {
    data object CreateQr : QrLoginUiIntent()
}