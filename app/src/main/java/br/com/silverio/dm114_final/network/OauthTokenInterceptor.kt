package br.com.silverio.dm114_final.network

import android.util.Log
import br.com.silverio.dm114_final.util.SharedPreferencesUtils
import okhttp3.Interceptor
import okhttp3.Response

private const val TAG = "OauthTokenInterceptor"

class OauthTokenInterceptor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        val accessToken = SharedPreferencesUtils.getAccessToken()
        if (accessToken != null) {
            Log.i(TAG, "Using the existing token")
            request = request.newBuilder()
                .addHeader("Authorization", "Bearer ${accessToken}")
                .build()
        }
        return chain.proceed(request)
    }
}