package com.example.splitpay

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.splitpay.adapter.GroupAdapter
import com.example.splitpay.databinding.FragmentGroupBinding
import com.example.splitpay.models.GroupResponse
import com.example.splitpay.utils.Constants.TAG
import com.example.splitpay.utils.TokenManager
import com.example.splitpay.viewmodel.UserViewModel
import com.google.gson.Gson


class GroupFragment : Fragment() {
    private var _binding: FragmentGroupBinding?=null
    private val binding get() = _binding!!
    private lateinit var tokenManager: TokenManager
    private lateinit var  userViewModel: UserViewModel
    private lateinit var adapter: GroupAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding= FragmentGroupBinding.inflate(inflater,container,false)
        tokenManager=TokenManager(requireContext())
        userViewModel= ViewModelProvider(requireActivity()).get(UserViewModel::class.java)
        adapter= GroupAdapter(::onItemClicked)
        return  binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.groupList.layoutManager=LinearLayoutManager(requireContext())
        binding.groupList.adapter=adapter
        userViewModel.getGroups()
        userViewModel._getAllUserGroup.observe(viewLifecycleOwner) {
             Log.i(TAG,it.data.toString())
             adapter.submitList(it.data)
        }
    }


    private fun onItemClicked(groupResponse: GroupResponse){
//        Toast.makeText(requireContext(),"hello world",Toast.LENGTH_SHORT).show()
          val bundle=Bundle()
          bundle.putInt("groupID",groupResponse.id!!.toInt())
          findNavController().navigate(R.id.action_groupFragment_to_expenseFragment,bundle)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null

    }
}