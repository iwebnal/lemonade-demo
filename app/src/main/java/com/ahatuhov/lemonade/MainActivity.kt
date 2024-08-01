package com.ahatuhov.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ahatuhov.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LemonadeTheme {
                BlockContainer()
            }
        }
    }
}

@Composable
fun BlockContainer() {
    Column(modifier = Modifier.fillMaxSize()) {
        TopBlock(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Yellow)
                .wrapContentHeight()
        )
        ContentBlock(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .background(Color.White)
        )
    }
}

@Composable
fun TopBlock(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Lemonade",
            color = Color.Black,
            style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold)
        )
    }
}

@Composable
fun ContentBlock(modifier: Modifier = Modifier) {
    var currentStep by remember { mutableStateOf(1) }
    var squeezeCount by remember { mutableStateOf(0) }

    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        when (currentStep) {
            1 -> {
                LemonTextAndImage(
                    textLabelResourceId = R.string.str_lemon_tree,
                    drawableResourceId = R.drawable.lemon_tree,
                    contentDescriptionResourceId = R.string.title_lemon_tree,
                    onImageClick = {
                        currentStep = 2
                        squeezeCount = (2..4).random()
                    })
            }

            2 -> {
                LemonTextAndImage(
                    textLabelResourceId = R.string.str_keep_tappin,
                    drawableResourceId = R.drawable.lemon_squeeze,
                    contentDescriptionResourceId = R.string.title_lemon,
                    onImageClick = {
                        squeezeCount--
                        if (squeezeCount == 0) {
                            currentStep = 3
                        }
                    })
            }

            3 -> {
                LemonTextAndImage(
                    textLabelResourceId = R.string.str_tap_the_lemonade,
                    drawableResourceId = R.drawable.lemon_drink,
                    contentDescriptionResourceId = R.string.title_glass_of_lemonade,
                    onImageClick = {
                        currentStep = 4
                    })
            }

            4 -> {
                LemonTextAndImage(
                    textLabelResourceId = R.string.str_empty_glass,
                    drawableResourceId = R.drawable.lemon_restart,
                    contentDescriptionResourceId = R.string.title_empty_glass,
                    onImageClick = {
                        currentStep = 1
                    })
            }
        }
    }
}

@Composable
fun LemonTextAndImage(
    textLabelResourceId: Int,
    drawableResourceId: Int,
    contentDescriptionResourceId: Int,
    onImageClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = onImageClick,
            shape = RoundedCornerShape(dimensionResource(R.dimen.button_corner_radius)),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.tertiaryContainer)
        ) {
            Image(
                painter = painterResource(drawableResourceId),
                contentDescription = stringResource(contentDescriptionResourceId),
                modifier = Modifier
                    .padding(30.dp),
                contentScale = ContentScale.Crop
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = stringResource(textLabelResourceId),
            color = Color.Black,
            style = TextStyle(fontSize = 16.sp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LemonadeTheme {
        BlockContainer()
    }
}