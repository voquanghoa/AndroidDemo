package com.quanghoa.appdemo.retrofit.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.quanghoa.appdemo.R
import com.quanghoa.appdemo.glideapp.GlideApp
import com.quanghoa.appdemo.retrofit.model.User
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.user_layout.*

class UserAdapter(var users: List<User>): RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.user_layout, parent, false))
    }

    override fun getItemCount(): Int {
        return users.size
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.user = users[position]
    }

    class UserViewHolder(view: View): RecyclerView.ViewHolder(view), LayoutContainer{

        override val containerView: View?
            get() = itemView

        var user: User? = null
            get() = field
            @SuppressLint("SetTextI18n")
            set(value){
                field = value
                value?.let {
                    txt_email.text = value.email
                    txt_fullname.text = "${value.firstName} ${value.lastName}"
                    GlideApp.with(itemView)
                        .load(value.avatar)
                        .into(img_avatar)
                }
            }
    }

}