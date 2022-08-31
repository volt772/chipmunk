package com.apx6.domain.dto

import com.apx6.domain.constants.CmdNetworkKeyTags
import com.google.gson.annotations.SerializedName

data class CmdLocationDoc (

    @field:SerializedName(CmdNetworkKeyTags.ADDRESS_NAME)
    val addressName: String = "",

    @field:SerializedName(CmdNetworkKeyTags.CATEGORY_GROUP_CODE)
    val categoryGroupCode: String = "",

    @field:SerializedName(CmdNetworkKeyTags.CATEGORY_GROUP_NAME)
    val categoryGroupName: String = "",

    @field:SerializedName(CmdNetworkKeyTags.CATEGORY_NAME)
    val categoryName: String = "",

    @field:SerializedName(CmdNetworkKeyTags.DISTANCE)
    val distance: String = "",

    @field:SerializedName(CmdNetworkKeyTags.ID)
    val id: String = "",

    @field:SerializedName(CmdNetworkKeyTags.PHONE)
    val phone: String = "",

    @field:SerializedName(CmdNetworkKeyTags.PLACE_NAME)
    val placeName: String = "",

    @field:SerializedName(CmdNetworkKeyTags.PLACE_URL)
    val placeUrl: String = "",

    @field:SerializedName(CmdNetworkKeyTags.ROAD_ADDRESS_NAME)
    val readAddressName: String = "",

    @field:SerializedName(CmdNetworkKeyTags.X)
    val x: String = "",

    @field:SerializedName(CmdNetworkKeyTags.Y)
    val y: String = "",

)
