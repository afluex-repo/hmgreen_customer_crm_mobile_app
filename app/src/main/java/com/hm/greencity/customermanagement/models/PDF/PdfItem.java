package com.hm.greencity.customermanagement.models.PDF;

public class PdfItem {
    private String pdfUrl;
    private String title;

    public PdfItem(String pdfUrl, String title) {
        this.pdfUrl = pdfUrl;
        this.title = title;
    }

    public String getPdfUrl() {
        return pdfUrl;
    }

    public String getTitle() {
        return title;
    }
}
