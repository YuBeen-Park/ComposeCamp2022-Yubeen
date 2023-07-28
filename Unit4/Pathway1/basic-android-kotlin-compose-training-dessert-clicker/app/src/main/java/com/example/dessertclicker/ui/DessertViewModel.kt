package com.example.dessertclicker.ui

import androidx.lifecycle.ViewModel
import com.example.dessertclicker.data.Datasource.dessertList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class DessertViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(DessertUiState())
    val uiState: StateFlow<DessertUiState> = _uiState.asStateFlow()


    fun onDessertClicked() {
        val dessertsSold = uiState.value.dessertsSold.inc()
        val nextDessertIndex = determineDessertIndex(dessertsSold)
        _uiState.update { currentState ->
            currentState.copy(
                revenue = currentState.revenue + currentState.currentDessertPrice,
                dessertsSold = dessertsSold,
                currentDessertIndex = nextDessertIndex,
                currentDessertPrice = dessertList[nextDessertIndex].price,
                currentDessertImageId = dessertList[nextDessertIndex].imageId,
            )
        }
    }

    private fun determineDessertIndex(dessertsSold: Int): Int{
        var nextDessertIndex = 0
        for (index in dessertList.indices) {
            if (dessertsSold >= dessertList[index].startProductionAmount) {
                nextDessertIndex = index
            } else {
                // The list of desserts is sorted by startProductionAmount. As you sell more desserts,
                // you'll start producing more expensive desserts as determined by startProductionAmount
                // We know to break as soon as we see a dessert who's "startProductionAmount" is greater
                // than the amount sold.
                break
            }
        }
        return nextDessertIndex
    }
}