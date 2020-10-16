package com.lost.managers.features.managers

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lost.domain.models.Manager
import com.lost.managers.R
import kotlinx.android.synthetic.main.managers_item.view.*

/**
 * Recyclerview adapter to show list of managers
 */
class ManagersAdapter : RecyclerView.Adapter<ManagersAdapter.ViewHolder?>() {
    private var managers = arrayListOf<Manager>()

    fun setManagers(managers: List<Manager>) {
        this.managers.clear()
        this.managers.addAll(managers)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.managers_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        managers[position].let {
            holder.set(it)
        }
    }

    override fun getItemCount(): Int {
        return managers.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var manager: Manager? = null

        fun set(manager: Manager) {
            this.manager = manager
            itemView.managers_item_textview_name.text = manager.name
            itemView.managers_item_textview_email.text = manager.email
        }
    }
}