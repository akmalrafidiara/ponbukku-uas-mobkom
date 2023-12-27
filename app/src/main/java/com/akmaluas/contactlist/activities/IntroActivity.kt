package com.akmaluas.contactlist.activities

import android.content.Intent
import android.os.Bundle
import android.view.animation.OvershootInterpolator
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.akmaluas.contactlist.MainActivity
import com.akmaluas.contactlist.R
import com.akmaluas.contactlist.ui.theme.ContactListTheme
import com.akmaluas.contactlist.ui.theme.DarkGrey
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.delay

class IntroActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            rememberSystemUiController().isStatusBarVisible = false
            ContactListTheme {
                IntroPage()
            }
        }
    }

    @Composable
    fun IntroPage() {
        val scale = remember { Animatable(0f) }

        LaunchedEffect(key1 = true) {
            scale.animateTo(
                targetValue = 0.7f,
                animationSpec = tween(
                    durationMillis = 2000,
                    easing = { OvershootInterpolator(4f).getInterpolation(it) }
                )
            )
            delay(2000L)
            navigateToMain()
        }
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Column {
                    Text(
                        text = "Ponbukku",
                        modifier = Modifier
                            .fillMaxWidth()
                            .scale(1.5f)
                            .scale(scale.value),
                        color = Color(0xFF000000),
                        fontSize = 70.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        fontFamily = FontFamily(
                            Font(R.font.stella, weight = FontWeight.Bold),
                        )
                    )
//                Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = "Akmal Rafi Diara Putra | 1313621007",
                        modifier = Modifier
                            .fillMaxWidth()
                            .scale(1.5f)
                            .scale(scale.value),
                        color = Color(0xFF000000),
                        fontSize = 10.sp,
                        textAlign = TextAlign.Center,
                    )
                }
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "App logo",
                    modifier = Modifier
                        .scale(3f)
                        .scale(scale.value)
                        .clip(CircleShape)
                )
            }
        }
    }
    private fun navigateToMain() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}