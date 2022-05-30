package com.example.simplegetrequest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.simplegetrequest.databinding.ActivityMainBinding
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    private lateinit var queue: RequestQueue
    lateinit var editText: EditText
    lateinit var button: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        queue = Volley.newRequestQueue(this)
        editText = findViewById(R.id.etPokemonAmount)
        button = findViewById(R.id.btnUpdatePokemon)
        button.setOnClickListener{
            val convertToInt = Integer.parseInt(editText.text.toString())
            getPokemonList(convertToInt)
        }
    }

    fun getPokemonList(listAmount: Int){
        val url = "https://pokeapi.co/api/v2/pokemon/?limit=${listAmount}"
        val jsonRequest = JsonObjectRequest(url, Response.Listener<JSONObject>{ response ->
            Log.i("JSONRESPONSE", response.getJSONArray("results").toString())
        },
        Response.ErrorListener { error ->
            Log.w("JSONRESPONSE", error.message as String)
        })

        queue.add(jsonRequest)
    }

    override fun onStop() {
        super.onStop()
        queue.cancelAll("Stopped")
    }
}