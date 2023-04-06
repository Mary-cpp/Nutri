package com.example.nutri.ui.navigation

import androidx.navigation.NavHostController
import androidx.navigation.NavOptions

class NavControllerHolder{

    var navController: NavHostController? = null

    private val navOptions = setNavOptions()

    private fun setNavOptions() : NavOptions {

        val optionsBuilder = NavOptions.Builder()
        return optionsBuilder.apply {
            setLaunchSingleTop(true)
            setRestoreState(true)
            navController?.graph?.startDestinationId?.let {
                setPopUpTo(
                    it,
                    inclusive = false,
                    saveState = true
                )
            }
        }.build()
    }

    fun navigateBack(){
        navController!!.navigateUp()
    }

    fun navigateToRoute(route: String){
        navController?.navigate(
            route = route,
            navOptions = navOptions
        )
    }

    fun navigateWithArgs(route: String, args: String){
        navController?.navigate(
            route = "$route/$args",
            navOptions = navOptions
        )
    }
}