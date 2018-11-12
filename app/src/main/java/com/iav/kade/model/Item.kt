package com.iav.kade.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Item(@SerializedName("idTeam")
                var teamId: String? = null,

                @SerializedName("idEvent")
                var lagaId: String? = null,

                @SerializedName("strTeam")
                var teamName: String? = null,
                @SerializedName("strEvent")
                var event: String? = null,

                @SerializedName("strHomeTeam")
                var teamHome: String? = null,

                @SerializedName("strAwayTeam")
                var teamAway: String? = null,

                @SerializedName("intHomeScore")
                var scoreHome: String? = null,

                @SerializedName("intAwayScore")
                var scoreAway: String? = null,

                @SerializedName("dateEvent")
                var dateEvent: String? = null,

                @SerializedName("strHomeFormation")
                var homeFormation: String? = null,

                @SerializedName("strAwayFormation")
                var awayFormation: String? = null,

                @SerializedName("strHomeGoalDetails")
                var goalHome: String? = null,

                @SerializedName("strAwayGoalDetails")
                var goalAway: String? = null,

                @SerializedName("strHomeLineupGoalkeeper")
                var keperHome: String? = null,
                @SerializedName("strHomeLineupDefense")
                var defenderHome: String? = null,
                @SerializedName("strHomeLineupMidfield")
                var midleHome: String? = null,
                @SerializedName("strHomeLineupForward")
                var forwardHome: String? = null,

                @SerializedName("strAwayLineupGoalkeeper")
                var keperAway: String? = null,
                @SerializedName("strAwayLineupDefense")
                var defenderAway: String? = null,
                @SerializedName("strAwayLineupMidfield")
                var midleAway: String? = null,
                @SerializedName("strAwayLineupForward")
                var forwardAway: String? = null,

                @SerializedName("intHomeShots")
                var shotsHome: String? = null,
                @SerializedName("intAwayShots")
                var shotsAway: String? = null,

                @SerializedName("idHomeTeam")
                var idHomeTeam: String? = null,

                @SerializedName("idAwayTeam")
                var idAwayTeam: String? = null,

                @SerializedName("strTeamBadge")
                var teamBadge: String? = null,

                @SerializedName("intFormedYear")
                var tahunBerdiri:String? = null,

                @SerializedName("strStadium")
                var lapangan:String? = null,
                @SerializedName("strDescriptionEN")
                var deskripsi:String? = null,

                @SerializedName("idPlayer")
                var idPlayer:String? = null,

                @SerializedName("strHeight")
                var tinggiPlaey:String? = null,

                @SerializedName("strWeight")
                var beratPlayer:String? = null,

                @SerializedName("strPosition")
                var posisiPlayer:String? = null,
                @SerializedName("strPlayer")
                var namaPlayer:String? = null,
                @SerializedName("strThumb")
                var fotoPlayer:String? = null,

                var posisi: String? = null) : Parcelable