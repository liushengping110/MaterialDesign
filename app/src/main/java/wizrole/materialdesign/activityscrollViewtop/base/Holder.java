package wizrole.materialdesign.activityscrollViewtop.base;

import android.graphics.Bitmap;
import android.view.View;


/**
 * Created by liushengping on 2016/11/27.
 * 何人执笔？
 */

public interface Holder {

    <T extends View> T getView(int rid);

    Holder setText(String result, int rid);

    Holder setResources(int did, int rid);

    Holder setBitmap(Bitmap bitmap, int rid);

    Holder setOnClickListener(View.OnClickListener onClickListener, int rid);

    Holder setImageView(String url, int rid, int drawable);

    Holder setImageView(int d_rid, int rid, int drawable);

    View getHoldView();
    /**隐藏控件*/
    Holder setVilGone(int rid);
    //显示
    Holder setVil(int rid);

}