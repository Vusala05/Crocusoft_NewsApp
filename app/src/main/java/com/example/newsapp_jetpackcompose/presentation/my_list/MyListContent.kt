package com.example.newsapp_jetpackcompose.presentation.my_list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.newsapp_jetpackcompose.common.components.NewsItem
import com.example.newsapp_jetpackcompose.core.BaseTheme
import com.example.newsapp_jetpackcompose.core.Strings
import com.example.newsapp_jetpackcompose.core.utils.DateUtil
import com.example.newsapp_jetpackcompose.ui.theme.LocalColors

@Composable
fun MyListContent(
    state: MyListContract.State,
    onNewsClick: (String) -> Unit
) {
    val colors = LocalColors.current

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(BaseTheme.dimens.PaddingLarge)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(BaseTheme.dimens.PaddingTiny)
                ) {
                    Box(
                        modifier = Modifier
                            .size(BaseTheme.dimens.PaddingMedium)
                            .background(colors.primaryText, CircleShape)
                    )
                    Text(
                        text = stringResource(Strings.news_catcher),
                        style = BaseTheme.textStyle.t20Bold,
                        color = colors.primaryText
                    )
                }

                Text(
                    text = DateUtil.getCurrentFormattedDate(),
                    modifier = Modifier.padding(top = BaseTheme.dimens.PaddingMedium),
                    style = BaseTheme.textStyle.t12,
                    color = colors.secondaryText
                )
            }
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(BaseTheme.dimens.PaddingSmall)
        ) {
            items(state.bookMarkedList) { news ->
                NewsItem(
                    newsUiModel = news,
                    onClick = { onNewsClick(news.url)
                    }
                )
            }
        }
    }
}