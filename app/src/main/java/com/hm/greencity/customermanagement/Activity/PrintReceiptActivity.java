package com.hm.greencity.customermanagement.Activity;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import com.google.gson.JsonObject;
import com.hm.greencity.customermanagement.Network.RetrofitClient;
import com.hm.greencity.customermanagement.common.BaseActivity;
import com.hm.greencity.customermanagement.databinding.ActivityPrintReceiptBinding;
import com.hm.greencity.customermanagement.models.Notes.GetNote.LstNotepad;
import com.hm.greencity.customermanagement.models.PrintReport.ResponsePrintReport;
import com.hm.greencity.customermanagement.retrofit.ApiServices;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PrintReceiptActivity extends BaseActivity {
    private static final int MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE = 1;
    ActivityPrintReceiptBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivityPrintReceiptBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());
        initView();
        onClickListener();
        requestPermissions();

    }

    @Override
    public void onNoteDelete(LstNotepad note) {

    }

    private void initView() {
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("PK_BookingDetailsId")) {
            String visitorId = intent.getStringExtra("PK_BookingDetailsId");
            PrintData(visitorId);
        }
    }

    private void onClickListener() {
        binding.backarrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PrintReceiptActivity.this, ReceiptActivity2.class));
            }
        });

        binding.saveReceiptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(PrintReceiptActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                    saveReceiptAsPDF();
                } else {
                    Toast.makeText(PrintReceiptActivity.this, "Permission not granted", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void requestPermissions() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE);
        }
    }

    private void saveReceiptAsPDF() {
        Bitmap bitmap = getBitmapFromView(binding.getRoot());
        if (bitmap != null) {
            createPdf(bitmap);
        } else {
            Toast.makeText(this, "Failed to create bitmap", Toast.LENGTH_SHORT).show();
        }
    }

    private Bitmap getBitmapFromView(View view) {
        Bitmap bitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        view.draw(canvas);
        return bitmap;
    }

    private void createPdf(Bitmap bitmap) {
        PdfDocument pdfDocument = new PdfDocument();
        PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(bitmap.getWidth(), bitmap.getHeight(), 1).create();
        PdfDocument.Page page = pdfDocument.startPage(pageInfo);
        Canvas canvas = page.getCanvas();
        canvas.drawBitmap(bitmap, 0, 0, null);
        pdfDocument.finishPage(page);


        File storageDir = getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS);
        if (storageDir != null && !storageDir.exists()) {
            storageDir.mkdirs();
        }

        File customDir = new File(storageDir, "MyCustomDirectory");
        if (!customDir.exists()) {
            customDir.mkdirs();
        }

        File file = new File(getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS) + "/MyCustomDirectory/", "receipt_" + System.currentTimeMillis() + ".pdf");

        try (FileOutputStream out = new FileOutputStream(file)) {
            pdfDocument.writeTo(out);
            Toast.makeText(this, "Receipt saved as PDF: " + file.getAbsolutePath(), Toast.LENGTH_SHORT).show();
            showDownloadNotification(file);
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Failed to save receipt: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        } finally {
            pdfDocument.close();
        }
    }

    private void showDownloadNotification(File file) {
        Uri fileUri = FileProvider.getUriForFile(this, getApplicationContext().getPackageName() + ".fileprovider", file);
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(fileUri, "application/pdf");
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        try {
            startActivity(intent);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(this, "No application available to view PDF", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission granted", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show();
            }
        }
    }



    public void PrintData(String id) {
        showLoading();
        JsonObject object = new JsonObject();
        object.addProperty("PK_BookingDetailsId",Integer.parseInt(id));
        Log.d("req datsa: ",""+object);
        ApiServices apiService = RetrofitClient.getClient().create(ApiServices.class);
        Call<ResponsePrintReport> call = apiService.getPrintReport(object);
        call.enqueue(new Callback<ResponsePrintReport>() {
            @Override
            public void onResponse(Call<ResponsePrintReport> call, Response<ResponsePrintReport> response) {
                int statusCode = response.code();
                Log.d(TAG, "HTTP Status Code: " + statusCode);
                if (response.isSuccessful() && response.body() != null) {
                    hideLoading();
                    ResponsePrintReport resVisitorList = response.body();
                    String apiStatus = resVisitorList.getStatus();
                    String apiMessage = resVisitorList.getMessage();
                    binding.customerid.setText(resVisitorList.getCustomerLoginId());
                    binding.bookingfor.setText(resVisitorList.getpK_BookingDetailsId());
                    binding.receiptno.setText(resVisitorList.getReceiptNo());
                    binding.bank.setText(resVisitorList.getBankName());
                    binding.bankBranch.setText(resVisitorList.getBankBranch());
                    binding.transationno.setText(resVisitorList.getTransactionNo());
                    binding.paidamount.setText(resVisitorList.getPaidAmount());
                    binding.totalAmount.setText(resVisitorList.getTotalPaid());
                    binding.depositeAmount.setText(resVisitorList.getNetPlotAmount());
                    binding.balanceAmount.setText(resVisitorList.getBalanceAmount());
                    binding.bookingdate.setText(resVisitorList.getBookingDate());
                    binding.transactiondate.setText(resVisitorList.getTransactionDate());
                    binding.paymentdate.setText(resVisitorList.getPaymentDate());
                    binding.entrydate.setText(resVisitorList.getEntryDate());
                    binding.address.setText(resVisitorList.getAddress());
                    binding.state.setText(resVisitorList.getState());
                    binding.site.setText(resVisitorList.getSiteName());
                    binding.city.setText(resVisitorList.getCity());
                    binding.sector.setText(resVisitorList.getSectorName());

                    binding.plotno.setText(resVisitorList.getPlotNo());
                    binding.paymentfor.setText(resVisitorList.getReasonOfPayment());
                    binding.bookingfor.setText(resVisitorList.getpK_BookingId());



                    Log.d(TAG, "API Status: " + apiStatus);
                    Log.d(TAG, "API Message: " + apiMessage);

                } else {
                    Log.e(TAG, "Response Error: " + response.message());

                    switch (statusCode) {
                        case 404:
                            Log.e(TAG, "Error: Resource not found.");
                            break;
                        case 500:
                            Log.e(TAG, "Error: Server error.");
                            break;
                        default:
                            Log.e(TAG, "Error: Unexpected status code.");
                            break;
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponsePrintReport> call, Throwable t) {
                Log.e(TAG, "Network Failure: " + t.getMessage());
            }
        });
    }
}
