package com.tutorials.countries.model

import com.google.gson.annotations.SerializedName

data class Country(
    @SerializedName("name") val name: String?,
    @SerializedName("flagPNG") val flagPNG: String?,
    @SerializedName("capital") val capital: String?
//    @SerializedName("alpha2Code") val alpha2Code: String,
//    @SerializedName("alpha3Code") val alpha3Code: String,
//    @SerializedName("altSpellings") val altSpellings: List<String>,
//    @SerializedName("area") val area: Int,
//    @SerializedName("borders") val borders: List<String>,
//    @SerializedName("callingCodes") val callingCodes: List<Int>,
//    @SerializedName("currencies") val currencies: List<Currencies>,
//    @SerializedName("demonym") val demonym: String,
//    @SerializedName("gini") val gini: String,
//    @SerializedName("languages") val languages: List<Languages>,
//    @SerializedName("latlng") val latlng: List<Double>,
//    @SerializedName("nativeName") val nativeName: String,
//    @SerializedName("numericCode") val numericCode: Int,
//    @SerializedName("population") val population: Int,
//    @SerializedName("region") val region: String,
//    @SerializedName("regionalBlocs") val regionalBlocs: List<RegionalBlocs>,
//    @SerializedName("subregion") val subregion: String,
//    @SerializedName("timezones") val timezones: List<String>,
//    @SerializedName("topLevelDomain") val topLevelDomain: List<String>,
//    @SerializedName("translations") val translations: Translations,
//    @SerializedName("cioc") val cioc: String
)

data class Event(val name:String = "")