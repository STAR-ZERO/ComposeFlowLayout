/**
 * Copyright 2021 Kenji Abe
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.star_zero.compose.flowlayout.sample

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.star_zero.compose.flowlayout.FlowLayout
import com.star_zero.compose.flowlayout.sample.ui.theme.ComposeFlowLayoutTheme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeFlowLayoutTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Sample()
                }
            }
        }
    }
}

@Composable
fun Sample() {
    FlowLayout(
        verticalSpacing = 8.dp,
        horizontalSpacing = 8.dp,
        modifier = Modifier.padding(16.dp)
    ) {
        Item("Kotlin", "FF786BDA")
        Item("Java", "FF0C8AC7")
        Item("JavaScript", "FFD5BA32")
        Item("Python", "FFFFCC3A")
        Item("C++", "FF01559D")
        Item("Swift", "FFFC5A2D")
        Item("Go", "FF69D2E0")
        Item("Dart", "FF43C4FE")
        Item("Rust", "FF000000")
    }
}

@Composable
fun Item(name: String, color: String) {
    Box(
        modifier = Modifier.background(
            color = Color(color.toLong(radix = 16)),
            shape = RoundedCornerShape(percent = 50)
        )
    ) {
        Text(
            text = name,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp),
            style = MaterialTheme.typography.h5,
            color = Color.White
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeFlowLayoutTheme {
        Surface(color = MaterialTheme.colors.background) {
            Sample()
        }
    }
}
