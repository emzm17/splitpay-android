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
import com.example.splitpay.models.DataItem
import com.example.splitpay.models.User
import com.example.splitpay.utils.NetworkResult
import com.example.splitpay.utils.TokenManager
import com.example.splitpay.viewmodel.UserViewModel


class UsersFragment : Fragment() {

    private var _binding: FragmentUsersBinding?=null
    private lateinit var userAdapter: UserAdapter
    private val binding get() = _binding!!
    private lateinit var tokenManager: TokenManager
    private lateinit var  userViewModel: UserViewModel
    private lateinit var allUser:ArrayList<DataItem>

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
        userAdapter = UserAdapter(::onItemClicked,true,tokenManager.getUserId())
        binding.friendrcview.layoutManager = LinearLayoutManager(requireContext())
        binding.friendrcview.adapter = userAdapter
        allUser= ArrayList()
        userViewModel.getAllUsers()
        observers()
    }
    private fun observers(){
        userViewModel._getAllUser.observe(viewLifecycleOwner)  { i->


            binding.progressBar.isVisible = false
            when (i) {

                is NetworkResult.Success -> {
                    userAdapter.submitList(i.data!!.data)
                }
                is NetworkResult.Loading -> {
                    binding.progressBar.isVisible = true
                }

                is NetworkResult.Error -> TODO()
            }
        }
    }
    private fun onItemClicked(user: DataItem){
                userViewModel.sendFriendRequest(user.userId!!)
                observers1()
    }

    private fun observers1() {
        userViewModel._sendfriendRequest.observe(viewLifecycleOwner)  { i->
            binding.progressBar.isVisible = false
            when (i) {
                is NetworkResult.Success -> {
                    Log.i("x1",i.data!!.message.toString())
                     Toast.makeText(requireContext(), i.data?.message,Toast.LENGTH_SHORT).show()
                }
                is NetworkResult.Loading -> {
                    Log.i("x2",i.data?.message.toString())
                    binding.progressBar.isVisible = true
                }

                is NetworkResult.Error -> {
                    Log.i("x3",i.message.toString())
                    Toast.makeText(requireContext(), i.message,Toast.LENGTH_SHORT).show()
                }
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null

    }

}