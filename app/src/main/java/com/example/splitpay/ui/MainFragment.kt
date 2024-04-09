package com.example.splitpay.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.splitpay.R

import com.example.splitpay.databinding.FragmentMainBinding
import com.example.splitpay.models.UserResponse
import com.example.splitpay.utils.Constants.TAG
import com.example.splitpay.utils.NetworkResult
import com.example.splitpay.utils.TokenManager
import com.example.splitpay.viewmodel.UserViewModel

class MainFragment : Fragment() {
    private var _binding: FragmentMainBinding?=null
    private val binding get() = _binding!!
    private lateinit var tokenManager: TokenManager
    private lateinit var  userViewModel: UserViewModel
    private lateinit var users:ArrayList<UserResponse>
    private var listfeature=arrayOf("Groups","Friends","Accept-request","Users","Profile")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding= FragmentMainBinding.inflate(inflater,container,false)
        tokenManager=TokenManager(requireContext())
        userViewModel=ViewModelProvider(requireActivity()).get(UserViewModel::class.java)
        users= ArrayList()
        return  binding.root
    }



    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.i(TAG,"back")
        observers()
        val adapter=ArrayAdapter(requireContext(),android.R.layout.simple_list_item_1,listfeature)
        binding.listview.adapter=adapter
        binding.listview.onItemClickListener=object : AdapterView.OnItemClickListener{
            override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                     when(position){
                         0->{
                           findNavController().navigate(R.id.action_mainFragment_to_groupFragment)
                         }
                         1->{
                             findNavController().navigate(R.id.action_mainFragment_to_friendsFragment)
                         }
                         2->{
                             findNavController().navigate(R.id.action_mainFragment_to_requestFragment)
                         }
                         3->{
                           findNavController().navigate(R.id.action_mainFragment_to_usersFragment)
                         }
                         4->{
                             findNavController().navigate(R.id.action_mainFragment_to_profileFragment)
                         }
                     }
            }
        }
        userViewModel.getparticularUser(tokenManager.getUserId())

    }



    private fun observers(){
        userViewModel._getparticularUser.observe(viewLifecycleOwner)  { i->
            binding.progressBar.isVisible = false
            when (i) {
                is NetworkResult.Success -> {
                    binding.dashboard.isVisible=true
                    binding.usernameTv.isVisible=true
                    binding.totalAmountTv.text = "₹ ${i.data!!.totalAmount}"
                    binding.totalOweTv.text = "₹ ${i.data.totalOwe}"
                    binding.totalOwedTv.text = "₹ ${i.data.totalOwed}"
                    binding.usernameTv.text = "Welcome back,${i.data.name}"
                }

                is NetworkResult.Error -> {

                }

                is NetworkResult.Loading -> {
                    binding.progressBar.isVisible = true
                }
            }
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null

    }




}