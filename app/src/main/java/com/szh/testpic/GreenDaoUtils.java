package com.szh.testpic;

import android.database.sqlite.SQLiteDatabase;

import com.szh.testpic.dao.DaoMaster;
import com.szh.testpic.dao.DaoSession;

/**
 * Created by sunzhijun on 2017/12/19.
 */


public class GreenDaoUtils {

    private DaoMaster.DevOpenHelper mHelper;
    private SQLiteDatabase db;
    private DaoMaster mDaoMaster;
    private DaoSession mDaoSession;

    private static GreenDaoUtils greenDaoUtils;

    private GreenDaoUtils(){}

    public static GreenDaoUtils getSingleTon(){
        if (greenDaoUtils==null){
            greenDaoUtils=new GreenDaoUtils();
        }
        return greenDaoUtils;
    }

    private void initGreenDao(){
        mHelper=new DaoMaster.DevOpenHelper(MyApplication.getApplication(),"test.db",null);
        db=mHelper.getWritableDatabase();
        mDaoMaster=new DaoMaster(db);
        mDaoSession=mDaoMaster.newSession();
    }

    public DaoSession getmDaoSession() {
        if (mDaoMaster==null){
            initGreenDao();
        }
        return mDaoSession;
    }

    public SQLiteDatabase getDb() {
        if (db==null){
            initGreenDao();
        }
        return db;
    }

}