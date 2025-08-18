package cc.kafuu.bilidownload.feature.compose.layout

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import cc.kafuu.bilidownload.R
import cc.kafuu.bilidownload.feature.compose.viewmodel.qrlolgin.QrLoginUiIntent
import cc.kafuu.bilidownload.feature.compose.viewmodel.qrlolgin.QrLoginUiState
import cc.kafuu.bilidownload.feature.compose.viewmodel.qrlolgin.QrLoginUserState
import cc.kafuu.bilidownload.feature.compose.views.AppTopBar

@Composable
fun QrLoginLayout(
    state: QrLoginUiState,
    onEvent: (QrLoginUiIntent) -> Unit
) {

    when (state) {
        QrLoginUiState.None -> Unit
        // 暂时性测试写法
        is QrLoginUiState.Normal -> Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(colorResource(R.color.general_window_background_color)),
            contentAlignment = Alignment.Center
        ) {
            Normal(state, onEvent)
        }
    }
}


@Composable
private fun Normal(
    state: QrLoginUiState.Normal,
    onEvent: (QrLoginUiIntent) -> Unit
) {
    Column(
        modifier = Modifier
            .statusBarsPadding()
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AppTopBar(title = stringResource(R.string.qr_login)) {
            onEvent(QrLoginUiIntent.TryBack)
        }
        QrBox(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight(),
            userState = state.userState,
        )
    }
}


@Composable
private fun QrBox(
    userState: QrLoginUserState,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        when (userState) {
            QrLoginUserState.QrRequesting -> {
                CircularProgressIndicator()
            }

            QrLoginUserState.QrRequestFailed -> {
                Image(
                    modifier = Modifier
                        .size(240.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(Color.White)
                        .padding(10.dp),
                    painter = painterResource(R.drawable.ic_qr_scan_failed),
                    contentDescription = null
                )
                Spacer(Modifier.height(10.dp))
                Text(text = stringResource(R.string.qr_waiting_confirmation_message))
            }

            is QrLoginUserState.WaitingScanning -> {
                Image(
                    modifier = Modifier
                        .size(240.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(Color.White)
                        .padding(10.dp),
                    bitmap = userState.qrBitmap.asImageBitmap(),
                    contentDescription = null
                )
                Spacer(Modifier.height(10.dp))
                Text(text = stringResource(R.string.qr_waiting_scanning_message))
            }

            is QrLoginUserState.WaitingConfirmation -> {
                Image(
                    modifier = Modifier
                        .size(240.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(Color.White)
                        .padding(10.dp),
                    painter = painterResource(R.drawable.ic_qr_scan_done),
                    contentDescription = null
                )
                Spacer(Modifier.height(10.dp))
                Text(text = stringResource(R.string.qr_waiting_confirmation_message))
            }

            is QrLoginUserState.Completed -> {
                CircularProgressIndicator()
                Text(text = stringResource(R.string.qr_completed_message))
            }

        }
    }
}