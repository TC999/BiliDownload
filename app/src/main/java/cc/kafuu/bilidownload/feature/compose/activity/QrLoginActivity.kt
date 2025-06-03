package cc.kafuu.bilidownload.feature.compose.activity

import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import cc.kafuu.bilidownload.common.core.compose.CoreCompActivity
import cc.kafuu.bilidownload.feature.compose.layout.QrLoginLayout
import cc.kafuu.bilidownload.feature.compose.viewmodel.qrlolgin.QrLoginUiIntent
import cc.kafuu.bilidownload.feature.compose.viewmodel.qrlolgin.QrLoginViewModel

class QrLoginActivity : CoreCompActivity() {
    private val mViewModel by viewModels<QrLoginViewModel>()

    @Composable
    override fun ViewContent() {
        val uiState by mViewModel.uiStateFlow.collectAsState()
        QrLoginLayout(uiState) { mViewModel.emit(it) }
        mViewModel.emit(QrLoginUiIntent.CreateQr)
    }
}