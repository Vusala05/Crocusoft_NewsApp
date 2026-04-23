package com.example.newsapp_jetpackcompose.core

import com.example.newsapp_jetpackcompose.core.constants.BaseTextStyle
import com.example.newsapp_jetpackcompose.core.constants.Dimens

abstract class BaseTheme {

    companion object{
     val dimens : Dimens
         get() = Dimens

     val textStyle : BaseTextStyle
         get() = BaseTextStyle
    }
}