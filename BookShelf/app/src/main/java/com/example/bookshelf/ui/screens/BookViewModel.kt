package com.example.bookshelf.ui.screens

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

sealed interface BookUiState {
    //todo change book:String to book:Book
    data class Success(val book: String) : BookUiState
    data object Loading : BookUiState
    data object Error : BookUiState

}

class BookViewModel (): ViewModel() {
    val bookUiState: MutableState<BookUiState> = mutableStateOf(BookUiState.Loading)

}