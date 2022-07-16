package com.nextory.testapp.ui.components

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.nextory.testapp.data.Book

class PreviewBookProvider : PreviewParameterProvider<Book> {
    override val values = sequenceOf(
        Book(1, "title", "author", "description", "", false)
    )
}