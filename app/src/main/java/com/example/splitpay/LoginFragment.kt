package com.example.splitpay

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.splitpay.databinding.FragmentLoginBinding
import com.example.splitpay.databinding.FragmentRegisterBinding
import com.example.splitpay.models.UserSigninRequest
import com.example.splitpay.models.UserSignupRequest
import com.example.splitpay.utils.NetworkResult
import com.example.splitpay.utils.TokenManager
import com.example.splitpay.viewmodel.AuthViewModel

class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding?=null
    private val binding get() = _binding!!
    private lateinit var  authViewModel: AuthViewModel
    private lateinit var tokenManager: TokenManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding= FragmentLoginBinding.inflate(inflater,container,false)
        tokenManager=TokenManager(requireContext())
        return  binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        authViewModel= ViewModelProvider(requireActivity()).get(AuthViewModel::class.java)
        observers()
        binding.btnSignUp.setOnClickListener {
            findNavController().popBackStack()

        }
        binding.btnLogin.setOnClickListener {
            val validationResult= validateUserInput()
            if(validationResult.first){
                authViewModel.loginUser(getUserSigninRequest())
            }
            else{
                 binding.txtError.text=validationResult.second
            }
        }
    }
    private fun observers(){
        authViewModel._userSigninResponseLiveData.observe(viewLifecycleOwner, Observer {
            binding.progressBar.isVisible=false
            when(it){
                is NetworkResult.Success->{
                    tokenManager.saveToken(it.data?.result!!)
                    findNavController().navigate(R.id.action_loginFragment_to_mainFragment)
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
        val password=binding.txtPassword.text.toString()
        return authViewModel.validateCreds("",email,password,true)
    }
    private fun getUserSigninRequest(): UserSigninRequest {
        val email=binding.txtEmail.text.toString()
        val password=binding.txtPassword.text.toString()
        return UserSigninRequest(email,password)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null

    }

}