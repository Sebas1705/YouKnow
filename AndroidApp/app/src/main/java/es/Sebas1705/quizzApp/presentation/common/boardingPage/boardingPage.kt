package es.Sebas1705.quizzApp.presentation.common.boardingPage

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import es.Sebas1705.quizzApp.ui.theme.MediumPadding
import es.Sebas1705.quizzApp.ui.theme.MediumTextHorPadding
import es.Sebas1705.quizzApp.ui.theme.MyApplicationTheme

@Composable
fun BoardingPage(
    modifier: Modifier = Modifier,
    page: Page
){
    Column(
        modifier = modifier
    ){
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.5f),
            painter = painterResource(id = page.image),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(MediumPadding))
        Text(
            text = page.title,
            modifier = Modifier.padding(horizontal = MediumTextHorPadding),
            style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.Bold),
            color = MaterialTheme.colorScheme.onPrimary
        )
        Spacer(modifier = Modifier.height(MediumPadding))
        Text(
            text = page.description,
            modifier = Modifier.padding(horizontal = MediumTextHorPadding),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onPrimary
        )
        Spacer(modifier = Modifier.height(MediumPadding))
    }
}

@Preview(showBackground = true,name = "Day")
@Preview(uiMode = UI_MODE_NIGHT_YES,showBackground = true,name = "Night")
@Composable
internal fun BoardingPagePreview(){
    MyApplicationTheme{
        BoardingPage(page = pageList[2])
    }
}