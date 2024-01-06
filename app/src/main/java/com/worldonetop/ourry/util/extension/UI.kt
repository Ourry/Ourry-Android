package com.worldonetop.ourry.util.extension

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.lastOrNull
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Date
import java.util.Locale


/**************************
 *        Modifier        *
 **************************/

/** 조건이 True 면 내부문 실행 **/
fun Modifier.conditional(condition : Boolean, modifier : Modifier.() -> Modifier) : Modifier {
    return if (condition) {
        then(modifier(Modifier))
    } else {
        this
    }
}

/** 조건에 따른 Modifier 정의 **/
fun Modifier.conditional(
    condition: Boolean,
    ifTrue: Modifier.() -> Modifier,
    ifFalse: (Modifier.() -> Modifier)? = null
): Modifier {
    return if (condition) {
        then(ifTrue(Modifier))
    } else if (ifFalse != null) {
        then(ifFalse(Modifier))
    } else {
        this
    }
}

/** ripple 비활성화 클릭 이벤트 **/
inline fun Modifier.clickableNoRipple(crossinline onClick: ()->Unit): Modifier = composed {
    clickable(
        indication = null,
        interactionSource = remember { MutableInteractionSource() }
    ) {
        onClick()
    }
}


/**************************
 *       ViewModel        *
 **************************/

/** ViewModel 값 업데이트 **/
fun <T> ViewModel.updateFlowFromCoroutine(flow: MutableSharedFlow<T>, settingListener:suspend (T)->T){
    viewModelScope.launch {
        if(flow is MutableStateFlow){
            flow.update { settingListener(it)  }
        }else{
            flow.lastOrNull()?.let{
                flow.emit(settingListener(it))
            }
        }
    }
}


/**************************
 *        Converter        *
 **************************/

@Composable
fun Dp.dpToPx() = with(LocalDensity.current) { this@dpToPx.toPx() }

@Composable
fun Int.pxToDp() = with(LocalDensity.current) { this@pxToDp.toDp() }

fun Date.formatUIString(): String{
    val currentTime = Calendar.getInstance()

    val minutes = (currentTime.timeInMillis - time) / 1000 / 60
    val hours = minutes / 60

    if(minutes < 60)
        return "${minutes}분 전"
    else if(hours < 24)
        return "${hours}시간 전"

    val toCalendar = Calendar.getInstance().apply { time = this@formatUIString }

    if(toCalendar.get(Calendar.YEAR) == currentTime.get(Calendar.YEAR))
        return SimpleDateFormat("MM/dd", Locale.getDefault()).format(this)
    else
        return SimpleDateFormat("yyyy/MM/dd", Locale.getDefault()).format(this)
}