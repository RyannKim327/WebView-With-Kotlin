package a

import android.os.Bundle
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import android.widget.LinearLayout
import android.widget.LinearLayout.LayoutParams
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible

class a : AppCompatActivity(){
	val DEFAULT_LINK = "https://www.google.com"

	lateinit var web_normal: WebView
	lateinit var web_incog: WebView

	override fun onCreate(bundle: Bundle?){
		super.onCreate(bundle)
		val base = LinearLayout(this)
		val normal = Button(this)
		web_normal = WebView(this)
		web_incog = WebView(this)

		base.orientation = LinearLayout.VERTICAL

		normal.text = "Normal Mode"

		normal.layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)

		web_normal.visibility = View.VISIBLE;
		web_incog.visibility = View.GONE;

		web_normal.settings.javaScriptEnabled = true
		web_normal.settings.javaScriptCanOpenWindowsAutomatically = false

		web_incog.settings.javaScriptEnabled = true
		web_incog.settings.javaScriptCanOpenWindowsAutomatically = false

		web_normal.webViewClient = WebViewClient()
		web_normal.webChromeClient = WebChromeClient()
		web_incog.webViewClient = WebViewClient()
		web_incog.webChromeClient = WebChromeClient()

		web_normal.loadUrl(DEFAULT_LINK)
		web_incog.loadUrl(DEFAULT_LINK)

		web_normal.layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
		web_incog.layoutParams = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT)

		normal.setOnClickListener(object: View.OnClickListener {
			override fun onClick(v: View?){
				if(web_normal.visibility == View.VISIBLE){
					web_normal.visibility = View.GONE
					web_incog.visibility = View.VISIBLE
					normal.text = "Incognito Mode"
				}else{
					web_normal.visibility = View.VISIBLE
					web_incog.visibility = View.GONE
					normal.text = "Normal Mode"
				}
			}
		})

		base.addView(normal)
		base.addView(web_normal)
		base.addView(web_incog)

		setContentView(base)
	}
	override fun onBackPressed() {
		if(web_normal.visibility == View.VISIBLE && web_incog.visibility == View.GONE){
			if(web_normal.canGoBack()){
				web_normal.goBack()
			}else{
				Toast.makeText(this, "Thank you for using", Toast.LENGTH_SHORT).show()
				super.onBackPressed()
			}
		}else if(web_incog.visibility == View.VISIBLE){
			if(web_incog.canGoBack()){
				web_incog.goBack()
			}else{
				Toast.makeText(this, "Thank you for using", Toast.LENGTH_SHORT).show()
				super.onBackPressed()
			}
		}
	}

	override fun onDestroy() {
		web_incog.clearHistory()
		web_incog.clearFormData()
		web_incog.clearCache(true)
		super.onDestroy()
	}
}