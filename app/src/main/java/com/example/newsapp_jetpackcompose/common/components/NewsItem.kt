package com.example.newsapp_jetpackcompose.common.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.newsapp_jetpackcompose.core.BaseTheme
import com.example.newsapp_jetpackcompose.core.Drawables
import com.example.newsapp_jetpackcompose.domain.uimodel.NewsUiModel
import com.example.newsapp_jetpackcompose.ui.theme.LocalColors
import com.example.newsapp_jetpackcompose.ui.theme.NewsApp_JetpackComposeTheme

@Composable
fun NewsItem(
    modifier: Modifier = Modifier,
    newsUiModel: NewsUiModel,
    onClick: () -> Unit
) {
    val colors = LocalColors.current

    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .background(Color.Transparent)
            .padding(BaseTheme.dimens.PaddingMedium),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            AppCapsule(
                onClick = {},
                content = {
                    Text(
                        text = "Finance",
                        style = BaseTheme.textStyle.t10,
                        color = colors.primaryText
                    )
                }
            )

            Text(
                text = newsUiModel.title,
                style = BaseTheme.textStyle.t16Bold,
                color = colors.primaryText,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = newsUiModel.publishedAt,
                    style = BaseTheme.textStyle.t10,
                    color = colors.secondaryText,
                )
                Text(
                    text = newsUiModel.author,
                    style = BaseTheme.textStyle.t12Bold,
                    color = colors.primaryText,
                   // maxLines = 1,
                    //overflow = TextOverflow.Ellipsis
                )
            }
        }

        AsyncImage(
            model = newsUiModel.urlToImage,
            contentDescription = null,
            modifier = Modifier
                .height(BaseTheme.dimens.photoHeight)
                .width(BaseTheme.dimens.photoWidth)
                .clip(RoundedCornerShape(size = BaseTheme.dimens.PaddingMedium)),
            contentScale = ContentScale.Crop ,
            placeholder = painterResource( Drawables.ic_launcher_background)
        )
    }
}
/*
@Preview
@Composable
fun SimpleComposablePrevieww() {
    NewsApp_JetpackComposeTheme {
        NewsItem(newsUiModel = NewsUiModel.mock, onClick = {})
    }
}*/
