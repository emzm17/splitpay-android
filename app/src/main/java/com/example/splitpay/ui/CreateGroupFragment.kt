package com.example.splitpay.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.splitpay.R
import com.example.splitpay.adapter.FriendAdapter
import com.example.splitpay.databinding.FragmentCreateGroupBinding
import com.example.splitpay.models.GroupRequest
import com.example.splitpay.models.UsersItem
import com.example.splitpay.utils.NetworkResult
import com.example.splitpay.utils.TokenManager
import com.example.splitpay.viewmodel.UserViewModel

class CreateGroupFragment : Fragment() {
    private var _binding: FragmentCreateGroupBinding?=null
    private val binding get() = _binding!!
    private lateinit var tokenManager: TokenManager
    private lateinit var  userViewModel: UserViewModel
    private lateinit var friendAdapter: FriendAdapter
    private lateinit var friendList:ArrayList<UsersItem>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding= FragmentCreateGroupBinding.inflate(inflater,container,false)
        tokenManager=TokenManager(requireContext())
        userViewModel= ViewModelProvider(requireActivity()).get(UserViewModel::class.java)
        return  binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        userViewModel.getFriends()
        friendList= ArrayList()
        friendAdapter= FriendAdapter(::onItemClick)
        binding.friendrcview.layoutManager= LinearLayoutManager(requireContext())
        binding.friendrcview.adapter=friendAdapter

        observers1()


        binding.submitBtn.setOnClickListener {
            val groupRequest=getGroupRequest()
            userViewModel.createGroup(groupRequest)
            observers()
        }


    }

    private fun observers1() {
        userViewModel._getUser.observe(viewLifecycleOwner) {
           when(it){
                  is NetworkResult.Success->{
                          friendAdapter.submitList(it.data!!.data)
                  }

               is NetworkResult.Error -> {
                    Toast.makeText(requireContext(),"${it.message}",Toast.LENGTH_SHORT).show()
               }
               is NetworkResult.Loading -> {

               }
           }


        }
    }

    private fun onItemClick(check:Boolean,user:UsersItem) {
           if(check){
               Toast.makeText(requireContext(),"${user.name} added to group", Toast.LENGTH_LONG).show()
               friendList.add(user)
           }
           else{
               Toast.makeText(requireContext(),"${user.name} remove to group", Toast.LENGTH_LONG).show()
               friendList.remove(user)
           }

    }

    private fun getGroupRequest():GroupRequest{
          val name=binding.txtName.text
          val users=friendList
        return GroupRequest(name.toString(),users)
    }
    private fun observers(){
        userViewModel._createGroupLiveData.observe(viewLifecycleOwner, Observer {

            when(it){
                is NetworkResult.Success->{
                     Toast.makeText(requireContext(),"group created successfully",Toast.LENGTH_SHORT).show()
                    findNavController().navigate(R.id.action_createGroupFragment_to_groupFragment)
                }
                is NetworkResult.Error->{
                    Toast.makeText(requireContext(),"something went wrong",Toast.LENGTH_SHORT).show()
                }

                is NetworkResult.Loading ->{

                }
            }
        })
    }

}