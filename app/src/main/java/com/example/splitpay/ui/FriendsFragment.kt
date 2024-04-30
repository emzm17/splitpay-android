package com.example.splitpay.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.splitpay.adapter.FriendsAdapter
import com.example.splitpay.databinding.FragmentFriendsBinding
import com.example.splitpay.models.User
import com.example.splitpay.utils.NetworkResult
import com.example.splitpay.utils.TokenManager
import com.example.splitpay.viewmodel.UserViewModel


class FriendsFragment : Fragment() {

    private var _binding: FragmentFriendsBinding?=null
    private lateinit var friendsAdapter: FriendsAdapter
    private val binding get() = _binding!!
    private lateinit var tokenManager: TokenManager
    private lateinit var  userViewModel: UserViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding= FragmentFriendsBinding.inflate(inflater,container,false)
        tokenManager=TokenManager(requireContext())
        userViewModel= ViewModelProvider(requireActivity()).get(UserViewModel::class.java)
        return  binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        friendsAdapter= FriendsAdapter()
        binding.friendrcview.layoutManager = LinearLayoutManager(requireContext())
        binding.friendrcview.adapter = friendsAdapter
        userViewModel.getFriends()
        observers()
    }
    private fun observers(){
        userViewModel._getUser.observe(viewLifecycleOwner)  { i->
            binding.progressBar.isVisible = false
            when (i) {
                is NetworkResult.Success -> {
                   friendsAdapter.submitList(i.data!!.data)
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