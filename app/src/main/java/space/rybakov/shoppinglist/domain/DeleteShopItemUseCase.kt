package space.rybakov.shoppinglist.domain

class DeleteShopItemUseCase(private val shopListRepository: ShopListRepository) {
    fun deleteShopItem(shopItemId: Int) {
        shopListRepository.getShopItem(shopItemId)
    }
}