package com.arina.myjetpackapp.ui.page.detail

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.arina.myjetpackapp.di.Injection
import com.arina.myjetpackapp.ui.common.UiState
import com.arina.myjetpackapp.ui.page.ViewModelFactory
import com.arina.myjetpackapp.ui.theme.MyJetpackAppTheme

@Composable
fun DetailPage(
    id: Long,
    viewModel: DetailViewModel = androidx.lifecycle.viewmodel.compose.viewModel(
        factory = ViewModelFactory(Injection.provideRepository())
    ),
    navigateBack: () -> Unit
){
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let{
        when(it) {
            is UiState.Loading -> {
                viewModel.getById(id)
            }
            is UiState.Success -> {
                val data = it.data
                DetailProduct(
                    product_name = data.product.product_name,
                    product_description = data.product.product_description,
                    product_ingredients = data.product.product_ingredient,
                    product_picture = data.product.product_picture,
                    onBackClick = navigateBack
                )
            }
            is UiState.Error -> {}
        }
    }
}

@Composable
fun DetailProduct(
    product_name: String,
    product_description: String,
    product_ingredients: String,
    product_picture: String,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
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
           AsyncImage(model = product_picture, contentDescription = "product_image", contentScale = ContentScale.Crop)
           Spacer(modifier = Modifier.height(20.dp))
           Text(
               text = product_name,
               fontWeight = FontWeight.Bold,
               fontSize = 22.sp
           )
           Spacer(modifier = Modifier.height(16.dp))
           Text(
               text = product_description,
               fontSize = 14.sp
           )
           Spacer(modifier = Modifier.height(20.dp))
           Text(
               text = "Ingredients",
               fontWeight = FontWeight.Bold,
               fontSize = 18.sp
           )
           Spacer(modifier = Modifier.height(8.dp))
           Text(
               text = product_ingredients,
               fontSize = 13.sp
           )
           Spacer(modifier = Modifier.height(20.dp))
       }
   }
}

@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun DetailProductPreview() {
    MyJetpackAppTheme {
        DetailProduct(
            product_name = "Product Name",
            product_description = "Product Description",
            product_ingredients = "Product Ingredients",
            product_picture = "Product Picture",
            onBackClick = { }
        )
    }
}