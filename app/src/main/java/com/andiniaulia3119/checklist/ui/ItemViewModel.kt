package com.andiniaulia3119.checklist.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andiniaulia3119.checklist.data.Item
import com.andiniaulia3119.checklist.data.ItemRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ItemViewModel(private val repository: ItemRepository) : ViewModel() {

    private val _items = MutableStateFlow<List<Item>>(emptyList())
    val items: StateFlow<List<Item>> = _items

    init {
        getItems()
    }

    private fun getItems() {
        viewModelScope.launch {
            _items.value = repository.getAllItems()
        }
    }

    fun addItem(item: Item) {
        viewModelScope.launch {
            repository.insertItem(item)
            getItems()
        }
    }

    fun deleteItem(item: Item) {
        viewModelScope.launch {
            repository.deleteItem(item)
        }
    }

    fun updateItem(item: Item) {
        viewModelScope.launch {
            repository.updateItem(item)
            getItems()
        }
    }
}
