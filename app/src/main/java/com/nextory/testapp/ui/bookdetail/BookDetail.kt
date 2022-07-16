package com.nextory.testapp.ui.bookdetail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.nextory.testapp.R
import com.nextory.testapp.data.Book
import com.nextory.testapp.ui.components.FavoriteToggleButton
import com.nextory.testapp.ui.components.PreviewBookProvider

// FIXME save / restore state on configuration and system kill
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookDetail(bookId: Long, bookDetailViewModel: BookDetailViewModel = hiltViewModel()) {

    LaunchedEffect(null) {
        bookDetailViewModel.getBook(bookId)
    }
    val book = bookDetailViewModel.book.collectAsState().value

    val toggleFavorite: () -> Unit = {
        bookDetailViewModel.toggleFavorite()
    }

    if (book != null) {
        Book(book, toggleFavorite)
    }
}

@Composable
private fun Book(book: Book, toggleFavorite: () -> Unit) {
    Column(
        Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surface)
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .background(MaterialTheme.colorScheme.primaryContainer)
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(16.dp)
            ) {
                Text(
                    text = book.title,
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
                Text(
                    text = book.author,
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
            }

            Box(
                modifier = Modifier
                    .padding(16.dp)
            ) {
                FavoriteToggleButton(favorite = book.favorite, toggleFavorite)
            }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {

            AsyncImage(
                model = book.imageUrl,
                contentDescription = stringResource(R.string.bookdetail_coverpage),
                modifier = Modifier
                    .height(200.dp)
                    .align(Alignment.CenterHorizontally)
                    .clip(RoundedCornerShape(8.dp))
            )
            Text(
                text = book.description,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp, 16.dp)
                    .align(Alignment.Start)
            )
            // Hide software bar in portrait mode
            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

@Preview
@Composable
private fun PreviewBookDetail(@PreviewParameter(PreviewBookProvider::class) book: Book) {
    Book(book) {}
}
