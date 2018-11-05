package com.example.xia.demo.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public abstract class DataBaseHelper {

	protected MySQLiteOpenHelper mOpenHelper;
	
	protected SQLiteDatabase mDatabase;
	
	private String TAG = "dataBaseHelper";
	
	protected static final byte[] mlocker = new byte[0];
	
	public DataBaseHelper(Context mContext){
		if(mOpenHelper == null){
			mOpenHelper = new MySQLiteOpenHelper(mContext, getDataBaseName(), getDataBaseVersion());
		}
		openDataBase();
	}
	
	/**
	 * 初始化数据库脚本
	 * @param db
	 */
	protected abstract void createDataBase(SQLiteDatabase db);
	
	/**
	 * 清除数据库脚本
	 * @param db
	 */
	protected abstract void clearDataBase(SQLiteDatabase db);
	
	/**
	 * 升级数据库脚本
	 * @param db
	 * @param oldVersion
	 * @param newVersion
	 */
	protected abstract void updateDataBase(SQLiteDatabase db, int oldVersion, int newVersion);

	/**
	 * 降级数据库脚本
	 * @param db
	 * @param oldVersion
	 * @param newVersion
	 */
	protected abstract void downDataBase(SQLiteDatabase db, int oldVersion, int newVersion);
	
	/**
	 * 获取数据库名称
	 * @return
	 */
	protected abstract String getDataBaseName();
	
	/**
	 * 获取数据库版本
	 * @return
	 */
	protected abstract int getDataBaseVersion();
	
	/**
	 * 打开数据库连接（如果已打开，先关闭再打开）
	 */
	public void openDataBase() {
        synchronized (mlocker) {
            try {
            	opendatabase();
            } catch (Exception e) {
//            	LogUtils.e(TAG + "    openDataBase", e);
                try {
                    Thread.sleep(1500);
                	opendatabase();
                } catch (Exception e2) {
//                	LogUtils.e(TAG + "    openDataBase again", e2);
                }
            }
        }
    }
	
	public void destory(){
		closeDataBase();
		mOpenHelper.close();
		mOpenHelper = null;
	}
	
	private void opendatabase() {
		if (mDatabase != null && mDatabase.isOpen()) {
			mDatabase.close();
        }
		mDatabase = mOpenHelper.getWritableDatabase();
	}
	
	/**
	 * 关闭数据库连接
	 */
	public void closeDataBase() {
		try {
            if (mDatabase != null && mDatabase.isOpen()) {
            	mDatabase.close();
            }
        } catch (Exception e) {
//        	LogUtils.e(TAG + " closeDataBase", e);
        }
    }
	
	/**
	 * 尝试打开数据库（如果已打开，不关闭直接返回）
	 */
	protected synchronized void tryOpenDataBase() {
		try {
			if (mDatabase == null || !mDatabase.isOpen()) {
				mDatabase = mOpenHelper.getWritableDatabase();
	        }
		} catch (Exception e) {
//			LogUtils.e(TAG + " tryOpenDataBase", e);
		}
	}
	
	/**
	 * 插入数据
	 * @param table 要插入数据的表的名称
	 * @param nullColumnHack 当values参数为空或者里面没有内容的时候，我们insert是会失败的（底层数据库不允许插入一个空行），为了防止这种情况，我们要在这里指定一个 列名，到时候如果发现将要插入的行为空行时，就会将你指定的这个列名的值设为null，然后再向数据库中插入。
	 * @param values 一个ContentValues对象，类似一个map.通过键值对的形式存储值。
	 * @return
	 */
    public synchronized long insert(String table, String nullColumnHack, ContentValues values) {
        tryOpenDataBase();
		try {
			return mDatabase.insert(table, nullColumnHack, values);
		}catch (Exception e){
			return -1;
		}
    }
    
    /**
	 * 插入或更新数据
	 * @param table 要插入数据的表的名称
	 * @param nullColumnHack 当values参数为空或者里面没有内容的时候，我们insert是会失败的（底层数据库不允许插入一个空行），为了防止这种情况，我们要在这里指定一个 列名，到时候如果发现将要插入的行为空行时，就会将你指定的这个列名的值设为null，然后再向数据库中插入。
	 * @param values 一个ContentValues对象，类似一个map.通过键值对的形式存储值。
	 * @return
	 */
    public synchronized long replace(String table, String nullColumnHack, ContentValues values){
    	tryOpenDataBase();
		try {
			return mDatabase.replace(table, nullColumnHack, values);
		}catch (Exception e){
			return -1;
		}
    }
    
    /**
     * 执行SQL
     * @param sql 执行语句
     */
    public synchronized void execSQL(String sql){
    	 tryOpenDataBase();
		try{
			mDatabase.execSQL(sql);
		} catch (Exception e){

		}
	}

    /**
     * 更新数据
     * @param table 要更新的表名
     * @param values 一个ContentValues对象，类似一个map.通过键值对的形式存储值。
     * @param whereClause 更新的条件，为一个字符串。如果为null，则所有行都将更新
     * @param whereArgs 字符串数组，和whereClause配合使用。有两种用法，如果whereClause的条件已经直接给出，如“class = “ + num，num是传入的参数，则whereArgs可设为null。如果是”class = ？“，则？会被whereArgs这个数组中对应的值替换，whereArgs给出？代表的值，有多个？的，字符串数组里的值依次填入。
     * @return
     */
    public synchronized int update(String table, ContentValues values, String whereClause, String[] whereArgs) {
		tryOpenDataBase();
		try {
        	return mDatabase.update(table, values, whereClause, whereArgs);
		}catch (Exception e){
			return -1;
		}
    }
    

	/**
	 * 删除数据
	 * @param table 要删除的表名
	 * @param whereClause 删除的条件，为一个字符串。如果为null，则所有行都将删除；
	 * @param whereArgs 字符串数组，和whereClause配合使用。有两种用法，如果whereClause的条件已经直接给出，如“class = “ + num，num是传入的参数，则whereArgs可设为null。如果是”class = ？“，则？会被whereArgs这个数组中对应的值替换，whereArgs给出？代表的值，有多个？的，字符串数组里的值依次填入。
	 * @return
	 */
    public synchronized int delete(String table, String whereClause, String[] whereArgs) {
    	tryOpenDataBase();
		try {
			return mDatabase.delete(table, whereClause, whereArgs);
		}catch (Exception e){
			return -1;
		}
	}
    
    /**
     * 执行查询
     * @param table 要查询的表名
     * @param columns 想要显示的列，若为空则返回所有列，不建议设置为空，如果不是返回所有列
     * @param selection where子句，声明要返回的行的要求，如果为空则返回表的所有行。
     * @param selectionArgs 字符串数组，和selection配合使用。有两种用法，如果selection的条件已经直接给出，如“class = “ + num，num是传入的参数，则selectionArgs可设为null。如果是”class = ？“，则？会被selectionArgs这个数组中对应的值替换，selectionArgs给出？代表的值，有多个？的，字符串数组里的值依次填入。
     * @param groupBy 分组方式，若为空则不分组.
     * @param having having条件，若为空则返回全部（不建议）
     * @param orderBy 排序方式，为空则为默认排序方式
     * @param limit 限制返回的记录的条数，为空则不限制
     * @return
     */
    public synchronized Cursor query(String table, String[] columns, String selection, String[] selectionArgs,
                                     String groupBy, String having, String orderBy, String limit) {
    	tryOpenDataBase();
		try {
        	return mDatabase.query(table, columns, selection, selectionArgs, groupBy, having, orderBy,limit);
		}catch (Exception e){
			return null;
		}
    }
    
    /**
     * 执行查询
     * @param sql 执行语句
     */
    public synchronized Cursor rawQuery(String sql){
    	tryOpenDataBase();
		try {
			return mDatabase.rawQuery(sql, null);
		}catch (Exception e){
			return null;
		}
    }
    
	/**
	 * 删除表并重置索引为0
	 * @param db
	 * @param tableName
	 */
	protected synchronized void dropTable(SQLiteDatabase db, String tableName) {
		String dropSql = "DROP TABLE IF EXISTS %s ;";
        String resetSql = "UPDATE sqlite_sequence SET seq=0 WHERE name='%s';";

        db.execSQL(String.format(dropSql, tableName));
        db.execSQL(String.format(resetSql, tableName));
	}
	
	/**
	 * 数据库是否打开
	 * @return
	 */
	public boolean isOpen() {
		return mDatabase != null && mDatabase.isOpen();
	}
	
	private class MySQLiteOpenHelper extends SQLiteOpenHelper {
		public MySQLiteOpenHelper(Context context, String name, int version) {
			super(context, name, null, version);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub
			try {
				createDataBase(db);
			} catch (Exception e) {
//				LogUtils.e(TAG + " create", e);
			}
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			try {
				updateDataBase(db, oldVersion, newVersion);
			} catch (Exception e) {
//				LogUt/ils.e(TAG + " upgrade", e);
				try {
					clearDataBase(db);
					createDataBase(db);
				} catch (Exception ex) {
//					LogUtils.e(TAG + " upgrade again %s", ex);
				}
			}
		}
		
		@Override
		public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			try {
				downDataBase(db, oldVersion, newVersion);
			} catch (Exception e) {
//				LogUtils.e(TAG + " downgrade", e);
				try {
					clearDataBase(db);
					createDataBase(db);
				} catch (Exception ex) {
//					LogUtils.e(TAG + " downgrade again", ex);
				}
			}
		}
	}
}
