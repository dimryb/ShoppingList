package space.rybakov.shoppinglist.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import space.rybakov.shoppinglist.domain.DeleteShopItemUseCase
import space.rybakov.shoppinglist.domain.EditShopItemUseCase
import space.rybakov.shoppinglist.domain.GetShopListUseCase
import space.rybakov.shoppinglist.domain.ShopItem
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val getShopListUseCase: GetShopListUseCase,
    private val deleteShopItemUseCase: DeleteShopItemUseCase,
    private val editShopItemUseCase: EditShopItemUseCase,
) : ViewModel() {

    val shopList = getShopListUseCase.getShopList()

    fun deleteShopItem(shopItem: ShopItem) {
        viewModelScope.launch {
            deleteShopItemUseCase.deleteShopItem(shopItem)
        }
    }

    fun changeEnableState(shopItem: ShopItem) {
        viewModelScope.launch {
            val newItem = shopItem.copy(enabled = !shopItem.enabled)
            editShopItemUseCase.editShopItem(newItem)
        }
    }

    fun editShopItem(shopItem: ShopItem) {
        Log.d("MainViewModel", "editShopItem $shopItem")
    }
}