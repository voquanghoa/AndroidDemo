package com.quanghoa.appdemo.retrofit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.quanghoa.appdemo.R
import com.quanghoa.appdemo.retrofit.adapter.UserAdapter
import com.quanghoa.appdemo.retrofit.model.UserResponse
import com.quanghoa.appdemo.retrofit.service.UserService
import kotlinx.android.synthetic.main.retrofit_fragment_layout.*
import org.jetbrains.anko.support.v4.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.OkHttpClient



class RetrofitFragment : Fragment(), Callback<UserResponse> {

    override fun onFailure(call: Call<UserResponse>, t: Throwable) {
        toast(t.message!!)
    }

    override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
        if(response.isSuccessful){
            val adapter = recyclerView.adapter as UserAdapter
            adapter.users = response.body()!!.data
            adapter.notifyDataSetChanged()
        }else{
            toast(response.errorBody()!!.string())
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.retrofit_fragment_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView.adapter = UserAdapter(listOf())

        val interceptor = RetrofitInterceptor()

        val client = OkHttpClient
            .Builder()
            .addInterceptor(interceptor)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://reqres.in/api/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create<UserService>(UserService::class.java)
        service.list(10, 1).enqueue(this)
    }
}