package com.example.splitpay.ui

import ExpenseAdapter
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.splitpay.R
import com.example.splitpay.databinding.FragmentExpenseBinding
import com.example.splitpay.models._DataItem
import com.example.splitpay.utils.Constants.userMap
import com.example.splitpay.utils.NetworkResult
import com.example.splitpay.utils.TokenManager
import com.example.splitpay.viewmodel.UserViewModel


class ExpenseFragment : Fragment() {

    private val bottomFabAmin:Animation by lazy {
        AnimationUtils.loadAnimation(requireContext(),R.anim.from_bottom_fab)
    }
    private val tobottomFabAmin:Animation by lazy {
        AnimationUtils.loadAnimation(requireContext(),R.anim.to_bottom_fab)
    }
    private val rotateclockFabAmin:Animation by lazy {
        AnimationUtils.loadAnimation(requireContext(),R.anim.rotate_clock_wise)
    }
    private val rotateanticlockFabAmin:Animation by lazy {
        AnimationUtils.loadAnimation(requireContext(),R.anim.rotate_anti_clock_wise)
    }
    private var _binding: FragmentExpenseBinding?=null
    private lateinit var  userName:MutableMap<Int,String>
    private var isExpanded=false
    private val binding get() = _binding!!
    private lateinit var tokenManager: TokenManager
    private lateinit var  userViewModel: UserViewModel
    private var groupId:Int = 0
    private lateinit var adapter: ExpenseAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding= FragmentExpenseBinding.inflate(inflater,container,false)
        tokenManager=TokenManager(requireContext())
        userViewModel= ViewModelProvider(requireActivity()).get(UserViewModel::class.java)
        return  binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btn.setOnClickListener {

            if (isExpanded) {
                shrinkFab()
            } else {
                expandFab()
            }

        }
        binding.settlementBtn.setOnClickListener {
            val id = arguments?.getInt("groupID")
            val bundle = Bundle()
            bundle.putInt("groupID", id!!);
               findNavController().navigate(R.id.action_expenseFragment_to_settlementFragment,bundle)
        }
        binding.addExpense.setOnClickListener {
            val id = arguments?.getInt("groupID")
            val bundle = Bundle()
            bundle.putInt("groupID", id!!);
            findNavController().navigate(
                R.id.action_expenseFragment_to_createExpenseFragment,
                bundle
            )
        }
        userViewModel.getExpenses(setInitialData())
        expenseobserve()
        adapter = ExpenseAdapter(::onItemClicked)
        binding.expenseList.layoutManager = LinearLayoutManager(requireContext())
        binding.expenseList.adapter = adapter


    }

    private fun expenseobserve(){

       userViewModel._getAllExpense.observe(viewLifecycleOwner) { i->

           binding.progressBar.isVisible = false
           when (i) {
               is NetworkResult.Success -> {
                   adapter.submitList(i.data!!.data)


               }
               is NetworkResult.Loading -> {
                   binding.progressBar.isVisible = true

               }

               is NetworkResult.Error -> {

               }
           }
       }
   }

    private fun shrinkFab() {
        binding.btn.startAnimation(rotateanticlockFabAmin)
        binding.addExpense.startAnimation(tobottomFabAmin)
        binding.settlementBtn.startAnimation(tobottomFabAmin)
        binding.addExpenseTv.startAnimation(tobottomFabAmin)
        binding.settlementTv.startAnimation(tobottomFabAmin)
        isExpanded=!isExpanded
    }

    private fun expandFab() {
        binding.btn.startAnimation(rotateclockFabAmin)
        binding.addExpense.startAnimation(bottomFabAmin)
        binding.settlementBtn.startAnimation(bottomFabAmin)
        binding.addExpenseTv.startAnimation(bottomFabAmin)
        binding.settlementTv.startAnimation(bottomFabAmin)
        isExpanded=!isExpanded

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null

    }
    private fun setInitialData():Int {
        val id=arguments?.getInt("groupID")
        return id!!
    }
    private fun onItemClicked(expense:_DataItem){
        val bundle=Bundle()
        bundle.putString("expenseName",expense.description)
        bundle.putString("expenseAmount",expense.amount)
        bundle.putString("expenseCreated",expense.created)
        bundle.putString("expensePayer",expense.payer?.get(0)!!.name)
        bundle.putString("expenseGroup", expense.groupId.toString())
        findNavController().navigate(R.id.action_expenseFragment_to_detailExpenseFragment,bundle)
    }



}