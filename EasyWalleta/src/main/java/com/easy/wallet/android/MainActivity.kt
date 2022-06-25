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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation
import com.easy.wallet.android.theme.EasyTheme
import com.easy.wallet.models.TokenAsset
import com.easy.wallet.remote.EasyApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import wallet.core.jni.HDWallet

class MainActivity : AppCompatActivity() {
    private val easyApi: EasyApi by inject()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EasyTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    val hdWallet = HDWallet(
                        "credit expect life fade cover suit response wash pear what skull force",
                        ""
                    )
                    val scope = rememberCoroutineScope()
                    val state = remember {
                        mutableStateOf(emptyList<TokenAsset>())
                    }
                    LaunchedEffect(key1 = null) {
                        scope.launch(Dispatchers.IO) {
                            state.value = try {
                                easyApi.loadAssets()
                            } catch (e: Exception) {
                                e.printStackTrace()
                                Log.e("Error", e.message.orEmpty())
                                emptyList()
                            }
                        }
                    }

                    LazyColumn(modifier = Modifier.fillMaxSize()) {
                        items(items = state.value) {
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
