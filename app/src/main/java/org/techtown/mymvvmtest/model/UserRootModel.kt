package org.techtown.mymvvmtest.model

data class UserRootModel(
    val incomplete_results: Boolean,
    val items: List<UserModel>,
    val total_count: Int
)