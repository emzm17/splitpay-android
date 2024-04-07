package com.example.splitpay.ui

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
import com.example.splitpay.models.ExpenseResponse
import com.example.splitpay.utils.Constants
import com.example.splitpay.viewmodel.UserViewModel
import java.text.SimpleDateFormat
import java.util.Locale


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
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
        val outputFormat = SimpleDateFormat("yyyy MMMM dd", Locale.getDefault())

        // Parse the input date string into a Date object
        val date = inputFormat.parse(createdAt)

        binding.nametv.text=name
        binding.amounttv.text=" ₹ ${amount}"
        binding.createdaTtv.text=outputFormat.format(date)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null

    }
}