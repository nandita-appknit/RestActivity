package com.example.restactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.koushikdutta.ion.Ion
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun chucknorrisClick(view: View) {
        Ion.with(this).load("https://api.icndb.com/jokes/random").asString()
            .setCallback { _, result ->

                Log.d("n", "The Json Data is:\n$result")
                processChuckData(result)
            }

    }

    private fun processChuckData(result: String) {
        val json = JSONObject(result)
        val obj: JSONObject = json.getJSONObject("value")
        val joke: String = obj.getString("joke")
        output.text = joke

    }

    fun catClick(view: View) {
        Ion.with(this).load("https://thecatapi.com/api/images/get?format=json&size=med&results_per_page=3")
            .asString()
            .setCallback { _, result ->

                Log.d("n", "The Json Data is:\n$result")
                processCatData(result)

            }
    }
    private fun processCatData(result: String){
        val url:String=
            JSONObject("{\"images\":$result}")
                .getJSONArray("images").getJSONObject(0)
                .getString("url")

        Picasso.get()
            .load(url).into(image_output)

    }
}