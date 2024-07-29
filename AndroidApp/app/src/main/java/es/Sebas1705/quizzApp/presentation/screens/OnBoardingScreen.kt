package es.Sebas1705.quizzApp.presentation.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import es.Sebas1705.quizzApp.presentation.common.boardingPage.BoardingPage
import es.Sebas1705.quizzApp.presentation.common.boardingPage.pageList

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnBoardingScreen(){
    Column(
       modifier = Modifier.fillMaxSize()
    ){
        val pagerState = rememberPagerState(initialPage = 0) {
            pageList.size
        }

        val buttonState = remember{
            derivedStateOf {
                val cp = pagerState.currentPage
                if(cp==0) listOf("","Siguiente")
                else if(cp==pageList.size-1) listOf("Atrás","Siguiente")
                else listOf("Back","Empezar!")
            }
        }
        
        HorizontalPager(state = pagerState) {
            BoardingPage(page = pageList[it])
        }

        Spacer(modifier = Modifier.weight(1f))

        
    }
}