package id.barakkastudio.sample.ui.component


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import id.barakkastudio.core.R
import id.barakkastudio.core.data.model.Product
import id.barakkastudio.core.ui.theme.Gray200
import id.barakkastudio.core.util.UtilFunctions.fromDollarToRupiah

@Composable
fun ProductItem(
    modifier: Modifier = Modifier,
    product: Product = Product()
) {
    Card(
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        modifier = modifier
            .padding(8.dp)
            .defaultMinSize()
    ) {
        Column(
            modifier = modifier.defaultMinSize()
        ) {
            SubcomposeAsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(product.thumbnail)
                    .crossfade(true)
                    .build(),
                loading = {
                    CircularProgressIndicator(
                        color = Color.LightGray,
                        modifier = Modifier.padding(48.dp)
                    )
                },
                contentDescription = stringResource(R.string.product_thumbnail),
                contentScale = ContentScale.Crop,
                modifier = modifier.height(180.dp)
            )
            Divider(color = Gray200, thickness = 1.dp)
            Column(
                modifier = modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                Text(
                    text = product.title ?: "",
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.ExtraBold
                    ),
                    color = Color.Black
                )
                Text(
                    text = product.price.fromDollarToRupiah(),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.secondary
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProductItemPreview() {
    ProductItem(
        product = Product(
            id = 1,
            title = "Product Title",
            price = 100000.0,
            thumbnail = "https://www.google.com/images/branding/googlelogo/2x/googlelogo_color_272x92dp.png"
        ),
        modifier = Modifier
    )
}