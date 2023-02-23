package com.txwstudio.gcard.data

import com.google.gson.annotations.SerializedName

/**
 * 搜尋 GitHub Repository 的回覆內容。
 *
 * SearchActivitiy.searchRepositories
 */
data class SearchRepoApiModel(
    @SerializedName("total_count") val totalCount: Long = 0,
    @SerializedName("incomplete_results")  val incompleteResults: Boolean = false,
    @SerializedName("items") val items: List<RepoDetails> = listOf()
)

data class RepoDetails(
    @SerializedName("id") val id: Long,
    @SerializedName("node_id") val nodeId: String,
    @SerializedName("full_name") val fullName: String,
    @SerializedName("name") val name: String,
    @SerializedName("owner") val repoOwner: RepoOwner,
    @SerializedName("private") val isPrivate: Boolean,
    @SerializedName("html_url") val htmlUrl: String,
    @SerializedName("description") val description: String,
    @SerializedName("fork") val isFork: Boolean,
    @SerializedName("url") val url: String,
    @SerializedName("created_at") val created_at: String,
    @SerializedName("updated_at") val updated_at: String,
    @SerializedName("pushed_at") val pushed_at: String,
    @SerializedName("homepage") val homepage: String,
    @SerializedName("size") val size: Long,
    @SerializedName("stargazers_count") val stargazers_count: Long,
    @SerializedName("watchers_count") val watchers_count: Long,
    @SerializedName("language") val language: String,
    @SerializedName("forks_count") val forksCount: Long,
    @SerializedName("open_issues_count") val openIssuesCount: Int,
    @SerializedName("master_branch") val master_branch: String,
    @SerializedName("default_branch") val default_branch: String,
    @SerializedName("score") val score: Long,

    @SerializedName("archive_url") val archive_url: String,
    @SerializedName("assignees_url") val assignees_url: String,
    @SerializedName("blobs_url") val blobs_url: String,
    @SerializedName("branches_url") val branches_url: String,
    @SerializedName("clone_url") val clone_url: String,
    @SerializedName("collaborators_url") val collaborators_url: String,
    @SerializedName("comments_url") val comments_url: String,
    @SerializedName("commits_url") val commits_url: String,
    @SerializedName("compare_url") val compare_url: String,
    @SerializedName("contents_url") val contents_url: String,
    @SerializedName("contributors_url") val contributors_url: String,
    @SerializedName("deployments_url") val deployments_url: String,
    @SerializedName("downloads_url") val downloads_url: String,
    @SerializedName("events_url") val events_url: String,
    @SerializedName("forks_url") val forks_url: String,
    @SerializedName("git_commits_url") val git_commits_url: String,
    @SerializedName("git_refs_url") val git_refs_url: String,
    @SerializedName("git_tags_url") val git_tags_url: String,
    @SerializedName("git_url") val git_url: String,
    @SerializedName("hooks_url") val hooks_url: String,
    @SerializedName("issue_comment_url") val issue_comment_url: String,
    @SerializedName("issue_events_url") val issue_events_url: String,
    @SerializedName("issues_url") val issues_url: String,
    @SerializedName("keys_url") val keys_url: String,
    @SerializedName("labels_url") val labels_url: String,
    @SerializedName("languages_url") val languages_url: String,
    @SerializedName("merges_url") val merges_url: String,
    @SerializedName("milestones_url") val milestones_url: String,
    @SerializedName("mirror_url") val mirror_url: String,
    @SerializedName("notifications_url") val notifications_url: String,
    @SerializedName("pulls_url") val pulls_url: String,
    @SerializedName("releases_url") val releases_url: String,
    @SerializedName("ssh_url") val ssh_url: String,
    @SerializedName("stargazers_url") val stargazers_url: String,
    @SerializedName("statuses_url") val statuses_url: String,
    @SerializedName("subscribers_url") val subscribers_url: String,
    @SerializedName("subscription_url") val subscription_url: String,
    @SerializedName("svn_url") val svn_url: String,
    @SerializedName("tags_url") val tags_url: String,
    @SerializedName("teams_url") val teams_url: String,
    @SerializedName("trees_url") val trees_url: String,

    @SerializedName("forks") val forks: Int,
    @SerializedName("open_issues") val openIssues: Int,
    @SerializedName("watchers") val watchers: Int,
    @SerializedName("has_issues") val hasIssues: Boolean,
    @SerializedName("has_projects") val hasProjects: Boolean,
    @SerializedName("has_pages") val hasPages: Boolean,
    @SerializedName("has_wiki") val hasWiki: Boolean,
    @SerializedName("has_downloads") val hasDownloads: Boolean,

    @SerializedName("archived") val isArchived: Boolean,
    @SerializedName("disabled") val isDisabled: Boolean,
    @SerializedName("visibility") val visibility: String,
    @SerializedName("license") val repoLicense: RepoLicense,

    )

data class RepoOwner(
    @SerializedName("avatar_url") val avatar_url: String,
    @SerializedName("events_url") val events_url: String,
    @SerializedName("followers_url") val followers_url: String,
    @SerializedName("following_url") val following_url: String,
    @SerializedName("gists_url") val gists_url: String,
    @SerializedName("gravatar_id") val gravatar_id: String,
    @SerializedName("html_url") val html_url: String,
    @SerializedName("id") val id: Int,
    @SerializedName("login") val login: String,
    @SerializedName("node_id") val node_id: String,
    @SerializedName("organizations_url") val organizations_url: String,
    @SerializedName("received_events_url") val received_events_url: String,
    @SerializedName("repos_url") val repos_url: String,
    @SerializedName("site_admin") val site_admin: Boolean,
    @SerializedName("starred_url") val starred_url: String,
    @SerializedName("subscriptions_url") val subscriptions_url: String,
    @SerializedName("type") val type: String,
    @SerializedName("url") val url: String
)

data class RepoLicense(
    @SerializedName("key") val key: String,
    @SerializedName("name") val name: String,
    @SerializedName("url") val url: String,
    @SerializedName("spdx_id") val spdxId: String,
    @SerializedName("node_id") val nodeId: String,
    @SerializedName("html_url") val htmlUrl: String,
)