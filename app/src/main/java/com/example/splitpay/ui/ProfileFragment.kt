package com.example.splitpay.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.splitpay.R
import com.example.splitpay.databinding.FragmentProfileBinding
import com.example.splitpay.models.UserSignupRequest
import com.example.splitpay.utils.NetworkResult
import com.example.splitpay.utils.TokenManager
import com.example.splitpay.viewmodel.UserViewModel


class ProfileFragment : Fragment() {
    private var _binding:FragmentProfileBinding?=null
    private val binding get() = _binding!!
    private lateinit var tokenManager: TokenManager
    private lateinit var  userViewModel: UserViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileBinding.inflate(inflater,container,false)
        tokenManager = TokenManager(requireContext())
        userViewModel = ViewModelProvider(requireActivity()).get(UserViewModel::class.java)
        return  binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.submitBtn.setOnClickListener {
             val validation=validateInput()
             if(validation.first){
                 userViewModel.updateInfo(getUsersignup())
//                 Toast.makeText(requireContext(),"user update",Toast.LENGTH_SHORT).show()

             }
            else{
                binding.txtError.text=validation.second
             }

        }

        observers()
    }
    private fun observers() {
        userViewModel._updateInfo.observe(viewLifecycleOwner) { i ->
            when (i) {
                is NetworkResult.Success -> {
                    Toast.makeText(requireContext(), "user info update", Toast.LENGTH_SHORT).show()
                    findNavController().navigate(R.id.action_profileFragment_to_mainFragment)

                }

                is NetworkResult.Error -> {
                    binding.txtError.text = i.message
                }

                is NetworkResult.Loading -> {

                }
            }
        }
    }




    private fun validateInput():Pair<Boolean,String> {
        val name=binding.txtName.text.toString()
        val email=binding.txtEmail.text.toString()
        val password=binding.txtPassword.text.toString()
       return  userViewModel.validateInput(name,email,password)
    }

    private fun getUsersignup(): UserSignupRequest {
        val name=binding.txtName.text.toString()
        val email=binding.txtEmail.text.toString()
        val password=binding.txtPassword.text.toString()
        return UserSignupRequest(email,name,password)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }



}