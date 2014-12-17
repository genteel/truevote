package co.afrolabs.truevotenaija.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import co.afrolabs.truevotenaija.R;
import co.afrolabs.truevotenaija.data.Contestant;

/**
 * Created by MY on 13/12/2014.
 */
public class ContestantsListAdapter extends BaseAdapter {
    private ArrayList<Contestant> listData;

    private LayoutInflater layoutInflater;

    public ContestantsListAdapter(Context context, ArrayList<Contestant> listData) {
        this.listData = listData;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Object getItem(int position) {
        return listData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.listview, null);
            holder = new ViewHolder();
            holder.nameView = (TextView) convertView.findViewById(R.id.nameView);
            holder.imageView = (ImageView) convertView.findViewById(R.id.imageView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.nameView.setText(listData.get(position).getFullName());
        holder.imageView.setImageResource(listData.get(position).getImageId());

        return convertView;
    }

    static class ViewHolder {
        TextView nameView;
        ImageView imageView;
    }
}
