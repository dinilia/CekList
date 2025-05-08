package com.andiniaulia3119.checklist.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andiniaulia3119.checklist.data.Item
import com.andiniaulia3119.checklist.data.ItemRepository
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ItemViewModel(private val repository: ItemRepository) : ViewModel() {

    private val _items = MutableStateFlow<List<Item>>(emptyList())
    val items: StateFlow<List<Item>> = _items

    init {
        // Mengambil data item dari repository
        getItems()
    }

    private fun getItems() {
        viewModelScope.launch {
            // Anda dapat menggantikan kode ini dengan logika dari repository
            _items.value = repository.getAllItems()
        }
    }

    fun addItem(item: Item) {
        viewModelScope.launch {
            repository.insertItem(item)
            getItems() // Update list setelah menambahkan item baru
        }
    }
}
