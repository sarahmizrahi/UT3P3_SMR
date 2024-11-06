
package com.example.mizrahi_sarah_ut3p3

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Accessibility
import androidx.compose.material.icons.filled.AllInclusive
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.mizrahi_sarah_ut3p3.ui.theme.switch1


//@Preview(showBackground = true)
@Composable
fun Principal(modifier: Modifier = Modifier, navController: NavController) {

    var bienvenida by remember {
        mutableStateOf("")
    }
    //Variable vista horinzontal
    val vistaHorizontal = Configuration.ORIENTATION_LANDSCAPE

    //Variable outlindTextfield
    var nombre by remember { mutableStateOf("") } //Variable para almacenar el valor del TextField
    val interactionSource = remember { MutableInteractionSource() } //Fuente de interacción

    //Variable para controlar error del campo nombre
    var nombreError by remember { mutableStateOf(false) }

    //Variable CheckBox
    val checked = remember { mutableStateOf(false) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxSize()
            .padding(40.dp)
            .verticalScroll(rememberScrollState(vistaHorizontal))
    )  //pantalla con scroll en vista horinzontal
    {

        //Imagen cargada desde internet
        AsyncImage(
            model = "https://img.freepik.com/vector-gratis/diseno-concepto-global-transferencia-dinero-digital_1017-17453.jpg",
            contentDescription = "Bola mundo",
            contentScale = ContentScale.Crop,
            modifier = Modifier.clip(CircleShape)
        )
        //Espacio
        //Spacer(modifier = Modifier.padding(top = 40.dp))

        //Texto preguntar nombre
        Text(
            "¿Cúal es tu nombre?",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Serif
        )

        //Campo para introducir nombre
        OutlinedTextField(
            modifier = modifier,
            value = nombre,
            onValueChange = {
                nombre = it
                nombreError = false //Resetea el estado de error cuando el usuario escribe algo
            },
            readOnly = false, //False para poder escribir
            textStyle = TextStyle(color = Color.Black),
            isError = nombreError,

            //Controla que el nombre se guarde y aparezca en Text(bienvenida)
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Send
            ),
            keyboardActions = KeyboardActions(onSend = {
                if (nombre.isBlank()) {
                    //Si el campo esta vacío, activa el estado de error
                    nombreError = true
                } else {
                    //Si hay texto, muestra el saludo y resetea el estado de error
                    bienvenida = "¡Hola $nombre!"
                    nombreError = false
                }
            }),

            placeholder = { Text(text = "Escribe aqui:", color = switch1) },
            leadingIcon = {  //Icono izquierda
                Icon(imageVector = Icons.Default.AllInclusive, contentDescription = "Nombre Icon")
            },
            trailingIcon = {  //Icono derecha
                Icon(imageVector = Icons.Default.Accessibility, contentDescription = "Nombre2 Icon")
            },
            maxLines = 2,
            interactionSource = interactionSource,  //Interaccionar con el campo
            shape = RoundedCornerShape(15.dp),  //Redondear esquinas
            /*colors = TextFieldDefaults.colors(
                unfocusedTextColor = Color.Black,    //Color texto escrito
                //focusedContainerColor = Color.Red,  //Color fondo campo al clicar
                unfocusedContainerColor = Color.White,  //Color fondo campo
                //cursorColor = Color.Yellow  //Color cursor

            ),*/
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = switch1
            )
        )

        //Texto de ayuda o de error para controlar que se introduzca el nombre. Codigo fuera del OutlinedTextField
        val assistiveElementText = if (nombreError) "Error: Obligatorio" else ""
        val assistiveElementColor = if (nombreError) {
            Color.Red
        } else {
            switch1 //Color de texto cuando no hay error
        }

        Text(
            text = assistiveElementText,
            color = assistiveElementColor,
        )

        //Espacio donde aparecerá el saludo con el nombre introducido
        Text(
            bienvenida,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 80.dp),
            fontSize = 70.sp,
            textAlign = TextAlign.Center,
            softWrap = true,    //Buena practica, si el texto es muy largo reduce el tamaño de la letra
                        //o pasa a una nueva linea si es necesario
            lineHeight = 70.sp, //Espaciado entre lineas, para que no se solapen
            maxLines = 2,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Cursive
        )


        //Boton cambio de pantalla. Agrupa bootn y checkbox
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(bottom = 40.dp)
                    ) {
                    Button(
                        onClick = {
                            if (nombre.isBlank()) {
                                //Si el campo esta vacío, activa el estado de error
                                nombreError = true
                            } else {
                                //Si hay texto, cambia de pantalla
                                navController.navigate("PantallaPaises")
                            }
                        },
                        shape = RoundedCornerShape(20.dp), colors = ButtonDefaults.buttonColors(
                            containerColor = switch1
                        )
                    ) {
                        Text("¡Vámonos!")
                    }
                    //CheckBox, aceptar terminos

                        Row(verticalAlignment = Alignment.CenterVertically) //Pone los componenetes a la misma altura
                        {
                            Checkbox(
                                checked = checked.value,
                                onCheckedChange = { checked.value = it },
                                colors = CheckboxDefaults.colors(
                                    checkedColor = switch1,        //Color cuando está seleccionado
                                    uncheckedColor = Color.Gray,       //Color cuando no está seleccionado
                                    checkmarkColor = Color.White       //Color del check interno
                                )
                            )
                            Text("Acepto términos y condiciones generales.", color = Color.Gray)

                        }
                    }
                }
    //Apartado con mi nombre y una imagen. Formato correcto para alinear en la esquina inferior izquierda
    Box(
        modifier = Modifier
            .fillMaxSize() //Ocupar toda la pantalla
    ) {
        Row(
            modifier = Modifier
                .align(Alignment.BottomStart),  //Esquina inferior izquierda
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(R.drawable.saludo),
                contentDescription = "Imagen Sarah",
            )
            Text("Sarah Mizrahi Roig")
        }
    }
}
