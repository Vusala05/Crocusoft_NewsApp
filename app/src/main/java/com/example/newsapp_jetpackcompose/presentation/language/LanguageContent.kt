package com.example.newsapp_jetpackcompose.presentation.language

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import com.example.newsapp_jetpackcompose.common.components.AppCapsule
import com.example.newsapp_jetpackcompose.core.BaseTheme
import com.example.newsapp_jetpackcompose.core.Drawables
import com.example.newsapp_jetpackcompose.core.language.AppLanguage
import com.example.newsapp_jetpackcompose.presentation.language.components.LanguageItem
import com.example.newsapp_jetpackcompose.ui.theme.LocalColors
@Composable
fun LanguageContent(
    postIntent: (LanguageContract.Intent) -> Unit,
    state: LanguageContract.State,
    onNavigateBack: () -> Unit
) {
    val colors = LocalColors.current
    val context = LocalContext.current

    LaunchedEffect(key1 = context) {
        postIntent(LanguageContract.Intent.LoadCurrentLanguage(context))
    }


    val languages = remember(state.selectedLanguageCode) {
        listOfNotNull(
            AppLanguage.fromCode(state.selectedLanguageCode),
            AppLanguage.EN,
            AppLanguage.AZ,
            AppLanguage.RU,
            AppLanguage.TR
        ).distinct()
    }

    Scaffold(
        modifier = Modifier.padding(top = BaseTheme.dimens.PaddingSmall),
        topBar = {
            AppCapsule(
                innerModifier = Modifier.padding(end = BaseTheme.dimens.PaddingLarge ),
                outerModifier = Modifier.size(BaseTheme.dimens.navigation, BaseTheme.dimens.detailBackActionHeight)
                    .padding(horizontal = BaseTheme.dimens.PaddingMedium),
                onClick = onNavigateBack
            ) {
                Icon(
                    painter = painterResource(Drawables.back_btn),
                    contentDescription = null,
                    modifier = Modifier.width(BaseTheme.dimens.backBtnWidth),
                    tint = colors.primaryText
                )
            }
        }
    ) { innerPadding ->

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
        ) {
            items(
                items = languages,
                key = { it.code }
            ) { language ->

                LanguageItem(
                    language = language,
                    isSelected = language.code == state.selectedLanguageCode,
                    onClick = {
                        postIntent(
                            LanguageContract.Intent.SelectLanguageCode(
                                context = context,
                                code = language.code
                            )
                        )
                    }
                )
            }
        }
    }
}