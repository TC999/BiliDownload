package cc.kafuu.bilidownload.feature.compose.layout

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.unit.dp
import cc.kafuu.bilidownload.feature.compose.viewmodel.qrlolgin.QrLoginUiIntent
import cc.kafuu.bilidownload.feature.compose.viewmodel.qrlolgin.QrLoginUiState

@Composable
fun QrLoginLayout(
    state: QrLoginUiState,
    onEvent: (QrLoginUiIntent) -> Unit
) {
    when (state) {
        QrLoginUiState.None -> Unit
        // 暂时性测试写法
        is QrLoginUiState.QrCode -> Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Image(
                modifier = Modifier.size(200.dp),
                bitmap = state.bitmap.asImageBitmap(),
                contentDescription = null
            )
        }
    }
}