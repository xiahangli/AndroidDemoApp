package com.example.xia.demo.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.xia.demo.bgservice.UploadTaskDAO;


public class MCMakeDatabaseHelper extends DataBaseHelper {

    /**
     * 数据库名
     */
    private static final String DataBaseName = "microCourseMake";
    /**
     * 当前数据库版本号，每次数据库表结构有改动需增加
     */
    private static final int DataBaseVersion = 1;

    private static  MCMakeDatabaseHelper _instance = null;
    private static final byte[] mLock = new byte[0];

    public final static MCMakeDatabaseHelper getInstance() {
        return _instance;
    }

    public final static boolean createInstance(Context mContext) {
        if (_instance == null) {
            _instance = new MCMakeDatabaseHelper(mContext);
            return true;
        }
        return false;
    }

    public MCMakeDatabaseHelper(Context mContext) {
        super(mContext);
    }

    @Override
    public void destory() {
        super.destory();
        _instance = null;
    }

    @Override
    protected void createDataBase(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL(UploadTaskDAO.getTableCreateSql());
    }

    @Override
    protected void clearDataBase(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        String[] tables = new String[]{
                UploadTaskDAO.NAME
        };
        for (String table : tables) {
            dropTable(db, table);
        }
    }

    @Override
    protected void updateDataBase(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub

    }

    @Override
    protected void downDataBase(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        clearDataBase(db);
        createDataBase(db);
    }

    @Override
    protected String getDataBaseName() {
        // TODO Auto-generated method stub
        return DataBaseName + "_" + "100" + ".db";
    }

    @Override
    protected int getDataBaseVersion() {
        // TODO Auto-generated method stub
        return DataBaseVersion;
    }

}
