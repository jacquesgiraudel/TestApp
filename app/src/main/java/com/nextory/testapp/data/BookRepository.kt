package com.nextory.testapp.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BookRepository @Inject constructor(
    private val bookDao: BookDao
) {
    fun observePagedBooks(pagingConfig: PagingConfig): Flow<PagingData<Book>> {
        return Pager(config = pagingConfig) {
            bookDao.observePagedBooks()
        }.flow
    }

    fun getBook(id: Long): Flow<Book> {
        return bookDao.getBook(id)
    }

    fun updateBook(id: Long, favorite:Boolean) {
        return bookDao.updateBook(id, favorite)
    }
}