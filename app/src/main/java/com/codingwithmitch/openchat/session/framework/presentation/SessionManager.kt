package com.codingwithmitch.openchat.session.framework.presentation

import androidx.datastore.DataStore
import androidx.datastore.preferences.Preferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.codingwithmitch.openchat.session.business.domain.model.AuthToken
import com.codingwithmitch.openchat.common.business.data.util.GenericErrors
import com.codingwithmitch.openchat.common.business.domain.state.*
import com.codingwithmitch.openchat.session.framework.datasource.datastore.SessionPreferences.KEY_ACCOUNT_PK
import com.codingwithmitch.openchat.session.framework.presentation.SessionStateEvent.*
import com.codingwithmitch.openchat.session.business.interactors.CheckAuthToken
import com.codingwithmitch.openchat.session.business.interactors.Login
import com.codingwithmitch.openchat.session.business.interactors.Logout
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

@ExperimentalCoroutinesApi
@Singleton
class SessionManager
@Inject
constructor(
        private val logoutUseCase: Logout,
        private val loginUseCase: Login,
        private val checkAuthTokenUseCase: CheckAuthToken,
        @Named("auth_preferences") private val authPreferences: DataStore<Preferences>,
) {

    private val _sessionState: MutableLiveData<SessionState?> = MutableLiveData()

    val sessionState: LiveData<SessionState?> get() =  _sessionState

    private var sessionScope: CoroutineScope? = null

    /**
     * ----------------------------------------------------------
     * GENERIC FOR BASE VIEWMODEL?!
     *  ----------------------------------------------------------
     */
    val dataChannelManager: DataChannelManager<SessionState>
            = object: DataChannelManager<SessionState>(){

        override suspend fun handleNewData(data: SessionState) {
            this@SessionManager.handleNewData(data)
        }
    }

    init {
        setupChannel()
    }

    val shouldDisplayProgressBar: StateFlow<Boolean>
            = dataChannelManager.shouldDisplayProgressBar

    val stateMessage: StateFlow<StateMessage?>
        get() = dataChannelManager.messageStack.stateMessage

    fun setupChannel() = dataChannelManager.setupChannel()

    suspend fun handleNewData(data: SessionState){
        data.authToken?.let { authToken ->
            onLoginSuccess(authToken = authToken)
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
            jobFunction: Flow<DataState<SessionState>?>
    ) = dataChannelManager.launchJob(stateEvent, jobFunction)


    fun setStateEvent(stateEvent: SessionStateEvent){
        getSessionManagerScope().launch {
            val job: Flow<DataState<SessionState>?> = when(stateEvent){

                is LogoutEvent -> {
                    onLogout()
                    logoutUseCase.execute(stateEvent = stateEvent)
                }
                is LoginEvent -> {
                    loginUseCase.execute(
                            stateEvent = stateEvent,
                            email = stateEvent.email,
                            password = stateEvent.password
                    )
                }

                is CheckAuthTokenEvent -> {
                    checkAuthTokenUseCase.execute(
                            stateEvent = stateEvent,
                            accountPk = stateEvent.accountPk
                    )
                }
            }
            launchJob(stateEvent, job)
        }
    }

    /**
     * ----------------------------------------------------------
     * GENERIC FOR BASE VIEWMODEL?!
     *  ----------------------------------------------------------
     */

    fun checkAuthToken(){
        authPreferences.data.onEach { preferences ->
            preferences.get(KEY_ACCOUNT_PK)?.let { pk ->
                setStateEvent(CheckAuthTokenEvent(pk))
            }
        }.launchIn(getSessionManagerScope())

    }

    suspend fun onLoginSuccess(authToken: AuthToken){
        withContext(Main){
            _sessionState.value = SessionState(authToken)
        }
    }

    private suspend fun onLogout(){
        withContext(Main){
            _sessionState.value = SessionState(authToken = null)
        }
    }

    private fun getSessionManagerScope(): CoroutineScope{
        if(sessionScope == null){
            sessionScope = CoroutineScope(IO)
        }
        return sessionScope as CoroutineScope
    }
}














