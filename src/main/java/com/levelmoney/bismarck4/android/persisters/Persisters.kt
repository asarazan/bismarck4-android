package com.levelmoney.bismarck4.android.persisters

import android.accounts.AccountManager
import android.content.Context
import com.levelmoney.bismarck4.Persister
import com.levelmoney.bismarck4.Serializer

/**
 * Created by Aaron Sarazan on 12/28/15.
 * Copyright(c) 2015 Level, Inc.
 */

object Persisters {
    public fun <T: Any> account(c: Context, key: String, accountType: String, serializer: Serializer<T>): Persister<T> {
        return object: AndroidFilePersister<T>(c, serializer) {
            override fun path(): String? {
                val account = AccountManager.get(c).getAccountsByType(accountType).firstOrNull() ?: return null
                return java.net.URLEncoder.encode(account.name, kotlin.Charsets.UTF_8.name()) + "/$key"
            }
        }
    }
}