package com.iav.kade.Helper

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Favorite(val id: Long?, val lagaId: String?, val teamHome: String?,
                    val teamAway: String?,val scoreHome: String?,val scoreAway: String?,val fHome : String?,val fAway: String?,
                    val goalHome: String?,val goalAway: String?,val shotHome: String?,val shotAway: String?,val kiperHome: String?,
                    val kiperAway: String?,val defendHome: String?,val defendAway: String?,val midleHome: String?,val midleAway: String?,
                    val forwardHome: String?,val forwardAway: String?,val idHome: String?,val idAway: String?,
                    val tanggal: String?, val teamBadge: String?) : Parcelable {

    companion object {
        const val TABLE_FAVORITE: String = "TABLE_FAVORITE"
        const val ID: String = "ID_"
        const val LAGA_ID: String = "LAGA_ID"
        const val TEAM_HOME: String = "TEAM_HOME"
        const val TEAM_AWAY: String = "TEAM_AWAY"
        const val SCORE_HOME: String = "SCORE_HOME"
        const val SCORE_AWAY: String = "SCORE_AWAY"

        const val FHOME: String = "FHOME"
        const val FAWAY: String = "FAWAY"
        const val GOALHOME: String = "GOALHOME"
        const val GOALAWAY: String = "GOALAWAY"
        const val SHOTHOME: String = "SHOT_HOME"
        const val SHOTAWAY: String = "SHOT_AWAY"
        const val KIPERHOME: String = "KIPERHOME"
        const val KIPER_AWAY: String = "KIPERAWAY"
        const val DEFENDHOME: String = "DEFENDHOME"
        const val DEFEND_AWAY: String = "DEFEND_AWAY"
        const val MIDLEHOME: String = "MIDLEHOME"
        const val MIDLE_AWAY: String = "MIDLE_AWAY"
        const val FORWARDHOME: String = "FORWARDHOME"
        const val FORWARDAWAY: String = "FORWARD_AWAY"
        const val IDHOME: String = "IDHOME"
        const val IDAWAY: String = "ID_AWAY"

        const val TANGGAL: String = "TANGGAL"
        const val TEAM_BADGE: String = "TEAM_BADGE"
    }
}