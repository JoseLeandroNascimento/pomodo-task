package com.example.pomodo_task.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
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
import androidx.compose.foundation.rememberScrollState
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pomodo_task.components.InputText
import com.example.pomodo_task.ui.theme.Green300

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateCatory(
    modifier: Modifier = Modifier,
    onDismissRequest: ()->Unit,
    show: Boolean
) {

    if(show){

        BasicAlertDialog(
            modifier = modifier,
            onDismissRequest = {
                onDismissRequest()
            },
        ) {

            Card(
                modifier = Modifier,
                colors = CardDefaults.cardColors(
                    containerColor = if (isSystemInDarkTheme()) MaterialTheme.colorScheme.surface else Color.White
                )

            ) {
                Column(
                    modifier = Modifier.padding(20.dp),
                    verticalArrangement = Arrangement.spacedBy(15.dp)
                ) {
                    Text(text = "Nova categoria")
                    InputText(
                        value = "",
                        onValueChange = {},
                        label = "Categoria"

                    )

                    ColorSelect()

                    Spacer(modifier =Modifier.height(8.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {

                        Button(
                            modifier = Modifier,
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Green300
                            ),
                            onClick = {},
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
                            modifier = Modifier,
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

}

@Composable
fun ColorSelect(modifier: Modifier = Modifier) {

    Row(
        modifier = modifier
            .horizontalScroll(state = rememberScrollState())
    ) {

        repeat(9) {
            Box(
                modifier = Modifier
                    .size(30.dp)
                    .background(color = Green300, shape = CircleShape)

            )
            Spacer(modifier = Modifier.width(7.5.dp))
        }
    }

}


@Preview(showBackground = true)
@Composable
private fun CreateCatoryPreview() {

    CreateCatory(show = true, onDismissRequest = {})
}