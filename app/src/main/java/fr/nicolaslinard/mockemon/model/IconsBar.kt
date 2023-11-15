package fr.nicolaslinard.mockemon.model

import fr.nicolaslinard.mockemon.R

data class IconsBar(
    val icon: Int,
    val iconSelected: Boolean = false,
    val title: String

)
object IconsBarProvider {
    fun getIconsBar(): MutableList<IconsBar> {
        return mutableListOf(
            IconsBar(
                icon = R.drawable.baseline_translate_24,
                title = "Traduire"
            ),
            IconsBar(
                icon = R.drawable.baseline_apps_24,
                iconSelected = true,
                title = "Applications"
            ),
            IconsBar(
                icon = R.drawable.baseline_refresh_24,
                title = "Rafraichir"
            )
        )
    }
}