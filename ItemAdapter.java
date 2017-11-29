package rdodenbier.byui.edu.christmaswishlist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by rdodenbier on 11/29/17.
 */

public class ItemAdapter extends BaseAdapter {

    LayoutInflater mInflater;
    Map<String, String> map;
    List<String> itemCategory;
    List<String> itemDetail;

    public ItemAdapter(Context c, Map m) {
        mInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        map = m;
        itemCategory = new ArrayList<String>(map.keySet());
        itemDetail = new ArrayList<String>(map.values());
    }

    @Override
    public int getCount() {

        return map.size();
    }

    @Override
    public Object getItem(int position) {

        return itemCategory.get(position);
    }

    @Override
    public long getItemId(int position) {

        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = mInflater.inflate(R.layout.item_layout, null);
        TextView itemCategoryTextView = (TextView) v.findViewById(R.id.itemCategoryTextView);
        TextView itemDetailTextView = (TextView) v.findViewById(R.id.itemDetailTextView);

        itemCategoryTextView.setText(itemCategory.get(position));
        itemDetailTextView.setText((itemDetail.get(position)));

        return null;
    }
}
