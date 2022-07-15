package com.nextory.testapp.ui

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.google.accompanist.navigation.material.ModalBottomSheetLayout
import com.google.accompanist.navigation.material.bottomSheet
import com.google.accompanist.navigation.material.rememberBottomSheetNavigator
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.nextory.testapp.ui.bookdetail.BookDetail
import com.nextory.testapp.ui.booklist.BookList
import com.nextory.testapp.ui.theme.TestAppTheme

private sealed class Screen(val route: String) {
    object BookList : Screen("book_list")
    object BookDetail : Screen("book_detail")
}

@OptIn(
    ExperimentalMaterialNavigationApi::class,
    ExperimentalAnimationApi::class
)
@Composable
fun TestApp() {
    TestAppTheme {
        val systemUiController = rememberSystemUiController()
        val useDarkIcons = !isSystemInDarkTheme()

        SideEffect {
            systemUiController.setSystemBarsColor(
                color = Color.Transparent,
                darkIcons = useDarkIcons
            )
        }

        val bottomSheetNavigator = rememberBottomSheetNavigator()
        val navController = rememberAnimatedNavController(bottomSheetNavigator)
        val showBookDetail = {
            bookId: Long ->
            navController.navigate(route = Screen.BookDetail.route + "?id=$bookId")
        }

        ModalBottomSheetLayout(bottomSheetNavigator) {
            AnimatedNavHost(navController, startDestination = Screen.BookList.route) {
                composable(route = Screen.BookList.route) {
                    BookList(showDetail = showBookDetail)
                }
                bottomSheet(Screen.BookDetail.route + "?id={id}") { backstackEntry ->
                    val bookId = backstackEntry.arguments?.getString("id")!!.toLong()
                    BookDetail(bookId = bookId)
                }
            }
        }
    }
}
