package com.example.xia.demo.bgservice;

import android.content.ContentValues;
import android.database.Cursor;

import com.example.xia.demo.db.MCMakeDatabaseHelper;

import java.util.ArrayList;


/**
 * Created by admin on 18/1/30.
 */

public class UploadTaskDAO {
    public final static String NAME = "table_upload_task";

    public final static String COL_ID  = "id";

    public final static String COL_COURSE_NAME = "courseName";

    public final static String COL_COURSE_TOTAL_TIME = "courseTotalTime";

    public final static String COL_COURSE_ID = "courseId";

    public final static String COL_COURSE_FILE_PATH = "courseFilePath";

    public final static String COL_CREATOR = "creator";

    public final static String COL_CREATE_DATE = "createDate";

    public final static String COL_STATUS = "status";

    public final static String COL_DESCRIBE = "describe";

    public final static String COL_POSTIL = "postil";

    public static String getTableCreateSql(){
        String res = "CREATE TABLE IF NOT EXISTS "
                + NAME
                + "("
                + COL_ID + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,"
                + COL_COURSE_ID + " INTEGER UNIQUE,"
                + COL_COURSE_NAME + " VARCHAR,"
                + COL_COURSE_TOTAL_TIME + " INTEGER,"
                + COL_COURSE_FILE_PATH + " VARCHAR,"
                + COL_CREATOR + " VARCHAR,"
                + COL_DESCRIBE + " VARCHAR,"
                + COL_CREATE_DATE + " VARCHAR,"
                + COL_POSTIL + " INTEGER DEFAULT '0',"
                + COL_STATUS + " INTEGER"
                + ")";
        return res;
    }

    private static ContentValues getContentValues(UploadTaskEntity entity){
        ContentValues values = new ContentValues();
        values.put(COL_COURSE_ID,entity.courseId);
        values.put(COL_COURSE_TOTAL_TIME, entity.courseTotalTime);
        values.put(COL_COURSE_NAME, entity.courseName);
        values.put(COL_COURSE_FILE_PATH,entity.courseFilePath);
        values.put(COL_CREATOR, entity.creator);
        values.put(COL_CREATE_DATE, entity.createDate);
        values.put(COL_STATUS,entity.status);
        values.put(COL_DESCRIBE,entity.describe);
        values.put(COL_POSTIL,entity.isPostil);
        return values;
    }

    public static UploadTaskEntity getEntity(Cursor cursor){
        UploadTaskEntity uploadTaskEntity = new UploadTaskEntity();
        uploadTaskEntity.courseId = cursor.getLong(cursor.getColumnIndex(COL_COURSE_ID));
        uploadTaskEntity.courseName = cursor.getString(cursor.getColumnIndex(COL_COURSE_NAME));
        uploadTaskEntity.courseTotalTime = cursor.getLong(cursor.getColumnIndex(COL_COURSE_TOTAL_TIME));
        uploadTaskEntity.courseFilePath = cursor.getString(cursor.getColumnIndex(COL_COURSE_FILE_PATH));
        uploadTaskEntity.creator = cursor.getString(cursor.getColumnIndex(COL_CREATOR));
        uploadTaskEntity.createDate = cursor.getString(cursor.getColumnIndex(COL_CREATE_DATE));
        uploadTaskEntity.status = cursor.getInt(cursor.getColumnIndex(COL_STATUS));
        uploadTaskEntity.describe = cursor.getString(cursor.getColumnIndex(COL_DESCRIBE));
        uploadTaskEntity.isPostil = cursor.getInt(cursor.getColumnIndex(COL_POSTIL));
        return uploadTaskEntity;
    }

    public static boolean add(UploadTaskEntity entity){
        return  MCMakeDatabaseHelper.getInstance().insert(NAME, null, getContentValues(entity)) > 0;
    }

    public static boolean replace(UploadTaskEntity entity){
        return  MCMakeDatabaseHelper.getInstance().replace(NAME, null, getContentValues(entity)) > 0;
    }

    public static boolean update(UploadTaskEntity entity){
        String where = COL_COURSE_ID + "="
                + entity.courseId;
        return MCMakeDatabaseHelper.getInstance().update(NAME, getContentValues(entity), where, null) > 0;
    }

    public static void delByCourseId(Long courseId){
        String where = COL_COURSE_ID + "="
                + courseId;
        MCMakeDatabaseHelper.getInstance().delete(NAME, where, null);
    }


    public static ArrayList<UploadTaskEntity> query(Long userId){
        ArrayList<UploadTaskEntity> entityList = new ArrayList<UploadTaskEntity>();
        Cursor cursor = null;
        try {
            cursor = MCMakeDatabaseHelper.getInstance().query(
                    NAME, null, null, null, null, null, null, null);

            if (cursor != null) {
                while (cursor.moveToNext()) {
                    entityList.add(getEntity(cursor));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
                cursor = null;
            }
        }
        return entityList;
    }

    public static UploadTaskEntity queryByCourseId(Long courseId){
        UploadTaskEntity entity = null;
        Cursor cursor = null;
        try {
            String where = COL_COURSE_ID + "="
                    + courseId;
            cursor = MCMakeDatabaseHelper.getInstance().query(
                    NAME, null, where, null, null, null, null, null);

            if (cursor != null) {
                while (cursor.moveToNext()) {
                    entity = getEntity(cursor);
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
                cursor.close();
                cursor = null;
            }
        }



        return entity;
    }
}
