package com.example.splitpay.ui

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.splitpay.R

import com.example.splitpay.databinding.FragmentMainBinding
import com.example.splitpay.models.UserResponse
import com.example.splitpay.utils.Constants
import com.example.splitpay.utils.Constants.TAG
import com.example.splitpay.utils.NetworkResult
import com.example.splitpay.utils.TokenManager
import com.example.splitpay.viewmodel.UserViewModel
import java.text.DecimalFormat

class MainFragment : Fragment() {
    private var _binding: FragmentMainBinding?=null
    private val binding get() = _binding!!
    private lateinit var tokenManager: TokenManager
    private lateinit var  userViewModel: UserViewModel
    private lateinit var users:ArrayList<UserResponse>
    private var listfeature=arrayOf("Groups","Friends","Accept-request","Users","Profile","Logout")
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
                         5->{
                             val builder= AlertDialog.Builder(requireContext())
                             builder.setTitle("Logout")
                             builder.setMessage("Are are sure you want to logout?")
                             builder.setIcon(R.drawable.ic_warning)
                             builder.setPositiveButton("Yes" ){ dialog: DialogInterface, i: Int ->
                                 clearSession()
                                 findNavController().navigate(R.id.action_mainFragment_to_loginFragment)
                                 dialog.dismiss()
                             }
                             builder.setNegativeButton("No"){ dialog: DialogInterface, i:Int->
                                 dialog.dismiss()
                             }
                             val alertDialog=builder.create()
                             alertDialog.setCancelable(false)
                             alertDialog.show()
                         }
                     }
            }
        }
        userViewModel.getparticularUser(tokenManager.getUserId())


    }
    private fun clearSession() {
        tokenManager.saveToken("","",-1,"")
    }




    @SuppressLint("SetTextI18n")
    private fun observers(){
        val decimalFormat = DecimalFormat("#.00")
        userViewModel._getparticularUser.observe(viewLifecycleOwner)  { i->
            binding.progressBar.isVisible = false
            when (i) {
                is NetworkResult.Success -> {
                    binding.dashboard.isVisible=true
                    binding.usernameTv.isVisible=true
                    binding.totalAmountTv.text = "₹ ${decimalFormat.format(i.data?.data?.total_amount)}"
                    binding.totalOweTv.text = "₹ ${decimalFormat.format(i.data?.data?.total_owe)}"
                    binding.totalOwedTv.text = "₹ ${decimalFormat.format(i.data?.data?.total_owed)}"
                    binding.usernameTv.text = "Welcome back,${i.data?.data?.name}"
                }

                is NetworkResult.Error -> {
                    Toast.makeText(context, "Error: ${i.message.toString()}", Toast.LENGTH_SHORT).show()
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