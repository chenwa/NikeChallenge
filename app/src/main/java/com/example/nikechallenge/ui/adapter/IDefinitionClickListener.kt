package com.example.nikechallenge.ui.adapter

import com.example.nikechallenge.model.data.DefinitionObject

interface IDefinitionClickListener {
    fun openSelectedDefinition(definition: DefinitionObject)
}