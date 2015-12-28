package com.levelmoney.bismarck4.android.persisters

import android.content.Context
import com.levelmoney.bismarck4.Serializer
import com.levelmoney.bismarck4.persisters.CachingPersister
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream

/**
 * Created by Aaron Sarazan on 9/7/15
 * Copyright(c) 2015 Level, Inc.
 */
public abstract class AndroidFilePersister<T: Any>(val context: Context, val serializer: Serializer<T>) : CachingPersister<T>() {

    protected abstract fun path(): String?

    override fun get(): T? {
        val path = path() ?: return null
        val cached = super.get()
        if (cached != null) return cached
        val file = File(context.filesDir, "/$path")
        if (!file.exists()) return null
        val loaded = FileInputStream(file).use { serializer.readObject(it) }
        super.put(loaded)
        return loaded
    }

    override fun put(data: T?) {
        val path = path() ?: return
        super.put(data)
        val file = File(context.filesDir, "/$path")
        if (data == null) {
            file.delete()
        } else {
            val parent = file.parentFile
            if (!parent.exists()) {
                parent.mkdirs()
            }
            if (!file.exists()) {
                file.createNewFile()
            }
            FileOutputStream(file).use { serializer.writeObject(it, data) }
        }
    }
}