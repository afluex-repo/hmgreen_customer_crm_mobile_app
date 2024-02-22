package com.hm.greencity.customermanagement.models.ResponseList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LstBlock {

    @SerializedName("BlockID")
    @Expose
    private String blockID;
    @SerializedName("BlockName")
    @Expose
    private String blockName;
    @SerializedName("BlockSiteID")
    @Expose
    private String blockSiteID;

    public String getBlockID() {
        return blockID;
    }

    public void setBlockID(String blockID) {
        this.blockID = blockID;
    }

    public String getBlockName() {
        return blockName;
    }

    public void setBlockName(String blockName) {
        this.blockName = blockName;
    }

    public String getBlockSiteID() {
        return blockSiteID;
    }

    public void setBlockSiteID(String blockSiteID) {
        this.blockSiteID = blockSiteID;
    }
}
