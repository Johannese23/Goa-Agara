package com.example.goaagara.ui.notifications

import android.app.ActionBar
import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.goaagara.MainActivity
import com.example.goaagara.R
import com.example.goaagara.adapter.NewsAdapter
import com.example.goaagara.model.News
import com.example.goaagara.model.ResponseServer
import com.example.goaagara.network.ConfigNetwork
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_notifications.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class NotificationsFragment : Fragment() {

    private lateinit var notificationsViewModel: NotificationsViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        notificationsViewModel =
                ViewModelProvider(this).get(NotificationsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_notifications, container, false)
//        val textView: TextView = root.findViewById(R.id.text_notifications)
        notificationsViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it
        })

        if (isConnect()){
                ConfigNetwork.getRetrofit().getDataNewsApple().enqueue(object : Callback<ResponseServer>{
                    override fun onFailure(call: Call<ResponseServer>, t: Throwable) {
                        t.message?.let { Log.d("error Server", it) }
                    }

                    override fun onResponse(
                        call: Call<ResponseServer>,
                        response: Response<ResponseServer>
                    ) {
                        Log.d("response server", response.message())
                        if (response.isSuccessful){
                            val status = response.body()?.status
                            if (status.equals("ok")){
                                val data = response.body()?.articles
                                showData(data)
                            }
                        }
                    }

                })
        }else{
            Snackbar.make(root, "device tidak connect dengan intenet", Snackbar.LENGTH_LONG).show()
        }

//        searchNotif.layoutParams = ActionBar.LayoutParams(Gravity.RIGHT)
        return root
    }

    fun isConnect():Boolean{
        val connect : ConnectivityManager = activity?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return connect.activeNetworkInfo != null && connect.activeNetworkInfo!!.isConnected
    }

    private fun showData(data: ArrayList<News>?) {

        listNotif.adapter =   NewsAdapter(data)
        listNotif2.adapter = NewsAdapter(data)

    }

}