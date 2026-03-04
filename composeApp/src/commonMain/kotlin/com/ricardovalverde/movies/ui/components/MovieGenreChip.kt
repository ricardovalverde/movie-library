package com.ricardovalverde.movies.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ricardovalverde.movies.ui.theme.MoviesAppTheme

@Composable
fun MovieGenreChip(
    modifier: Modifier = Modifier,
    genre: String
) {
    Surface(
        modifier = modifier,
        shape = MaterialTheme.shapes.large,
        color = MaterialTheme.colorScheme.primary
    ) {
        Text(
            modifier = Modifier.padding(horizontal = 10.dp),
            text = genre,
            style = MaterialTheme.typography.labelSmall
        )

    }

}

@Preview(showBackground = true)
@Composable
fun MovieGenreChipPreview() {
    MoviesAppTheme {
        MovieGenreChip(
            genre = "Action"
        )
    }
}