package com.quanghoa.appdemo.storage.internal

import android.content.Context
import java.io.FileNotFoundException

object InternalStorage{

    fun read(context: Context, path: String): String{
        try {
            context.openFileInput(path).use {
                return String(it.readBytes())
            }
        }catch (ex: FileNotFoundException){
            return ""
        }
    }

    fun write(context: Context, path: String, content: String){
        context.openFileOutput(path, Context.MODE_PRIVATE)
            .write(content.toByteArray())
    }

    fun delete(context: Context, path: String){
        context.deleteFile(path)
    }
}