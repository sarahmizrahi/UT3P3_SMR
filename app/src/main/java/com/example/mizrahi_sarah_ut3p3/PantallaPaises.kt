package com.example.mizrahi_sarah_ut3p3

import android.content.res.Configuration
import android.widget.Toast
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.mizrahi_sarah_ut3p3.ui.theme.switch1
import com.example.mizrahi_sarah_ut3p3.ui.theme.switch2
import com.example.mizrahi_sarah_ut3p3.ui.theme.switch3
import com.example.mizrahi_sarah_ut3p3.ui.theme.switch4
import com.example.mizrahi_sarah_ut3p3.ui.theme.switch5


//@Preview(showBackground = true)
@Composable
fun PantallaPaises(modifier: Modifier = Modifier, navController: NavController) {

    //Variable vista horinzontal
    val vistaHorizontal = Configuration.ORIENTATION_LANDSCAPE

    //Orientacion. Obtiene la orientacion actual
    val configuration = LocalConfiguration.current

    //Variable switch
    var estado1 by rememberSaveable { mutableStateOf(true) }

    //Variable Radiobuttons
    //Paises
    val paisesOrigen = listOf(
        "Estados Unidos \$", "Rusia \u20BD", "España \u20AC", "Tailandia \u0E3F", "Japon \u00A5",
        "Filipinas \u20B1", "Nigeria \u20A6", "India \u20B9")
    val paisesDestino = listOf(
        "Argentina \u0024", "Egipto E£", "Australia A\u0024", "Turquía \u20BA",
        "Canadá C\u0024", "Kenia KSh", "Brasil R\u0024", "Grecia \u20AC")

    //Estado Radiobutton y uso de Toast
    var estadoRadioOrigen by rememberSaveable { mutableStateOf("") }
    var estadoRadioDestino by rememberSaveable { mutableStateOf("") }
    val context = LocalContext.current


    Box(modifier = Modifier
        .fillMaxSize()
        .padding(40.dp)
        .verticalScroll(rememberScrollState(vistaHorizontal))) {

        //Row para crear 2 columnas
        Row (
            modifier = Modifier.fillMaxWidth() //ocupa el ancho de la pantalla
        ){
            //Pedir origen a traves de radiobuttons
            Column(modifier = Modifier
                .fillMaxSize()
                .weight(1f),
            ) {
                Text("Origen:", fontWeight = FontWeight.Bold, fontSize = 20.sp, color = switch5)
                paisesOrigen.forEach { item ->
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        RadioButton(
                            selected = estadoRadioOrigen == item,
                            onClick = {
                                estadoRadioOrigen = item
                            },  //OJO, Actualiza el juego seleccionado
                            colors = RadioButtonDefaults.colors(
                                selectedColor = switch1,
                                unselectedColor = switch4
                            )
                        )
                        Text(text = item, fontFamily = FontFamily.SansSerif, color = switch5)
                    }
                }
            }
            //Espacio entre columnas
            Spacer(modifier = Modifier.size(30.dp))

            //Pedir destino a traves de radiobuttons
            Column(modifier = Modifier
                .fillMaxSize()
                .weight(1f)
            ) {
                Text("Destino:", fontWeight = FontWeight.Bold, fontSize = 20.sp, color = switch5)
                paisesDestino.forEach { item ->
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        RadioButton(
                            selected = estadoRadioDestino == item,
                            onClick = {
                                estadoRadioDestino = item
                            },  //OJO, Actualiza el juego seleccionado
                            colors = RadioButtonDefaults.colors(
                                selectedColor = switch1,
                                unselectedColor = switch4
                            )
                        )
                        Text(text = item, fontFamily = FontFamily.SansSerif, color = switch5)
                    }
                }
            }
        }

        //Cuando la orientacion sea vertical
        when (configuration.orientation) {
            Configuration.ORIENTATION_PORTRAIT -> {
                //Boton flotante. FAB. Controla la eleccion del origen y destino y lo muestra
                FloatingActionButton(
                    onClick = {
                        when {
                            estadoRadioOrigen.isEmpty() && estadoRadioDestino.isEmpty() -> {
                                Toast.makeText(
                                    context, "No has pulsado ninguna opcion.",
                                    Toast.LENGTH_LONG
                                ).show()
                            }

                            estadoRadioOrigen.isEmpty() -> {
                                Toast.makeText(
                                    context, "Selecciona un origen.",
                                    Toast.LENGTH_LONG
                                ).show()
                            }

                            estadoRadioDestino.isEmpty() -> {
                                Toast.makeText(
                                    context, "Selecciona un destino.",
                                    Toast.LENGTH_LONG
                                ).show()
                            }

                            else -> {
                                val mensaje = "Origen: $estadoRadioOrigen  -  Destino: $estadoRadioDestino"
                                Toast.makeText(
                                    context,
                                    mensaje,
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                        }
                    },
                    containerColor = switch1,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(top = 130.dp)
                )
                {
                    //Icono dentro del boton añadir
                    Icon(
                        imageVector = Icons.Outlined.Add,
                        contentDescription = "add",
                        tint = Color.White
                    )
                }


                //Controla que el switch esté colocado
                Column(
                    modifier = Modifier
                        .padding(top = 300.dp)
                        .align(Alignment.CenterStart)
                ) {
                    //Estados
                    Text("Guardar origen", fontStyle = FontStyle.Italic)

                    //Guardar origen
                    Switch(
                        checked = estado1,
                        onCheckedChange = { estado1 = !estado1 },
                        colors = SwitchDefaults.colors(
                            uncheckedThumbColor = switch3,    //circulo clicado
                            uncheckedTrackColor = switch4,   //pista clicada
                            checkedThumbColor = switch1,    //circulo sin clicar
                            checkedTrackColor = switch2,  //pista sin clicar
                        ),
                        //Personaliza el circulo, le pone un icono
                        thumbContent = if (estado1) {   //Al clicar cambia el estado del boton
                            {
                                Icon(
                                    imageVector = Icons.Filled.Check,
                                    contentDescription = "add",
                                    modifier = Modifier.size(SwitchDefaults.IconSize),
                                )
                            }
                        } else {
                            null
                        }
                    )
                   Spacer(modifier = Modifier.size(40.dp))

                    //TextButton, con texto CALCULAR. Pasa de pantalla
                    //Al clicar aparece como sombra, la forma del boton.
                    TextButton(onClick = { navController.navigate("Puntuacion") }) {
                        Text(text = "Valóranos", color = switch1, fontWeight = FontWeight.ExtraBold,
                            fontFamily = FontFamily.SansSerif,fontSize =30.sp)
                    }
                }

            }
            //Orientacion horizontal
            else -> {
            //Boton flotante. FAB. Controla la eleccion del origen y destino y lo muestra
                FloatingActionButton(
                    onClick = {
                        when {
                            estadoRadioOrigen.isEmpty() && estadoRadioDestino.isEmpty() -> {
                                Toast.makeText(
                                    context, "No has pulsado ninguna opcion.",
                                    Toast.LENGTH_LONG
                                ).show()
                            }

                            estadoRadioOrigen.isEmpty() -> {
                                Toast.makeText(
                                    context, "Selecciona un origen.",
                                    Toast.LENGTH_LONG
                                ).show()
                            }

                            estadoRadioDestino.isEmpty() -> {
                                Toast.makeText(
                                    context, "Selecciona un destino.",
                                    Toast.LENGTH_LONG
                                ).show()
                            }

                            else -> {
                                val mensaje = "Origen: $estadoRadioOrigen  -  Destino: $estadoRadioDestino"
                                Toast.makeText(
                                    context,
                                    mensaje,
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                        }
                    },
                    containerColor = switch1,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(top = 350.dp)
                )
                {
                    //Icono dentro del boton añadir
                    Icon(
                        imageVector = Icons.Outlined.Add,
                        contentDescription = "add",
                        tint = Color.White
                    )
                }


                //Controla que el switch esté colocado
                Column(
                    modifier = Modifier
                        .padding(top = 450.dp)
                        .align(Alignment.BottomStart)
                ) {
                    //Estados
                    Text("Guardar origen", fontStyle = FontStyle.Italic)

                    //Guardar origen
                    Switch(
                        checked = estado1,
                        onCheckedChange = { estado1 = !estado1 },
                        colors = SwitchDefaults.colors(
                            uncheckedThumbColor = switch3,    //circulo clicado
                            uncheckedTrackColor = switch4,   //pista clicada
                            checkedThumbColor = switch1,    //circulo sin clicar
                            checkedTrackColor = switch2,  //pista sin clicar
                        ),
                        //Personaliza el circulo, le pone un icono
                        thumbContent = if (estado1) {   //Al clicar cambia el estado del boton
                            {
                                Icon(
                                    imageVector = Icons.Filled.Check,
                                    contentDescription = "add",
                                    modifier = Modifier.size(SwitchDefaults.IconSize),
                                )
                            }
                        } else {
                            null
                        }
                    )


                    //TextButton, con texto CALCULAR. Pasa de pantalla
                    //Al clicar aparece como sombra, la forma del boton.
                    TextButton(onClick = { navController.navigate("Puntuacion") }) {
                        Text(text = "Valóranos", color = switch1, fontWeight = FontWeight.ExtraBold,
                            fontFamily = FontFamily.SansSerif,fontSize =30.sp)
                    }
                }
            }
        }
    }
}



