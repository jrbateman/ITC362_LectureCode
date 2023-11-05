package com.bateman.msu.criminalintent

import android.os.Bundle
import android.util.Log
import android.util.Log.DEBUG
import android.view.*
import androidx.core.app.BundleCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bateman.msu.criminalintent.databinding.FragmentCrimeListBinding
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

//private const val TAG = "CrimeListFragment"

class CrimeListFragment :Fragment() {

    private var _binding: FragmentCrimeListBinding? = null
    private val binding
        get() = checkNotNull(_binding) {
            "Cannot access binding because it is null. Is the view visible?"
        }

    private val crimeListViewModel :CrimeListViewModel by viewModels()
//    private var job: Job? = null

  /*  override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "total Crimes :  ${crimeListViewModel.crimes.size}")
    }*/
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCrimeListBinding.inflate(inflater,container, false)

        binding.crimeRecyclerView.layoutManager = LinearLayoutManager(context)

       /* val crimes = crimeListViewModel.crimes
        val adapter = CrimeListAdapter(crimes)
        binding.crimeRecyclerView.adapter = adapter*/

        return binding.root
    }

  /*  override fun onStart(){
        super.onStart()

        job = viewLifecycleOwner.lifecycleScope.launch{
            val crimes = crimeListViewModel.loadCrimes()
            binding.crimeRecyclerView.adapter = CrimeListAdapter(crimes)
        }

    }*/

 /*   override fun onStop() {
        super.onStop()
        job?.cancel()

    }*/

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
              // val crimes = crimeListViewModel.loadCrimes()
               crimeListViewModel.crimes.collect() { crimes ->
                   binding.crimeRecyclerView.adapter =
                       CrimeListAdapter(crimes){ crimeId->
                           findNavController().navigate(
                              // R.id.show_crime_detail
                           CrimeListFragmentDirections.showCrimeDetail(crimeId)
                           )
                       }
                }
             }
            }
        }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
