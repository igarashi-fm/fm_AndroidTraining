package com.hogehoge.fmandroidtraining;

import android.content.Context;
import android.view.*;
import android.widget.*;
import android.widget.ArrayAdapter;

import java.util.List;


public class CustomAdapter extends ArrayAdapter<String> {

    private LayoutInflater inflater;

    static class ViewHolder {
        Button deleteButton;
        TextView textView;
        CheckBox checkBox;

        public ViewHolder(View v){
            super();
            this.textView = v.findViewById(R.id.text);
            this.deleteButton = v.findViewById(R.id.deleteButton);
            this.checkBox = v.findViewById(R.id.checkBox);
        }
    }

    public CustomAdapter(Context context,int resourceId,  List<String> objects) {
        super(context,resourceId,objects);
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {

        ViewHolder holder;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.list_item, parent, false);

            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        String item = getItem(position);
        holder.textView.setText(item);
        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((ListView) parent).performItemClick(view, position, R.id.checkBox);
            }
        });

        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((ListView) parent).performItemClick(view, position, R.id.deleteButton);

            }
        });

        return convertView;
    }




    @Override
    public long getItemId(int position) {
        return position;
    }
}
