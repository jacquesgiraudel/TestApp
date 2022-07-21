package com.nextory.testapp.ui.bookdetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nextory.testapp.data.Book
import com.nextory.testapp.data.BookRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookDetailViewModel @Inject constructor(
    private val bookRepository: BookRepository
) : ViewModel() {

    fun getBook(id: Long): Flow<Book> {
        return bookRepository.getBook(id).flowOn(Dispatchers.IO)
    }

    fun toggleFavorite(id: Long, favorite: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            bookRepository.updateBook(id, favorite)
        }
    }
}