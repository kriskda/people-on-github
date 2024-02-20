package io.github.kriskda.user.list.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import coil.request.ImageRequest
import io.github.kriskda.R
import io.github.kriskda.common.preview.UserItemListProvider
import io.github.kriskda.items
import io.github.kriskda.user.list.model.UserItem
import kotlinx.coroutines.flow.flowOf

@Composable
fun UserListView(
    listState: LazyListState,
    users: LazyPagingItems<UserItem>,
    onItemClick: (String) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        state = listState,
    ) {
        items(users, key = { it.id }) {
            UserItemView(
                user = it,
                onItemClick = onItemClick,
            )
        }
    }
}

@Composable
private fun UserItemView(
    user: UserItem,
    onItemClick: (String) -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onItemClick(user.userName) },
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(0.2f)
                .aspectRatio(1f)
        ) {
            AsyncImage(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        color = MaterialTheme.colorScheme.primaryContainer,
                        shape = CircleShape,
                    )
                    .clip(CircleShape),
                model = ImageRequest.Builder(LocalContext.current)
                    .data(user.avatarUrl)
                    .crossfade(true)
                    .build(),
                placeholder = painterResource(R.drawable.ic_user),
                fallback = painterResource(R.drawable.ic_user),
                contentDescription = stringResource(R.string.user_list_avatar_content_description),
            )
        }
        Column {
            Text(
                text = user.userName,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = stringResource(R.string.user_list_user_id, user.id.toString()),
                style = MaterialTheme.typography.labelMedium,
            )
        }

    }
}

@Preview
@Composable
private fun UserListScreenPreview(
    @PreviewParameter(UserItemListProvider::class) users: PagingData<UserItem>
) {
    UserListView(
        listState = LazyListState(),
        users = flowOf(users).collectAsLazyPagingItems(),
        onItemClick = {},
    )
}