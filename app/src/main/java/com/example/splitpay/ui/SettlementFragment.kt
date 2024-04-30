package com.example.splitpay.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.splitpay.adapter.SettlementAdapter
import com.example.splitpay.databinding.FragmentSettlementBinding
import com.example.splitpay.utils.NetworkResult
import com.example.splitpay.utils.TokenManager
import com.example.splitpay.viewmodel.UserViewModel

class SettlementFragment : Fragment() {
    private var _binding: FragmentSettlementBinding? = null
    private val binding get() = _binding!!
    private lateinit var tokenManager: TokenManager
    private lateinit var userViewModel: UserViewModel
    private lateinit var adapter: SettlementAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSettlementBinding.inflate(inflater, container, false)
        tokenManager = TokenManager(requireContext())
        userViewModel = ViewModelProvider(requireActivity()).get(UserViewModel::class.java)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter= SettlementAdapter()
        val id=arguments?.getInt("groupID")
        Log.i("settlement",id.toString())
        userViewModel.getsettlement(id!!.toInt())
        binding.settlementList.layoutManager= LinearLayoutManager(requireContext())
        binding.settlementList.adapter=adapter
        observer()
    }

    private fun observer() {
        userViewModel._getsettlement.observe(viewLifecycleOwner) { i->

            binding.progressBar.isVisible = false
            when (i) {
                is NetworkResult.Success -> {
                    adapter.submitList(i.data!!.data!!.res)
                }
                is NetworkResult.Loading -> {
                    binding.progressBar.isVisible = true
                }

                is NetworkResult.Error ->{

                }
            }
        }
    }
}