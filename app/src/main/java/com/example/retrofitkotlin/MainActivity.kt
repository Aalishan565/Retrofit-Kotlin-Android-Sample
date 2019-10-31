package com.example.retrofitkotlin

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {
    private var listData: ArrayList<Post> = ArrayList()

    companion object {
        val Tag: String = "MainActivity"
    }

    private var communicationManager: CommunicationManager? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView.layoutManager = LinearLayoutManager(this)

        communicationManager = CommunicationManager().getInstance()
        val call = communicationManager?.getPostListReq()
        call?.enqueue(object : Callback<List<Post>> {
            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>?) {
                listData = response?.body() as ArrayList<Post>
                recyclerView.adapter = PostAdapter(listData, this@MainActivity)
                Log.d(Tag, response?.body().toString())
                Toast.makeText(
                    this@MainActivity,
                    "" + response?.body().toString(),
                    Toast.LENGTH_LONG
                ).show()
            }

            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                Toast.makeText(this@MainActivity, "" + t, Toast.LENGTH_LONG).show()
            }
        })
    }
}
