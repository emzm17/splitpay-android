package com.example.splitpay.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.splitpay.R
import com.example.splitpay.adapter.RequestAdapter
import com.example.splitpay.adapter.UserAdapter
import com.example.splitpay.databinding.FragmentRequestBinding
import com.example.splitpay.databinding.FragmentUsersBinding
import com.example.splitpay.models.DataItem1
import com.example.splitpay.models.User
import com.example.splitpay.utils.NetworkResult
import com.example.splitpay.utils.TokenManager
import com.example.splitpay.viewmodel.UserViewModel


class RequestFragment : Fragment() {
    private var _binding: FragmentRequestBinding?=null
    private lateinit var requestAdapter: RequestAdapter
    private val binding get() = _binding!!
    private lateinit var tokenManager: TokenManager
    private lateinit var  userViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding= FragmentRequestBinding.inflate(inflater,container,false)
        tokenManager=TokenManager(requireContext())
        userViewModel= ViewModelProvider(requireActivity()).get(UserViewModel::class.java)
        return  binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requestAdapter= RequestAdapter(::onItemClicked)
        binding.friendrcview.layoutManager = LinearLayoutManager(requireContext())
        binding.friendrcview.adapter =requestAdapter
        userViewModel.getFriendRequest()
        observers()
    }
    private fun onItemClicked(user:DataItem1){
        userViewModel.acceptFriendRequest(user.userId!!.toInt())
        observers1()
    }
    private fun observers1() {
        userViewModel._acceptfriendRequest.observe(viewLifecycleOwner)  { i->
            binding.progressBar.isVisible = false
            when (i) {
                is NetworkResult.Success -> {
                    Toast.makeText(requireContext(), i.data?.message, Toast.LENGTH_SHORT).show()
                }
                is NetworkResult.Loading -> {
                    binding.progressBar.isVisible = true
                }

                is NetworkResult.Error -> {}

            }
        }
    }


    private fun observers() {
        userViewModel._getfriendRequest.observe(viewLifecycleOwner)  { i->
            binding.progressBar.isVisible = false
            when (i) {
                is NetworkResult.Success -> {
                    requestAdapter.submitList(i.data!!.data)
                }
                is NetworkResult.Loading -> {
                    binding.progressBar.isVisible = true
                }

                is NetworkResult.Error -> {

                }

            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null

    }

}