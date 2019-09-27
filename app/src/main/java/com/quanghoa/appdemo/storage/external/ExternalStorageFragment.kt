package com.quanghoa.appdemo.storage.external

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.quanghoa.appdemo.R
import kotlinx.android.synthetic.main.external_storage_layout.*
import kotlinx.android.synthetic.main.external_storage_layout.bt_save
import kotlinx.android.synthetic.main.shared_preferences_layout.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.support.v4.toast
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream

const val REQUEST_WRITE_FILE = 0xFF0
const val REQUEST_READ_FILE = 0xFF1

class ExternalStorageFragment : Fragment(){

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.external_storage_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bt_save.onClick {
            val writePermission = Manifest.permission.WRITE_EXTERNAL_STORAGE
            val granted = PackageManager.PERMISSION_GRANTED
            if(ContextCompat.checkSelfPermission(context!!, writePermission) != granted){
                requestPermissions(arrayOf(writePermission), REQUEST_WRITE_FILE)
            }else{
                saveFile()
            }
        }

        bt_open.onClick {
            val readPermission = Manifest.permission.READ_EXTERNAL_STORAGE
            val granted = PackageManager.PERMISSION_GRANTED
            if(ContextCompat.checkSelfPermission(context!!, readPermission) != granted){
                requestPermissions(arrayOf(readPermission), REQUEST_READ_FILE)
            }else{
                readFile()
            }
        }

        bt_clear.onClick {
            txt_note.setText("")
        }
    }

    private fun saveFile(){
        val file = File(context!!.getExternalFilesDir(null), "note.txt")
        file.createNewFile()

        FileOutputStream(file).use {
            it.write(txt_note.text.toString().toByteArray())
        }

        toast("Saved")
    }

    private fun readFile(){
        val file = File(context!!.getExternalFilesDir(null), "note.txt")

        if(file.exists()){
            FileInputStream(file).use {
                txt_note.setText(String(it.readBytes()))
            }
        }
        else{
            toast("File not found")
        }

    }
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if(grantResults.contains(PackageManager.PERMISSION_GRANTED)){
            if(requestCode == REQUEST_WRITE_FILE){
                saveFile()
            }
            if(requestCode == REQUEST_READ_FILE){
                readFile()
            }
        }
    }
}