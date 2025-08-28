package com.oat.dev.composewithviewmodel.network

import com.google.gson.annotations.SerializedName
import java.util.UUID

data class UserResponse(
    @SerializedName("pull_requests")
    val pullRequest: List<PullRequest> = emptyList()
) {
    data class PullRequest(
        val id: String = UUID.randomUUID().toString(),
        @SerializedName("title")
        val title: String,
        @SerializedName("issue_url")
        val issueUrl: String,
        @SerializedName("repo_name")
        val repoName: String,
        @SerializedName("body")
        val body: String,
        @SerializedName("created_at")
        val createdAT: String
    )
}
