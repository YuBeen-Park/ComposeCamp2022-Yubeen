package toss.next.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Device
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import toss.next.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ArtSpaceApp()
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ArtSpaceApp() {
    var index by remember {
        mutableStateOf(0)
    }
    ArtSpaceTheme {
        Column(
            modifier = Modifier.fillMaxSize().padding(horizontal = 5.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = Modifier.height(20.dp))
            ArtImage(index)
            Spacer(modifier = Modifier.height(30.dp))
            ArtText(index)
            Spacer(modifier = Modifier.height(20.dp))
            ArtButton({
                index = when (index) {
                    0 -> 2
                    else -> index - 1
                }
            }, { index = (index + 1) % 3 })
        }
    }
}

@Composable
fun ArtImage(index: Int) {
    val imgRes = when (index) {
        0 -> R.drawable.monet
        1 -> R.drawable.monet2
        else -> R.drawable.monet3
    }
    Image(
        painter = painterResource(id = imgRes),
        contentDescription = "ArtImage",
    )
}

@Composable
fun ArtText(index: Int) {
    val titleRes = when (index) {
        0 -> R.string.art_title1
        1 -> R.string.art_title2
        else -> R.string.art_title3
    }
    val painterRes = when (index) {
        0 -> R.string.artist1
        1 -> R.string.artist2
        else -> R.string.artist3
    }
    Text(
        text = stringResource(id = titleRes),
        fontSize = 14.sp,
        fontWeight = FontWeight.Bold
    )
    Text(
        text = stringResource(id = painterRes),
        fontSize = 12.sp,
    )
}

@Composable
fun ArtButton(prev: () -> Unit, next: () -> Unit) {
    Row(
        modifier = Modifier.height(100.dp).fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Button(onClick = prev) {
            Text("Previous", fontSize = 10.sp)
        }
        Spacer(modifier = Modifier.height(10.dp))
        Button(onClick = next) {
            Text("Next", fontSize = 10.sp)
        }
    }
}