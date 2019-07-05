package id.aasumitro.made.utils

import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.module.AppGlideModule

/**
 * Created by A. A. Sumitro on 08/02/19.
 * aasumitro@merahputih.id
 * https://aasumitro.id
 */

@GlideModule
class GlideModule : AppGlideModule()

//val requestOptions = RequestOptions()
//requestOptions.apply{
//    placeholder(R.drawable.ic_cloud_download_gray_24dp)
//    error(R.drawable.ic_broken_image_gray_24dp)
//    centerCrop()
//}
//
//GlideApp.with(this)
//.setDefaultRequestOptions(requestOptions)
//.load(thumbnailUrl+stream.thumbnail)
//.into(thumbnail)