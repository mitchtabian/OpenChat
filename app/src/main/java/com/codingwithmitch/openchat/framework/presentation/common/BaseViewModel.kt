package com.codingwithmitch.openchat.framework.presentation.common

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@ExperimentalCoroutinesApi
abstract class BaseViewModel<ViewState>: ViewModel(){

    private val _viewState: MutableStateFlow<ViewState> = MutableStateFlow(getCurrentViewStateOrNew())

    val viewState: StateFlow<ViewState> get() =  _viewState

    fun setViewState(viewState: ViewState){
        _viewState.value = viewState
    }

    fun getCurrentViewStateOrNew(): ViewState {
        return _viewState.value?: initNewViewState()
    }

    abstract fun initNewViewState(): ViewState

}









