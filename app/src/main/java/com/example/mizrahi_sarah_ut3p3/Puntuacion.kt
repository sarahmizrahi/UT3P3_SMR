package com.example.mizrahi_sarah_ut3p3

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.StarOutline
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.mizrahi_sarah_ut3p3.ui.theme.switch1
import kotlin.math.ceil
import kotlin.math.floor

//@Preview(showBackground = true)
@Composable
fun Puntuacion( modifier: Modifier = Modifier, navController: NavController,
    rating: Double = 0.0,
    stars: Int = 5,
) {


    val unfilledStars = (stars - ceil(rating)).toInt().coerceAtLeast(0) //Asegurarse de que no sea negativo



    //Columna para organizar horizontalmente los elementos
    Column(modifier = Modifier.fillMaxWidth().padding(40.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally) {

        Text("Valora tu experiencia:",
            fontFamily = FontFamily.SansSerif, fontWeight = FontWeight.Bold, fontSize = 20.sp)

        //Espacio
        Spacer(modifier = Modifier.size(70.dp))



        //Organiza las opciones
        Row(modifier = Modifier.fillMaxWidth().padding(10.dp),
            verticalAlignment = Alignment.CenterVertically) {
            Text("Accesibilidad:", fontFamily = FontFamily.SansSerif)
            repeat(unfilledStars) {
                Icon(
                    imageVector = Icons.Outlined.StarOutline,
                    contentDescription = "star",
                    tint = switch1
                )
            }
        }
        Row(modifier = Modifier.fillMaxWidth().padding(10.dp),
            verticalAlignment = Alignment.CenterVertically) {
            Text("Simplicidad:", fontFamily = FontFamily.SansSerif)
            repeat(unfilledStars) {
                Icon(
                    imageVector = Icons.Outlined.StarOutline,
                    contentDescription = "star",
                    tint = switch1
                )
            }
        }
        Row(modifier = Modifier.fillMaxWidth().padding(10.dp),
            verticalAlignment = Alignment.CenterVertically) {
            Text("Intuitiva:", fontFamily = FontFamily.SansSerif)
            repeat(unfilledStars) {
                Icon(
                    imageVector = Icons.Outlined.StarOutline,
                    contentDescription = "star",
                    tint = switch1
                )
            }
        }
        Row(modifier = Modifier.fillMaxWidth().padding(10.dp),
            verticalAlignment = Alignment.CenterVertically) {
            Text("Velocidad:", fontFamily = FontFamily.SansSerif)
            repeat(unfilledStars) {
                Icon(
                    imageVector = Icons.Outlined.StarOutline,
                    contentDescription = "star",
                    tint = switch1
                )
            }
        }
    }
}