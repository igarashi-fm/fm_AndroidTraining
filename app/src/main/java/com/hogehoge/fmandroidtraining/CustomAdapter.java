package com.hogehoge.fmandroidtraining;

import android.content.Context;
import android.view.*;
import android.widget.*;



abstract class CustomAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private  int resourcedId;
    private String[] items;

    static class Member {
        Button deleteButton;
        TextView textView;
    }

    CustomAdapter(Context context, int resourcedId, String[] items) {
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.resourcedId = resourcedId;
        this.items = items;
    }
    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        Member holder;
        if (convertView == null) {
            convertView = inflater.inflate(resourcedId, parent, false);

            holder = new Member();
            holder.textView = convertView.findViewById(R.id.text);
            holder.deleteButton = convertView.findViewById(R.id.deleteButton);
            convertView.setTag(holder);
        } else {
            holder = (Member) convertView.getTag();
        }

        holder.textView.setText(items[position]);


        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((ListView) parent).performItemClick(view, position, R.id.deleteButton);
            }
        });

        return convertView;
    }

    @Override
    public int getCount() {
        return items.length;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}
