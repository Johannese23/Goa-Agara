package com.example.goaagara.ui.notifications.detail

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.drawable.ColorDrawable
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.addCallback
import androidx.core.text.HtmlCompat
import com.bumptech.glide.Glide
import com.example.goaagara.R
import com.example.goaagara.model.dataNews
import kotlinx.android.synthetic.main.activity_detail_news.*

class detail_news : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_news)

        val data = intent.getParcelableExtra<dataNews>("data")
        supportActionBar?.show()
        supportActionBar?.title = HtmlCompat.fromHtml("<font color='"+ Color.BLACK+"'>"+"Detail Berita"+"</font>", HtmlCompat.FROM_HTML_MODE_LEGACY)
        supportActionBar?.setBackgroundDrawable(ColorDrawable(Color.WHITE))

        titleDetail.text = data?.title
        Glide.with(this).load(data?.urlToImage).into(img)
        description.text = data?.description
        description2.text = data?.description
        val actionBar = supportActionBar
        val upArrow= resources.getDrawable(R.drawable.abc_ic_ab_back_material)
        upArrow.setColorFilter(Color.parseColor("#BEBEBE"), PorterDuff.Mode.SRC_ATOP)
        supportActionBar?.setHomeAsUpIndicator(upArrow)
        actionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home-> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

}