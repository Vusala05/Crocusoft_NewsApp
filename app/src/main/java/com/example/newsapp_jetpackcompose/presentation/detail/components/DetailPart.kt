package com.example.newsapp_jetpackcompose.presentation.detail.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.newsapp_jetpackcompose.common.components.AppCapsule
import com.example.newsapp_jetpackcompose.core.BaseTheme
import com.example.newsapp_jetpackcompose.core.Strings
import com.example.newsapp_jetpackcompose.domain.uimodel.NewsUiModel
import com.example.newsapp_jetpackcompose.ui.theme.LocalColors

@Composable
fun DetailPart(
    modifier: Modifier = Modifier,
    newsUiModel: NewsUiModel,
    onCLick : () -> Unit
){
    val scrollState = rememberScrollState()
    val colors = LocalColors.current
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = colors.background, shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp)),
             contentAlignment = Alignment.TopCenter
    ){

        Column(
            modifier = Modifier
                .padding(horizontal = BaseTheme.dimens.PaddingExtraLarge).padding(top = BaseTheme.dimens.PaddingMedium)
                .verticalScroll(scrollState),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(BaseTheme.dimens.PaddingLarge),
            ) {
            AppCapsule(
                modifier = Modifier.align(Alignment.Start),
              onClick = {},
                content = {
                    Text(text = "Finance", style = BaseTheme.textStyle.t11, color = colors.primaryText)
                }
            )
            Text(text = newsUiModel.title, style = BaseTheme.textStyle.t26Bold, color = colors.primaryText)
            Text(text = newsUiModel.author, modifier = Modifier.align(Alignment.End) ,style = BaseTheme.textStyle.t12Bold, color = colors.authColor)
            Text(text = newsUiModel.description ,style = BaseTheme.textStyle.t15Bold, color = colors.primaryText)
            Text(text = newsUiModel.content ,
                style = BaseTheme.textStyle.t12,
                color = colors.secondaryText)
            Text(text = stringResource(Strings.read_more),
                modifier = Modifier.align(Alignment.End),
                style = BaseTheme.textStyle.t12Bold,
                color = colors.readMoreColor)





        }

    }

}