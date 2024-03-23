package com.example.splitpay

import android.annotation.SuppressLint
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.splitpay.api.RetrofitHelper2
import com.example.splitpay.api.RetrofitHelper2.api2

import com.example.splitpay.databinding.FragmentLoginBinding
import com.example.splitpay.databinding.FragmentMainBinding
import com.example.splitpay.models.UserResponse
import com.example.splitpay.models.UserSigninResponse
import com.example.splitpay.utils.Constants
import com.example.splitpay.utils.Constants.TAG
import com.example.splitpay.utils.Constants.USERID
import com.example.splitpay.utils.Constants.authToken
import com.example.splitpay.utils.TokenManager
import com.example.splitpay.viewmodel.AuthViewModel
import com.example.splitpay.viewmodel.UserViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

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

                         }
                         3->{

                         }
                         4->{
                             findNavController().navigate(R.id.action_mainFragment_to_profileFragment)
                         }
                     }
            }

        }

        userViewModel.getAllUsers()
        userViewModel._getAllUser.observe(viewLifecycleOwner) { it ->
//            Log.i(TAG, it.data.toString())
            users.clear()
                if(it.data!=null) {
                    users.addAll(it.data!!)
                    users.forEach {
                        if (it.userId==tokenManager.getUserId()) {


                            binding.totalAmountTv.text = "₹ ${it.totalAmount}"
                            binding.totalOweTv.text = "₹ ${it.totalOwe}"
                            binding.totalOwedTv.text = "₹ ${it.totalOwed}"
                            binding.usernameTv.text = "Welcome back,${it.name}"
                        }
                    }
                }

        }
//            binding.friendsBtn.setOnClickListener {
//                findNavController().navigate(R.id.action_mainFragment_to_friendsFragment)
//            }
//            binding.groupsBtn.setOnClickListener {
//                findNavController().navigate(R.id.action_mainFragment_to_groupFragment)
//            }
//            binding.profileBtn.setOnClickListener {
//                findNavController().navigate(R.id.action_mainFragment_to_profileFragment)
//            }


    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null

    }




}