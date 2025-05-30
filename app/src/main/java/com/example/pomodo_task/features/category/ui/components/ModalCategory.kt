package com.example.pomodo_task.features.category.ui.components

import android.graphics.Color.colorToHSV
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import com.example.pomodo_task.components.HueBar
import com.example.pomodo_task.components.InputText
import com.example.pomodo_task.components.SatValPanel
import com.example.pomodo_task.ui.theme.Green300

data class CategoryData(
    val id: Int,
    val name: String,
    val color: Int,
    val active: Boolean
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ModalCategory(
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit,
    onSubmit: (CategoryData) -> Unit,
    value: CategoryData? = null,
) {

    var categoryName by remember { mutableStateOf(value?.name ?: "") }
    val nameInvalidMessage = "Nome de categoria inválido"
    var showErrorName by remember { mutableStateOf(false) }

    val hsv = remember {
        val hsv = floatArrayOf(0f, 0f, 0f)
        colorToHSV(if (value != null) Color(value.color).toArgb() else Color.Blue.toArgb(), hsv)
        mutableStateOf(
            Triple(hsv[0], hsv[1], hsv[2])
        )
    }

    val colorInit = Color.hsv(
        hsv.value.first,
        hsv.value.second,
        hsv.value.third
    )

    val backgroundColor = remember(hsv.value) {
        mutableStateOf(
            colorInit
        )
    }

    fun nameValid(): Boolean {
        return categoryName.trim().isNotEmpty()
    }

    fun isValid(): Boolean {
        return nameValid()
    }

    fun submit() {

        if (isValid()) {

            if (value == null) {

                onSubmit(
                    CategoryData(
                        id = 0,
                        name = categoryName,
                        active = true,
                        color = backgroundColor.value.toArgb()
                    )
                )
            } else {

                onSubmit(
                    CategoryData(
                        id = value.id,
                        name = categoryName,
                        active = true,
                        color = backgroundColor.value.toArgb()
                    )
                )

            }

            showErrorName = false
            categoryName = ""
            backgroundColor.value = colorInit
            onDismissRequest()
        } else {

            if (categoryName.trim().isEmpty()) {

                showErrorName = true
            }
        }
    }


    BasicAlertDialog(
        modifier = modifier,
        onDismissRequest = {
            onDismissRequest()
        },
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {

        Card(
            modifier = Modifier,
            colors = CardDefaults.cardColors(
                containerColor = if (isSystemInDarkTheme()) MaterialTheme.colorScheme.surface else Color.White
            )

        ) {
            Column(
                modifier = Modifier
                    .padding(20.dp)
                    .width(300.dp),
                verticalArrangement = Arrangement.spacedBy(15.dp)
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "Nova categoria",
                )

                InputText(
                    modifier = Modifier.fillMaxWidth(),
                    value = categoryName,
                    onValueChange = {
                        categoryName = it
                    },
                    label = "Categoria",
                    leadingIcon = {
                        Box(
                            modifier = Modifier
                                .size(25.dp)
                                .background(color = backgroundColor.value, shape = CircleShape)
                        )
                    },
                    supportingText = { if (!nameValid() && showErrorName) Text(text = nameInvalidMessage) },
                    isError = !nameValid() && showErrorName
                )

                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {

                    SatValPanel(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(100.dp),
                        hue = hsv.value.first
                    ) { sat, valueColor ->
                        hsv.value = Triple(hsv.value.first, sat, valueColor)
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    HueBar(
                        modifier = Modifier
                            .height(18.dp)
                            .fillMaxWidth(),
                    ) { hue ->
                        hsv.value = Triple(hue, hsv.value.second, hsv.value.third)
                    }

                }

                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {

                    Button(
                        modifier = Modifier.weight(1f),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Green300
                        ),
                        onClick = {
                            submit()
                        },
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Check,
                                contentDescription = null,
                                tint = Color.White
                            )
                            Text(
                                "Salvar",
                                fontWeight = FontWeight.Medium,
                                fontSize = 14.sp,
                                color = Color.White
                            )
                        }
                    }

                    OutlinedButton(
                        modifier = Modifier.weight(1f),
                        border = BorderStroke(width = 1.5.dp, color = Green300),
                        onClick = {
                            onDismissRequest()
                        },
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Cancel,
                                contentDescription = null,
                                tint = Green300
                            )
                            Text(
                                "Cancelar",
                                fontWeight = FontWeight.Medium,
                                fontSize = 14.sp,
                                color = Green300
                            )
                        }
                    }


                }
            }
        }

    }
}
