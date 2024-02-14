package io.github.kriskda.user.list.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import io.github.kriskda.R
import io.github.kriskda.common.animation.ScreenTransitionAnimation
import io.github.kriskda.user.destinations.UserDetailsScreenDestination
import io.github.kriskda.user.list.UserListViewModel
import io.github.kriskda.user.list.model.UserItem

@RootNavGraph(start = true)
@Destination(style = ScreenTransitionAnimation::class)
@Composable
fun UserListScreen(
    navigator: DestinationsNavigator,
    viewModel: UserListViewModel = hiltViewModel()
) {

    val users by viewModel.usersStateFlow.collectAsStateWithLifecycle()

    UserListScreen(
        users = users,
        onItemClick = { navigator.navigate(UserDetailsScreenDestination(it)) }
    )
}

@Composable
private fun UserListScreen(
    users: List<UserItem>,
    onItemClick: (String) -> Unit
) {
    val listState = rememberLazyListState()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
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
            .clickable { onItemClick(user.id) },
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
        Text(
            text = user.userName,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )
    }
}

@Preview
@Composable
private fun UserListScreenPreview() {
    UserListScreen(
        users = testUsers(),
        onItemClick = {}
    )
}

private fun testUsers() =
    (1..10).map {
        UserItem(
            id = it.toString(),
            userName = "User $it",
            avatarUrl = "https://avatars.githubusercontent.com/u/2589087?s=400&u=b921ee47bbc3ef5e1b03e45877d337ea16bc7590&v=4"
        )
    }