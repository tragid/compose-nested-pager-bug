package com.frontend.mobile.composenestedpagerbug

import android.annotation.SuppressLint
import android.graphics.Color as AndroidColor
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.snapping.SnapPosition
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsIgnoringVisibility
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch


@Composable
fun InnerItemBox(
    modifier: Modifier = Modifier,
    title: String,
) {
    Box(
        modifier = modifier
            .width(124.dp)
            .aspectRatio(0.67f)
            .borderRadius(10.dp)
            .background(Color.DarkGray),
        contentAlignment = Alignment.Center,
    ) {
        Text(text = title, color = Color.White)
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalLayoutApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.dark(
                AndroidColor.TRANSPARENT
            ),
            navigationBarStyle = SystemBarStyle.light(
                AndroidColor.TRANSPARENT, AndroidColor.TRANSPARENT
            )
        )
        super.onCreate(savedInstanceState)

        setContent {
            val tabs = listOf(
                "Tab 1",
                "Tab 2",
                "Tab 3",
            )

            val pagerState = rememberPagerState {
                tabs.size
            }
            val scope = rememberCoroutineScope()
            val state = rememberLazyListState()
            val flingBehavior = rememberSnapFlingBehavior(
                lazyListState = state,
                snapPosition = SnapPosition.Start,
            )

            Scaffold(
                contentWindowInsets = WindowInsets.statusBarsIgnoringVisibility,
                containerColor = Color.White,
                contentColor = Color.Black,
                modifier = Modifier.background(Color.White),
            ) { padding ->
                Column(
                    Modifier
                        .fillMaxSize()
                        .padding(padding)) {
                    Column(modifier = Modifier.fillMaxWidth()) {
                        TabRow(
                            modifier = Modifier.fillMaxWidth(),
                            selectedTabIndex = pagerState.currentPage,
                            containerColor = Color.Transparent,
                            divider = {},
                        ) {
                            tabs.forEachIndexed { index, title ->
                                Tab(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(44.dp),
                                    text = {
                                        Text(text = title)
                                    },
                                    selected = index == pagerState.currentPage,
                                    onClick = {
                                        scope.launch {
                                            pagerState.animateScrollToPage(index)
                                        }
                                    },
                                )
                            }
                        }
                    }

                    HorizontalPager(state = pagerState, modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)) { index ->
                        Box(modifier = Modifier
                            .fillMaxSize()
                            .background(
                                if (index == 0) {
                                    Color.White
                                } else if (index == 1) {
                                    Color.Cyan
                                } else {
                                    Color.Yellow
                                }
                            ), contentAlignment = Alignment.Center) {
                            if (index == 0) {
                                LazyRow(
                                    state = state,
                                    flingBehavior = flingBehavior,
                                    contentPadding = PaddingValues(
                                        horizontal = 8.dp,
                                    ),
                                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                                ) {
                                    items(
                                        count = 20,
                                        key = { it }
                                    ) {
                                        InnerItemBox(title = "Item $it")
                                    }
                                }
                            } else {
                                Text(text = tabs[index])   
                            }
                        }
                    }
                }
            }
        }
    }
}