package com.example.nutri.ui.navigation

import android.os.Bundle
import androidx.core.os.bundleOf

interface NavigationRouter{
    fun routeTo(screenRoute : String, params:Bundle = bundleOf()){
        throw NotImplementedError (message = "You used router, but don't implemented it for screen $screenRoute with params $params")
    }
}