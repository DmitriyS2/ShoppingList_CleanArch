package com.sd.shoppinglist.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sd.shoppinglist.data.ShopItemRepositoryImpl
import com.sd.shoppinglist.domain.DeleteShopItemUseCase
import com.sd.shoppinglist.domain.EditShopItemUseCase
import com.sd.shoppinglist.domain.GetShopListUseCase
import com.sd.shoppinglist.domain.ShopItem

class MainViewModel:ViewModel() {

    private val repository = ShopItemRepositoryImpl

    private val getShopListUseCase = GetShopListUseCase(repository)
    private val deleteShopItemUseCase = DeleteShopItemUseCase(repository)
    private val editShopItemUseCase = EditShopItemUseCase(repository)

//    val shopList = MutableLiveData<List<ShopItem>>()
//
//    fun getShopList() {
//        val list = getShopListUseCase.getShopList()
//        shopList.value = list
//    }
    val shopList = getShopListUseCase.getShopList()

    fun deleteShopItem(shopItem: ShopItem) {
        deleteShopItemUseCase.deleteShopItem(shopItem)
   //     getShopList()
    }

    fun changeEnableState(shopItem: ShopItem) {
        val newSHopItem = shopItem.copy(enabled = !shopItem.enabled)
        editShopItemUseCase.editShopItem(newSHopItem)
  //      getShopList()
    }
}