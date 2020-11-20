package com.codingwithmitch.openchat.common.business.data.network

import com.codingwithmitch.openchat.common.business.data.network.NetworkErrors.NETWORK_DATA_NULL
import com.codingwithmitch.openchat.common.business.data.network.NetworkErrors.NETWORK_ERROR
import com.codingwithmitch.openchat.common.business.data.network.NetworkErrors.NETWORK_ERROR_UNKNOWN
import com.codingwithmitch.openchat.common.business.domain.state.*

abstract class ApiResponseHandler <ViewState, Data>(
    private val response: ApiResult<Data?>,
    private val stateEvent: StateEvent?
){

    suspend fun getResult(): DataState<ViewState>? {

        return when(response){

            is ApiResult.GenericError -> {
                DataState.error(
                    response = Response(
                        message = "${stateEvent?.errorInfo()}\n\nReason: ${response.errorMessage.toString()}",
                        uiComponentType = UIComponentType.Dialog(),
                        messageType = MessageType.Error()
                    ),
                    stateEvent = stateEvent
                )
            }

            is ApiResult.NetworkError -> {
                DataState.error(
                    response = Response(
                        message = "${stateEvent?.errorInfo()}\n\nReason: ${response.errorMessage?: NETWORK_ERROR_UNKNOWN}",
                        uiComponentType = UIComponentType.Dialog(),
                        messageType = MessageType.Error()
                    ),
                    stateEvent = stateEvent
                )
            }

            is ApiResult.Success -> {
                if(response.value == null){
                    DataState.error(
                        response = Response(
                            message = "${stateEvent?.errorInfo()}\n\nReason: ${NETWORK_DATA_NULL}.",
                            uiComponentType = UIComponentType.Dialog(),
                            messageType = MessageType.Error()
                        ),
                        stateEvent = stateEvent
                    )
                }
                else{
                    handleSuccess(resultObj = response.value)
                }
            }

        }
    }

    abstract suspend fun handleSuccess(resultObj: Data): DataState<ViewState>?

}