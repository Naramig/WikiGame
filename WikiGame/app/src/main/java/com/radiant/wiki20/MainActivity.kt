package com.radiant.wiki20

import android.os.Bundle
import android.util.Log
import android.view.View
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


lateinit var webView: WebView
lateinit var nameOfTheTopic: TextView
lateinit var numberOfClicks: TextView

var counter = -2 // -2 because webView opens 2 url before starts
var indexOfSerchingTopic = -1

val randomPageUrl = "https://en.wikipedia.org/wiki/Special:Random"
val topics : MutableList<Topic> = arrayListOf()

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        nameOfTheTopic = findViewById(R.id.searchTopicName_textView)
        numberOfClicks = findViewById(R.id.numberOfClicks_textView)
        webView = findViewById(R.id.webView)

        applySettingsToWEbView()
        createArrayOfTopics(topics)
        startNewGame()


    }

    private fun applySettingsToWEbView() {
        val webSetting: WebSettings = webView.settings
        webSetting.domStorageEnabled = true
        webSetting.javaScriptEnabled = true
        webView.webViewClient = WebViewClientSubClass()
    }

    private fun createArrayOfTopics(topics: MutableList<Topic>) {
        topics.add(Topic("Vladimir Putin", "/Vladimir_Putin"))
        topics.add(Topic("Donald Trump", "/Donald_Trump"))
        topics.add(Topic("Sooronbay Jeenbekov", "/Sooronbay_Jeenbekov"))
        topics.add(Topic("New York City", "/New_York_City"))
        topics.add(Topic("Kyrgyzstan", "/Kyrgyzstan"))
        topics.add(Topic("Bill Gates", "/Bill_Gates"))
        topics.add(Topic("Germany", "/Germany"))
        topics.add(Topic("Moscow", "/Moscow"))
        topics.add(Topic("Bishkek", "/Bishkek"))
        topics.add(Topic("Nuclear weapon", "/Nuclear_weapon"))
        topics.add(Topic("Munich", "/Munchen"))

    }


    private class WebViewClientSubClass : WebViewClient() {
        var oldUrl = ""
        override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
            if(url.contains(topics[indexOfSerchingTopic].url)){
                nameOfTheTopic.text = "You Won!!!"
            }
            if(!oldUrl.equals(url)){
                    ++counter
                if(counter >=0)
                    numberOfClicks.text = counter.toString()
            }
            return false
        }
    }

    fun startNewGame() {
        if(topics.size <= indexOfSerchingTopic+1) indexOfSerchingTopic =0 else indexOfSerchingTopic++
        counter = -2
        nameOfTheTopic.text = topics[indexOfSerchingTopic].topic
        webView.loadUrl(randomPageUrl)
    }

    fun nextGameButtonListener(view: View){
        startNewGame()
    }

}
