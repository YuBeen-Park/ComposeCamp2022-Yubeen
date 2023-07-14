package com.example.affirmationscodelab.ui.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Affirmation(
    @StringRes val strResId: Int,
    @DrawableRes val imgResId: Int,
)
