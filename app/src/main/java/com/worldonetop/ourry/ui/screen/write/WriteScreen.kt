package com.worldonetop.ourry.ui.screen.write

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.worldonetop.ourry.R
import com.worldonetop.ourry.ui.component.box.BlueShadow
import com.worldonetop.ourry.ui.component.input.InputField
import com.worldonetop.ourry.ui.screen.home.DATA_Category
import com.worldonetop.ourry.ui.theme.BackgroundPrimaryColor
import com.worldonetop.ourry.ui.theme.BackgroundSecondaryColor
import com.worldonetop.ourry.ui.theme.BodyTextStyle
import com.worldonetop.ourry.ui.theme.DefaultTextStyle
import com.worldonetop.ourry.ui.theme.Gray30
import com.worldonetop.ourry.ui.theme.Gray60
import com.worldonetop.ourry.ui.theme.Gray70
import com.worldonetop.ourry.ui.theme.LargeTextStyle
import com.worldonetop.ourry.ui.theme.Primary60
import com.worldonetop.ourry.ui.theme.SmallTextStyle
import com.worldonetop.ourry.ui.theme.default_horizontal_padding
import com.worldonetop.ourry.ui.theme.default_vertical_padding
import com.worldonetop.ourry.util.extension.clickableNoRipple
import com.worldonetop.ourry.util.extension.conditional

@Preview(showSystemUi = true)
@Composable
private fun WritePreview() {
    WriteScreen(
        rememberNavController(),
    )
}
@Composable
fun WriteScreen(
    navController: NavController
) {
    val scrollState = rememberScrollState()
    var category by remember { mutableStateOf("")}
    var showCategory by remember { mutableStateOf(false)}
    var title by remember { mutableStateOf("")}
    var body by remember { mutableStateOf("")}
    val choice = List(5){
        remember {
            mutableStateOf(if(it<2) "" else null)
        }
    }
    SelectCategory(
        isShow = showCategory,
        onDismiss = {
            showCategory = false
            it?.let { category = it }
        },
        currentCategory = category.ifEmpty { null },
        items = DATA_Category.drop(1)
    )

    Scaffold(
        topBar = {
            Header(
                canNext = isNext(category, title, body, choice),
                onBack = { navController.popBackStack() },
                onNext = {},
            )
        },
        containerColor = BackgroundSecondaryColor
    ) { innerPadding ->
        Column(
            Modifier
                .verticalScroll(scrollState)
                .padding(innerPadding)
                .padding(vertical = default_vertical_padding)
        ) {
            RowCategory(
                text = category
            ) {
                showCategory = true
            }

            Spacer(modifier = Modifier.height(16.dp))

            RowInput(
                value = title,
                onValueChange = { title = it },
                headerText = stringResource(id = R.string.write_title_header),
                singleLine = true,
                onDelete = null,
            )

            Spacer(modifier = Modifier.height(16.dp))

            RowInput(
                value = body,
                onValueChange = { body = it },
                headerText = stringResource(id = R.string.write_content_header),
                singleLine = false,
                maxLength = 300,
                onDelete = null,
            )

            Spacer(modifier = Modifier.height(16.dp))

            RowInput(
                value = choice[0].value!!,
                onValueChange = { if(it.length <= 16) choice[0].value = it },
                headerText = stringResource(id = R.string.write_choice_header),
                singleLine = true,
                onDelete = null,
            )

            Spacer(modifier = Modifier.height(8.dp))

            RowInput(
                value = choice[1].value!!,
                onValueChange = { if(it.length <= 16) choice[1].value = it },
                headerText = null,
                singleLine = true,
                onDelete = null,
            )

            Spacer(modifier = Modifier.height(8.dp))

            for(i in 2 until choice.size){
                choice[i].value?.let {
                    RowInput(
                        value = it,
                        onValueChange = { if(it.length <= 16) choice[i].value = it },
                        headerText = null,
                        singleLine = true,
                        onDelete = { choice[i].value = null },
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }

            choice.firstOrNull {
                it.value == null
            }?.let {
                BlueShadow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = default_horizontal_padding)
                        .clickable {
                            it.value = ""
                        }
                ) {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                horizontal = default_horizontal_padding,
                                vertical = 8.dp
                            ),
                        text = stringResource(id = R.string.write_choice_add),
                        style = SmallTextStyle,
                        color = Gray70,
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}

private fun isNext(
    category: String?,
    title: String?,
    body: String?,
    choice: List<MutableState<String?>>?
): Boolean {
    if(category.isNullOrBlank() || title.isNullOrBlank() || body.isNullOrBlank())
        return false

    if(choice == null || choice.take(2).any { it.value.isNullOrBlank() })
        return false

    return choice.drop(2).all {
        it.value == null || it.value!!.isNotBlank()
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
private fun Header(
    canNext: Boolean = false,
    onBack: ()-> Unit = {},
    onNext: ()-> Unit = {},
) {
    CenterAlignedTopAppBar(
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = BackgroundSecondaryColor
        ),
        navigationIcon = {
            Text(
                text = stringResource(id = R.string.cancel_short),
                style = LargeTextStyle,
                fontWeight = FontWeight.Medium,
                modifier = Modifier
                    .padding(start = 32.dp)
                    .clickableNoRipple {
                        onBack()
                    }
            )
        },
        title = {
            Text(
                text = stringResource(id = R.string.write_title),
                style = LargeTextStyle
            )
        },
        actions = {
            Text(
                text = stringResource(id = R.string.complete_short),
                style = LargeTextStyle,
                fontWeight = FontWeight.Medium,
                color = if(canNext) Color.Unspecified else Gray30,
                modifier = Modifier
                    .padding(end = 32.dp)
                    .clickableNoRipple {
                        if (canNext)
                            onNext()
                    }
            )
        },
    )
}

@Preview
@Composable
private fun RowCategory(
    text: String = "",
    onClick: ()->Unit = {}
){
    BlueShadow(
        modifier = Modifier
            .clickable {
                onClick()
            }
            .fillMaxWidth()
            .padding(horizontal = default_horizontal_padding)
    ) {
        Row(
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = text.ifEmpty { stringResource(id = R.string.write_category_placeholder) },
                style = DefaultTextStyle,
                modifier = Modifier.weight(1f)
            )
            Icon(
                imageVector = Icons.Default.KeyboardArrowRight,
                contentDescription = null
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showSystemUi = true)
@Composable
private fun SelectCategory(
    isShow: Boolean = true,
    onDismiss: (String?) -> Unit = {},
    currentCategory: String? = null,
    items: List<String> = emptyList()
) {
    var category by remember { mutableStateOf(currentCategory) }
    val scrollState = rememberScrollState()

    if (isShow) {
        Dialog(
            onDismissRequest = { onDismiss(category) },
            properties = DialogProperties(
                usePlatformDefaultWidth = false,
            ),
        ) {
            Scaffold(
                modifier = Modifier
                    .fillMaxSize()
                    .background(BackgroundPrimaryColor),
                topBar = {
                    TopAppBar(
                        navigationIcon = {
                            IconButton(onClick = {
                              onDismiss(null)
                            }) {
                                Icon(
                                    imageVector = Icons.Default.KeyboardArrowLeft,
                                    contentDescription = null
                                )
                            }
                        },
                        title = {
                            Text(text = stringResource(id = R.string.back))
                        },
                    )
                }
            ) { innerPadding ->
                Column( modifier = Modifier
                    .verticalScroll(scrollState)
                    .padding(innerPadding)
                ) {
                    for(item in items){
                        val isSelect = item == currentCategory
                        BlueShadow(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = default_horizontal_padding, vertical = 8.dp)
                                .conditional(isSelect) {
                                    border(
                                        width = 1.dp,
                                        color = Primary60,
                                        shape = RoundedCornerShape(size = 8.dp)
                                    )
                                }
                                .clickable {
                                    onDismiss(item)
                                }
                        ) {
                            Text(
                                text = item,
                                style = DefaultTextStyle,
                                color = if(isSelect) Primary60 else Color.Unspecified,
                                fontWeight = if(isSelect) FontWeight.SemiBold else FontWeight.Normal,
                                modifier = Modifier.padding(default_horizontal_padding)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun RowInput(
    value: String,
    onValueChange: (String) -> Unit,
    headerText: String? = null,
    singleLine: Boolean = true,
    maxLength: Int? = null,
    onDelete: (() -> Unit)? = null,
) {
    Column {
        headerText?.let {
            Text(
                modifier = Modifier.padding(
                    start = default_horizontal_padding,
                    bottom = 8.dp
                ),
                text = it,
                style = BodyTextStyle,
                color = Gray60
            )
        }
        BlueShadow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = default_horizontal_padding)
        ) {
            InputField(
                modifier = Modifier
                    .conditional(!singleLine) {
                        defaultMinSize(minHeight = 160.dp)
                    }
                    .conditional(maxLength != null) {
                        padding(bottom = 20.dp)
                    }
                ,
                value = value,
                onValueChange = { if(maxLength == null || value.length < maxLength) onValueChange(it) },
                contentPadding = PaddingValues(
                    horizontal = default_horizontal_padding,
                    vertical = 8.dp
                ),
                textStyle = DefaultTextStyle,
                singleLine = singleLine,
                trailing = onDelete?.let {
                    {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = null,
                            modifier = Modifier.clickableNoRipple { it() }
                        )
                    }
                },
            )

            maxLength?.let {
                    Row(
                        Modifier
                            .align(Alignment.BottomEnd)
                            .padding(horizontal = 10.dp, vertical = 5.dp)
                    ) {
                        Text(
                            text = value.length.toString(),
                        )
                        Text(
                            text = "/$it",
                            color = Gray30
                        )
                    }
            }
        }
    }
}