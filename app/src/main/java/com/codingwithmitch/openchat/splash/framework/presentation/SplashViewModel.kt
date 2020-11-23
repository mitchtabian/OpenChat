package com.codingwithmitch.openchat.splash.framework.presentation

import androidx.datastore.DataStore
import androidx.datastore.preferences.Preferences
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codingwithmitch.openchat.common.business.data.util.GenericErrors
import com.codingwithmitch.openchat.common.business.domain.state.*
import com.codingwithmitch.openchat.session.SessionManager
import com.codingwithmitch.openchat.splash.business.interactors.CheckAuthToken
import com.codingwithmitch.openchat.splash.framework.datasource.preferences.SplashPreference.KEY_ACCOUNT_PK
import com.codingwithmitch.openchat.splash.framework.presentation.state.SplashStateEvent
import com.codingwithmitch.openchat.splash.framework.presentation.state.SplashStateEvent.*
import com.codingwithmitch.openchat.splash.framework.presentation.state.SplashViewState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Named

@ExperimentalCoroutinesApi
class SplashViewModel
@ViewModelInject
constructor(
    @Assisted private val savedStateHandle: SavedStateHandle,
    private val sessionManager: SessionManager,
    private val checkAuthTokenUseCase: CheckAuthToken,
    @Named("auth_preferences") private val authPreferences: DataStore<Preferences>,
): ViewModel(){

    val sessionState = sessionManager.sessionState

    /**
    * ----------------------------------------------------------
    * GENERIC FOR BASE VIEWMODEL?!
    *  ----------------------------------------------------------
    */
    val dataChannelManager: DataChannelManager<SplashViewState>
            = object: DataChannelManager<SplashViewState>(){

        override fun handleNewData(data: SplashViewState) {
            this@SplashViewModel.handleNewData(data)
        }
    }

    val shouldDisplayProgressBar: StateFlow<Boolean>
            = dataChannelManager.shouldDisplayProgressBar

    val stateMessage: StateFlow<StateMessage?>
        get() = dataChannelManager.messageStack.stateMessage

    fun setupChannel() = dataChannelManager.setupChannel()

    fun handleNewData(data: SplashViewState){
        data.authToken?.let { authToken ->
            sessionManager.onLoginSuccess(authToken = authToken)
        }
    }

    fun emitStateMessageEvent(
        stateMessage: StateMessage,
        stateEvent: StateEvent
    ) = flow{
        emit(
            DataState.error<ViewState>(
                response = stateMessage.response,
                stateEvent = stateEvent
            )
        )
    }

    fun emitInvalidStateEvent(stateEvent: StateEvent) = flow {
        emit(
            DataState.error<ViewState>(
                response = Response(
                    message = GenericErrors.INVALID_STATE_EVENT,
                    uiComponentType = UIComponentType.None(),
                    messageType = MessageType.Error()
                ),
                stateEvent = stateEvent
            )
        )
    }

    fun clearStateMessage(index: Int = 0){
        dataChannelManager.clearStateMessage(index)
    }

    fun clearActiveStateEvents() = dataChannelManager.clearActiveStateEventCounter()

    fun clearAllStateMessages() = dataChannelManager.clearAllStateMessages()

    fun printStateMessages() = dataChannelManager.printStateMessages()

    fun cancelActiveJobs() = dataChannelManager.cancelJobs()

    fun launchJob(
        stateEvent: StateEvent,
        jobFunction: Flow<DataState<SplashViewState>?>
    ) = dataChannelManager.launchJob(stateEvent, jobFunction)

    suspend fun setStateEvent(stateEvent: SplashStateEvent){
        val job: Flow<DataState<SplashViewState>?> = when(stateEvent){
            is CheckAuthTokenEvent -> {
                checkAuthTokenUseCase.execute(
                    stateEvent = stateEvent,
                    accountPk = stateEvent.accountPk
                )
            }
        }
        launchJob(stateEvent, job)
    }


    /**
     * ----------------------------------------------------------
     * GENERIC FOR BASE VIEWMODEL?!
     *  ----------------------------------------------------------
     */

    fun checkAuthToken(){
        authPreferences.data.onEach { preference ->
            preference.get(KEY_ACCOUNT_PK)?.let { pk ->
                setStateEvent(CheckAuthTokenEvent(pk))
            }
        }.launchIn(viewModelScope)
    }
}















