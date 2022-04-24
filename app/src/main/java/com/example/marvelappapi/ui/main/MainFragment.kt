package com.example.marvelappapi.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.marvelappapi.LOG_TAG
import com.example.marvelappapi.R
import com.example.marvelappapi.data.Marvel
import com.example.marvelappapi.ui.shared.SharedViewModel

class MainFragment : Fragment(),
    MainRecyclerAdapter.MarvelItemListener {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: SharedViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var swipeLayout: SwipeRefreshLayout
    private lateinit var navController: NavController

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        (requireActivity() as AppCompatActivity).run {
            supportActionBar?.setDisplayHomeAsUpEnabled(false)
        }

        val view = inflater.inflate(R.layout.main_fragment, container, false)
        recyclerView = view.findViewById(R.id.recyclerView)
        navController = Navigation.findNavController(
            requireActivity(), R.id.nav_host
        )

        swipeLayout = view.findViewById(R.id.swipeLayout)
        swipeLayout.setOnRefreshListener {
            viewModel.refeshData()
        }

        viewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
        viewModel.marvelData.observe(viewLifecycleOwner, Observer {
            val adapter = MainRecyclerAdapter(requireContext(), it, this)
            recyclerView.adapter = adapter
            //code removes the refreshing symbol when all data is retrived
            swipeLayout.isRefreshing = false
        })
        return view
    }

    override fun onMarvelItemClick(marvel: Marvel) {
        Log.i(LOG_TAG, "Selected Marvel: ${marvel.superhero_name}")
        viewModel.selectedMarvel.value = marvel
        navController.navigate(R.id.action_nav_detail)
    }

}