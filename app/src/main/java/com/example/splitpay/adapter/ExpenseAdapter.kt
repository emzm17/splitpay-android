import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.splitpay.databinding.ExpenseItemBinding
import com.example.splitpay.models._DataItem


class ExpenseAdapter(
    private val onItemClick: (_DataItem) -> Unit
) :
    ListAdapter<_DataItem,ExpenseAdapter.ExpenseViewHolder>(ComparatorDiffUtil()){
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ExpenseAdapter.ExpenseViewHolder {
        val binding= ExpenseItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ExpenseViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ExpenseAdapter.ExpenseViewHolder, position: Int) {
        val item=getItem(position)
        item.let {
             holder.bind(it)
        }
    }


    inner class ExpenseViewHolder(private val binding: ExpenseItemBinding): RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(expense:_DataItem){
              binding.descriptionTv.text=expense.description
              binding.amountTv.text="₹ ${expense.amount}"
              binding.createdTv.text="Paid by:-${expense.payer?.get(0)!!.name}"
              binding.root.setOnClickListener {
                onItemClick(expense)
            }
        }

    }



}

class ComparatorDiffUtil: DiffUtil.ItemCallback<_DataItem>() {

    override fun areItemsTheSame(oldItem: _DataItem, newItem: _DataItem): Boolean {
        return oldItem.expenseId == newItem.expenseId
    }

    override fun areContentsTheSame(oldItem: _DataItem, newItem: _DataItem): Boolean {
        return oldItem == newItem
    }
}