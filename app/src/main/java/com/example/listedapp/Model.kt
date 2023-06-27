package com.example.listedapp

import androidx.room.Entity
import com.google.gson.annotations.SerializedName

@Entity(tableName = "data_able")
data class ResponseModel(
    @SerializedName("status") var status: Boolean? = null,
    @SerializedName("statusCode") var statusCode: Int? = null,
    @SerializedName("message") var message: String? = null,
    @SerializedName("support_whatsapp_number") var supportWhatsappNumber: String? = null,
    @SerializedName("extra_income") var extraIncome: Double? = null,
    @SerializedName("total_links") var totalLinks: Int? = null,
    @SerializedName("total_clicks") var totalClicks: Int? = null,
    @SerializedName("today_clicks") var todayClicks: Int? = null,
    @SerializedName("top_source") var topSource: String? = null,
    @SerializedName("top_location") var topLocation: String? = null,
    @SerializedName("startTime") var startTime: String? = null,
    @SerializedName("links_created_today") var linksCreatedToday: Int? = null,
    @SerializedName("applied_campaign") var appliedCampaign: Int? = null,
    @SerializedName("data") var data: Data? = Data()
)

data class Data(
    @SerializedName("recent_links") var recentLinks: ArrayList<RecentLinks> = arrayListOf(),
    @SerializedName("top_links") var topLinks: ArrayList<TopLinks> = arrayListOf(),
    @SerializedName("overall_url_chart") var overallUrlChart: Map<String,String> = HashMap(),
)

data class RecentLinks(
    @SerializedName("url_id") var urlId: Int? = null,
    @SerializedName("web_link") var webLink: String? = null,
    @SerializedName("smart_link") var smartLink: String? = null,
    @SerializedName("title") var title: String? = null,
    @SerializedName("total_clicks") var totalClicks: Int? = null,
    @SerializedName("original_image") var originalImage: String? = null,
    @SerializedName("thumbnail") var thumbnail: String? = null,
    @SerializedName("times_ago") var timesAgo: String? = null,
    @SerializedName("created_at") var createdAt: String? = null,
    @SerializedName("domain_id") var domainId: String? = null,
    @SerializedName("url_prefix") var urlPrefix: String? = null,
    @SerializedName("url_suffix") var urlSuffix: String? = null,
    @SerializedName("app") var app: String? = null
)


data class TopLinks(
    @SerializedName("url_id") var urlId: Int? = null,
    @SerializedName("web_link") var webLink: String? = null,
    @SerializedName("smart_link") var smartLink: String? = null,
    @SerializedName("title") var title: String? = null,
    @SerializedName("total_clicks") var totalClicks: Int? = null,
    @SerializedName("original_image") var originalImage: String? = null,
    @SerializedName("thumbnail") var thumbnail: String? = null,
    @SerializedName("times_ago") var timesAgo: String? = null,
    @SerializedName("created_at") var createdAt: String? = null,
    @SerializedName("domain_id") var domainId: String? = null,
    @SerializedName("url_prefix") var urlPrefix: String? = null,
    @SerializedName("url_suffix") var urlSuffix: String? = null,
    @SerializedName("app") var app: String? = null
)
