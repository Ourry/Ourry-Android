package com.worldonetop.ourry.ui.screen.home

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.worldonetop.ourry.R
import com.worldonetop.ourry.ui.theme.BackgroundPrimaryColor
import com.worldonetop.ourry.ui.theme.BodyTextStyle
import com.worldonetop.ourry.ui.theme.Gray10
import com.worldonetop.ourry.ui.theme.Gray50
import com.worldonetop.ourry.ui.theme.LargeTextStyle
import com.worldonetop.ourry.ui.theme.Primary20
import com.worldonetop.ourry.ui.theme.Primary60
import com.worldonetop.ourry.ui.theme.Primary80
import com.worldonetop.ourry.ui.theme.SmallTextStyle
import com.worldonetop.ourry.ui.theme.TinyTextStyle
import com.worldonetop.ourry.ui.theme.default_horizontal_padding
import com.worldonetop.ourry.util.extension.formatUIString
import com.worldonetop.ourry.util.extension.pxToDp
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import java.time.LocalDateTime
import java.util.Date
import java.util.zip.Checksum
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min
import kotlin.math.roundToInt

@Preview(showSystemUi = true)
@Composable
private fun HomePreview() {
    HomeScreen(
        rememberNavController(),
    )
}
@Composable
fun HomeScreen(
    navController: NavController
) {

    var categoryHeight by remember { mutableIntStateOf(200) }
    var selectIndex by remember { mutableIntStateOf(0) }
    var isShowView by remember { mutableStateOf(true) }

    val nestedScrollConnection = remember {
        object : NestedScrollConnection {
            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
                val dy = available.y
                if(dy < 0) {
                    if(isShowView)
                        isShowView = false
                } else {
                    if(!isShowView)
                        isShowView = true
                }
                return Offset.Zero
            }
        }
    }


    Scaffold(
        containerColor = BackgroundPrimaryColor,
        topBar = {
            Header(
                onSearch= {

                },
                onSetting= {

                },
                onNoti= {

                },
            )
        },
        floatingActionButton = {
            AnimatedVisibility(
                visible = isShowView,
                enter = slideInVertically{ it*2 },
                exit = slideOutVertically{ it*2 }
            ) {
                FloatingActionButton(
                    onClick = {  },
                    shape = CircleShape,
                    containerColor = Primary60,
                    contentColor = BackgroundPrimaryColor
                ) {
                    Icon(
                        modifier = Modifier.size(24.dp),
                        painter = painterResource(id = R.drawable.ic_write),
                        contentDescription = null
                    )
                }
            }
        }
    ) { innerPadding ->
        Box(
            Modifier.padding(innerPadding)
        ) {
            Content(
                modifier = Modifier.nestedScroll(nestedScrollConnection),
                spaceTop = categoryHeight.pxToDp(),
                items = DATA_Board
            )
            AnimatedVisibility(
                visible = isShowView,
                enter = slideInVertically() + fadeIn(),
                exit = slideOutVertically() + fadeOut()
            ) {
                Category(
                    modifier = Modifier.onSizeChanged { categoryHeight = it.height },
                    items = DATA_Category,
                    currentIndex = selectIndex
                )
            }
        }
    }
}

@Preview
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun Header(
    onSearch: ()-> Unit = {},
    onSetting: ()-> Unit = {},
    onNoti: ()-> Unit = {},
) {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = BackgroundPrimaryColor,
        ),
        title = {},
        windowInsets = WindowInsets(left = default_horizontal_padding, right = default_horizontal_padding),
        navigationIcon = {
            Image(
                modifier = Modifier
                    .size(84.dp, 30.dp),
                painter = painterResource(id = R.drawable.app_icon),
                contentDescription = null
            )
        },
        actions = {
            IconButton(onClick = onSearch) {
                Icon(imageVector = Icons.Outlined.Search, contentDescription = null)
            }
            IconButton(onClick = onSetting) {
                Icon(imageVector = Icons.Outlined.Settings, contentDescription = null)
            }
            IconButton(onClick = onNoti) {
                Icon(imageVector = Icons.Outlined.Notifications, contentDescription = null)
            }
        }
    )
}
@Composable
private fun Category(
    modifier: Modifier = Modifier,
    items: List<String>,
    currentIndex: Int,
) {
    val scrollState = rememberScrollState()

    Row(
        modifier
            .background(BackgroundPrimaryColor)
            .horizontalScroll(scrollState)
    ) {
        for(i in items.indices) {
            CategoryItem(
                text = items[i],
                isChecked = currentIndex  == i,
            )
        }
    }
}

@Preview
@Composable
private fun CategoryItem(
    modifier: Modifier = Modifier,
    text: String = "전체",
    isChecked: Boolean = false,
    onChecked: ()->Unit = {},
) {
    Button(
        modifier = Modifier.padding(horizontal = 4.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = if(isChecked) BackgroundPrimaryColor else Gray10
        ),
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(1.dp, if(isChecked) Primary60 else Gray10),
        onClick = onChecked
    ) {
        Text(
            text = text,
            style = SmallTextStyle,
            color = if(isChecked) Primary60 else Gray50
        )
    }
}

@Composable
private fun Content(
    modifier: Modifier = Modifier,
    spaceTop: Dp = 0.dp,
    items: List<TEMP_Board>
) {
    LazyColumn(
        modifier = modifier
    ) {
        item { Spacer(Modifier.height(spaceTop)) }
        items(items) { item ->
            ContentItem(
                title = item.title,
                writer = item.writer,
                responseCnt = item.responseCnt,
                commentCnt = item.commentCnt,
                time = item.time,
                onClick = {},
            )
        }
    }
}


@Preview
@Composable
private fun ContentItem(
    modifier: Modifier = Modifier,
    title: String = "전세집을 구하고 있어요",
    writer: String = "홍길동",
    responseCnt: Int = 10,
    commentCnt: Int = 72,
    time: Date = Date(),
    onClick: ()->Unit = {},
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = default_horizontal_padding, vertical = 4.dp)
            .shadow(
                elevation = 4.dp,
                shape = RoundedCornerShape(size = 18.dp),
                ambientColor = Primary20,
                spotColor = Primary20
            )
            .background(BackgroundPrimaryColor)
            .padding(default_horizontal_padding)
    ) {
        Column {
            Text(
                text = title,
                style = LargeTextStyle,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
            )
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = writer,
                    style = SmallTextStyle,
                    color = Gray50
                )

                Spacer(modifier = Modifier.weight(1f))

                ContentItemInfo(painterResource(id = R.drawable.ic_vote), responseCnt.toString())
                Spacer(modifier = Modifier.width(10.dp))
                ContentItemInfo(painterResource(id = R.drawable.ic_comment), commentCnt.toString())
                Spacer(modifier = Modifier.width(10.dp))
                ContentItemInfo(painterResource(id = R.drawable.ic_time), time.formatUIString())
            }
        }
    }
}

@Composable
private fun ContentItemInfo(
    icon: Painter,
    text: String
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier.size(15.dp),
            painter = icon,
            contentDescription = null,
            tint = Gray50,
        )
        Spacer(modifier = Modifier.width(5.dp))
        Text(
            text = text,
            style = TinyTextStyle,
            fontWeight = FontWeight.W600,
            color = Gray50
        )
    }
}