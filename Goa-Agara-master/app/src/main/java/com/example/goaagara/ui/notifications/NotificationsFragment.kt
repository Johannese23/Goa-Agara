package com.example.goaagara.ui.notifications

import android.app.ActionBar
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.text.HtmlCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.goaagara.MainActivity
import com.example.goaagara.R
import com.example.goaagara.adapter.NewsAdapter
import com.example.goaagara.adapter.NewsAdapter2
import com.example.goaagara.model.News
import com.example.goaagara.model.ResponseServer
import com.example.goaagara.network.ConfigNetwork
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_notifications.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class NotificationsFragment : Fragment() {

    var news:ArrayList<News>? = ArrayList()
    var displayList:ArrayList<News>? = ArrayList()
    var news2:ArrayList<News>? = ArrayList()
    var displayList2:ArrayList<News>? = ArrayList()

    private lateinit var notificationsViewModel: NotificationsViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        notificationsViewModel =
                ViewModelProvider(this).get(NotificationsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_notifications, container, false)
        val konten = root.findViewById(R.id.kontent) as LinearLayout
        konten.visibility = View.GONE

        val progre = root.findViewById(R.id.progress) as ProgressBar
        val nav = (activity as AppCompatActivity)
        nav.supportActionBar?.show()
        nav.supportActionBar?.title = HtmlCompat.fromHtml("<font color='"+Color.BLACK+"'>"+"Artikel Berita Desa"+"</font>",HtmlCompat.FROM_HTML_MODE_LEGACY)
        nav.supportActionBar?.setBackgroundDrawable(ColorDrawable(Color.WHITE))
        if (isConnect()){
                ConfigNetwork.getRetrofit().getDataNewsApple().enqueue(object : Callback<ResponseServer>{
                    override fun onFailure(call: Call<ResponseServer>, t: Throwable) {
                        progre.visibility = View.GONE
                        t.message?.let { Log.d("error Server", it) }
                    }

                    override fun onResponse(
                        call: Call<ResponseServer>,
                        response: Response<ResponseServer>
                    ) {
                        Log.d("response server", response.message())
                        if (response.isSuccessful){
                            progre.visibility = View.GONE
                            konten.visibility = View.VISIBLE
                            val status = response.body()?.status
                            if (status.equals("ok")){
                                news2 = response.body()?.articles
                                news2?.let { displayList2?.addAll(it) }
                                showData(displayList2)
                            }
                        }
                    }

                })

            ConfigNetwork.getRetrofit().getDataNewsHeadlines().enqueue(object : Callback<ResponseServer>{
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
                            news = response.body()?.articles
                            news?.let { displayList?.addAll(it) }
                            showData2(displayList)
                        }
                    }
                }

            })
        }else{
            Snackbar.make(root, "device tidak connect dengan intenet", Snackbar.LENGTH_LONG).show()
        }



        val searc = root.findViewById(R.id.searchNotif) as SearchView
        searc.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                if (p0!!.isNotEmpty()){
                    displayList?.clear()
                    displayList2?.clear()
                    val search =  p0.toLowerCase()
                    news?.forEach {
                        if(it?.title?.toLowerCase()?.contains(search)!!){
                            displayList?.add(it)
                        }
                    }

                    news2?.forEach{
                        if(it?.title?.toLowerCase()?.contains(search)!!){
                            displayList2?.add(it)
                        }
                    }

                    listNotif2.adapter?.notifyDataSetChanged()

                    listNotif.adapter?.notifyDataSetChanged()
                }else{
                    displayList?.clear()
                    news?.let { displayList?.addAll(it) }
                    listNotif2.adapter?.notifyDataSetChanged()

                    displayList2?.clear()
                    news2?.let { displayList2?.addAll(it) }
                    listNotif.adapter?.notifyDataSetChanged()
                }
                return true
            }

        })

//        if (search!=null){
////            val searcView = search.actionView as SearchView
//
//        }

        return root
    }

    fun isConnect():Boolean{
        val connect : ConnectivityManager = activity?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return connect.activeNetworkInfo != null && connect.activeNetworkInfo!!.isConnected
    }

    private fun showData(data: ArrayList<News>?) {
        listNotif.adapter = NewsAdapter(data)
    }

    private fun showData2(data: ArrayList<News>?) {
        listNotif2.adapter = NewsAdapter2(data)
    }


}