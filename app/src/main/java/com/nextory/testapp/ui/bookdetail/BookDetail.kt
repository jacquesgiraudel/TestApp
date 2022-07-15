package com.nextory.testapp.ui.bookdetail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.nextory.testapp.data.Book
import com.nextory.testapp.ui.components.PreviewBookProvider

// FIXME save / restore state on configuration and system kill
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookDetail(bookId: Long, bookDetailViewModel: BookDetailViewModel = hiltViewModel()) {

    LaunchedEffect(null) {
        bookDetailViewModel.getBook(bookId)
    }
    val book = bookDetailViewModel.book.collectAsState().value

    Book(book)
}

@Composable
private fun Book(book: Book?){
    Column(Modifier.fillMaxWidth().background(MaterialTheme.colorScheme.surface)) {

        Column(modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primaryContainer)
            .padding(16.dp)
            .align(Alignment.Start)) {
            Text(text = book?.title ?: "", style = MaterialTheme.typography.titleLarge, color = MaterialTheme.colorScheme.onPrimaryContainer)
            Text(text = book?.author ?: "", style = MaterialTheme.typography.titleSmall, color = MaterialTheme.colorScheme.onPrimaryContainer)
        }
        AsyncImage(
            model = book?.imageUrl ?: "",
            contentDescription = null,
            modifier = Modifier
                .height(200.dp)
                .align(Alignment.CenterHorizontally)
                .clip(RoundedCornerShape(8.dp))
        )
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .align(Alignment.Start)) {
            Text(text = book?.description ?: "", style = MaterialTheme.typography.bodyLarge, color = MaterialTheme.colorScheme.onSurface)
            // Hide software bar in portrait mode
            // FIXME the last line is cut in landscape mode, bug of accompanist which is in alpha ?
            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

@Preview
@Composable
private fun PreviewBookDetail(@PreviewParameter(PreviewBookProvider::class) book: Book?){
    Book(book)
}
