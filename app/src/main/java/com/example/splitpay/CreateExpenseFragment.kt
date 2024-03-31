package com.example.splitpay

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.splitpay.databinding.FragmentCreateExpenseBinding
import com.example.splitpay.databinding.FragmentExpenseBinding
import com.example.splitpay.models.ExpenseRequest
import com.example.splitpay.utils.NetworkResult
import com.example.splitpay.utils.TokenManager
import com.example.splitpay.viewmodel.UserViewModel


class CreateExpenseFragment : Fragment() {
    private var _binding: FragmentCreateExpenseBinding?=null
    private val binding get() = _binding!!
    private lateinit var tokenManager: TokenManager
    private lateinit var  userViewModel: UserViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding= FragmentCreateExpenseBinding.inflate(inflater,container,false)
        tokenManager=TokenManager(requireContext())
        userViewModel= ViewModelProvider(requireActivity()).get(UserViewModel::class.java)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.submitBtn.setOnClickListener {
             setData()
             observers()
        }


    }

    private fun setData() {
        val id=arguments?.getInt("groupID")
        Log.i("groupID", id.toString())
        val amount=binding.txtAmount.text
        val desc=binding.txtDescr.text
        val expense=ExpenseRequest(amount.toString().toInt(),id,desc.toString(),tokenManager.getUserId())
        userViewModel.createExpense(expense)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null

    }
    private fun observers(){
        userViewModel._createExpenseLiveData.observe(viewLifecycleOwner, Observer {

            when(it){
                is NetworkResult.Success->{
                    Toast.makeText(requireContext(),"expense created successfully", Toast.LENGTH_SHORT).show()
                    findNavController().navigate(R.id.action_createExpenseFragment_to_groupFragment)
                }
                is NetworkResult.Error->{
                    Toast.makeText(requireContext(),"something went wrong", Toast.LENGTH_SHORT).show()
                }

                is NetworkResult.Loading ->{

                }
            }
        })
    }


}