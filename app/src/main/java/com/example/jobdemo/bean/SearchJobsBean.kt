package com.example.jobdemo.bean


import com.google.gson.annotations.SerializedName

data class SearchJobsBean(
    @SerializedName("Count")
    val count: Int,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("timeFlag")
    val timeFlag: String,
    @SerializedName("userInfo")
    val userInfo: List<UserInfo>
) {
    data class UserInfo(
        @SerializedName("AcademicRequirements")
        val academicRequirements: String,
        @SerializedName("Area")
        val area: String,
        @SerializedName("CompanyName")
        val companyName: String,
        @SerializedName("CompanyUrl")
        val companyUrl: String,
        @SerializedName("CreateDateTime")
        val createDateTime: String,
        @SerializedName("DisplayTag")
        val displayTag: String,
        @SerializedName("distance")
        val distance: String,
        @SerializedName("ExperienceYear")
        val experienceYear: String,
        @SerializedName("FromCompanyId")
        val fromCompanyId: String,
        @SerializedName("ID")
        val iD: Int,
        @SerializedName("JobTag")
        val jobTag: String,
        @SerializedName("JobType")
        val jobType: String,
        @SerializedName("Payroll")
        val payroll: String,
        @SerializedName("PostName")
        val postName: String,
        @SerializedName("ServiceTag")
        val serviceTag: String
    )
}