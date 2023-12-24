package com.worldonetop.ourry.util.extension

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.lastOrNull
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


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


/** ripple 비활성화 클릭 이벤트 **/
inline fun Modifier.clickableNoRipple(crossinline onClick: ()->Unit): Modifier = composed {
    clickable(
        indication = null,
        interactionSource = remember { MutableInteractionSource() }
    ) {
        onClick()
    }
}