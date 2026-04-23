package com.example.newsapp_jetpackcompose.presentation.home.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import com.example.newsapp_jetpackcompose.common.components.AppCapsule
import com.example.newsapp_jetpackcompose.core.BaseTheme
import com.example.newsapp_jetpackcompose.domain.uimodel.NewsUiModel
import com.example.newsapp_jetpackcompose.ui.theme.LocalColors

@Composable
fun NewsItemCard(
    modifier: Modifier = Modifier,
    news: NewsUiModel,
    onClick: () -> Unit
) {
    val colors = LocalColors.current
    Card(
        modifier = modifier
            .width(BaseTheme.dimens.articleCardWidth)
            .height(BaseTheme.dimens.articleCardHeight)
            .clickable { onClick() },
        shape = RoundedCornerShape(BaseTheme.dimens.PaddingMedium)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {

            AsyncImage(
                model = news.urlToImage,
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop,
            )


            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.Black.copy(alpha = 0.7f)
                            ),
                            startY = 300f
                        )
                    )
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(BaseTheme.dimens.PaddingMedium),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                AppCapsule(
                    modifier = Modifier.align(Alignment.Start),
                    onClick = {},
                    content = {
                        Text(
                            text = "Finance",
                            style = BaseTheme.textStyle.t11,
                            color = colors.primaryText
                        )
                    }
                )
                Text(
                    text = news.title,
                    style = BaseTheme.textStyle.t18Bold,
                    color = colors.primaryText,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(bottom = BaseTheme.dimens.PaddingSmall)
                )
            }
        }
    }
}

