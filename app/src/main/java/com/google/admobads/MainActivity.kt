package com.google.admobads

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.compose.LifecycleResumeEffect
import com.google.admobads.ui.theme.AdmobAdsTheme
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.LoadAdError

class MainActivity : ComponentActivity() {
  @OptIn(ExperimentalMaterial3Api::class)
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContent {
      AdmobAdsTheme {
        
        Scaffold(modifier = Modifier.fillMaxSize(),
          topBar = {
            TopAppBar(title = { Text(text = "AdMob Banner") },
              navigationIcon = { Icon(imageVector = Icons.Default.Star, contentDescription = null) },
              actions = { Icon(imageVector = Icons.Default.MoreVert, contentDescription = null) })
          }) { innerPadding ->
          Column(modifier = Modifier
            .padding(innerPadding)
            .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {
            AdMobBanner()
            
            Button(onClick = { /*TODO*/ }) {
              Text(text = "Banner Ad")
            }
          }
        }
      }
    }
  }
  
  @Composable
  private fun AdMobBanner() {
    val context = LocalContext.current
    val adId = stringResource(id = R.string.admob_banner_ad)
    val adView = remember {
      val adRequest = AdRequest.Builder().build()
      AdView(context).apply {
        adUnitId = adId
        setAdSize(AdSize.MEDIUM_RECTANGLE)
        adListener = object : AdListener() {
          override fun onAdClicked() {
            super.onAdClicked()
          }
          
          override fun onAdClosed() {
            super.onAdClosed()
          }
          
          override fun onAdFailedToLoad(p0: LoadAdError) {
            super.onAdFailedToLoad(p0)
          }
          
          override fun onAdImpression() {
            super.onAdImpression()
          }
          
          override fun onAdLoaded() {
            super.onAdLoaded()
          }
          
          override fun onAdOpened() {
            super.onAdOpened()
          }
          
          override fun onAdSwipeGestureClicked() {
            super.onAdSwipeGestureClicked()
          }
        }
        loadAd(adRequest)
      }
    }
    AndroidView({ adView })
    
    LifecycleResumeEffect(key1 = Unit) {
      adView.resume()
      onPauseOrDispose { adView.pause() }
    }
  }
}

