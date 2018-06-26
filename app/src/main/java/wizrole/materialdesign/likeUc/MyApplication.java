package wizrole.materialdesign.likeUc;

import android.app.Application;
import android.content.Context;
import android.util.Log;

/**
 * Created by 何人执笔？ on 2018/6/26.
 * liushengping
 */

public class MyApplication extends Application {
    private static Context mAppContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mAppContext=this;
    }

    public static Context getAppContext() {
        return mAppContext;
    }
}
