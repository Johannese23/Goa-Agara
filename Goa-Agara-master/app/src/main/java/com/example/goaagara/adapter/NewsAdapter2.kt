package com.example.goaagara.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.goaagara.R
import com.example.goaagara.model.News
import com.example.goaagara.model.dataNews
import com.example.goaagara.ui.notifications.detail.detail_news
import kotlinx.android.synthetic.main.list_notifications_popular.view.*

class NewsAdapter2(var data: ArrayList<News>?) :RecyclerView.Adapter<NewsAdapter2.NewsHolder>() {
    class NewsHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val title = itemView.titleNotification
        val des = itemView.descNotification
        val icon = itemView.imgNotification
        val date = itemView.date
        val radius = itemView.resources.getDimensionPixelSize(R.dimen.corner_radius)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_notifications_popular, parent, false)
        val holder =NewsHolder(view)

        return holder
    }

    override fun getItemCount(): Int {
        return data?.size ?: 0
    }

    override fun onBindViewHolder(holder: NewsHolder, position: Int) {
        holder.des.text =data?.get(position)?.description
        holder.title.text = data?.get(position)?.title
        holder.date.text = data?.get(position)?.publishedAt

        Glide.with(holder.itemView.context)
            .load(data?.get(position)?.urlToImage)
            .apply(RequestOptions
                .overrideOf(270, 320)
                .fitCenter())
            .transform(RoundedCorners(holder.radius))
            .into(holder.icon)


        holder.itemView.setOnClickListener{
            val intent = Intent(holder.itemView.context, detail_news::class.java)
            intent.putExtra("data", dataNews(
                    data?.get(position)?.id.toString(),
                    data?.get(position)?.name.toString(),
                    data?.get(position)?.author.toString(),
                    data?.get(position)?.title.toString(),
                    data?.get(position)?.description.toString(),
                    data?.get(position)?.publishedAt.toString(),
                    data?.get(position)?.url.toString(),
                    data?.get(position)?.urlToImage.toString()
            )
            )
            holder.itemView.context.startActivity(intent)
        }
    }
}

//class NewsAdapter(context: Callback<ResponseServer>, private val data: List<News>?): BaseAdapter() {
////class NewsAdapter(private val context: Context,pval data: ArrayList<News>?): BaseAdapter() {
//    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
//        val view = LayoutInflater.from(parent?.context).inflate(R.layout.list_menu, parent, false)
//
//        val jenis = view.findViewById<TextView>(R.id.title)
//        val date = view.findViewById<TextView>(R.id.date)
//        val icon = view.findViewById<ImageView>(R.id.icon)
//
//        val item = data?.get(position)
//
//        jenis.text =item?.title
//        date.text ="Published at : "+item?.publishedAt
//         Glide.with(view.context)
//                 .load(item?.urlToImage)
//                 .into(icon)
//        return view
//    }
//
//    override fun getItem(position: Int): Any {
//        return data?.get(position) ?:0
//    }
//
//    override fun getItemId(position: Int): Long {
//        return position.toLong()
//    }
//
//    override fun getCount(): Int = data?.size ?: 0
//}