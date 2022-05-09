package space.rybakov.shoppinglist.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import space.rybakov.shoppinglist.data.ShopListRepositoryImpl
import space.rybakov.shoppinglist.domain.DeleteShopItemUseCase
import space.rybakov.shoppinglist.domain.EditShopItemUseCase
import space.rybakov.shoppinglist.domain.GetShopListUseCase
import space.rybakov.shoppinglist.domain.ShopItem

class MainViewModel : ViewModel() {

    private val repository = ShopListRepositoryImpl

    private val getShopListUseCase = GetShopListUseCase(repository)
    private val deleteShopItemUseCase = DeleteShopItemUseCase(repository)
    private val editShopItemUseCase = EditShopItemUseCase(repository)

    val shopList = getShopListUseCase.getShopList()

    fun deleteShopItem(shopItem: ShopItem) {
        deleteShopItemUseCase.deleteShopItem(shopItem)
    }

    fun changeEnableState(shopItem: ShopItem) {
        val newItem = shopItem.copy(enabled = !shopItem.enabled)
        editShopItemUseCase.editShopItem(newItem)
    }

    fun editShopItem(shopItem: ShopItem) {
        Log.d("MainViewModel", "editShopItem $shopItem")
    }
}