package org.sahil.portfolio

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import org.sahil.portfolio.components.AnimatedBackground
import org.sahil.portfolio.components.aboutme.AboutMe
import org.sahil.portfolio.components.hero.HeroSection
import org.sahil.portfolio.components.skills.Skills
import org.sahil.portfolio.components.topBar.CompactDrawerContent
import org.sahil.portfolio.components.topBar.TopAppBar
import org.sahil.portfolio.components.topBar.TopAppBarSection
import org.sahil.portfolio.theme.AppTheme
import org.sahil.portfolio.util.MaxWidthContainer

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App() {
    var isDark by rememberSaveable { mutableStateOf(false) }
    AppTheme(isDark) {
        var selectedSection by remember { mutableStateOf(TopAppBarSection.Home) }
        val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

        val drawerState = rememberDrawerState(DrawerValue.Closed)

        val scrollState = rememberLazyListState()
        val scope = rememberCoroutineScope()

        CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
            ModalNavigationDrawer(
                drawerState = drawerState,
                drawerContent = {
                    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr) {
                        CompactDrawerContent(
                            selectedSection = selectedSection,
                            onSectionClick = {
                                selectedSection = it
                                scope.launch {
                                    drawerState.close()
                                    scrollState.animateScrollToItem(selectedSection.ordinal)
                                }
                            },
                            onHireMeClick = {}
                        )
                    }
                },
            ) {
                CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr) {
                    Box {
                        AnimatedBackground(isDark = isDark)
                        Scaffold(
                            containerColor = Color.Transparent, topBar = {
                                MaxWidthContainer {
                                    TopAppBar(
                                        selectedSection = selectedSection,
                                        onSectionClick = {
                                            selectedSection = it
                                            scope.launch {
                                                scrollState.animateScrollToItem(selectedSection.ordinal)
                                            }
                                        },
                                        isDark = isDark,
                                        toggleDarkTheme = { isDark = it },
                                        scrollBehavior = scrollBehavior,
                                        onHireMeClick = {},
                                        onMenuClick = {
                                            scope.launch { drawerState.open() }
                                        }
                                    )
                                }
                            }) {
                            MaxWidthContainer {
                                LazyColumn(
                                    modifier = Modifier.fillMaxSize().padding(it).nestedScroll(scrollBehavior.nestedScrollConnection),
                                    verticalArrangement = Arrangement.spacedBy(120.dp),
                                    state = scrollState
                                ) {
                                    item { HeroSection() }
                                    item { AboutMe() }
                                    item { Skills() }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
