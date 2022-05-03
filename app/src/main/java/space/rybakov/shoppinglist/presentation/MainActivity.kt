package space.rybakov.shoppinglist.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import space.rybakov.shoppinglist.R
import space.rybakov.shoppinglist.domain.ShopItem

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var shopListAdaptor: ShopListAdaptor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupRecyclerView()
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.shopList.observe(this) {
            shopListAdaptor.shopList = it
        }
    }

    private fun setupRecyclerView() {
        val rvShopList = findViewById<RecyclerView>(R.id.rv_shop_list)
        with(rvShopList) {
            shopListAdaptor = ShopListAdaptor()
            adapter = shopListAdaptor
            recycledViewPool.setMaxRecycledViews(
                ShopListAdaptor.VIEW_TYPE_ENABLED,
                ShopListAdaptor.MAX_PULL_SIZE
            )
            recycledViewPool.setMaxRecycledViews(
                ShopListAdaptor.VIEW_TYPE_DISABLED,
                ShopListAdaptor.MAX_PULL_SIZE
            )
        }
        shopListAdaptor.onShopItemLongClickListener = {
            viewModel.changeEnableState(it)
        }
    }
}