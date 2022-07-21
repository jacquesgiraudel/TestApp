package com.nextory.testapp.data

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface BookDao {
    @Query("SELECT * FROM book")
    fun observePagedBooks(): PagingSource<Int, Book>

    @Query("SELECT * FROM book where id = :id")
    fun getBook(id: Long): Flow<Book>

    @Query("UPDATE book SET favorite = :favorite where id = :id")
    fun updateBook(id: Long, favorite: Boolean)
}