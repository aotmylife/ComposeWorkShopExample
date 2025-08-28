package com.oat.dev.composewithviewmodel.screen

import kotlinx.serialization.Serializable

sealed class Screen {
    @Serializable
    object Main : Screen()

    @Serializable
    object Detail : Screen()
}