package dev.trindadeaquiles.github.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;

import com.bumptech.glide.Glide;

import com.google.android.material.card.MaterialCardView;
import dev.trindadeaquiles.R;
import dev.trindadeaquiles.databinding.ContentContributorBinding;
import java.util.ArrayList;
import java.util.HashMap;

public class ContributorsAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<HashMap<String, Object>> mData;
    private LayoutInflater mInflater;
    ListView listS;
    private ContentContributorBinding binding;

    public ContributorsAdapter(
            Context contexto, ArrayList<HashMap<String, Object>> dados, ListView listSs) {
        mContext = contexto;
        mData = dados;
        mInflater = LayoutInflater.from(contexto);
        listS = listSs;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public HashMap<String, Object> getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    static class ViewHolder {
        TextView login, bio;
        ImageView avatar;
        MaterialCardView card;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            try {
                convertView = mInflater.inflate(R.layout.content_contributor, parent, false);
                viewHolder = new ViewHolder();
                viewHolder.login = convertView.findViewById(R.id.login);
                viewHolder.avatar = convertView.findViewById(R.id.avatar);
                viewHolder.bio = convertView.findViewById(R.id.bio);
                viewHolder.card = convertView.findViewById(R.id.card);
                convertView.setTag(viewHolder);
                viewHolder.login.setText(mData.get(position).get("login").toString());
                viewHolder.bio.setText(mData.get(position).get("bio").toString());
                Uri url = Uri.parse(mData.get(position).get("avatar-url").toString());
                Glide.with(mContext).load(url).into(viewHolder.avatar);
            } catch (Exception e) {
                // Toast.makeText(mContext, e.toString(), 4000).show();
            }
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        return convertView;
    }
}
