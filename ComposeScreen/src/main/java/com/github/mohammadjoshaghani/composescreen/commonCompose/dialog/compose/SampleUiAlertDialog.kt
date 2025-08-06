package com.github.mohammadjoshaghani.composescreen.commonCompose.dialog.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.github.mohammadjoshaghani.composescreen.commonCompose.UISpacer
import com.github.mohammadjoshaghani.composescreen.commonCompose.dialog.UIAlertDialog
import com.github.mohammadjoshaghani.composescreen.extension.clickableWitoutHighlight


@Composable
internal fun UIAlertDialog.SampleUiAlertDialog(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .clickableWitoutHighlight {
                if (setCanceledOnTouchOutside) {
                    dismiss()
                }
            }
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Card(
                modifier = Modifier.widthIn(max = 300.dp),
                colors = CardDefaults.cardColors(MaterialTheme.colorScheme.surface),
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    title?.let {
                        Text(
                            text = it,
                            style = MaterialTheme.typography.headlineMedium,
                            modifier = Modifier
                                .height(56.dp)
                                .fillMaxWidth()
                                .wrapContentWidth(Alignment.CenterHorizontally)
                                .wrapContentHeight(Alignment.CenterVertically)
                        )
                    }

                    message?.let {
                        Text(
                            text = it,
                            style = MaterialTheme.typography.bodyMedium,
                            textAlign = TextAlign.Right,
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentWidth(Alignment.CenterHorizontally)
                        )
                    }

                    Row(
                        modifier = Modifier
                            .padding(top = 32.dp)
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        buttonCancelTitle?.let {
                            TextButton(
                                modifier = Modifier
                                    .weight(1f),
                                onClick = {
                                    dismiss()
                                    buttonCancelBlock?.invoke()
                                }
                            ) {
                                Text(
                                    text = it,
                                    color = cancelButtonContentColor,
                                )
                            }
                        }

                        if (buttonCancelTitle != null && buttonActionText != null){
                            UISpacer(8)
                        }

                        buttonActionText?.let { textTitle ->
                            Button(
                                onClick = {
                                    dismiss()
                                    buttonActionBlock?.invoke()
                                },
                                colors = ButtonDefaults.buttonColors(
                                    primaryButtonContainerColor,
                                    contentColor = primaryButtonContentColor
                                ),
                                modifier = Modifier
                                    .weight(1f),
                                shape = MaterialTheme.shapes.large
                            ) {
                                Text(
                                    text = textTitle,
                                    softWrap = false,
                                    maxLines = 1,
                                    color = primaryButtonContentColor,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier.wrapContentWidth()
                                )
                            }


                        }
                    }
                }
            }
        }
    }
}