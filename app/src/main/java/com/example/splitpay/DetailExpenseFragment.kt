package com.example.splitpay

import android.R
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.lifecycle.ViewModelProvider
import com.example.splitpay.databinding.FragmentDetailExpenseBinding
import com.example.splitpay.databinding.FragmentExpenseBinding
import com.example.splitpay.models.ExpenseResponse
import com.example.splitpay.utils.Constants
import com.example.splitpay.utils.TokenManager
import com.example.splitpay.viewmodel.UserViewModel
import com.google.gson.Gson


class DetailExpenseFragment : Fragment() {
    private var _binding: FragmentDetailExpenseBinding?=null
    private lateinit var expense:ExpenseResponse
    private val binding get() = _binding!!
    private lateinit var friendlist:ArrayList<String>
    private lateinit var  userViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding=FragmentDetailExpenseBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        friendlist= ArrayList()
        setData()
        val groupId=arguments?.getString("expenseGroup")
        userViewModel= ViewModelProvider(requireActivity()).get(UserViewModel::class.java)
        userViewModel._getAllGroup.observe(viewLifecycleOwner){
             it.data?.forEach {
                      if(it.id==groupId?.toInt()){
                             it.usersId?.forEach { i->
                                 friendlist.add(Constants.userNamemap[i]!!)
                             }

                      }
             }
        }

        val adapter= ArrayAdapter(requireContext(), R.layout.simple_list_item_1,friendlist)
        binding.expenselistview.adapter=adapter


    }

    @SuppressLint("SetTextI18n")
    private fun setData() {
        val name= arguments?.getString("expenseName")
        val amount = arguments?.getString("expenseAmount")
        val createdAt=arguments?.getString("expenseCreated")

        binding.nametv.text=name
        binding.amounttv.text=" ₹ ${amount}"
        binding.createdaTtv.text=createdAt
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null

    }
}