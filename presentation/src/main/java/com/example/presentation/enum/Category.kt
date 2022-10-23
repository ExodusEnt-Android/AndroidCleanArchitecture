package com.example.presentation.enum

import com.example.presentation.R

enum class Category(val viewId: Int, val queryString: String) {
    BUSINESS(R.id.tv_business,"business"),
    ENTERTAINMENT(R.id.tv_entertain,"entertainment"),
    GENERAL(R.id.tv_general,"general"),
    HEALTH(R.id.tv_health,"health"),
    SCIENCE(R.id.tv_science,"science"),
    SPORTS(R.id.tv_sports,"sports"),
    TECHNOLOGY(R.id.tv_technology,"technology")
}