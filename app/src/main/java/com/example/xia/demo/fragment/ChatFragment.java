package com.example.xia.demo.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * @User Xiahangli
 * @Date 2018/10/25  14:26
 * @Email xiahangli@qq.com
 * @Descrip 注意handlerThreadp(一种Thread,持有handler,looper)的使用
 */
public class ChatFragment extends BaseFragment {

    private HandlerThread mHandlerThread;

    private ChatHandler mThreadHandler;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mHandlerThread = new HandlerThread("ChatFragmentThread");
        mHandlerThread.start();//启动线程
        mThreadHandler = new ChatHandler(mHandlerThread.getLooper());//注意这里调用looper入参的构造函数,这里的looper是handlerThread对应的looper
//        mRecycleView

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.)
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    //todo 防止内存泄漏
    class ChatHandler extends Handler {

        public ChatHandler(Looper looper) {
            super(looper);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            //todo 处理具体的消息
        }
    }
}
