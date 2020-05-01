package com.example.nikechallenge.viewmodel

import com.example.nikechallenge.model.DefinitionResponse
import com.example.nikechallenge.model.Repository
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.LiveData

class DefinitionsViewModel : ViewModel() {

    private val urbanDescription = MutableLiveData<DefinitionResponse>()
    private val urbanDescriptionError = MutableLiveData<String>()

    private val repository: Repository by lazy {
        Repository()
    }

    fun getDefinitions(input: String) {
        repository.setListener(::updateObservable)
        repository.getDefinition(input)
    }

    private fun updateObservable(response: DefinitionResponse?) {
        if (response == null) {
            urbanDescriptionError.postValue("Error")
        }
        else {
            urbanDescription.postValue(response)
        }
    }

    fun sortDataUp() {
        urbanDescription.value?.list?.sortedWith(compareBy {
            it.thumbs_up
        })?.reversed()?.apply {
            urbanDescription.postValue(DefinitionResponse(this))
        }
    }

    fun sortDataDown() {
        urbanDescription.value?.list?.sortedWith(compareBy {
            it.thumbs_down
        })?.reversed()?.apply {
            urbanDescription.postValue(DefinitionResponse(this))
        }

    }

    fun getUrbanDescription(): LiveData<DefinitionResponse> = urbanDescription
    fun getUrbanDescriptionError(): LiveData<String> = urbanDescriptionError

    override fun onCleared() {
        super.onCleared()
        repository.removeListener()
    }
}