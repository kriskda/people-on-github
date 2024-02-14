package io.github.kriskda.user.details.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import io.github.kriskda.R
import io.github.kriskda.common.animation.ScreenTransitionAnimation
import io.github.kriskda.common.info.FailureView
import io.github.kriskda.common.info.LoadingView
import io.github.kriskda.user.details.UserDetailsViewModel
import io.github.kriskda.user.details.model.UserDetails
import io.github.kriskda.user.details.model.UserDetailsNavArgs
import io.github.kriskda.user.details.model.UserDetailsUiState

@Destination(
    style = ScreenTransitionAnimation::class,
    navArgsDelegate = UserDetailsNavArgs::class
)
@Composable
fun UserDetailsScreen(
    viewModel: UserDetailsViewModel = hiltViewModel()
) {
    val uiState by viewModel.userDetailsUiState.collectAsStateWithLifecycle()

    UserDetailsScreen(uiState)
}

@Composable
private fun UserDetailsScreen(
    uiState: UserDetailsUiState
) {
    when (uiState) {
        UserDetailsUiState.Loading -> LoadingView()
        is UserDetailsUiState.Loaded -> UserDetailsView(uiState.user)
        UserDetailsUiState.Failure -> FailureView()
    }
}

@Composable
private fun UserDetailsView(
    user: UserDetails
) {
    Column(
        modifier = Modifier.fillMaxSize().padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(0.5f)
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
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Bold
        )
    }
}

@Preview
@Composable
private fun UserDetailsScreenPreview_Loading() {
    UserDetailsScreen(
        uiState = UserDetailsUiState.Loading
    )
}

@Preview
@Composable
private fun UserDetailsScreenPreview_Loaded() {
    UserDetailsScreen(
        uiState = UserDetailsUiState.Loaded(userDetails())
    )
}

@Preview
@Composable
private fun UserDetailsScreenPreview_Failure() {
    UserDetailsScreen(
        uiState = UserDetailsUiState.Failure
    )
}

private fun userDetails() =
    UserDetails(
        id = "User 1",
        userName = "User 1",
        avatarUrl = "https://avatars.githubusercontent.com/u/2589087?s=400&u=b921ee47bbc3ef5e1b03e45877d337ea16bc7590&v=4"
    )