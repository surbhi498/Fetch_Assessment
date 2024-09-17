package com.example.myassessment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch



class ItemViewModel : ViewModel() {
    private val _groupedItems = MutableLiveData<Map<Int, List<Item>>>()
    val groupedItems: LiveData<Map<Int, List<Item>>> get() = _groupedItems

    init {
        viewModelScope.launch {
            val response = RetrofitClient.apiService.getItems()
            // Step 1: Filter out items where "name" is blank or null
            val filteredItems = response.filter { !it.name.isNullOrBlank() }

            // Step 2: Group items by "listId"
            val grouped = filteredItems.groupBy { it.listId }

            // Step 3: Sort the groups by listId and then sort items within each group
            val sortedGrouped = grouped.toSortedMap().mapValues { (_, items) ->
                items.sortedWith(compareBy<Item> { item ->
                    val nameWithoutItem = item.name?.replace("Item", "", ignoreCase = true)?.trim()
                    nameWithoutItem?.toIntOrNull() ?: Int.MAX_VALUE
                }.thenBy { it.name })
            }
            _groupedItems.postValue(sortedGrouped)
        }
    }
}
