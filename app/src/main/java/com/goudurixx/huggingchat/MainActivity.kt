package com.goudurixx.huggingchat

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.goudurixx.huggingchat.core.network.models.Model
import com.goudurixx.huggingchat.core.network.services.HuggingFaceApi
import com.goudurixx.huggingchat.ui.theme.HuggingChatTheme
import androidx.paging.compose.LazyPagingItems
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme

class MainActivity : ComponentActivity() {

    private val huggingFaceApi = HuggingFaceApi()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()


        setContent {

            var modelList by remember {
                mutableStateOf<List<Model>?>(null)
            }
            LaunchedEffect(Unit) {
                modelList = huggingFaceApi.getModels()
            }
            HuggingChatTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(modifier = Modifier.padding(innerPadding)) {
                        Text(text = "Here are the beautiful models")
                        LazyColumn {
                            modelList?.let { models ->
                                items(count = models.size, key = { models[it]._id }) { index ->
                                    ModelCard(
                                        model = models[index],
                                        modifier = Modifier.padding(8.dp)
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ModelCard(model: Model, modifier: Modifier = Modifier) {
    Card(modifier = modifier) {
        Text(
            text = model.id,
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(4.dp)
        )
        Row(modifier = Modifier.padding(4.dp)) {
            Text(text = "Tags :")
            model.tags.forEach { Text(text = " <$it> ") }
        }
        Row(modifier = Modifier.padding(4.dp).fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text(text = "${model.likes}♥")
            Text(text = "${model.createdAt}⏰")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ModelCardPreview() {
    val model = Model(
        _id = "621ffdc036468d709f174328",
        id = "albert/albert-base-v1",
        likes = 7,
        private = false,
        downloads = 17412,
        tags = listOf("transformers", "pytorch", "region:us"),
        pipeline_tag = "fill-mask",
        library_name = "transformers",
        createdAt = "2022-03-02T23:29:04.000Z",
        modelId = "albert/albert-base-v1",
    )
    HuggingChatTheme {
        ModelCard(model = model)
    }
}