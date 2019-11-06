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

import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.invariant.dhamaka.BuildConfig
import com.invariant.dhamaka.R
import com.invariant.dhamaka.model.Rider
import com.mikhaellopez.circularimageview.CircularImageView
import kotlinx.android.synthetic.main.list_item_rider.view.*

@BindingAdapter("riderItemImage")
fun CircularImageView.setRiderItemImage(item: Rider?) {
    item?.let { it ->
        Glide.with(this)
            .load(BuildConfig.IMAGE_URL + it.imageUrl)
            .placeholder(R.drawable.ic_account_circle_gray_200dp)
            .into(riderImage)
    }
}

/*@BindingAdapter("riderImage")
fun CircularImageView.setRiderImage(item: Rider?) {
    item?.let { it ->
        Glide.with(this)
            .load(BuildConfig.IMAGE_URL + it.imageUrl)
            .placeholder(R.drawable.ic_account_circle_gray_200dp)
            .into(riderImageView)
    }
}*/

/*@BindingAdapter("updateRiderImage")
fun CircularImageView.updateRiderImage(item: Rider?) {
    item?.let { it ->
        Glide.with(this)
            .load(BuildConfig.IMAGE_URL + it.imageUrl)
            .placeholder(R.drawable.ic_account_circle_gray_200dp)
            .into(updateRiderImageView)
    }
}*/
