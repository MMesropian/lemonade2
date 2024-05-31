package com.example.lemonade2

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonade2.ui.theme.Lemonade2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Lemonade2Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    mainScreen()
                }
            }
        }
    }
}

@Composable
fun mainScreen() {

    var currentStep by remember { mutableStateOf(1) }
    var squeezeCount by remember { mutableStateOf(1) }


    when (currentStep) {
        1 -> LemonTextAndImage(
            imageId = R.drawable.lemon_tree,
            textId = R.string.lemon_select,
            onImageClick = {
                currentStep = 2
                squeezeCount = (2..4).random()
            }
        )

        2 -> LemonTextAndImage(
            imageId = R.drawable.lemon_squeeze,
            textId = R.string.lemon_squeeze,
            onImageClick = {
                if (squeezeCount == 0)
                    currentStep = 3
                else
                    squeezeCount--
            }
        )

        3 -> LemonTextAndImage(
            imageId = R.drawable.lemon_drink,
            textId = R.string.lemon_drink,
            onImageClick = {
                currentStep = 4
            }
        )

        else -> LemonTextAndImage(
            imageId = R.drawable.lemon_restart,
            textId = R.string.lemon_empty_glass,
            onImageClick = {
                currentStep = 1
            }
        )
    }
}

@Composable
fun LemonTextAndImage(
    imageId: Int,
    textId: Int,
    onImageClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column {
        Row(
            modifier = Modifier
                .height(50.dp)
                .fillMaxWidth()
                .background(Color.Yellow),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Lemonade",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        }
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Button(
                modifier = Modifier.border(2.dp, Color(105, 205, 216), RoundedCornerShape(40.dp)),
                onClick = onImageClick,
                shape = RoundedCornerShape(40.dp)
            ) {
                Image(
                    modifier = Modifier
                        .height(200.dp)
                        .width(160.dp)
                        .padding(24.dp),
                    painter = painterResource(id = imageId),
                    contentDescription = "lemon tree",
                )
            }
            Spacer(
                modifier = Modifier.height(16.dp)
            )
            Text(
                text = stringResource(id = textId),
                fontSize = 18.sp
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    Lemonade2Theme {
        mainScreen()
    }
}


