package com.sourabh.stockapp.presentation.topGainerList

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sourabh.stockapp.R
import com.sourabh.stockapp.domain.module.TopGainer

@Composable
fun StockCard(
    modifier: Modifier = Modifier,
    topGainer: TopGainer
) {
    Card(
        modifier = modifier.padding(16.dp),
        shape = MaterialTheme.shapes.medium
    ) {
        Column(
            modifier = Modifier
                .height(150.dp)
                .width(150.dp)
                .padding(16.dp)
        ) {
            Text(
                text = topGainer.ticker,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = MaterialTheme.colorScheme.onBackground,
                maxLines = 2,
                modifier = Modifier
                    .padding(top = 16.dp)
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "$ ${topGainer.price}",
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.onBackground
            )

            Spacer(modifier = Modifier.height(5.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "+ ${topGainer.change_percentage}",
                    fontStyle = FontStyle.Italic,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp,
                    color = colorResource(id = R.color.green_dark)
                )

                Icon(
                    modifier = Modifier.size(33.dp),
                    imageVector = Icons.Default.ArrowDropUp,
                    contentDescription = null,
                    tint = Color.Green
                )
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun StockCardPreview() {
    StockCard(
        topGainer = TopGainer(
            change_amount = "1.0",
            change_percentage = "1.0",
            price = "1.0",
            ticker = "AAPL",
            volume = "1.0"
        )
    )
}