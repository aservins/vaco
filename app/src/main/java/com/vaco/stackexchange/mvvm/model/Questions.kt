package com.vaco.stackexchange.mvvm.model

data class Questions(
    var tags : ArrayList<String> = ArrayList(),
    val owner : Owner,
    var is_answered: Boolean,
    var view_count: Int,
    var answer_count: Int,
    var score: Int,
    var last_activity_date: Int,
    var creation_date: Long,
    var question_id: Int,
    var content_license: String,
    var link: String,
    var title: String
)