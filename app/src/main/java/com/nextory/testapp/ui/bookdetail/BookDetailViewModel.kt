package com.nextory.testapp.ui.bookdetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nextory.testapp.data.Book
import com.nextory.testapp.data.BookRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookDetailViewModel @Inject constructor(
    private val bookRepository: BookRepository
) : ViewModel() {

    private val _book: MutableStateFlow<Book?> = MutableStateFlow(
        null
    )
    val book: StateFlow<Book?> = _book

    fun getBook(id: Long){
        viewModelScope.launch(Dispatchers.IO) {
            bookRepository.getBook(id).collect {
                book -> _book.value = book
            }
        }
    }
}