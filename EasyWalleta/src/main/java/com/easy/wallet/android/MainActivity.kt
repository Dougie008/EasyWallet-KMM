package com.easy.wallet.android

import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.lifecycle.flowWithLifecycle
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation
import com.easy.wallet.android.theme.EasyTheme
import com.easy.wallet.viewmodels.AssetsViewModel
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {
    private val viewModel: AssetsViewModel by inject()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EasyTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    /*val hdWallet = HDWallet(
                        "credit expect life fade cover suit response wash pear what skull force",
                        ""
                    )*/
                    val scope = rememberCoroutineScope()
                    viewModel.loadLocal()
                    val lifecycleOwner = LocalLifecycleOwner.current
                    val lifecycleAssetsFlow = remember(viewModel.assetsState, lifecycleOwner) {
                        viewModel.assetsState.flowWithLifecycle(lifecycleOwner.lifecycle)
                    }
                    val assetState by lifecycleAssetsFlow.collectAsState(initial = viewModel.assetsState.value).also {
                        Log.i("=====", it.value.localContent)
                    }

                    LazyColumn(modifier = Modifier.fillMaxSize()) {
                        items(items = assetState.tokenAssets) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 16.dp, top = 4.dp, bottom = 4.dp, end = 16.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                AsyncImage(
                                    modifier = Modifier.size(32.dp),
                                    model = ImageRequest.Builder(LocalContext.current)
                                        .data(it.icon)
                                        .crossfade(true)
                                        .transformations(CircleCropTransformation())
                                        .build(),
                                    contentDescription = null
                                )
                                Text(
                                    text = it.symbol,
                                    modifier = Modifier
                                        .height(24.dp)
                                        .padding(start = 4.dp)
                                )
                            }
                            Divider(modifier = Modifier.fillMaxWidth(), color = Color(0X11111111))
                        }
                    }
                }
            }
        }
    }
}
