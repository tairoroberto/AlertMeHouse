package br.com.alertmehouse.alertmehouse.view.home

import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Switch
import android.widget.TextView
import br.com.alertmehouse.alertmehouse.R
import br.com.alertmehouse.alertmehouse.model.AlarmDevice

class HomeRecyclerAdapter(private var list: MutableList<AlarmDevice>,
                          private val onClick: (position: Int) -> Unit) : RecyclerView.Adapter<HomeRecyclerAdapter.ViewHolder>() {

    private var lastPosition = -1

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.bind(item)
        holder.itemView.setOnClickListener({
            onClick(position)
        })
        setAnimation(holder.itemView, position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.item, parent, false))
    }

    private fun setAnimation(viewToAnimate: View, position: Int) {
        if (position > 0) {
            val animation = AnimationUtils.loadAnimation(viewToAnimate.context, android.R.anim.slide_in_left)
            viewToAnimate.startAnimation(animation)
            lastPosition = position
        }
    }

    override fun getItemCount(): Int = list.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val textViewNameDevice: TextView = view.findViewById(R.id.textViewNameDevice)
        private val switchDevice: TextView = view.findViewById(R.id.switchDevice)

        fun bind(alarmDevice: AlarmDevice) {
            textViewNameDevice.text = alarmDevice.name
            if (alarmDevice.status) {
                switchDevice.text = switchDevice.context.getString(R.string.enabled)
                switchDevice.setTextColor(ContextCompat.getColor(switchDevice.context, R.color.enable))
            } else {
                switchDevice.text = switchDevice.context.getString(R.string.disabled)
                switchDevice.setTextColor(ContextCompat.getColor(switchDevice.context, R.color.primary))
            }
        }
    }

    fun update(items: List<AlarmDevice>?) {
        this.list.clear()
        if (items != null) {
            this.list.addAll(items)
        }
        notifyDataSetChanged()
    }
}