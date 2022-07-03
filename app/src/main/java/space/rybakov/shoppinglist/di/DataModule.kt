package space.rybakov.shoppinglist.di

import android.app.Application
import dagger.Binds
import dagger.Module
import dagger.Provides
import space.rybakov.shoppinglist.data.AppDatabase
import space.rybakov.shoppinglist.data.ShopListDao
import space.rybakov.shoppinglist.data.ShopListRepositoryImpl
import space.rybakov.shoppinglist.domain.ShopListRepository

@Module
interface DataModule {

    @ApplicationScope
    @Binds
    fun bindShopListRepository(impl: ShopListRepositoryImpl): ShopListRepository

    companion object {

        @ApplicationScope
        @Provides
        fun providesShopListDao(
            application: Application
        ): ShopListDao {
            return AppDatabase.getInstance(application).shopListDao()
        }
    }
}