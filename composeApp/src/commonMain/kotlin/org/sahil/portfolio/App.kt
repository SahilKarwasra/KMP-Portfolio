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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
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
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import org.sahil.portfolio.components.AnimatedBackground
import org.sahil.portfolio.components.aboutme.AboutMe
import org.sahil.portfolio.components.contact.ContactSection
import org.sahil.portfolio.components.experience.ExperienceSection
import org.sahil.portfolio.components.footer.FooterSection
import org.sahil.portfolio.components.hero.HeroSection
import org.sahil.portfolio.components.myServices.ServicesSection
import org.sahil.portfolio.components.project.ProjectsSection
import org.sahil.portfolio.components.skills.SkillsSection
import org.sahil.portfolio.components.topBar.CompactDrawerContent
import org.sahil.portfolio.components.topBar.TopAppBar
import org.sahil.portfolio.components.topBar.TopAppBarSection
import org.sahil.portfolio.theme.AppTheme
import org.sahil.portfolio.util.MaxWidthContainer

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App() {
    var isDark by rememberSaveable { mutableStateOf(true) }
    AppTheme(isDark) {
        var selectedSection by remember { mutableStateOf(TopAppBarSection.Home) }
        val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

        val drawerState = rememberDrawerState(DrawerValue.Closed)

        val scrollState = rememberLazyListState()
        val scope = rememberCoroutineScope()

        // Map sections to their indices
        val sectionIndices = remember {
            mapOf(
                TopAppBarSection.Home to 0,
                TopAppBarSection.About to 1,
                TopAppBarSection.Skills to 2,
                TopAppBarSection.Experience to 3,
                TopAppBarSection.Project to 4,
                TopAppBarSection.Services to 5,
                TopAppBarSection.Contact to 6
            )
        }

        // Update selected section based on scroll position
        val currentSection by remember {
            derivedStateOf {
                val firstVisibleIndex = scrollState.firstVisibleItemIndex
                when (firstVisibleIndex) {
                    0 -> TopAppBarSection.Home
                    1 -> TopAppBarSection.About
                    2 -> TopAppBarSection.Skills
                    3 -> TopAppBarSection.Experience
                    4 -> TopAppBarSection.Project
                    5 -> TopAppBarSection.Services
                    6 -> TopAppBarSection.Contact
                    else -> TopAppBarSection.Home
                }
            }
        }

        // Update selectedSection when scrolling
        LaunchedEffect(currentSection) {
            selectedSection = currentSection
        }
        val appBarAlpha by remember {
            derivedStateOf {
                val offset = scrollBehavior.state.contentOffset
                when {
                    offset <= 0f -> 0f
                    offset in 0f..50f -> 0.05f
                    else -> 0.05f
                }
            }
        }
        val uriHandler = LocalUriHandler.current

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
                                    val index = sectionIndices[it] ?: 0
                                    scrollState.animateScrollToItem(index)
                                }
                            },
                            onHireMeClick = {
                                uriHandler.openUri("https://drive.google.com/file/d/1UoqFWIDntRKUr8Q6LCynn30fq-iado_p/view?usp=sharing")
                            }
                        )
                    }
                },
            ) {
                CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr) {
                    Box {
                        AnimatedBackground(isDark = isDark)
                        Scaffold(
                            containerColor = Color.Transparent,
                            topBar = {
                                MaxWidthContainer {
                                    TopAppBar(
                                        selectedSection = selectedSection,
                                        onSectionClick = {
                                            selectedSection = it
                                            scope.launch {
                                                val index = sectionIndices[it] ?: 0
                                                scrollState.animateScrollToItem(index)
                                            }
                                        },
                                        isDark = isDark,
                                        toggleDarkTheme = { isDark = it },
                                        scrollBehavior = scrollBehavior,
                                        onHireMeClick = {
                                            uriHandler.openUri("https://drive.google.com/file/d/1UoqFWIDntRKUr8Q6LCynn30fq-iado_p/view?usp=sharing")
                                        },
                                        onMenuClick = {
                                            scope.launch { drawerState.open() }
                                        },
                                        backgroundAlpha = appBarAlpha
                                    )
                                }
                            }
                        ) {
                            MaxWidthContainer {
                                LazyColumn(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(it)
                                        .nestedScroll(scrollBehavior.nestedScrollConnection),
                                    verticalArrangement = Arrangement.spacedBy(40.dp),
                                    state = scrollState
                                ) {
                                    item(key = "Hero") { HeroSection(contactMeClick = {
                                        scope.launch {
                                            val index = sectionIndices[TopAppBarSection.Contact] ?: 0
                                            scrollState.animateScrollToItem(index)

                                        }
                                    }) }
                                    item(key = "About") { AboutMe() }
                                    item(key = "Skills") { SkillsSection() }
                                    item(key = "Experience") { ExperienceSection() }
                                    item(key = "Projects") { ProjectsSection() }
                                    item(key = "Services") { ServicesSection() }
                                    item(key = "Contact") {ContactSection() }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}