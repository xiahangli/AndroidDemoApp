package com.example.xia.demo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;
import android.view.View;

/**
 * @User Xiahangli
 * @Date 2018/10/25  10:45
 * @Email xiahangli@qq.com
 * @Descrip frgment的基础逻辑类,返回，键盘，进度展示等
 */
public class BaseFragment extends Fragment {

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        //ToolBar title 等初始化
        super.onViewCreated(view, savedInstanceState);
    }

    /**
     * 返回操作
     */
    protected void back() {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();//得到frgment
        //得到回退栈中所有fragment个数
        int backStackEntryCount = fragmentManager.getBackStackEntryCount();//栈个数
        int index = backStackEntryCount - 1;
        String tag = "";
        for (; index >= 0; index--) {//从栈顶遍历，找到当前的fragment
            //拿到回退栈中的每一个元素
            FragmentManager.BackStackEntry backStackEntry = fragmentManager.getBackStackEntryAt(index);
            String name = backStackEntry.getName();//得到通过addToBackStack方式add到回退栈中的fragment的名字

            //根据tagm名称拿到对应的fragment
            Fragment fragment = fragmentManager.findFragmentByTag(name);//根据tag找到fragment

            if (fragment.equals(this)) {//todo ==判断是否可行
                tag = name;
                break;
            }
        }

        //弹出fragment并显示
        if (!TextUtils.isEmpty(tag)) {
            fragmentManager.popBackStack(tag, FragmentManager.POP_BACK_STACK_INCLUSIVE);//管理器弹出该fragment及以上的所有fragment
        } else {
            fragmentManager.popBackStack();//cf popBackStackImmediate
//            fragmentManager.popBackStackImmediate();//cf
        }

    }
}
