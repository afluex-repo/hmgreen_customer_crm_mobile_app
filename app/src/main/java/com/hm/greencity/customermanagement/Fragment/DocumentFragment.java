package com.hm.greencity.customermanagement.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.hm.greencity.customermanagement.R;
import com.hm.greencity.customermanagement.adapters.PdfAdapter;
import com.hm.greencity.customermanagement.models.PDF.PdfItem;
import java.util.ArrayList;
import java.util.List;


public class DocumentFragment extends Fragment {

    private RecyclerView recyclerView;
    private PdfAdapter pdfAdapter;
    private List<PdfItem> pdfList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_document, container, false);

        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        int numberOfColumns = 3;
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), numberOfColumns);
        recyclerView.setLayoutManager(gridLayoutManager);


        pdfList = new ArrayList<>();
        pdfList.add(new PdfItem("https://www.w3.org/WAI/ER/tests/xhtml/testfiles/resources/pdf/dummy.pdf", " PDF 1"));
        pdfList.add(new PdfItem("https://www.w3.org/WAI/ER/tests/xhtml/testfiles/resources/pdf/dummy.pdf", " PDF 2"));
        pdfList.add(new PdfItem("https://www.w3.org/WAI/ER/tests/xhtml/testfiles/resources/pdf/dummy.pdf", " PDF 3"));
        pdfList.add(new PdfItem("https://www.w3.org/WAI/ER/tests/xhtml/testfiles/resources/pdf/dummy.pdf", " PDF 4"));
        pdfList.add(new PdfItem("https://www.w3.org/WAI/ER/tests/xhtml/testfiles/resources/pdf/dummy.pdf", " PDF 5"));
        pdfList.add(new PdfItem("https://www.w3.org/WAI/ER/tests/xhtml/testfiles/resources/pdf/dummy.pdf", " PDF 6"));
        pdfList.add(new PdfItem("https://www.w3.org/WAI/ER/tests/xhtml/testfiles/resources/pdf/dummy.pdf", " PDF 7"));
        pdfList.add(new PdfItem("https://www.w3.org/WAI/ER/tests/xhtml/testfiles/resources/pdf/dummy.pdf", " PDF 8"));
        pdfList.add(new PdfItem("https://www.w3.org/WAI/ER/tests/xhtml/testfiles/resources/pdf/dummy.pdf", " PDF 9"));
        pdfList.add(new PdfItem("https://www.w3.org/WAI/ER/tests/xhtml/testfiles/resources/pdf/dummy.pdf", " PDF 10"));
        pdfList.add(new PdfItem("https://www.w3.org/WAI/ER/tests/xhtml/testfiles/resources/pdf/dummy.pdf", " PDF 11"));
        pdfList.add(new PdfItem("https://www.w3.org/WAI/ER/tests/xhtml/testfiles/resources/pdf/dummy.pdf", " PDF 12"));



        pdfAdapter = new PdfAdapter(pdfList, getContext());
        recyclerView.setAdapter(pdfAdapter);


        return view;
    }
}
