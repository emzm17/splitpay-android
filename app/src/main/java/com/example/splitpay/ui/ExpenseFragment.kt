package com.example.splitpay.ui

import ExpenseAdapter
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.splitpay.R
import com.example.splitpay.databinding.FragmentExpenseBinding
import com.example.splitpay.models.ExpenseResponse
import com.example.splitpay.utils.Constants.userNamemap
import com.example.splitpay.utils.TokenManager
import com.example.splitpay.viewmodel.UserViewModel


class ExpenseFragment : Fragment() {
    private var _binding: FragmentExpenseBinding?=null
    private lateinit var  userName:MutableMap<Int,String>
    private val binding get() = _binding!!
    private lateinit var tokenManager: TokenManager
    private lateinit var  userViewModel: UserViewModel
    private var expense:ExpenseResponse?=null
    private var groupId:Int = 0
    private lateinit var adapter: ExpenseAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding= FragmentExpenseBinding.inflate(inflater,container,false)
        tokenManager=TokenManager(requireContext())
        userViewModel= ViewModelProvider(requireActivity()).get(UserViewModel::class.java)

        userName=mutableMapOf()
        return  binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setInitialData()
        adapter=ExpenseAdapter(::onItemClicked)
        userViewModel.getAllUsers()
        userViewModel._getAllUser.observe(viewLifecycleOwner){
            it.data?.forEach {
                userName.put(it.userId!!,it.name!!)
            }
            userNamemap=userName

        }

        binding.expenseList.layoutManager= LinearLayoutManager(requireContext())
        binding.expenseList.adapter=adapter
        binding.addExpense.setOnClickListener {
             val id=arguments?.getInt("groupID")
             val bundle=Bundle()
             bundle.putInt("groupID",id!!);
             findNavController().navigate(R.id.action_expenseFragment_to_createExpenseFragment,bundle)
        }


        userViewModel.getExpenses(groupId)
        userViewModel._getAllExpense.observe(viewLifecycleOwner){
              adapter.submitList(it.data)

        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null

    }

    private fun setInitialData() {
        val id=arguments?.getInt("groupID")
        groupId=id!!
    }
    private fun onItemClicked(expense:ExpenseResponse){
        val bundle=Bundle()
        bundle.putString("expenseName",expense.description)
        bundle.putString("expenseAmount",expense.amount)
        bundle.putString("expenseCreated",expense.created)
        bundle.putString("expenseGroup", expense.groupId.toString())
        findNavController().navigate(R.id.action_expenseFragment_to_detailExpenseFragment,bundle)
    }



}