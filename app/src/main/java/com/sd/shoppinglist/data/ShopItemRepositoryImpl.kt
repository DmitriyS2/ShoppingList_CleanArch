package com.sd.shoppinglist.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sd.shoppinglist.domain.ShopItem
import com.sd.shoppinglist.domain.ShopListRepository
import java.lang.RuntimeException

object ShopItemRepositoryImpl:ShopListRepository {
    private val shopList = mutableListOf<ShopItem>()
    private val shopListLD = MutableLiveData<List<ShopItem>>()
    private var autoIncrementId = 0

    init {
        for (i in 0 until 10) {
            val item = ShopItem("Name $i", i, false)
            addShopItem(item)
        }
    }

    override fun addShopItem(shopItem: ShopItem) {
       if (shopItem.id==ShopItem.UNDEFINED_ID) {
           shopItem.id = autoIncrementId++
       }
        shopList.add(shopItem)
        updateList()
    }

    override fun deleteShopItem(shopItem: ShopItem) {
        shopList.remove(shopItem)
        updateList()
    }

    override fun editShopItem(shopItem: ShopItem) {
       val oldElement = getShopItem(shopItem.id)
        deleteShopItem(oldElement)
        addShopItem(shopItem)
    }

    override fun getShopItem(id: Int): ShopItem {
        return shopList.find {
            it.id==id
        } ?: throw RuntimeException("not found element with id=$id")
    }

    override fun getShopList(): LiveData<List<ShopItem>> {
      return shopListLD
    }

    private fun updateList() {
        shopListLD.value = shopList.toList() // toList - чтобы вернуть копию
    }
}