package com.nextory.testapp.ui.booklist

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.paging.PagingConfig
import androidx.paging.filter
import com.nextory.testapp.data.BookRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import java.util.*
import javax.inject.Inject

@HiltViewModel
class BookListViewModel @Inject constructor(
    private val handle: SavedStateHandle,
    bookRepository: BookRepository
) : ViewModel() {

    private val _searchedText = MutableStateFlow(handle.get<String?>(SAVE_SEARCHED_TEXT) ?: "")
    val searchedText: StateFlow<String> = _searchedText

    val pagedBooks = _searchedText.flatMapLatest { searchText ->
        bookRepository.observePagedBooks(PAGING_CONFIG).map { book ->
            book.filter {
                it.title.lowercase(Locale.getDefault()).contains(searchText) || it.author.lowercase(
                    Locale.getDefault()
                ).contains(searchText)
            }
        }
    }.flowOn(Dispatchers.IO)

    fun searchText(text: String) {
        _searchedText.value = text.lowercase(Locale.getDefault())
        handle[SAVE_SEARCHED_TEXT] = _searchedText.value
    }

    companion object {
        val PAGING_CONFIG = PagingConfig(
            pageSize = 12,
            enablePlaceholders = false
        )
        const val SAVE_SEARCHED_TEXT = "searched_text"
    }
}