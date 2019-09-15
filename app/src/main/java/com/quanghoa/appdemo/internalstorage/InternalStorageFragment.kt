package com.quanghoa.appdemo.internalstorage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.gson.Gson
import com.quanghoa.appdemo.R
import com.quanghoa.appdemo.internalstorage.models.MyContact
import kotlinx.android.synthetic.main.internal_storage_fragment_layout.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.support.v4.toast

class InternalStorageFragment : Fragment(){

    private val path = "contact.json"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.internal_storage_fragment_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadContact()

        bt_save.onClick {
            saveContact()
            toast("Saved!!")
        }

        bt_clear.onClick {
            InternalStorage.delete(context!!, path)
            display(null)
        }
    }

    private fun loadContact() {
        val json = InternalStorage.read(context!!, path)
        if (json.isNotEmpty()) {
            val myContact = Gson().fromJson(json, MyContact::class.java)

            display(myContact)
        }
    }

    private fun display(myContact: MyContact?){
        txt_first_name.setText(myContact?.firstName)
        txt_last_name.setText(myContact?.lastName)
        txt_phone.setText(myContact?.phoneNumber)
        txt_note.setText(myContact?.notes)
    }

    private fun saveContact() {

        val myContact = MyContact(
            txt_first_name.text.toString(),
            txt_last_name.text.toString(),
            txt_phone.text.toString(),
            txt_note.text.toString()
        )

        val json = Gson().toJson(myContact)
        InternalStorage.write(context!!, path, json)
    }
}