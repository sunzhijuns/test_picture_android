package com.szh.testpic;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.szh.testpic.beans.Student;

import java.util.List;

/**
 * Created by sunzhijun on 2017/12/19.
 */
public class StudentAdapter extends BaseAdapter{
    private Context mContext;
    private List<Student> mList;
    private LayoutInflater mInflater;

    public StudentAdapter(Context mContext, List<Student> mList) {
        this.mContext = mContext;
        this.mList = mList;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null){
            holder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.adapter_stu_item,parent,false);
            holder.id = convertView.findViewById(R.id.tv_id_adap_stu_item);
            holder.name = convertView.findViewById(R.id.tv_name_adap_stu_item);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        Student student = mList.get(position);
        holder.id.setText(student.getId() + "");
        holder.name.setText(student.getName() + "");

        return convertView;
    }
    static class ViewHolder{
        private TextView id;
        private TextView name;
    }
}
