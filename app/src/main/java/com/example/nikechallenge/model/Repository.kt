package com.example.nikechallenge.model

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Repository {

    private var viewModelListener: ((DefinitionResponse?) -> Unit)? = null

    fun getDefinition(input: String) {
        onWordDefinitionChanged(input)
    }

    private fun onWordDefinitionChanged(input: String) {

        IRapidApi.getRetrofit().getResponse(input)
            .enqueue(object : Callback<DefinitionResponse> {
                override fun onFailure(call: Call<DefinitionResponse>, t: Throwable) {
                    viewModelListener?.invoke(null)
                }

                override fun onResponse(
                    call: Call<DefinitionResponse>,
                    response: Response<DefinitionResponse>
                ) {
                    viewModelListener?.invoke(response.body())
                }
            })
    }

    fun setListener(viewModeListener: (DefinitionResponse?) -> Unit) {
        this.viewModelListener = viewModeListener
    }

    fun removeListener() {
        viewModelListener = null
    }
}