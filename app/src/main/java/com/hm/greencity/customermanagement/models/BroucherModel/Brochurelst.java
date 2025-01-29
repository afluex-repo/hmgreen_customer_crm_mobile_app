package com.hm.greencity.customermanagement.models.BroucherModel;

import com.google.gson.annotations.SerializedName;

public class Brochurelst {
    @SerializedName("SiteName")
    public String siteName;
    @SerializedName("SiteBrochure")
    public String siteBrochure;

    public Brochurelst(String siteName, String siteBrochure) {
        this.siteName = siteName;
        this.siteBrochure = siteBrochure;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public String getSiteBrochure() {
        return siteBrochure;
    }

    public void setSiteBrochure(String siteBrochure) {
        this.siteBrochure = siteBrochure;
    }
}
