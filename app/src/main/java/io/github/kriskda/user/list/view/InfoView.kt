package io.github.kriskda.user.list.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.kriskda.R

@Composable
fun LoadingStateInfoView(
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
            .padding(vertical = 16.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun ErrorStateInfoView(
    modifier: Modifier = Modifier,
    onLoadMore: () -> Unit,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
            .padding(vertical = 16.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        Button(
            onClick = { onLoadMore() }
        ) {
            Text(text = stringResource(R.string.user_list_load_again))
        }
    }
}

@Preview
@Composable
private fun LoadingStateInfoViewPreview() {
    LoadingStateInfoView()
}

@Preview
@Composable
private fun ErrorStateInfoViewPreview() {
    ErrorStateInfoView(
        onLoadMore = {}
    )
}