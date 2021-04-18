import com.google.gson.GsonBuilder
import com.google.gson.annotations.SerializedName
import com.google.gson.reflect.TypeToken
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

object IssueParser {
    private val jsonFile = File("issues.json")

    @JvmStatic
    fun main(args: Array<String>? = null) {
        val type = object : TypeToken<List<Issue>>() {}.type
        GsonBuilder()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
            .create()
            .fromJson<List<Issue>>(jsonFile.reader(), type)
            .forEachIndexed { index, issue ->
                val htmlContent = buildString {
                    appendLine("---")
                    appendLine("layout: post")
                    appendLine("title: ${issue.title}")
                    appendLine("---")
                    appendLine()
                    appendLine()
                    appendLine(issue.body)
                }

                val date = SimpleDateFormat("yyyy-MM-dd").format(issue.createdAt)
                File("_posts/${date}-${issue.number}.html")
                    .writeText(htmlContent)
            }
    }
}

data class Issue(
    @field:SerializedName("assignees")
    val assignees: List<Any>,
    @field:SerializedName("created_at")
    val createdAt: Date,
    @field:SerializedName("title")
    val title: String,
    @field:SerializedName("body")
    val body: String,
    @field:SerializedName("labels_url")
    val labelsUrl: String,
    @field:SerializedName("author_association")
    val authorAssociation: String,
    @field:SerializedName("number")
    val number: Int,
    @field:SerializedName("updated_at")
    val updatedAt: String,
    @field:SerializedName("performed_via_github_app")
    val performedViaGithubApp: Any,
    @field:SerializedName("comments_url")
    val commentsUrl: String,
    @field:SerializedName("active_lock_reason")
    val activeLockReason: Any,
    @field:SerializedName("repository_url")
    val repositoryUrl: String,
    @field:SerializedName("id")
    val id: Int,
    @field:SerializedName("state")
    val state: String,
    @field:SerializedName("locked")
    val locked: Boolean,
    @field:SerializedName("comments")
    val comments: Int,
    @field:SerializedName("closed_at")
    val closedAt: Any,
    @field:SerializedName("url")
    val url: String,
    @field:SerializedName("labels")
    val labels: List<Any>,
    @field:SerializedName("milestone")
    val milestone: Any,
    @field:SerializedName("events_url")
    val eventsUrl: String,
    @field:SerializedName("html_url")
    val htmlUrl: String,
    @field:SerializedName("assignee")
    val assignee: Any,
    @field:SerializedName("user")
    val user: User,
    @field:SerializedName("node_id")
    val nodeId: String,
    @field:SerializedName("pull_request")
    val pullRequest: PullRequest
)

data class User(

    @field:SerializedName("gists_url")
    val gistsUrl: String,
    @field:SerializedName("repos_url")
    val reposUrl: String,
    @field:SerializedName("following_url")
    val followingUrl: String,
    @field:SerializedName("starred_url")
    val starredUrl: String,
    @field:SerializedName("login")
    val login: String,
    @field:SerializedName("followers_url")
    val followersUrl: String,
    @field:SerializedName("type")
    val type: String,
    @field:SerializedName("url")
    val url: String,
    @field:SerializedName("subscriptions_url")
    val subscriptionsUrl: String,
    @field:SerializedName("received_events_url")
    val receivedEventsUrl: String,
    @field:SerializedName("avatar_url")
    val avatarUrl: String,
    @field:SerializedName("events_url")
    val eventsUrl: String,
    @field:SerializedName("html_url")
    val htmlUrl: String,
    @field:SerializedName("site_admin")
    val siteAdmin: Boolean,
    @field:SerializedName("id")
    val id: Int,
    @field:SerializedName("gravatar_id")
    val gravatarId: String,
    @field:SerializedName("node_id")
    val nodeId: String,
    @field:SerializedName("organizations_url")
    val organizationsUrl: String
)

data class PullRequest(
    @field:SerializedName("patch_url")
    val patchUrl: String,
    @field:SerializedName("html_url")
    val htmlUrl: String,
    @field:SerializedName("diff_url")
    val diffUrl: String,
    @field:SerializedName("url")
    val url: String
)