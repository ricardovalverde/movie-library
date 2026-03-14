package com.ricardovalverde.movies.ui.movies

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ricardovalverde.movies.ui.components.shimmerEffect
import com.ricardovalverde.movies.ui.theme.MoviesAppTheme

@Composable
fun MovieListScreenSkeleton() {
    val skeletonBackground = shimmerEffect()

    Column(modifier = Modifier.padding(horizontal = 16.dp)) {

        Box(
            modifier = Modifier
                .background(skeletonBackground, shape = RoundedCornerShape(5.dp))
                .width(200.dp)
                .height(15.dp)

        )

        Row(
            modifier = Modifier.padding(top = 12.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            repeat(10) {
                Column {

                    Box(
                        modifier = Modifier
                            .width(140.dp)
                            .height(210.dp)
                            .background(skeletonBackground, shape = RoundedCornerShape(12.dp)),

                        )

                    Box(
                        modifier = Modifier
                            .padding(vertical = 10.dp)
                            .background(skeletonBackground, shape = RoundedCornerShape(5.dp))
                            .width(90.dp)
                            .height(15.dp)

                    )
                }
            }
        }
    }
}

@Composable
@Preview
fun MovieListScreenSkeletonPreview() {
    MoviesAppTheme {
        MovieListScreenSkeleton()
    }
}
