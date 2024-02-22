package com.hm.greencity.customermanagement.models.ResponseList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LstSite {
    @SerializedName("SiteID")
    @Expose
    private String siteID;
    @SerializedName("SiteName")
    @Expose
    private String siteName;

    public String getSiteID() {
        return siteID;
    }

    public void setSiteID(String siteID) {
        this.siteID = siteID;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

}
