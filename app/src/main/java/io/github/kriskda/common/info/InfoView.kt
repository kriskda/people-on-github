package io.github.kriskda.common.info

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import io.github.kriskda.R

@Composable
fun LoadingView() {
    InfoView(R.string.info_loading)
}

@Composable
fun FailureView() {
    InfoView(R.string.info_failure)
}

@Composable
private fun InfoView(
    @StringRes textResId: Int
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = stringResource(textResId))
    }
}

@Preview
@Composable
private fun LoadingViewPreview() {
    LoadingView()
}

@Preview
@Composable
private fun FailureViewPreview() {
    FailureView()
}