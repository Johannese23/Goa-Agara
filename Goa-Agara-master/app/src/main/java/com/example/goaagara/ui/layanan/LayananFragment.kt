package com.example.goaagara.ui.layanan

import android.graphics.Color
import android.graphics.Point
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.HtmlCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.goaagara.R
import kotlinx.android.synthetic.main.fragment_layanan.*
import kotlinx.android.synthetic.main.fragment_layanan.view.*

class LayananFragment : Fragment() {

    private lateinit var layananViewModel: LayananViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        layananViewModel =
                ViewModelProvider(this).get(LayananViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_layanan, container, false)

        val nav = (activity as AppCompatActivity)
        nav.supportActionBar?.show()
        nav.supportActionBar?.title = HtmlCompat.fromHtml("<font color='"
                + Color.WHITE+"'>"
                +"Daftar Layanan Desa"
                +"</font>", HtmlCompat.FROM_HTML_MODE_LEGACY)
        nav.supportActionBar?.setBackgroundDrawable(ColorDrawable(resources.getColor(R.color.green2)))

        val jSurat = root.jenisSurat
        val item = resources.getStringArray(R.array.jenisSurat)
        val spinAdapter = ArrayAdapter<String>(root.context, android.R.layout.simple_spinner_dropdown_item, item)
        spinAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        jSurat.adapter = spinAdapter

        return root
    }

}