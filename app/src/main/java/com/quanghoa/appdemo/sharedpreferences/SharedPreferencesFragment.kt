package com.quanghoa.appdemo.sharedpreferences

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.quanghoa.appdemo.R
import kotlinx.android.synthetic.main.shared_preferences_layout.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.support.v4.toast

private const val SHARED_PREFERENCES_NAME = "SharedPreferencesFragment"

class SharedPreferencesFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return layoutInflater.inflate(R.layout.shared_preferences_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sharedPreferences = context!!.getSharedPreferences(
            SHARED_PREFERENCES_NAME,
            Context.MODE_PRIVATE)

        displayPreferences(sharedPreferences)

        bt_clear.onClick {
            sharedPreferences
                .edit()
                .clear()
                .apply()
        }

        bt_save.onClick {

            sharedPreferences
                .edit()
                .putString("Username", "quanghoa")
                .putString("Password", "1234")
                .putInt("Age", 31)
                .putBoolean("Agreed", true)
                .apply()

            toast("Saved!!")
        }

    }

    private fun displayPreferences(sharedPreferences: SharedPreferences){
        txt_username.setText(sharedPreferences.getString("Username", ""))
        txt_password.setText(sharedPreferences.getString("Password", ""))
        txt_age.setText(sharedPreferences.getInt("Age", 0).toString())
        cb_agree.isChecked = sharedPreferences.getBoolean("Agreed", false)
    }
}