package com.example.newsapp_jetpackcompose.presentation.detail

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
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.newsapp_jetpackcompose.common.components.AppCapsule
import com.example.newsapp_jetpackcompose.core.BaseTheme
import com.example.newsapp_jetpackcompose.core.Drawables
import com.example.newsapp_jetpackcompose.presentation.detail.components.DetailPart
import com.example.newsapp_jetpackcompose.presentation.detail.components.DropDownMenu
import com.example.newsapp_jetpackcompose.ui.theme.LocalColors
import kotlinx.coroutines.flow.SharedFlow

@Composable
fun NewsDetailContent(
    state : NewsDetailContract.State,
    postIntent : (NewsDetailContract.Intent) -> Unit,
    effect : SharedFlow<NewsDetailContract.Effect>,
    onNavigateBack: () -> Unit
) {
    val colors = LocalColors.current
    var expanded by remember { mutableStateOf(false) }
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        AsyncImage(
            model = state.selectedNews.urlToImage,
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(BaseTheme.dimens.DetailImageHeight),
            contentScale = ContentScale.Fit
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 24.dp)
                .align(Alignment.TopStart),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            AppCapsule(
                modifier = Modifier
                    .width(BaseTheme.dimens.detailBackActionWidth)
                    .height(BaseTheme.dimens.detailBackActionHeight),
                onClick = onNavigateBack
            ) {
                Icon(
                    painter = painterResource(Drawables.back_btn),
                    contentDescription = null,
                    modifier = Modifier.size(BaseTheme.dimens.backBtnWidth),
                    tint = colors.primaryText
                )

                Spacer(modifier = Modifier.width(BaseTheme.dimens.PaddingTiny))

                Text(
                    text = state.selectedNews.title,
                    style = BaseTheme.textStyle.t10,
                    color = colors.primaryText,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
            Box {
                AppCapsule(
                    modifier = Modifier.size(BaseTheme.dimens.iconSize),
                    onClick = { expanded = true }
                ) {
                    Icon(
                        painter = painterResource(Drawables.settings),
                        contentDescription = null,
                        modifier = Modifier.size(18.dp),
                        tint = colors.primaryText
                    )
                }
                DropDownMenu(expanded = expanded,
                    onDismiss = {expanded = false},
                    isNewsSelected = state.isBookmarked,
                    postIntent = postIntent,
                    news = state.selectedNews)
            }
        }

        DetailPart(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = BaseTheme.dimens.topPadding)
                .align(Alignment.TopStart),
            newsUiModel = state.selectedNews,
            onCLick = {}
        )
    }
}