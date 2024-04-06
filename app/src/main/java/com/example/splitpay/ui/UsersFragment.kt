package com.example.splitpay.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.splitpay.adapter.UserAdapter
import com.example.splitpay.databinding.FragmentUsersBinding
import com.example.splitpay.models.User
import com.example.splitpay.utils.Constants.TAG
import com.example.splitpay.utils.NetworkResult
import com.example.splitpay.utils.TokenManager
import com.example.splitpay.viewmodel.UserViewModel


class UsersFragment : Fragment() {

    private var _binding: FragmentUsersBinding?=null
    private lateinit var userAdapter: UserAdapter
    private val binding get() = _binding!!
    private lateinit var tokenManager: TokenManager
    private lateinit var  userViewModel: UserViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding= FragmentUsersBinding.inflate(inflater,container,false)
        tokenManager=TokenManager(requireContext())
        userViewModel= ViewModelProvider(requireActivity()).get(UserViewModel::class.java)
        return  binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        userAdapter = UserAdapter(::onItemClicked,true)
        binding.friendrcview.layoutManager = LinearLayoutManager(requireContext())
        binding.friendrcview.adapter = userAdapter
        userViewModel.getAllUsers()
        observers()
    }
    private fun observers(){
        userViewModel._getAllUser.observe(viewLifecycleOwner)  { i->
            binding.progressBar.isVisible = false
            when (i) {
                is NetworkResult.Success -> {
                    userAdapter.submitList(i.data)
                }
                is NetworkResult.Loading -> {
                    binding.progressBar.isVisible = true
                }

                is NetworkResult.Error -> TODO()
            }
        }
    }
    private fun onItemClicked(user: User){
                userViewModel.sendFriendRequest(user.userId!!.toInt())
                observers1()
    }

    private fun observers1() {
        userViewModel._sendfriendRequest.observe(viewLifecycleOwner)  { i->
            binding.progressBar.isVisible = false
            when (i) {
                is NetworkResult.Success -> {
                     Toast.makeText(requireContext(), i.data?.message,Toast.LENGTH_SHORT).show()
                }
                is NetworkResult.Loading -> {
                    binding.progressBar.isVisible = true
                }

                is NetworkResult.Error -> TODO()
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null

    }

}