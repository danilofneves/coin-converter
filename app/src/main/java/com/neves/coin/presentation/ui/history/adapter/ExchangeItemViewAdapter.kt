package com.neves.coin.presentation.ui.history.adapter

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.neves.coin.databinding.ItemHistoryBinding
import com.neves.coin.presentation.extensions.formatCurrency
import com.neves.coin.presentation.model.Coin
import com.neves.coin.presentation.ui.history.state.ExchangeItemUiState

class ExchangeItemViewAdapter:
    ListAdapter<ExchangeItemUiState, ExchangeItemViewAdapter.ViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemHistoryBinding.inflate(inflater, parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(
        private val binding: ItemHistoryBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: ExchangeItemUiState) {
            binding.tvName.text = item.name
            val coin = Coin.getByName(item.codein)
            binding.tvValue.text = item.bid.formatCurrency(coin.locale)
        }
    }

}

class DiffCallback : DiffUtil.ItemCallback<ExchangeItemUiState>() {
    override fun areItemsTheSame(oldItem: ExchangeItemUiState, newItem: ExchangeItemUiState) = oldItem == newItem
    override fun areContentsTheSame(oldItem: ExchangeItemUiState, newItem: ExchangeItemUiState) =
        oldItem.id == newItem.id
}