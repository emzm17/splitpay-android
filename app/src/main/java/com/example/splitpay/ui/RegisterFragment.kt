package com.example.splitpay.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.splitpay.R
import com.example.splitpay.databinding.FragmentRegisterBinding
import com.example.splitpay.models.UserSignupRequest
import com.example.splitpay.utils.Constants.authToken
import com.example.splitpay.utils.NetworkResult
import com.example.splitpay.utils.TokenManager
import com.example.splitpay.viewmodel.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class RegisterFragment : Fragment() {
    @Inject

    private var _binding:FragmentRegisterBinding?=null
    private val binding get() = _binding!!
    private lateinit var  authViewModel:AuthViewModel
    private lateinit var tokenManager: TokenManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding= FragmentRegisterBinding.inflate(inflater,container,false)
        tokenManager=TokenManager(requireContext())
        authToken=tokenManager.getToken().toString()
      return  binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(tokenManager.getToken()!=null && tokenManager.getToken()!=""){
            findNavController().navigate(R.id.action_registerFragment_to_mainFragment)

        }
        authViewModel=ViewModelProvider(requireActivity()).get(AuthViewModel::class.java)
        binding.btnLogin.setOnClickListener {
             findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
        }
        binding.btnSignUp.setOnClickListener {
            val validationResult= validateUserInput()
            if(validationResult.first){
                  authViewModel.registerUser(getUserSignupRequest())
            }
            else{
                 binding.txtError.text=validationResult.second
            }
        }
       observers()
    }

    private fun observers(){
        authViewModel._userSignupResponseLiveData.observe(viewLifecycleOwner, Observer {
            binding.progressBar.isVisible=false
            when(it){
                is NetworkResult.Success->{
                    findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
                }
                is NetworkResult.Error->{
                    binding.txtError.text=it.message
                }
                is NetworkResult.Loading->{
                    binding.progressBar.isVisible=true
                }
            }
        })
    }
    private fun validateUserInput(): Pair<Boolean, String> {
         val email=binding.txtEmail.text.toString()
         val name=binding.txtUsername.text.toString()
         val password=binding.txtPassword.text.toString()
         return authViewModel.validateCreds(name,email,password,false)
    }
    private fun getUserSignupRequest():UserSignupRequest{
        val email=binding.txtEmail.text.toString()
        val name=binding.txtUsername.text.toString()
        val password=binding.txtPassword.text.toString()
        return UserSignupRequest(email,name,password)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null

    }

}