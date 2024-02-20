package io.github.kriskda.user.list.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import io.github.kriskda.common.animation.ScreenTransitionAnimation
import io.github.kriskda.common.info.FailureView
import io.github.kriskda.common.info.LoadingView
import io.github.kriskda.common.preview.ThemePreviews
import io.github.kriskda.common.preview.UserItemListProvider
import io.github.kriskda.user.destinations.UserDetailsScreenDestination
import io.github.kriskda.user.list.UserListViewModel
import io.github.kriskda.user.list.model.UserItem
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch

@RootNavGraph(start = true)
@Destination(style = ScreenTransitionAnimation::class)
@Composable
fun UsersScreen(
    navigator: DestinationsNavigator,
    viewModel: UserListViewModel = hiltViewModel()
) {
    val usersPagingFlow = viewModel.usersPagingFlow.collectAsLazyPagingItems()
    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    UsersScreen(
        usersPagingItems = usersPagingFlow,
        listState = listState,
        onItemClick = { navigator.navigate(UserDetailsScreenDestination(it)) },
        onLoadMore = { usersPagingFlow.retry() },
        onSearch = { searchText ->
            val itemIndex = usersPagingFlow.itemSnapshotList.indexOfFirst {
                it?.userName?.contains(searchText) ?: false
            }
            if (itemIndex != -1) {
                coroutineScope.launch {
                    listState.animateScrollToItem(itemIndex)
                }
            }
        }
    )
}

@Composable
private fun UsersScreen(
    usersPagingItems: LazyPagingItems<UserItem>,
    listState: LazyListState,
    onItemClick: (String) -> Unit,
    onLoadMore: () -> Unit,
    onSearch: (String) -> Unit,
) {
    Column(
        modifier = Modifier.padding(8.dp)
    ) {
        Box(
            modifier = Modifier.weight(1f)
        ) {
            UserListView(
                listState = listState,
                users = usersPagingItems,
                onItemClick = onItemClick,
            )
            when (usersPagingItems.loadState.append) {
                is LoadState.Error ->
                    ErrorStateInfoView(
                        modifier = Modifier.align(Alignment.BottomCenter),
                        onLoadMore = onLoadMore,
                    )

                LoadState.Loading ->
                    LoadingStateInfoView(
                        modifier = Modifier.align(Alignment.BottomCenter),
                    )

                else -> Unit
            }

            when (usersPagingItems.loadState.refresh) {
                is LoadState.Error -> FailureView()
                LoadState.Loading -> LoadingView()
                else -> Unit
            }
        }
        SearchView(
            onSearch = onSearch
        )
    }
}

@ThemePreviews
@Composable
private fun UserListScreenPreview(
    @PreviewParameter(UserItemListProvider::class) users: PagingData<UserItem>
) {
    UsersScreen(
        usersPagingItems = flowOf(users).collectAsLazyPagingItems(),
        listState = LazyListState(),
        onItemClick = {},
        onLoadMore = {},
        onSearch = {}
    )
}