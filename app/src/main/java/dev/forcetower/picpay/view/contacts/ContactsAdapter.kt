package dev.forcetower.picpay.view.contacts

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import dev.forcetower.picpay.R
import dev.forcetower.picpay.core.bindings.inflate
import dev.forcetower.picpay.databinding.ItemContactBinding

class ContactsAdapter : ListAdapter<Int, ContactsAdapter.ContactHolder>(DiffCallback) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ContactsAdapter.ContactHolder {
        return ContactHolder(parent.inflate(R.layout.item_contact))
    }

    override fun onBindViewHolder(holder: ContactsAdapter.ContactHolder, position: Int) {

    }

    inner class ContactHolder(val binding: ItemContactBinding) : RecyclerView.ViewHolder(binding.root)

    private object DiffCallback : DiffUtil.ItemCallback<Int>() {
        override fun areItemsTheSame(oldItem: Int, newItem: Int): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Int, newItem: Int): Boolean {
            return oldItem == newItem
        }
    }

}