package com.alpamedev.styleshop.framework

import org.json.JSONArray
import org.json.JSONException

fun formatImageUrl(url: String): String {
    return try {
        val jsonArray = JSONArray(url)
        if (jsonArray.length() > 0) {
            jsonArray.getString(0)
        } else {
            ""
        }
    } catch (e: JSONException) {
        url
    }
}