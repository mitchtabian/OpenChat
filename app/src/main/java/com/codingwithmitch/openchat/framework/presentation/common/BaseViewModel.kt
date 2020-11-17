package com.codingwithmitch.openchat.framework.presentation.common

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@ExperimentalCoroutinesApi
abstract class BaseViewModel<ViewState>: ViewModel(){

    private val _viewState: MutableStateFlow<ViewState> by lazy {
        MutableStateFlow(initNewViewState())
    }

    val viewState: StateFlow<ViewState> = _viewState

    fun setViewState(viewState: ViewState){
        _viewState.value = viewState
    }

    fun getCurrentViewStateOrNew(): ViewState {
        return _viewState.value?: initNewViewState()
    }

    abstract fun initNewViewState(): ViewState

}









