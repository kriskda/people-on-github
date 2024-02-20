package io.github.kriskda.common.preview

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.paging.PagingData
import io.github.kriskda.user.details.model.UserDetails
import io.github.kriskda.user.list.model.UserItem

@Preview(name = "Dark Mode", showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Preview(name = "Light Mode", showBackground = true, uiMode = UI_MODE_NIGHT_NO)
annotation class ThemePreviews

class UserDetailsProvider : PreviewParameterProvider<UserDetails> {
    override val values = sequenceOf(
        UserDetails(
            id = 1,
            userName = "User 1",
            avatarUrl = "https://avatars.githubusercontent.com/u/2589087?s=400&u=b921ee47bbc3ef5e1b03e45877d337ea16bc7590&v=4",
            profileUrl = "https://test.com",
            publicReposCount = "12",
            followersCount = "2",
            followingCount = "9",
        )
    )
}

class UserItemListProvider : PreviewParameterProvider<PagingData<UserItem>> {
    override val values = sequenceOf(
        PagingData.from(
            (1..10).map {
                UserItem(
                    id = it,
                    userName = "User $it",
                    avatarUrl = "https://avatars.githubusercontent.com/u/2589087?s=400&u=b921ee47bbc3ef5e1b03e45877d337ea16bc7590&v=4"
                )
            }
        )
    )
}
