package com.hm.greencity.customermanagement.models.PrintReport;

import com.google.gson.annotations.SerializedName;

public class ResponsePrintReport {
    @SerializedName("Status")
    public String status;
    @SerializedName("Message")
    public String message;
    @SerializedName("PK_BookingDetailsId")
    public String pK_BookingDetailsId;
    @SerializedName("CompanyName")
    public String companyName;
    @SerializedName("CompanyAddress")
    public String companyAddress;
    @SerializedName("CompanyPin")
    public String companyPin;
    @SerializedName("CompanyState")
    public String companyState;
    @SerializedName("CompanyCity")
    public String companyCity;
    @SerializedName("CompanyContactNo")
    public String companyContactNo;
    @SerializedName("CompanyLandLine")
    public String companyLandLine;
    @SerializedName("CompanyWebsite")
    public String companyWebsite;
    @SerializedName("CompanyEmailID")
    public String companyEmailID;
    @SerializedName("SiteName")
    public String siteName;
    @SerializedName("BookingDate")
    public String bookingDate;
    @SerializedName("ReceiptNo")
    public String receiptNo;
    @SerializedName("CustomerLoginId")
    public String customerLoginId;
    public String customerMobile;
    @SerializedName("CustomerName")
    public String customerName;
    @SerializedName("Address")
    public String address;
    @SerializedName("State")
    public String state;
    @SerializedName("City")
    public String city;
    @SerializedName("Pin")
    public String pin;
    @SerializedName("SectorName")
    public String sectorName;
    @SerializedName("BlockName")
    public String blockName;
    @SerializedName("PlotNo")
    public String plotNo;
    @SerializedName("PlotArea")
    public String plotArea;
    @SerializedName("NetPlotAmount")
    public String netPlotAmount;
    @SerializedName("TotalPaid")
    public String totalPaid;
    @SerializedName("BalanceAmount")
    public String balanceAmount;
    @SerializedName("PaidAmount")
    public String paidAmount;
    @SerializedName("AmountInWords")
    public String amountInWords;
    @SerializedName("PaymentMode")
    public String paymentMode;
    @SerializedName("TransactionNo")
    public String transactionNo;
    @SerializedName("TransactionDate")
    public String transactionDate;
    @SerializedName("BankName")
    public String bankName;
    @SerializedName("BankBranch")
    public String bankBranch;
    @SerializedName("EntryDate")
    public String entryDate;
    @SerializedName("LateCharge")
    public String lateCharge;
    @SerializedName("AssociateID")
    public String associateID;
    @SerializedName("PLCName")
    public String pLCName;
    @SerializedName("Remarks")
    public String remarks;
    @SerializedName("ReciverBank")
    public String reciverBank;
    @SerializedName("PlotNumber")
    public String plotNumber;
    @SerializedName("NetPlotAmountInWords")
    public String netPlotAmountInWords;
    @SerializedName("PLC")
    public String pLC;
    @SerializedName("AssociateName")
    public String associateName;
    @SerializedName("CorporateOffice")
    public String corporateOffice;
    @SerializedName("PaymentDate")
    public String paymentDate;
    @SerializedName("ReasonOfPayment")
    public String reasonOfPayment;
    @SerializedName("Contact")
    public String contact;
    @SerializedName("CustomerFatherName")
    public String customerFatherName;
    @SerializedName("PK_BookingId")
    public String pK_BookingId;

    public ResponsePrintReport(String status, String message, String pK_BookingDetailsId, String companyName, String companyAddress, String companyPin, String companyState, String companyCity, String companyContactNo, String companyLandLine, String companyWebsite, String companyEmailID, String siteName, String bookingDate, String receiptNo, String customerLoginId, String customerMobile, String customerName, String address, String state, String city, String pin, String sectorName, String blockName, String plotNo, String plotArea, String netPlotAmount, String totalPaid, String balanceAmount, String paidAmount, String amountInWords, String paymentMode, String transactionNo, String transactionDate, String bankName, String bankBranch, String entryDate, String lateCharge, String associateID, String pLCName, String remarks, String reciverBank, String plotNumber, String netPlotAmountInWords, String pLC, String associateName, String corporateOffice, String paymentDate, String reasonOfPayment, String contact, String customerFatherName, String pK_BookingId) {
        this.status = status;
        this.message = message;
        this.pK_BookingDetailsId = pK_BookingDetailsId;
        this.companyName = companyName;
        this.companyAddress = companyAddress;
        this.companyPin = companyPin;
        this.companyState = companyState;
        this.companyCity = companyCity;
        this.companyContactNo = companyContactNo;
        this.companyLandLine = companyLandLine;
        this.companyWebsite = companyWebsite;
        this.companyEmailID = companyEmailID;
        this.siteName = siteName;
        this.bookingDate = bookingDate;
        this.receiptNo = receiptNo;
        this.customerLoginId = customerLoginId;
        this.customerMobile = customerMobile;
        this.customerName = customerName;
        this.address = address;
        this.state = state;
        this.city = city;
        this.pin = pin;
        this.sectorName = sectorName;
        this.blockName = blockName;
        this.plotNo = plotNo;
        this.plotArea = plotArea;
        this.netPlotAmount = netPlotAmount;
        this.totalPaid = totalPaid;
        this.balanceAmount = balanceAmount;
        this.paidAmount = paidAmount;
        this.amountInWords = amountInWords;
        this.paymentMode = paymentMode;
        this.transactionNo = transactionNo;
        this.transactionDate = transactionDate;
        this.bankName = bankName;
        this.bankBranch = bankBranch;
        this.entryDate = entryDate;
        this.lateCharge = lateCharge;
        this.associateID = associateID;
        this.pLCName = pLCName;
        this.remarks = remarks;
        this.reciverBank = reciverBank;
        this.plotNumber = plotNumber;
        this.netPlotAmountInWords = netPlotAmountInWords;
        this.pLC = pLC;
        this.associateName = associateName;
        this.corporateOffice = corporateOffice;
        this.paymentDate = paymentDate;
        this.reasonOfPayment = reasonOfPayment;
        this.contact = contact;
        this.customerFatherName = customerFatherName;
        this.pK_BookingId = pK_BookingId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getpK_BookingDetailsId() {
        return pK_BookingDetailsId;
    }

    public void setpK_BookingDetailsId(String pK_BookingDetailsId) {
        this.pK_BookingDetailsId = pK_BookingDetailsId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public String getCompanyPin() {
        return companyPin;
    }

    public void setCompanyPin(String companyPin) {
        this.companyPin = companyPin;
    }

    public String getCompanyState() {
        return companyState;
    }

    public void setCompanyState(String companyState) {
        this.companyState = companyState;
    }

    public String getCompanyCity() {
        return companyCity;
    }

    public void setCompanyCity(String companyCity) {
        this.companyCity = companyCity;
    }

    public String getCompanyContactNo() {
        return companyContactNo;
    }

    public void setCompanyContactNo(String companyContactNo) {
        this.companyContactNo = companyContactNo;
    }

    public String getCompanyLandLine() {
        return companyLandLine;
    }

    public void setCompanyLandLine(String companyLandLine) {
        this.companyLandLine = companyLandLine;
    }

    public String getCompanyWebsite() {
        return companyWebsite;
    }

    public void setCompanyWebsite(String companyWebsite) {
        this.companyWebsite = companyWebsite;
    }

    public String getCompanyEmailID() {
        return companyEmailID;
    }

    public void setCompanyEmailID(String companyEmailID) {
        this.companyEmailID = companyEmailID;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public String getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(String bookingDate) {
        this.bookingDate = bookingDate;
    }

    public String getReceiptNo() {
        return receiptNo;
    }

    public void setReceiptNo(String receiptNo) {
        this.receiptNo = receiptNo;
    }

    public String getCustomerLoginId() {
        return customerLoginId;
    }

    public void setCustomerLoginId(String customerLoginId) {
        this.customerLoginId = customerLoginId;
    }

    public String getCustomerMobile() {
        return customerMobile;
    }

    public void setCustomerMobile(String customerMobile) {
        this.customerMobile = customerMobile;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getSectorName() {
        return sectorName;
    }

    public void setSectorName(String sectorName) {
        this.sectorName = sectorName;
    }

    public String getBlockName() {
        return blockName;
    }

    public void setBlockName(String blockName) {
        this.blockName = blockName;
    }

    public String getPlotNo() {
        return plotNo;
    }

    public void setPlotNo(String plotNo) {
        this.plotNo = plotNo;
    }

    public String getPlotArea() {
        return plotArea;
    }

    public void setPlotArea(String plotArea) {
        this.plotArea = plotArea;
    }

    public String getNetPlotAmount() {
        return netPlotAmount;
    }

    public void setNetPlotAmount(String netPlotAmount) {
        this.netPlotAmount = netPlotAmount;
    }

    public String getTotalPaid() {
        return totalPaid;
    }

    public void setTotalPaid(String totalPaid) {
        this.totalPaid = totalPaid;
    }

    public String getBalanceAmount() {
        return balanceAmount;
    }

    public void setBalanceAmount(String balanceAmount) {
        this.balanceAmount = balanceAmount;
    }

    public String getPaidAmount() {
        return paidAmount;
    }

    public void setPaidAmount(String paidAmount) {
        this.paidAmount = paidAmount;
    }

    public String getAmountInWords() {
        return amountInWords;
    }

    public void setAmountInWords(String amountInWords) {
        this.amountInWords = amountInWords;
    }

    public String getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }

    public String getTransactionNo() {
        return transactionNo;
    }

    public void setTransactionNo(String transactionNo) {
        this.transactionNo = transactionNo;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankBranch() {
        return bankBranch;
    }

    public void setBankBranch(String bankBranch) {
        this.bankBranch = bankBranch;
    }

    public String getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(String entryDate) {
        this.entryDate = entryDate;
    }

    public String getLateCharge() {
        return lateCharge;
    }

    public void setLateCharge(String lateCharge) {
        this.lateCharge = lateCharge;
    }

    public String getAssociateID() {
        return associateID;
    }

    public void setAssociateID(String associateID) {
        this.associateID = associateID;
    }

    public String getpLCName() {
        return pLCName;
    }

    public void setpLCName(String pLCName) {
        this.pLCName = pLCName;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getReciverBank() {
        return reciverBank;
    }

    public void setReciverBank(String reciverBank) {
        this.reciverBank = reciverBank;
    }

    public String getPlotNumber() {
        return plotNumber;
    }

    public void setPlotNumber(String plotNumber) {
        this.plotNumber = plotNumber;
    }

    public String getNetPlotAmountInWords() {
        return netPlotAmountInWords;
    }

    public void setNetPlotAmountInWords(String netPlotAmountInWords) {
        this.netPlotAmountInWords = netPlotAmountInWords;
    }

    public String getpLC() {
        return pLC;
    }

    public void setpLC(String pLC) {
        this.pLC = pLC;
    }

    public String getAssociateName() {
        return associateName;
    }

    public void setAssociateName(String associateName) {
        this.associateName = associateName;
    }

    public String getCorporateOffice() {
        return corporateOffice;
    }

    public void setCorporateOffice(String corporateOffice) {
        this.corporateOffice = corporateOffice;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getReasonOfPayment() {
        return reasonOfPayment;
    }

    public void setReasonOfPayment(String reasonOfPayment) {
        this.reasonOfPayment = reasonOfPayment;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getCustomerFatherName() {
        return customerFatherName;
    }

    public void setCustomerFatherName(String customerFatherName) {
        this.customerFatherName = customerFatherName;
    }

    public String getpK_BookingId() {
        return pK_BookingId;
    }

    public void setpK_BookingId(String pK_BookingId) {
        this.pK_BookingId = pK_BookingId;
    }
}
