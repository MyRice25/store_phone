package vn.name.greenwich.appstorephone.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import vn.name.greenwich.appstorephone.Class.Nav_menu;
import vn.name.greenwich.appstorephone.R;

public class Menu_nav_Adapter extends BaseAdapter {

    List<Nav_menu> array;
    Context context;

    public Menu_nav_Adapter( Context context, List<Nav_menu> array) {
        this.array = array;
        this.context = context;
    }

    @Override
    public int getCount() {
        return array.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    public class ViewHolder{
        TextView name_nav;
        ImageView image_nav;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if (view == null){
            viewHolder = new ViewHolder();
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.item_menu_nav, null);
            viewHolder.name_nav = view.findViewById(R.id.item_name_menu);
            viewHolder.image_nav = view.findViewById(R.id.item_image_menu);
            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.name_nav.setText(array.get(i).getName());
        Glide.with(context).load(array.get(i).getImage()).into(viewHolder.image_nav);
        return view;
    }
}
