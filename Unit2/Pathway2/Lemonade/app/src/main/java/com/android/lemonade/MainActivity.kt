package com.android.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.android.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeTheme {
                LemonadeApp()
            }
        }
    }
}

@Preview
@Composable
fun LemonadeApp() {
    LemonadeWithDescription(
        Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    )
}

@Composable
fun LemonadeWithDescription(modifier: Modifier = Modifier) {
    var step by remember { mutableStateOf(0) }
    var curStep by remember { mutableStateOf(0) }
    val squeeze = (1..5).random()
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        val description = when (step) {
            0 -> R.string.lemon_content_description1
            1 -> R.string.lemon_content_description2
            2 -> R.string.lemon_content_description3
            else -> R.string.lemon_content_description4
        }
        val treeImage = when (step) {
            0 -> R.drawable.lemon_tree
            1 -> R.drawable.lemon_squeeze
            2 -> R.drawable.lemon_drink
            else -> R.drawable.lemon_restart
        }
        Text(
            text = stringResource(description),
            fontSize = 18.sp,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        Image(
            painter = painterResource(id = treeImage), contentDescription = "",
            modifier = Modifier
                .border(2.dp, Color(105, 205, 216), RoundedCornerShape(4.dp))
                .clickable {
                    when (step) {
                        1 -> {
                            curStep++
                            if (curStep == squeeze) {
                                step = 2
                                curStep = 0
                            }
                        }
                        else -> step = (step + 1) % 4
                    }
                }
        )
    }
}