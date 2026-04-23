package com.example.newsapp_jetpackcompose.presentation.language.components

import android.R
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.newsapp_jetpackcompose.core.BaseTheme
import com.example.newsapp_jetpackcompose.core.language.AppLanguage
import com.example.newsapp_jetpackcompose.ui.theme.LocalColors

@Composable
fun LanguageItem(
    modifier: Modifier = Modifier,
    language: AppLanguage,
    isSelected : Boolean,
    onClick : () -> Unit
) {
    val colors = LocalColors.current
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(BaseTheme.dimens.PaddingSmall),
    ) {

        Text(
            text = stringResource(language.titleRes),
            style = BaseTheme.textStyle.t18Bold,
            color = if(isSelected) colors.selectedItem else colors.primaryText,
            modifier = Modifier.padding(BaseTheme.dimens.PaddingLarge)
                .clickable(onClick = onClick))

        HorizontalDivider(
            modifier = Modifier.fillMaxWidth(),
            thickness = 1.dp,
            color = colors.primaryText)
    }

}