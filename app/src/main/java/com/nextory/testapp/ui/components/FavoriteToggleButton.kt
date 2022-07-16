package com.nextory.testapp.ui.components

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.nextory.testapp.R
import com.nextory.testapp.ui.theme.favorite

@Composable
fun FavoriteToggleButton(favorite: Boolean, toggleFavorite: (() -> Unit)? = null){
    IconToggleButton(
        checked = !favorite,
        enabled = toggleFavorite != null,
        onCheckedChange = { toggleFavorite?.invoke() }) {

        val favoriteIconId =
            if (favorite) R.drawable.ic_favorite else R.drawable.ic_not_favorite
        val clickable = toggleFavorite != null
        val contentDescId = if (clickable)
            if (favorite) R.string.favorite_removefrom else R.string.favorite_addto
        else
            if (favorite) R.string.favorite_added else R.string.favorite_not

        Icon(
            painter = painterResource(id = favoriteIconId),
            modifier = Modifier.size(48.dp),
            tint = MaterialTheme.colorScheme.favorite,
            contentDescription = stringResource(contentDescId)
        )
    }
}