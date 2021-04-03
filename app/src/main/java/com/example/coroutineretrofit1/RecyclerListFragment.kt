package com.example.coroutineretrofit1

import android.os.Bundle
import android.os.SystemClock
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.coroutineretrofit1.model.RecylerList
import kotlinx.android.synthetic.main.fragment_recycler_list.*


/**
 * A simple [Fragment] subclass.
 * Use the [RecyclerListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RecyclerListFragment : Fragment() {
    private lateinit var name: String
    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var t: MyThread

    private fun getThread(): MyThread {
        return MyThread("First", progressBar)
    }

    private lateinit var recyclerAdapter: RecyclerViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_recycler_list, container, false)
        val viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        initializeViewModel(view, viewModel)
        initViewModel(view, viewModel)

        return view
    }

    private fun initViewModel(view: View?, viewModel: MainActivityViewModel) {

        viewModel.getRecyclerListObservers().observe(this, Observer<RecylerList> {
            progressBar.visibility = View.GONE
            if (t.isAlive) {
                t.setMyExit(true)
            }
            if (it != null) {
                recyclerAdapter.setItemList(it.items)

            } else {
                Toast.makeText(activity, "Error getting data", Toast.LENGTH_SHORT).show()
            }

        })
    }

    private fun initializeViewModel(view: View, viewModel: MainActivityViewModel) {
        val searchView: SearchView = view.findViewById<SearchView>(R.id.searchview)
        recyclerView = view.findViewById(R.id.recyclerview)
        progressBar = view.findViewById(R.id.progressBar)
        progressBar.max = 15
        t=getThread()
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(text: String?): Boolean {
                searchView.setIconified(true);
                searchView.clearFocus();
                if (!text.isNullOrEmpty()) {
                    if (t.isAlive) {
                        t.setMyExit(true)
                    }
                    SystemClock.sleep(500)
                    t = getThread()
                    t.start()
                    progressBar.visibility = View.VISIBLE
                    viewModel.makeApiCall(text.trim())
                }

                return true
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                return true
            }

        })

        recyclerView.layoutManager = LinearLayoutManager(activity)
        val decoration = DividerItemDecoration(activity, DividerItemDecoration.VERTICAL)
        recyclerView.addItemDecoration(decoration)
        recyclerAdapter = RecyclerViewAdapter()
        recyclerView.adapter = recyclerAdapter
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            RecyclerListFragment()
    }
}