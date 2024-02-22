package com.hm.greencity.customermanagement.models.AssociatePlotAvalibility;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AssociateLstAvailability {


    @SerializedName("PlotID")
    @Expose
    private String plotID;
    @SerializedName("SiteID")
    @Expose
    private String siteID;
    @SerializedName("SectorID")
    @Expose
    private String sectorID;
    @SerializedName("BlockID")
    @Expose
    private String blockID;
    @SerializedName("PlotNumber")
    @Expose
    private String plotNumber;
    @SerializedName("PlotStatus")
    @Expose
    private String plotStatus;
    @SerializedName("ColorCSS")
    @Expose
    private String colorCSS;
    @SerializedName("PlotAmount")
    @Expose
    private String plotAmount;
    @SerializedName("PlotArea")
    @Expose
    private String plotArea;
    @SerializedName("PlotSize")
    @Expose
    private String plotSize;
    @SerializedName("SiteName")
    @Expose
    private String siteName;
    @SerializedName("BlockName")
    @Expose
    private String blockName;
    @SerializedName("SectorName")
    @Expose
    private String sectorName;

    public String getPlotID() {
        return plotID;
    }

    public void setPlotID(String plotID) {
        this.plotID = plotID;
    }

    public String getSiteID() {
        return siteID;
    }

    public void setSiteID(String siteID) {
        this.siteID = siteID;
    }

    public String getSectorID() {
        return sectorID;
    }

    public void setSectorID(String sectorID) {
        this.sectorID = sectorID;
    }

    public String getBlockID() {
        return blockID;
    }

    public void setBlockID(String blockID) {
        this.blockID = blockID;
    }

    public String getPlotNumber() {
        return plotNumber;
    }

    public void setPlotNumber(String plotNumber) {
        this.plotNumber = plotNumber;
    }

    public String getPlotStatus() {
        return plotStatus;
    }

    public void setPlotStatus(String plotStatus) {
        this.plotStatus = plotStatus;
    }

    public String getColorCSS() {
        return colorCSS;
    }

    public void setColorCSS(String colorCSS) {
        this.colorCSS = colorCSS;
    }

    public String getPlotAmount() {
        return plotAmount;
    }

    public void setPlotAmount(String plotAmount) {
        this.plotAmount = plotAmount;
    }

    public String getPlotArea() {
        return plotArea;
    }

    public void setPlotArea(String plotArea) {
        this.plotArea = plotArea;
    }

    public String getPlotSize() {
        return plotSize;
    }

    public void setPlotSize(String plotSize) {
        this.plotSize = plotSize;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public String getBlockName() {
        return blockName;
    }

    public void setBlockName(String blockName) {
        this.blockName = blockName;
    }

    public String getSectorName() {
        return sectorName;
    }

    public void setSectorName(String sectorName) {
        this.sectorName = sectorName;
    }


}
