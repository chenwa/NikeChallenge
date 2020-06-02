package com.example.nikechallenge.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nikechallenge.R
import com.example.nikechallenge.model.DefinitionResponse
import com.example.nikechallenge.viewmodel.DefinitionsViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val definitionsViewModel: DefinitionsViewModel by viewModel()

    private val urbanAdapter : DefinitionListAdapter by lazy {
        DefinitionListAdapter()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()

        definitionsViewModel.getUrbanDescription().observe(this,
            Observer<DefinitionResponse> { t ->
                t?.let { initAdapter((it)) }
            })
        definitionsViewModel.getUrbanDescriptionError().observe(this,
            Observer<String> { t ->
                t?.let { Toast.makeText(this@MainActivity, t, Toast.LENGTH_SHORT).show()}
            })
    }

    private fun initViews(){
        setSupportActionBar(toolbar)
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.adapter = urbanAdapter
        navigation_view.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.sort_up -> {
                    sortUpvotes()
                    drawer_layout.closeDrawers()
                    true
                }
                R.id.sort_down -> {
                    sortDownvotes()
                    drawer_layout.closeDrawers()
                    true
                }
                else -> {
                    false
                }
            }
        }
    }

    private fun sortDownvotes() {
        definitionsViewModel.sortDataDown()
    }

    private fun sortUpvotes() {
        definitionsViewModel.sortDataUp()
    }

    fun initAdapter(dataSet: DefinitionResponse){
        urbanAdapter.dataSet = dataSet
    }

    fun searchForDefinition(userInput: String) {
        definitionsViewModel.getDefinitions(userInput)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_search, menu)

        val searchView = menu?.findItem(R.id.search_action)?.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let { searchForDefinition(it) }
                menu.findItem(R.id.search_action).collapseActionView()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }
        })

        val drawerAction = menu.findItem(R.id.drawer_menu)
        drawerAction.setOnMenuItemClickListener {
            drawer_layout.openDrawer(navigation_view)
            true
        }

        return super.onCreateOptionsMenu(menu)
    }

}
