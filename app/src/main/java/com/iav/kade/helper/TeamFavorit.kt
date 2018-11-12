package com.iav.kade.helper

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TeamFavorit(val id: Long?,
                       val teamId: String?,
                       val teamImage:String?,val teamName:String?,
                       val tahunBerdiri:String?,
                       val lapangan:String?,val deskripsi:String?) : Parcelable{


    companion object {
        const val TABLE_TEAM: String = "TABLE_TEAM"
        const val ID: String = "ID_"
        const val TEAM_ID: String = "TEAM_ID"
        const val TEAM_IMAGE: String = "TEAM_IMAGE"
        const val TEAM_NAME: String = "TEAM_NAME"
        const val TAHUN_BERDIRI:String = "TAHUNN_BERDIRI"
        const val LAPANGAN:String = "LAPANGAN"
        const val DESKRIPIS:String = "DESKRIPSI"
    }
}

