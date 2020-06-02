package com.example.nikechallenge.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.nikechallenge.R
import com.example.nikechallenge.model.data.DefinitionObject
import kotlinx.android.synthetic.main.definition_fragment_layout.*

class DefinitionFragment: Fragment() {

    companion object{
        const val DEFINITION_KEY = "GET_DEFINITION"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.definition_fragment_layout, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.getParcelable<DefinitionObject>(DEFINITION_KEY)?.let{ definition ->

            definition.apply {
                word_textview.text  = getString(R.string.DefinitionText)
                word_definition.text = this.definition
            }
        }
    }
}