package com.example.madlevel3task2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.madlevel3task2.databinding.ItemPortalBinding
import kotlinx.android.synthetic.main.fragment_portals.view.*
import kotlinx.android.synthetic.main.item_portal.view.*

class PortalsAdapter(private val portals: List<Portal>    ,  private val onClickListener: (View, Portal) -> Unit) : RecyclerView.Adapter<PortalsAdapter.ViewHolder>(){


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val binding = ItemPortalBinding.bind(itemView)

        fun databind(portal: Portal) {
            itemView.tvTitle.text = portal.title
            itemView.tvUrl.text = portal.url
        }
    }

    /**
     * Creates and returns a ViewHolder object, inflating a standard layout called simple_list_item_1.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_portal, parent, false)
        )
    }

    /**
     * Returns the size of the list
     */
    override fun getItemCount(): Int {
        return portals.size
    }


    /**
     * Called by RecyclerView to display the data at the specified position.
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val portal = portals[position]
        holder.itemView.setOnClickListener { view ->
            onClickListener.invoke(view, portal)
        }
        holder.databind(portals[position])
    }



}