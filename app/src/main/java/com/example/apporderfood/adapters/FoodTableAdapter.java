package com.example.apporderfood.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.apporderfood.DTO.FoodTableDTO;
import com.example.apporderfood.R;

import java.util.List;

public class ShowFoodTableAdapter extends BaseAdapter {

    private Context mContext;
    private List<FoodTableDTO> mListFoodTableDTO;

    public ShowFoodTableAdapter(Context context, List<FoodTableDTO> listFoodTableDTO) {
        mContext = context;
        mListFoodTableDTO = listFoodTableDTO;
    }

    @Override
    public int getCount() {
        return mListFoodTableDTO != null ? mListFoodTableDTO.size():0;
    }

    @Override
    public Object getItem(int position) {
        return mListFoodTableDTO.get(position);
    }

    @Override
    public long getItemId(int position) {
        return mListFoodTableDTO != null ? mListFoodTableDTO.get(position).getFoodTableId():0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder viewHolder;
        if (view == null){
            view = LayoutInflater.from(mContext).inflate(R.layout.item_food_table, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.mTvNameFoodTable = view.findViewById(R.id.tv_name_table_food);
            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) view.getTag();
        }
        FoodTableDTO foodTableDTO = mListFoodTableDTO.get(position);
        viewHolder.mTvNameFoodTable.setText(foodTableDTO.getFoodTableName());




        return null;
    }

    static class ViewHolder{
        private TextView mTvNameFoodTable;
    }
}
