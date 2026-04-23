package com.example.newsapp_jetpackcompose.presentation.detail.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.newsapp_jetpackcompose.core.BaseTheme
import com.example.newsapp_jetpackcompose.core.Drawables
import com.example.newsapp_jetpackcompose.core.Strings
import com.example.newsapp_jetpackcompose.domain.uimodel.NewsUiModel
import com.example.newsapp_jetpackcompose.presentation.detail.NewsDetailContract
import com.example.newsapp_jetpackcompose.ui.theme.LocalColors

@Composable
fun DropDownMenu(
    postIntent : (NewsDetailContract.Intent) -> Unit,
    expanded : Boolean,
    onDismiss : () -> Unit,
    isNewsSelected : Boolean,
    news : NewsUiModel
){
    val colors = LocalColors.current
    DropdownMenu(
        expanded = expanded,
        onDismissRequest = { onDismiss() },
        modifier = Modifier
            .background(colors.background, shape = RoundedCornerShape(BaseTheme.dimens.PaddingMedium))
            .width(BaseTheme.dimens.settingsCardWidth)

    ) {
        DropdownMenuItem(
            text = { Text(text = stringResource(Strings.save),
                style = BaseTheme.textStyle.t16Bold,
                color = colors.primaryText) },
            leadingIcon = {
                Icon(painter =
                    if(!isNewsSelected)painterResource(Drawables.bookmark)
                    else painterResource(Drawables.bookmark_save),
                    contentDescription = null, modifier = Modifier.size(BaseTheme.dimens.settingsBtn))
            },
            onClick = {
               postIntent(NewsDetailContract.Intent.ToggleSaveBtn(news))
            }
        )
        HorizontalDivider(modifier = Modifier.padding(horizontal = 8.dp))

        DropdownMenuItem(
            text = { Text("Share", style = BaseTheme.textStyle.t16Bold) },
            leadingIcon = {
                Icon(painter = painterResource(Drawables.share), contentDescription = null, tint = Color.Blue,
                    modifier = Modifier.size(BaseTheme.dimens.settingsBtn))
            },
            onClick = {
                onDismiss()
            }
        )

    }
}

