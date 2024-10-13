package com.nelalexxx.getfrominternet

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import org.jsoup.Jsoup
import java.io.IOException

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    // Для html-страницы
        GlobalScope.launch(Dispatchers.IO){
            val text = withContext(Dispatchers.IO) {
                try {
                    val client = OkHttpClient()
                    val request = Request.Builder()
                        .url("https://pw.mail.ru")
                        .build()
                    val response = client.newCall(request).execute()
                    val html = response.body?.string() ?: ""
                    Jsoup.parse(html).text() // Извлекаем только текст с помощью Jsoup
                } catch (e: IOException) {
                    "" // Возвращаем пустую строку в случае ошибки
                }
            }
            println(text)
        }

//        Но при работе с Retrofit мы не исопльзуем OkHttp и Gson напрямую
//        val gson = GsonBuilder().create()
//        val retrofit = Retrofit.Builder()
//            .baseUrl("https://example.com/") // Базовый URL вашего API
//            .addConverterFactory(GsonConverterFactory.create(gson))
//            .build()


    }
}

// Для простого Json Обьекта
data class MyData(val name: String, val age: Int)
fun parseSimpleJson() {
    val jsonString = """
        {
            "name": "John Doe",
            "age": 30
        }"""
    val gson = Gson()
    val myData = gson.fromJson(jsonString, MyData::class.java)

    println(myData.name) // Выведет "John Doe"
    println(myData.age) // Выведет 30
}