/*
 * Copyright 2018, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.invariant.dhamaka.util

import android.widget.ImageView
import android.widget.RatingBar
import androidx.databinding.BindingAdapter
import com.invariant.dhamaka.R
import com.invariant.dhamaka.model.Product
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.list_item_product.view.*

@BindingAdapter("productItemImage")
fun ImageView.setRiderItemImage(item: Product?) {
    item?.let { it ->
        /*Glide.with(this)
            .load(BuildConfig.IMAGE_URL + it.imageUrl)
            .placeholder(R.drawable.ic_account_circle_gray_200dp)
            .into(productImageView)*/

        Picasso.get()
            .load(it.imageUrl)
            .placeholder(R.drawable.macbook_pro)
            .into(productImageView)
    }
}

@BindingAdapter("android:rating")
fun setRating(ratingBar: RatingBar, rating: Float) {
    rating.let {
        ratingBar.rating = it
    }
}

/*@BindingAdapter("strikeThroughText")
fun TextView.setStrikeThroughText(textView: TextView, text: String?) {
    textView.text = text
    textView.paintFlags = textView.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
}*/

/*@BindingAdapter("riderImage")
fun CircularImageView.setRiderImage(item: Product?) {
    item?.let { it ->
        Glide.with(this)
            .load(BuildConfig.IMAGE_URL + it.imageUrl)
            .placeholder(R.drawable.ic_account_circle_gray_200dp)
            .into(riderImageView)
    }
}*/

/*@BindingAdapter("updateRiderImage")
fun CircularImageView.updateRiderImage(item: Product?) {
    item?.let { it ->
        Glide.with(this)
            .load(BuildConfig.IMAGE_URL + it.imageUrl)
            .placeholder(R.drawable.ic_account_circle_gray_200dp)
            .into(updateRiderImageView)
    }
}*/
