package com.xjs.myrecords.base.DataSave;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * create xjs
 * date  2017/7/16
 * description
 */

public class TestSaveData {

    static class SharePreferenceTest{
        private Context mContext;
        public static final String PREFERENCE_FILE_USER = "user";
        public SharePreferenceTest(Context context) {
            this.mContext = context;
        }
        public SharedPreferences getInstance(){
            //private
            return mContext.getApplicationContext().getSharedPreferences(
                    PREFERENCE_FILE_USER,Context.MODE_PRIVATE);
        }
        public boolean saveCommit(String saveDataKey,String saveDataValue) {
            return getInstance().edit().putString(saveDataKey,saveDataValue).commit();
        }
        public void saveApply(String saveDataKey,String saveDataValue) {
            getInstance().edit().putString(saveDataKey,saveDataValue).apply();
        }
    }

    static class DataBaseSQL{

    }
    static class FileSave{

    }
    static class ContentProvide{

    }
    static class InternetSave{

    }

}
