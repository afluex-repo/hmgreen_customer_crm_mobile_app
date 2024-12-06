package com.hm.greencity.customermanagement.models.Team;

import com.google.gson.annotations.SerializedName;

public class LstTeam {
    @SerializedName("PK_TeamId")
    public String pK_TeamId;
    @SerializedName("TeamName")
    public String teamName;

    public LstTeam(String pK_TeamId, String teamName) {
        this.pK_TeamId = pK_TeamId;
        this.teamName = teamName;
    }

    public String getpK_TeamId() {
        return pK_TeamId;
    }

    public void setpK_TeamId(String pK_TeamId) {
        this.pK_TeamId = pK_TeamId;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }
}
