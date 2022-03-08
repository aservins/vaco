package com.vaco.stackexchange.mvvm.model

data class Owner(
    var account_id: Int,
    var reputation: Int,
    var user_id: Int,
    var user_type: String,
    var accept_rate: Int,
    var profile_image: String,
    var display_name: String,
    var link: String
)
