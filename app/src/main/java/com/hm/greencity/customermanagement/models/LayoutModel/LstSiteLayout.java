package com.hm.greencity.customermanagement.models.LayoutModel;

import com.google.gson.annotations.SerializedName;

public class LstSiteLayout {
    @SerializedName("SiteName")
    public String siteName;
    @SerializedName("SiteLayout")
    public String siteLayout;

    public LstSiteLayout(String siteName, String siteLayout) {
        this.siteName = siteName;
        this.siteLayout = siteLayout;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public String getSiteLayout() {
        return siteLayout;
    }

    public void setSiteLayout(String siteLayout) {
        this.siteLayout = siteLayout;
    }
}
