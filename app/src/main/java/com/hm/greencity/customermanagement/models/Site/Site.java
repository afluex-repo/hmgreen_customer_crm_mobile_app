package com.hm.greencity.customermanagement.models.Site;

public class Site {

        private String siteID;
        private String siteName;

        // Constructor
        public Site(String siteID, String siteName) {
            this.siteID = siteID;
            this.siteName = siteName;
        }
        // Getter for siteID
        public String getSiteID() {
            return siteID;
        }

        // Getter for siteName
        public String getSiteName() {
            return siteName;
        }

        // Override toString() so that the Spinner shows siteName
        @Override
        public String toString() {
            return siteName;
        }
    }


