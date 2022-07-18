package space.rybakov.shoppinglist.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import space.rybakov.shoppinglist.data.ShopListProvider
import space.rybakov.shoppinglist.presentation.MainActivity
import space.rybakov.shoppinglist.presentation.ShopItemFragment

@ApplicationScope
@Component(
    modules = [
        DataModule::class,
        ViewModelModule::class
    ]
)
interface ApplicationComponent {

    fun inject(activity: MainActivity)

    fun inject(fragment: ShopItemFragment)

    fun inject(provider: ShopListProvider)

    @Component.Factory
    interface Factory {

        fun create (
            @BindsInstance application: Application
        ): ApplicationComponent
    }
}