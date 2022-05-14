package space.rybakov.shoppinglist.presentation

import androidx.lifecycle.ViewModel
import space.rybakov.shoppinglist.data.ShopListRepositoryImpl
import space.rybakov.shoppinglist.domain.AddShopItemUseCase
import space.rybakov.shoppinglist.domain.EditShopItemUseCase
import space.rybakov.shoppinglist.domain.GetShopItemUseCase
import space.rybakov.shoppinglist.domain.ShopItem
import java.lang.Exception

class ShopItemViewModel : ViewModel() {
    private val repository = ShopListRepositoryImpl

    private val getShopItemUseCase = GetShopItemUseCase(repository)
    private val addShopItemUseCase = AddShopItemUseCase(repository)
    private val editShopItemUseCase = EditShopItemUseCase(repository)

    fun getStopItem(shopItemId: Int) {
        val item = getShopItemUseCase.getShopItem(shopItemId)

    }

    fun addShopItem(inputName: String?, inputCount: String?) {
        val name = parseName(inputName)
        val count = parseCount(inputCount)
        val fieldsValid = validateInput(name, count)
        if (fieldsValid){
            val shopItem = ShopItem(name, count, true)
            addShopItemUseCase.addShopItem(shopItem)
        }
    }

    fun editShopItem(shopItemId: Int, inputName: String?, inputCount: String?) {
        val name = parseName(inputName)
        val count = parseCount(inputCount)
        val fieldsValid = validateInput(name, count)
        if (fieldsValid){
            val shopItem = getShopItemUseCase.getShopItem(shopItemId)
            val newShopItem = ShopItem(name, count, shopItem.enabled, shopItem.id)
            editShopItemUseCase.editShopItem(newShopItem)
        }
    }

    private fun parseName(inputName: String?): String {
        return inputName?.trim() ?: ""
    }

    private fun parseCount(inputCount: String?): Int {
        return try {
            inputCount?.trim()?.toInt() ?: 0
        } catch (e: Exception) {
            0
        }
    }

    private fun validateInput(name: String, count: Int): Boolean {
        var result = true
        if (name.isBlank()) {
            //TODO: show error input name
            result = false
        }
        if (count <= 0) {
            //TODO: show error input count
            result = false
        }
        return result
    }
}