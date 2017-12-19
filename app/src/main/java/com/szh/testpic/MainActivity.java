package com.szh.testpic;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.szh.testpic.beans.Student;
import com.szh.testpic.dao.StudentDao;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    StudentDao studentDao;
    private EditText etId;
    private EditText etName;
    private Button btnAdd;
    private Button btnDelete;
    private Button btnQuery;
    private Button btnUpdate;
    private ListView lvShow;
    private StudentAdapter studentAdapter;
    private List<Student> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
        initEvent();
    }

    private void initEvent() {
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //add
                String id = etId.getText().toString();
                String name = etName.getText().toString();
                if (isNotEmpty(id) && isNotEmpty(name)) {
                    QueryBuilder qb = studentDao.queryBuilder();
                    ArrayList<Student> list = (ArrayList<Student>) qb.where(StudentDao.Properties.Id.eq(id)).list();
                    if (list.size() > 0) {
                        Toast.makeText(MainActivity.this, "已经有该id的学生", Toast.LENGTH_SHORT).show();
                    } else {
                        Student student = new Student(Long.valueOf(id), name);
                        studentDao.insert(student);
                        loadAll(studentDao.loadAll());
                        studentAdapter.notifyDataSetChanged();
                        Toast.makeText(MainActivity.this, "插入学生信息成功", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    if (isEmpty(id) && isNotEmpty(name)) {
                        Toast.makeText(MainActivity.this, "id为空", Toast.LENGTH_SHORT).show();
                    }
                    if (isEmpty(name) && isNotEmpty(id)) {
                        Toast.makeText(MainActivity.this, "姓名为空", Toast.LENGTH_SHORT).show();
                    }
                    if (isEmpty(id) && isEmpty(name)) {
                        Toast.makeText(MainActivity.this, "请填写学生信息", Toast.LENGTH_SHORT).show();
                    }

                }
                etId.setText("");
                etName.setText("");
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //delete
                String id = etId.getText().toString();
                if (isNotEmpty(id)) {
                    studentDao.deleteByKey(Long.valueOf(id));

                    QueryBuilder qb = studentDao.queryBuilder();
                    ArrayList<Student> list = (ArrayList<Student>) qb.where(StudentDao.Properties.Id.eq(id)).list();
                    if (list.size() < 1) {
                        Toast.makeText(MainActivity.this, "删除学生数据成功", Toast.LENGTH_SHORT).show();
                        etId.setText("");
                        etName.setText("");
                        loadAll(studentDao.loadAll());
                        studentAdapter.notifyDataSetChanged();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "id为空", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnQuery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //query
                String id = etId.getText().toString();
                if (isNotEmpty(id)) {
                    QueryBuilder qb = studentDao.queryBuilder();
                    ArrayList<Student> list = (ArrayList<Student>) qb.where(StudentDao.Properties.Id.eq(id)).list();
                    if (list.size() > 0) {
                        loadAll(list);
                        studentAdapter.notifyDataSetChanged();
                    } else {
                        mList.clear();
                        Toast.makeText(MainActivity.this, "不存在该学生数据", Toast.LENGTH_SHORT).show();
                    }
                    etId.setText("");
                    etName.setText("");
                } else {
                    Toast.makeText(MainActivity.this, "id为空", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //update
                String id = etId.getText().toString();
                String name = etName.getText().toString();
                if (isNotEmpty(id) && isInteger(id) && isNotEmpty(name)){
                    studentDao.update(new Student(Long.valueOf(id),name));
                    loadAll(studentDao.loadAll());
                    studentAdapter.notifyDataSetChanged();
                    Toast.makeText(MainActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
                }else{
                    if (!isNotEmpty(id)){
                        Toast.makeText(MainActivity.this, "id不能为空", Toast.LENGTH_SHORT).show();
                    }else if (!isInteger(id)){
                        Toast.makeText(MainActivity.this, "id必须为大于零的数字", Toast.LENGTH_SHORT).show();
                    }else{//!isNotEmpty(name)
                        Toast.makeText(MainActivity.this, "name不能为空", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }


    private void initData() {
        studentDao = GreenDaoUtils.getSingleTon().getmDaoSession().getStudentDao();
        mList = new ArrayList<>();
        loadAll(studentDao.loadAll());
        studentAdapter = new StudentAdapter(this,mList);
        lvShow.setAdapter(studentAdapter);
    }

    private void loadAll(List<Student> students) {
        mList.clear();
        for (Student student : students){
            mList.add(student);
        }
    }

    private void initView() {

        etId = findViewById(R.id.et_id);
        etName = findViewById(R.id.et_name);
        btnAdd = findViewById(R.id.btn_insert);
        btnDelete = findViewById(R.id.btn_delete);
        btnQuery = findViewById(R.id.btn_query);
        btnUpdate = findViewById(R.id.btn_update);
        lvShow = findViewById(R.id.lv_show_list);
    }
    private boolean isNotEmpty(String s){
        if (s!=null && !s.equals("")){
            return true;
        }else{
            return false;
        }
    }
    private boolean isEmpty(String s){
        if (isNotEmpty(s)){
            return false;
        }else{
            return true;
        }
    }
    private boolean isInteger(String s){
        if (isEmpty(s)) return false;
        int i = 0,len = s.length();
        if (len < 1) return false;
        if (len > 1){
            if (s.charAt(0) == '0')
                return false;
        }
        for (int j = 0; j < len; j++) {
            if (s.charAt(j) < '0' || s.charAt(j) > '9') return false;
        }
        return true;
    }
}
