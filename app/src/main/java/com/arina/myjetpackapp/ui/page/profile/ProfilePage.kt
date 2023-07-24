package com.arina.myjetpackapp.ui.page.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arina.myjetpackapp.ui.theme.MyJetpackAppTheme

@Composable
fun ProfilePage(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        item {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back",
                modifier = Modifier
                    .padding(16.dp)
                    .clickable { onBackClick() }
            )
            Image(
                modifier = modifier
                    .fillMaxWidth()
                    .height(350.dp)
                    .padding(16.dp)
                    .clip(CircleShape),
                contentDescription = "Profile",
                painter = painterResource(id = com.arina.myjetpackapp.R.drawable.arina_pic)
            )
            Text(
                text = "Arina Sabilahaq",
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(1f)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "ar.inasbil@gmail.com",
                fontSize = 18.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(1f)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfilePagePreview() {
    MyJetpackAppTheme {
        ProfilePage(onBackClick = {})
    }
}