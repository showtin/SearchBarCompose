package com.learn.scrolltextapp.presentation

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import com.learn.scrolltextapp.R

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBarCompose(
    modifier: Modifier = Modifier,
    hint: String = "Search",
    onSearchParamChange: (String) -> Unit,
    onSearchClick: (String) -> Unit,
) {
    Scaffold {
        Box(
            modifier = modifier
                .clip(RoundedCornerShape(100))
                .background(

                    Color.Gray.copy(alpha = .24F)

                )
                .fillMaxWidth()
                .height(TextFieldDefaults.MinHeight) // 56.dp
        ) {
            var searchParam: String by remember { mutableStateOf("") }

            val focusRequester = remember { FocusRequester() }
            val focusManager = LocalFocusManager.current

            TextField(
                value = searchParam,
                onValueChange = { newValue ->
                    searchParam = if (newValue.trim().isNotEmpty()) newValue else ""
                    onSearchParamChange(newValue)
                },
                modifier = Modifier
                    .fillMaxSize()
                    .focusRequester(focusRequester = focusRequester),
                singleLine = true,
                placeholder = {
                    Text(text = hint)
                },
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                keyboardOptions = KeyboardOptions(
                    autoCorrect = true,
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Search
                ),
                keyboardActions = KeyboardActions(
                    onSearch = {
                        onSearchClick(searchParam)
                    }
                ),
                trailingIcon = {
                    Row {
                        AnimatedVisibility(visible = searchParam.trim().isNotEmpty()) {
                            IconButton(onClick = {
                                focusManager.clearFocus()
                                searchParam = ""
                                onSearchParamChange(searchParam)
                            }) {
                                Icon(
                                    imageVector = Icons.Default.Clear,
                                    contentDescription = null
                                )
                            }
                        }

                        IconButton(onClick = {
                            onSearchClick(searchParam)
                        }) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_search),
                                contentDescription = null
                            )
                        }
                    }
                }
            )

        }
    }
}