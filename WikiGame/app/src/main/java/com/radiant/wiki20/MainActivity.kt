package com.radiant.wiki20

import android.content.Context
import android.database.Cursor
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
var counter = -2
var topics: Array<String> = arrayOf("Putin", "Donald Trump")
var topicsURL = arrayOf("/Vladimir_Putin", "/Donald_Trump")
var i = 0;

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        nameOfTheTopic = findViewById(R.id.searchTopicName_textView)
        numberOfClicks = findViewById(R.id.numberOfClicks_textView)
        webView = findViewById(R.id.webView)

        nameOfTheTopic.text = topics[i]
        numberOfClicks.text = counter.toString()

        val webSetting: WebSettings = webView.settings
        webSetting.domStorageEnabled = true
        webSetting.javaScriptEnabled = true
        webView.webViewClient = WebViewClientSubClass()
        webView.loadUrl("https://en.wikipedia.org/wiki/Special:Random")
    }
    private class WebViewClientSubClass : WebViewClient() {
        var oldUrl = ""
        override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
            if(url.contains(topicsURL[i])){
                nameOfTheTopic.text = "You Won!!!"
                Log.v("TEST23", url);

            }
            if(!oldUrl.equals(url)){
                ++counter
                numberOfClicks.text = counter.toString()
                Log.v("TEST2", url);

            }
            Log.v("TEST", url)
            return false
        }
    }

    fun startNewGame(view: View) {
        if(topics.size <= i+1) i =0 else i++
        counter = -2
        nameOfTheTopic.text = topics[i]
        numberOfClicks.text = counter.toString()
        webView.loadUrl("https://en.wikipedia.org/wiki/Special:Random")
    }

}
