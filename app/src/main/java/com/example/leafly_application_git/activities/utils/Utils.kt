package com.example.leafly_application_git.activities.utils

import android.content.Context
import java.io.IOException
import java.io.InputStream
import java.nio.charset.Charset


public class Utils {

    val UTF_8: Charset = charset("UTF-8")

    fun getJsonFromAssets(
        context: Context,
        fileName: String?
    ): String? {
        val jsonString: String
        jsonString = try {
            val `is`: InputStream = context.assets.open(fileName!!)
            val size: Int = `is`.available()
            val buffer = ByteArray(size)
            `is`.read(buffer)
            `is`.close()
            String(buffer, UTF_8)
        } catch (e: IOException) {
            e.printStackTrace()
            return null
        }
        return jsonString
    }
}