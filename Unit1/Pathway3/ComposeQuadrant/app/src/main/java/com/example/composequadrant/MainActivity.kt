package com.example.composequadrant

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composequadrant.ui.theme.ComposeQuadrantTheme

// https://developer.android.com/codelabs/basic-android-kotlin-compose-composables-practice-problems?hl=ko&continue=https%3A%2F%2Fdeveloper.android.com%2Fcourses%2Fpathways%2Fandroid-basics-compose-unit-1-pathway-3%3Fhl%3Dko%23codelab-https%3A%2F%2Fdeveloper.android.com%2Fcodelabs%2Fbasic-android-kotlin-compose-composables-practice-problems#3

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeQuadrantTheme {
                ComposeQuadrantApp()
            }
        }
    }
}

@Composable
fun ComposeQuadrantApp() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.weight(1f)
        ) {
            ComposableInfoCard(
                title = stringResource(id = R.string.first_title),
                description = stringResource(id = R.string.first_description),
                backgroundColor = Color.Green,
                modifier = Modifier.weight(1f)
            )
            ComposableInfoCard(
                title = stringResource(id = R.string.second_title),
                description = stringResource(id = R.string.second_description),
                backgroundColor = Color.Yellow,
                modifier = Modifier.weight(1f)
            )
        }
        Row(
            modifier = Modifier.weight(1f)
        ) {
            ComposableInfoCard(
                title = stringResource(id = R.string.third_title),
                description = stringResource(id = R.string.third_description),
                backgroundColor = Color.Cyan,
                modifier = Modifier.weight(1f)
            )
            ComposableInfoCard(
                title = stringResource(id = R.string.fourth_title),
                description = stringResource(id = R.string.fourth_description),
                backgroundColor = Color.LightGray,
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
private fun ComposableInfoCard(
    title: String,
    description: String,
    backgroundColor: Color,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
            .fillMaxHeight()
            .background(backgroundColor)
            .padding(16.dp),
    ) {
        Text(
            text = title,
            modifier = Modifier.padding(bottom = 16.dp),
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Justify,
        )
        Text(text = description)
    }
}


@Preview(showBackground = true)
@Composable
fun ComposeQuadrantPreview() {
    ComposeQuadrantTheme {
        ComposeQuadrantApp()
    }
}