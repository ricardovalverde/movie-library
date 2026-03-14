package com.ricardovalverde.movies.ui.movieDetail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ricardovalverde.movies.domain.model.movie1
import com.ricardovalverde.movies.ui.components.shimmerEffect
import com.ricardovalverde.movies.ui.theme.MoviesAppTheme

@Composable
fun MovieDetailScreenSkeleton() {
    val skeletonColor = shimmerEffect()

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Surface(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(16.dp)
                .weight(2f)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(skeletonColor, shape = MaterialTheme.shapes.large),
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(2f)
                .padding(top = 1.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .width(200.dp)
                    .height(20.dp)
                    .background(skeletonColor, MaterialTheme.shapes.medium)
                )


            Spacer(modifier = Modifier.height(20.dp))


            Row(verticalAlignment = Alignment.CenterVertically) {
                repeat(3) {
                    Box(
                        modifier = Modifier
                            .width(60.dp)
                            .height(10.dp)
                            .background(skeletonColor, MaterialTheme.shapes.medium),
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                }

            }

            Spacer(modifier = Modifier.height(20.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                repeat(2) {
                    Box(
                        modifier = Modifier
                            .width(60.dp)
                            .height(10.dp)
                            .background(skeletonColor, MaterialTheme.shapes.medium),
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                }

            }

            Spacer(modifier = Modifier.height(10.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .padding(horizontal = 16.dp).background(skeletonColor, MaterialTheme.shapes.large),

            )


            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier.background(skeletonColor, MaterialTheme.shapes.large),

                    ) {
                    Box(
                        modifier = Modifier.padding(horizontal = 10.dp)
                            .background(skeletonColor, MaterialTheme.shapes.large),

                        )

                }
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(skeletonColor)
                    .padding(horizontal = 16.dp),

                )


            Row(modifier = Modifier.padding(16.dp),verticalAlignment = Alignment.CenterVertically) {
                repeat(3) {
                    Box(
                        modifier = Modifier
                            .width(180.dp)
                            .height(80.dp)
                            .background(skeletonColor, MaterialTheme.shapes.medium),
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                }

            }

            Box(
                modifier = Modifier.fillMaxSize().padding(16.dp).background(
                    skeletonColor,
                    MaterialTheme.shapes.medium
                )
            )
        }
    }

}

@Preview
@Composable
fun MovieDetailSuccessPreview() {
    MoviesAppTheme {
        MovieDetailScreen(
            movieDetailState = MovieDetailViewModel.MovieDetailState.Success(
                movie1
            ),
            onNavigationIconClick = {}
        )
    }
}

@Composable
@Preview
fun MovieDetailScreenSkeletonPreview() {
    MoviesAppTheme {
        MovieDetailScreenSkeleton()
    }
}