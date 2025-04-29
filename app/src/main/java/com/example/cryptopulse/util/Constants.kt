package com.example.cryptopulse.util

import android.util.Log
import kotlinx.coroutines.CoroutineExceptionHandler

object Constants {
    const val BASE_URL = "https://api.coingecko.com/api/v3/"
    const val API_KEY = "X-Api-Key: CG-hUDsR5iCET4Cg41CC4h28eDr"
}

val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
    Log.e("CoroutineException", "Error: ${throwable.message}", throwable)
}