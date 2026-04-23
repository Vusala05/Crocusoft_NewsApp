package com.example.newsapp_jetpackcompose.presentation.home

import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.newsapp_jetpackcompose.common.components.AppCapsule
import com.example.newsapp_jetpackcompose.common.components.NewsItem
import com.example.newsapp_jetpackcompose.core.BaseTheme
import com.example.newsapp_jetpackcompose.core.Drawables
import com.example.newsapp_jetpackcompose.core.Strings
import com.example.newsapp_jetpackcompose.core.utils.DateUtil
import com.example.newsapp_jetpackcompose.presentation.home.components.AppSearchBar
import com.example.newsapp_jetpackcompose.presentation.home.components.NewsItemCard
import com.example.newsapp_jetpackcompose.ui.theme.LocalColors
import kotlinx.coroutines.flow.SharedFlow

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeContent(
    postIntent: (HomeContract.Intent) -> Unit,
    state: HomeContract.State,
    effect: SharedFlow<HomeContract.Effect>,
    onNavigateDetail: (String) -> Unit,
    onNavigateLanguage: () -> Unit
) {
    val colors = LocalColors.current
    val context = LocalContext.current
    val pagerState = rememberPagerState(pageCount = { state.topHeadlinesNews.size })

    LaunchedEffect(effect) {
        effect.collect {
            when(it){
                is HomeContract.Effect.ShowError ->{
                    Toast.makeText(context,it.message, Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = colors.background,
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(BaseTheme.dimens.PaddingLarge),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(BaseTheme.dimens.PaddingTiny)
            ) {
                Box(
                    modifier = Modifier
                        .size(BaseTheme.dimens.icon)
                        .background(colors.primaryText, CircleShape)
                )
                Text(
                    text = stringResource(Strings.news_catcher),
                    style = BaseTheme.textStyle.t20Bold,
                    color = colors.primaryText
                )
            }
        }
    ) { innerPadding ->

        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = BaseTheme.dimens.PaddingLarge)
                .fillMaxSize(),
        ) {

            item {
                Text(
                    text = DateUtil.getCurrentFormattedDate(),
                    style = BaseTheme.textStyle.t13,
                    color = colors.secondaryText
                )
            }

            item {
                Spacer(modifier = Modifier.height(BaseTheme.dimens.PaddingExtraLarge))
            }

            item {
                HorizontalPager(
                    state = pagerState,
                    modifier = Modifier.fillMaxSize()
                ) { page ->
                    val news = state.topHeadlinesNews[page]
                    NewsItemCard(news = news, onClick = {
                        onNavigateDetail(news.url)
                    })
                }

            }

            item {
                Spacer(modifier = Modifier.height(BaseTheme.dimens.PaddingLarge))
            }

            stickyHeader {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(colors.background)
                ) {
                    AppSearchBar(
                        value = state.searchQuery,
                        onValueChange = {
                            postIntent(HomeContract.Intent.OnSearchQueryChange(it))
                        }
                    )
                }
            }

            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = BaseTheme.dimens.PaddingMedium),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    AppCapsule(
                        outerModifier = Modifier.size(BaseTheme.dimens.actionBthWidth, BaseTheme.dimens.actionBthHeight),
                        onClick = onNavigateLanguage,
                        content = {
                            Text(
                                text = "EN",
                                style = BaseTheme.textStyle.t11,
                                color = colors.primaryText
                            )
                            Spacer(modifier = Modifier.width(BaseTheme.dimens.PaddingLarge))

                                androidx.compose.material3.Icon(
                                    painter = painterResource(Drawables.check),
                                    contentDescription = null,
                                    modifier = Modifier.size(BaseTheme.dimens.iconAction),
                                    tint = colors.onSecondary
                                )

                        }
                    )
                }
            }

            if (state.isLoading) {
                item {
                    Box(
                        modifier = Modifier.fillParentMaxSize().background(color = Color.Transparent),
                        contentAlignment = Alignment.Center,

                        ) {
                        CircularProgressIndicator(modifier = Modifier.size(64.dp),
                            color = colors.primaryText,
                            strokeWidth = 4.dp)
                    }
                }
            } else {
                items(
                    items = state.worldNews,
                    key = { news -> news.url }
                ) { news ->
                    NewsItem(
                        newsUiModel = news,
                        onClick = {
                            onNavigateDetail(news.url)
                        }
                    )
                }
            }
        }
    }
}