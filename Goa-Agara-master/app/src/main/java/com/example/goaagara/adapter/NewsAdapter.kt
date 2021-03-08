package com.example.goaagara.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.goaagara.R
import com.example.goaagara.model.News
import kotlinx.android.synthetic.main.list_notifications.view.*

class NewsAdapter(var data: ArrayList<News>?) :RecyclerView.Adapter<NewsAdapter.NewsHolder>() {
    class NewsHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val title = itemView.titleNotification
        val des = itemView.descNotification
        val icon = itemView.imgNotification
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_notifications_new, parent, false)
        val holder =NewsHolder(view)

        return holder
    }

    override fun getItemCount(): Int {
        return data?.size ?: 0
    }

    override fun onBindViewHolder(holder: NewsHolder, position: Int) {
        holder.des.text =data?.get(position)?.description
        holder.title.text = data?.get(position)?.title
        Glide.with(holder.itemView.context).load(data?.get(position)?.urlToImage).into(holder.icon)


//        holder.itemView.setOnClickListener{
//            val intent = Intent(holder.itemView.context, detail::class.java)
//            intent.putExtra("url", data?.get(position)?.url)
//            holder.itemView.context.startActivity(intent)
//        }
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