package com.arina.myjetpackapp.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.arina.myjetpackapp.ui.theme.MyJetpackAppTheme

@Composable
fun ProductsItem(
    productName: String,
    productDescription: String,
    productImage: String,
    modifier: Modifier = Modifier
){
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        AsyncImage(model = productImage, contentDescription = "product_image", contentScale = ContentScale.Crop,
        modifier = Modifier
            .height(150.dp)
            .width(150.dp)
            .padding(start = 8.dp, top = 8.dp, bottom = 8.dp, end = 8.dp))
        Column {
            Text(
                text = productName,
                fontWeight = FontWeight.Bold,
                fontSize = 22.sp,
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(start = 8.dp, end = 8.dp)
            )
            Text(
                text = productDescription,
                fontWeight = FontWeight.Normal,
                maxLines = 3,
                fontSize = 13.sp,
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(start = 8.dp, end = 8.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProductsItemPreview() {
    MyJetpackAppTheme {
        ProductsItem(productName = "Product Name", productDescription = "Product Description", productImage = "")
    }
}
