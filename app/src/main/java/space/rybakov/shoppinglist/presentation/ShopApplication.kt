package space.rybakov.shoppinglist.presentation

import android.app.Application
import space.rybakov.shoppinglist.di.DaggerApplicationComponent

class ShopApplication : Application() {

    val component by lazy {
        DaggerApplicationComponent.factory().create(this)
    }
}