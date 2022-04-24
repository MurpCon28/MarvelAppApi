package com.example.marvelappapi.ui.detail

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.marvelappapi.LOG_TAG
import com.example.marvelappapi.R
import com.example.marvelappapi.databinding.FragmentDetailBinding
import com.example.marvelappapi.ui.shared.SharedViewModel

/**
 * A simple [Fragment] subclass.
 * Use the [DetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DetailFragment : Fragment() {
   private lateinit var viewModel: SharedViewModel
   private lateinit var navController: NavController

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        //up button(brings user back to main fragment)
        (requireActivity() as AppCompatActivity).run {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
        //allows the up button to work
        setHasOptionsMenu(true)
        navController = Navigation.findNavController(
                requireActivity(), R.id.nav_host
        )

        viewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)

        viewModel.selectedMarvel.observe(viewLifecycleOwner, Observer {
            Log.i(LOG_TAG, "Selected Marvel: ${it.superhero_name}")
        })

        val binding = FragmentDetailBinding.inflate(
            inflater, container, false
        )
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        return binding.root
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item?.itemId == android.R.id.home) {
            navController.navigateUp()
        }
        return super.onOptionsItemSelected(item)
    }
}