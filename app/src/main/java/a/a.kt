package a

import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import android.widget.LinearLayout
import android.widget.LinearLayout.LayoutParams
import androidx.appcompat.app.AppCompatActivity

class a : AppCompatActivity(){
	val DEFAULT_LINK = "https://www.google.com"
	override fun onCreate(bundle: Bundle?){
		super.onCreate(bundle)
		val base = LinearLayout(this)
		val tabs = LinearLayout(this)
		val normal = Button(this)
		val incognito = Button(this)
		val web_normal = WebView(this)
		val web_incog = WebView(this)

		base.setOrientation(LinearLayout.VERTICAL)
		tabs.setOrientation(LinearLayout.HORIZONTAL)

		tabs.setLayoutParams(LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT))

		normal.setText("Normal Mode")
		incognito.setText("Incognito")

		normal.setLayoutParams(LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT, 0.5f))
		incognito.setLayoutParams(LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT, 0.5f))

		tabs.addView(normal)
		tabs.addView(incognito)

		web_normal.setWebViewClient(WebViewClient())
		web_normal.setWebChromeClient(WebChromeClient())
		web_incog.setWebViewClient(WebViewClient())
		web_incog.setWebChromeClient(WebChromeClient())

		web_normal.loadUrl(DEFAULT_LINK)
		web_incog.loadUrl(DEFAULT_LINK)

		web_normal.setLayoutParams(LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT))
		web_incog.setLayoutParams(LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT))

		base.addView(tabs)
		base.addView(web_normal)
		base.addView(web_incog)

		setContentView(base)
	}
}