package com.oat.dev.composewithviewmodel.Screen

import kotlinx.serialization.Serializable

sealed class Screen {
    @Serializable
    object Main : Screen()

    @Serializable
    object Detail : Screen()
}