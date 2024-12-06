package com.hm.greencity.customermanagement.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import androidx.annotation.RequiresApi;
import com.google.gson.JsonObject;
import com.hm.greencity.customermanagement.Network.RetrofitClient;
import com.hm.greencity.customermanagement.R;
import com.hm.greencity.customermanagement.common.BaseActivity;
import com.hm.greencity.customermanagement.databinding.ActivityPrintReceiptBinding;
import com.hm.greencity.customermanagement.models.Notes.GetNote.LstNotepad;
import com.hm.greencity.customermanagement.models.PrintReport.ResponsePrintReport;
import com.hm.greencity.customermanagement.retrofit.ApiServices;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;



public class PrintReceiptActivity extends BaseActivity {
    private static final int MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE = 1;
    ActivityPrintReceiptBinding binding;

    private ResponsePrintReport responsePrintReport;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivityPrintReceiptBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());
        initView();
        onClickListener();
    }

    private void initView() {
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("PK_BookingDetailsId")) {
            String visitorId = intent.getStringExtra("PK_BookingDetailsId");
            PrintData(visitorId);
        }
    }

    private void onClickListener() {
        binding.saveReceiptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (responsePrintReport != null) {
                    generatePDF(responsePrintReport);
                } else {
                    Toast.makeText(PrintReceiptActivity.this, "Data not available", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void PrintData(String id) {
        JsonObject object = new JsonObject();
        object.addProperty("PK_BookingDetailsId", Integer.parseInt(id));
        ApiServices apiService = RetrofitClient.getClient().create(ApiServices.class);
        Call<ResponsePrintReport> call = apiService.getPrintReport(object);
        call.enqueue(new Callback<ResponsePrintReport>() {
            @Override
            public void onResponse(Call<ResponsePrintReport> call, Response<ResponsePrintReport> response) {
                if (response.isSuccessful() && response.body() != null) {
                    responsePrintReport = response.body(); // Store response
                    binding.customerid.setText(responsePrintReport.getCustomerLoginId());
                    binding.bookingfor.setText(responsePrintReport.getpK_BookingDetailsId());
                    binding.receiptno.setText(responsePrintReport.getReceiptNo());
                    binding.bank.setText(responsePrintReport.getBankName());
                    binding.bankBranch.setText(responsePrintReport.getBankBranch());
                    binding.transationno.setText(responsePrintReport.getTransactionNo());
                    binding.paidamount.setText(responsePrintReport.getPaidAmount());
                    binding.totalAmount.setText(responsePrintReport.getTotalPaid());
                    binding.depositeAmount.setText(responsePrintReport.getNetPlotAmount());
                    binding.balanceAmount.setText(responsePrintReport.getBalanceAmount());
                    binding.bookingdate.setText(responsePrintReport.getBookingDate());
                    binding.transactiondate.setText(responsePrintReport.getTransactionDate());
                    binding.paymentdate.setText(responsePrintReport.getPaymentDate());
                    binding.entrydate.setText(responsePrintReport.getEntryDate());
                    binding.address.setText(responsePrintReport.getAddress());
                    binding.state.setText(responsePrintReport.getState());
                    binding.site.setText(responsePrintReport.getSiteName());
                    binding.city.setText(responsePrintReport.getCity());
                    binding.sector.setText(responsePrintReport.getSectorName());
                    binding.plotno.setText(responsePrintReport.getPlotNo());
                    binding.paymentfor.setText(responsePrintReport.getReasonOfPayment());
                } else {
                    Log.e("PrintData", "Response Error: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<ResponsePrintReport> call, Throwable t) {
                Log.e("PrintData", "Network Failure: " + t.getMessage());
            }
        });
    }


//    @RequiresApi(Build.VERSION_CODES.KITKAT)
//    public void generatePDF(ResponsePrintReport data) {
//        View receiptView = findViewById(R.id.receiptlayout);
//        PdfDocument pdfDocument = new PdfDocument();
//        PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(1080, 2220, 2).create();  // Set page size
//        PdfDocument.Page page = pdfDocument.startPage(pageInfo);
//        Canvas canvas = page.getCanvas();
//        receiptView.measure(View.MeasureSpec.makeMeasureSpec(pageInfo.getPageWidth(), View.MeasureSpec.EXACTLY),
//                View.MeasureSpec.makeMeasureSpec(pageInfo.getPageHeight(), View.MeasureSpec.EXACTLY));
//        receiptView.layout(0, 0, receiptView.getMeasuredWidth(), receiptView.getMeasuredHeight());
//        receiptView.draw(canvas);
//        pdfDocument.finishPage(page);
//        File file = new File(getExternalFilesDir(null), "Receipt_" + data.getReceiptNo() + ".pdf");
//        try {
//            if (!file.getParentFile().exists()) {
//                file.getParentFile().mkdirs();
//            }
//            pdfDocument.writeTo(new FileOutputStream(file));
//            Toast.makeText(getApplicationContext(), "PDF file generated..", Toast.LENGTH_SHORT).show();
//        } catch (IOException e) {
//            e.printStackTrace();
//            Toast.makeText(getApplicationContext(), "Failed to generate PDF file..", Toast.LENGTH_SHORT).show();
//        }
//        pdfDocument.close();
//    }

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    public void generatePDF(ResponsePrintReport data) {
        View receiptView = findViewById(R.id.receiptlayout);
        PdfDocument pdfDocument = new PdfDocument();
        PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(1080, 2220, 2).create();
        PdfDocument.Page page = pdfDocument.startPage(pageInfo);
        Canvas canvas = page.getCanvas();

        receiptView.measure(View.MeasureSpec.makeMeasureSpec(pageInfo.getPageWidth(), View.MeasureSpec.EXACTLY),
                View.MeasureSpec.makeMeasureSpec(pageInfo.getPageHeight(), View.MeasureSpec.EXACTLY));
        receiptView.layout(0, 0, receiptView.getMeasuredWidth(), receiptView.getMeasuredHeight());
        receiptView.draw(canvas);
        pdfDocument.finishPage(page);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(MediaStore.MediaColumns.DISPLAY_NAME, "Receipt_" + data.getReceiptNo() + ".pdf");
            contentValues.put(MediaStore.MediaColumns.MIME_TYPE, "application/pdf");
            contentValues.put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_DOWNLOADS);
            Uri pdfUri = getContentResolver().insert(MediaStore.Files.getContentUri("external"), contentValues);
            try (OutputStream outputStream = getContentResolver().openOutputStream(pdfUri)) {
                if (outputStream != null) {
                    pdfDocument.writeTo(outputStream);
                    Toast.makeText(getApplicationContext(), "PDF file generated in Downloads", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Failed to save PDF", Toast.LENGTH_SHORT).show();
                }
            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(getApplicationContext(), "Failed to generate PDF file..", Toast.LENGTH_SHORT).show();
            }
        } else {
            File downloadFolder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
            File file = new File(downloadFolder, "Receipt_" + data.getReceiptNo() + ".pdf");

            try {
                if (!downloadFolder.exists()) {
                    downloadFolder.mkdirs();
                }
                pdfDocument.writeTo(new FileOutputStream(file));
                Toast.makeText(getApplicationContext(), "PDF file generated in Downloads", Toast.LENGTH_SHORT).show();
            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(getApplicationContext(), "Failed to generate PDF file..", Toast.LENGTH_SHORT).show();
            }
        }
        pdfDocument.close();
    }


    @Override
    public void onNoteDelete(LstNotepad note) {

    }
}
