package io.github.kriskda.user.list.view

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import io.github.kriskda.R

@Composable
fun SearchView(
    onSearch: (String) -> Unit
) {
    var searchText by remember { mutableStateOf("") }

    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth(),
        value = searchText,
        onValueChange = { newText ->
            searchText = newText
            onSearch(newText)
        },
        label = { Text(stringResource(R.string.user_list_search_user)) },
        singleLine = true,
        textStyle = MaterialTheme.typography.bodyLarge
    )
}

@Preview
@Composable
private fun SearchViewPreview() {
    SearchView {}
}