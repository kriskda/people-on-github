package io.github.kriskda.user.details.view

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.ramcosta.composedestinations.annotation.Destination
import io.github.kriskda.R
import io.github.kriskda.common.preview.ThemePreviews
import io.github.kriskda.common.animation.ScreenTransitionAnimation
import io.github.kriskda.common.info.FailureView
import io.github.kriskda.common.info.LoadingView
import io.github.kriskda.common.preview.UserDetailsProvider
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
    val context = LocalContext.current

    UserDetailsScreen(
        uiState = uiState,
        onProfileUrlClick = {
            val urlIntent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse(it)
            )
            context.startActivity(urlIntent)
        }
    )
}

@Composable
private fun UserDetailsScreen(
    uiState: UserDetailsUiState,
    onProfileUrlClick: (String) -> Unit,
) {
    when (uiState) {
        UserDetailsUiState.Loading -> LoadingView()
        is UserDetailsUiState.Loaded -> UserDetailsView(
            user = uiState.user,
            onProfileUrlClick = onProfileUrlClick,
        )

        UserDetailsUiState.Failure -> FailureView()
    }
}

@Composable
private fun UserDetailsView(
    user: UserDetails,
    onProfileUrlClick: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxSize()
            .padding(8.dp),
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

        ClickableText(
            text = AnnotatedString(
                text = user.profileUrl,
                spanStyle = SpanStyle(
                    color = Color.Blue,
                    fontSize = 16.sp,
                    textDecoration = TextDecoration.Underline
                )
            ),
            onClick = { onProfileUrlClick(user.profileUrl) }
        )

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.Start,
        ) {
            Text(
                text = stringResource(R.string.user_details_public_repos, user.publicReposCount),
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                text = stringResource(R.string.user_details_followers, user.followersCount),
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                text = stringResource(R.string.user_details_following, user.followingCount),
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

@ThemePreviews
@Composable
private fun UserDetailsScreenPreview_Loaded(
    @PreviewParameter(UserDetailsProvider::class) userDetails: UserDetails
) {
    UserDetailsScreen(
        uiState = UserDetailsUiState.Loaded(userDetails),
        onProfileUrlClick = {},
    )
}

@ThemePreviews
@Composable
private fun UserDetailsScreenPreview_Loading() {
    UserDetailsScreen(
        uiState = UserDetailsUiState.Loading,
        onProfileUrlClick = {},
    )
}

@ThemePreviews
@Composable
private fun UserDetailsScreenPreview_Failure() {
    UserDetailsScreen(
        uiState = UserDetailsUiState.Failure,
        onProfileUrlClick = {},
    )
}