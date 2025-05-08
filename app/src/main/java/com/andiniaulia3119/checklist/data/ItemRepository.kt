package com.andiniaulia3119.checklist.data

class ItemRepository(private val itemDao: ItemDao) {

    suspend fun getAllItems(): List<Item> {
        return itemDao.getAllItems()
    }

    suspend fun insertItem(item: Item) {
        itemDao.insertItem(item)
    }
}
