package com.example.foodpick.common.utils

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.widget.Toast

object StoreIntentUtils {

    /**
     * 통화 화면으로 전환
     */
    fun dialPhone(context: Context, phoneNumber: String) {
        val cleanNumber = phoneNumber.filter { it.isDigit() }
        if (cleanNumber.isBlank()) {
            Toast.makeText(context, "유효한 전화번호가 아닙니다", Toast.LENGTH_SHORT).show()
            return
        }
        val telUri = Uri.parse("tel:$cleanNumber")
        val intent = Intent(Intent.ACTION_DIAL, telUri)
        tryStartActivity(context, intent, errorMessage = "전화 걸기 기능을 사용할 수 없습니다", logTag = "DialPhone")
    }

    /**
     * 외부 지도 앱 or 웹으로 전환
     */
    fun openMap(context: Context, address: String) {
        val encodedAddress = Uri.encode(address)

        // 지도 앱이 있으면 바로 실행
        val mapUri = Uri.parse("geo:0,0?q=$encodedAddress")
        val mapIntent = Intent(Intent.ACTION_VIEW, mapUri)
        if (tryStartActivity(context, mapIntent, errorMessage = "지도 앱을 찾을 수 없습니다", logTag = "OpenMap"))
            return

        // 지도 앱이 없으면 웹 실행
        val webUri = Uri.parse("https://map.naver.com/v5/search/$encodedAddress")
        val webIntent = Intent(Intent.ACTION_VIEW, webUri)
        tryStartActivity(context, webIntent, errorMessage = "지도 앱/브라우저를 찾을 수 없습니다", logTag = "OpenMap")
    }

    /**
     * 링크 사이트로 전환
     */
    fun openLink(context: Context, link: String) {
        val uri = Uri.parse(link)
        val intent = Intent(Intent.ACTION_VIEW, uri)
        tryStartActivity(context, intent, errorMessage = "링크를 열 수 없습니다", logTag = "OpenLink")
    }

    /**
     * 앱 설치 여부 확인 후 인텐트 실행, 실패 시 메시지와 로그
     */
    @SuppressLint("QueryPermissionsNeeded")
    private fun tryStartActivity(context: Context, intent: Intent, errorMessage: String, logTag: String = "StoreIntentUtils"): Boolean {
        return try {
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            if (intent.resolveActivity(context.packageManager) != null) {
                context.startActivity(intent)
                true
            } else {
                Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
                Log.e(logTag, errorMessage)
                false
            }
        } catch (e: Exception) {
            Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
            Log.e(logTag, "${errorMessage}: ${e.message}")
            false
        }
    }

}
