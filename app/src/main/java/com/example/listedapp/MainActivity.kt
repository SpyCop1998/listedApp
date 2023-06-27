package com.example.listedapp

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.listedapp.databinding.ActivityMainBinding
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.LineGraphSeries
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : AppCompatActivity() {
    lateinit var viewModel: MainViewModel
    lateinit var binding: ActivityMainBinding
    private val adapterTopLinks = LinkAdapterTopLinks()
    private val adapterRecentLinks = LinkAdapterRecentLinks()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNavigationView.background = null
        binding.bottomNavigationView.itemIconTintList=null
        binding.bottomNavigationView.isClickable=false
        binding.bottomNavigationView.menu.getItem(2).isEnabled = false

        binding.recyclerviewTopLinks.adapter = adapterTopLinks
        binding.recyclerviewRecentLinks.adapter = adapterRecentLinks

        binding.settingImg.background.alpha = 30

        val graph = binding.graph

        graph.getGridLabelRenderer().setVerticalLabelsVisible(false);
        graph.getGridLabelRenderer().setHorizontalLabelsVisible(false);

        binding.greetingText.text = Utils.getGreetingText()

        val retrofitService = RetrofitService.getInstance()
        val mainRepository = MainRepository(retrofitService)

        binding.topLinkBtn.setOnClickListener {

            binding.topLinkBtn.backgroundTintList =
                ColorStateList.valueOf(resources.getColor(R.color.blue))
            binding.recentLinkBtn.backgroundTintList =
                ColorStateList.valueOf(resources.getColor(R.color.white))
            binding.recentLinkBtn.setTextColor(resources.getColor(R.color.black))
            binding.topLinkBtn.setTextColor(resources.getColor(R.color.white))
            binding.recyclerviewRecentLinks.visibility = View.GONE
            binding.recyclerviewTopLinks.visibility = View.VISIBLE
        }

        binding.recentLinkBtn.setOnClickListener {
            binding.topLinkBtn.setTextColor(resources.getColor(R.color.black))
            binding.recentLinkBtn.setTextColor(resources.getColor(R.color.white))

            binding.recentLinkBtn.backgroundTintList =
                ColorStateList.valueOf(resources.getColor(R.color.blue))

            binding.topLinkBtn.backgroundTintList =
                ColorStateList.valueOf(resources.getColor(R.color.white))


            binding.recyclerviewRecentLinks.visibility = View.VISIBLE
            binding.recyclerviewTopLinks.visibility = View.GONE
        }


        viewModel = ViewModelProvider(
            this,
            MainViewModelFactory(mainRepository)
        ).get(MainViewModel::class.java)

        viewModel.data.observe(this) {
            binding.clicks.text = it.todayClicks.toString()
            binding.location.text = it.topLocation
            binding.source.text = it.topSource
            it.data?.let { it1 -> adapterTopLinks.setLinks(it1.topLinks) }
            it.data?.let { it1 -> adapterRecentLinks.setLinks(it1.recentLinks) }

            val arr= Array<DataPoint>(it.data!!.overallUrlChart.size){ DataPoint(1.0,2.0) }

            var i=0

            val inputFormat = SimpleDateFormat("yyyy-MM-dd")


            for ((key, value) in it.data!!.overallUrlChart) {
                arr.set(i, DataPoint(inputFormat.parse(key),value.toDouble()))
                i++
            }

            val series = LineGraphSeries(arr)
            graph.addSeries(series)

        }

        viewModel.errorMessage.observe(this) {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }

        viewModel.loading.observe(this, Observer {
            if (it) {
                print("1")
            } else {
                print("2")
            }
        })
        try {
            viewModel.getData(AppApplication.bearerToken)
        } catch (e: java.lang.Exception) {
            print(e.message)
        }

    }

}