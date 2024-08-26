package com.hm.greencity.customermanagement.NotePad;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.hm.greencity.customermanagement.R;
import java.util.List;


public class MyAdapter extends ArrayAdapter<String> {
    private final Context context;
    private final List<String> values;

    public MyAdapter(Context context, List<String> values) {
        super(context, R.layout.list_item, values);
        this.context = context;
        this.values = values;

    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.list_item, parent, false);

        TextView textView = rowView.findViewById(R.id.textView);
//        Button openButton = rowView.findViewById(R.id.button);
//        ImageView deleteButton = rowView.findViewById(R.id.delete_button);
//        textView.setText(values.get(position));

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("ITEM", values.get(position));
                context.startActivity(intent);
            }
        });


//        deleteButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(context, "Delete button clicked for item " + values.get(position), Toast.LENGTH_SHORT).show();
//                values.remove(position);
//                notifyDataSetChanged();
//            }
//        });

        return rowView;
    }
}
