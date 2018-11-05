package com.example.xia.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private RecyclerView mRecyclerView;
    private TextView tv_tittle;
    private RecyclerView.OnScrollListener recScrollListener;
    private LinearLayoutManager mLinearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        LinearLayout linearLayout = findViewById(R.id.ll);
//            ImageView iv = new ImageView(this);
//            iv.setImageResource(R.mipmap.world_map);
//            linearLayout.addView(iv);
//        Observable.just()
//        this.startService(new Intent(this, Service.class));
//        startActivity(new Intent(this,ConcreteActivity.class));
//        HttpJuHeMethods.getInstance().showMeTheCode();
//       VerticalProgressBar pro=findViewById(R.id.pro);
//       pro.setMax(100);
//       pro.setProgress(100);
//       pro.setSecondaryProgress(100);
        /**================第二步发起路由跳转==========================*/
//        ARouter.getInstance()
//                .build(RouterConfig.HomePath.INDEX)
//                .withString("name", "888")//携带参数跳转
//                .withInt("age", 11)
//                .navigation();
        /**=================第二步发起路由跳转end=========================*/
//        initView();
//        final Button undo = findViewById(R.id.undo);

//       final GuaGuaKa guaGuaKa = findViewById(R.id.guaguaka);


        /////////////////////////////////////////////
        DisplayMetrics metric = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metric);
        int width = metric.widthPixels; // 屏幕宽度（像素）
        int height = metric.heightPixels; // 屏幕高度（像素）
        float density = metric.density; // 屏幕密度（0.75 / 1.0 / 1.5）
        int densityDpi = metric.densityDpi; // 屏幕密度DPI（120 / 160 / 240）




        /**
         * String 看电影
         *不关心兄弟如何把老婆给他的，他老婆怎么把闺蜜给他的
         * Bitmap 开房
         */
//        undo.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//               //todo
//                Observable.create(new OnSubscrible<String>() {
//                    @Override
//                    public void call(Subscrible<? super String> subscrible) {
//                        //do sth
//                        subscrible.onNext("男生，看电影");
//                    }
//                }).map(new Func1<String, Bitmap>() {
//                    @Override
//                    public Bitmap call(String s) {
//                        Log.i(TAG,s);
//                        Log.i(TAG,"老婆不愿意和你开房");
//                        return BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher);
//                    }
//                }).subscrible(new Subscrible<Bitmap>() {//这个new 的Subscrible是
//                    @Override
//                    public void onNext(Bitmap bitmap) {
//                        Log.i(TAG,"---->开房的女生");
//                    }
//                });
//            }
//        });
//        Button make_trans = findViewById(R.id.make_trans);
//        make_trans.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                guaGuaKa.makeTrans();
//            }
//        });
    }
    private float getDp(int id){
        float dimen = 0;
        String string = this.getResources().getString(id).replace("dip", "");
        dimen = Float.parseFloat(string);
        return dimen;
    }

    public void left(View v ){
    }

    public void right(View view){
        DisplayMetrics metric = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metric);
        int width = metric.widthPixels; // 屏幕宽度（像素）
        int height = metric.heightPixels; // 屏幕高度（像素）
        float density = metric.density; // 屏幕密度（0.75 / 1.0 / 1.5）
        int densityDpi = metric.densityDpi; // 屏幕密度DPI（120 / 160 / 240）


        System.out.println("width=" + width + "height="+height+"dendity="+density+"densitydpi="+densityDpi);
        Toast.makeText(this,"width=" + width + "height="+height+"dendity="+density+"densitydpi="+densityDpi,Toast.LENGTH_LONG).show();

    }
//    private void initView() {
//        final List<String> list = new ArrayList();
//        for (int i = 0; i < 50; i++) {
//            list.add(i + "哈哈哈哈");
//        }
//        mRecyclerView = findViewById(R.id.rec);
//        //设置RecyclerView管理器
//        mLinearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
//        mRecyclerView.setLayoutManager(mLinearLayoutManager);
//        //初始化适配器
//        mAdapter = new MyRecyclerViewAdapter(list, this);
//        // 设置添加或删除item时的动画，这里使用默认动画
//        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
//        // 设置适配器
//        mRecyclerView.setAdapter(mAdapter);
//
//        tv_tittle = findViewById(R.id.tv_tittle);
//        tv_tittle.setText(list.get(0));
//        recScrollListener = new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
//                super.onScrollStateChanged(recyclerView, newState);
//            }
//
//            @Override
//            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//                super.onScrolled(recyclerView, dx, dy);
//                int firstVisiblesItems = mLinearLayoutManager.findFirstVisibleItemPosition();
//                tv_tittle.setText(list.get(firstVisiblesItems));
//            }
//        };
//        mRecyclerView.setOnScrollListener(recScrollListener);
//        MoveToPosition(mLinearLayoutManager, mRecyclerView, 49);
//    }

    /**
     * RecyclerView 移动到当前位置，
     *
     * @param manager       设置RecyclerView对应的manager
     * @param mRecyclerView 当前的RecyclerView
     * @param n             要跳转的位置
     */
    public void MoveToPosition(LinearLayoutManager manager, RecyclerView mRecyclerView, int n) {


        int firstItem = manager.findFirstVisibleItemPosition();
        int lastItem = manager.findLastVisibleItemPosition();
        if (n <= firstItem) {
            mRecyclerView.scrollToPosition(n);
        } else if (n <= lastItem) {
            int top = mRecyclerView.getChildAt(n - firstItem).getTop();
            mRecyclerView.scrollBy(0, top);
        } else {
            mRecyclerView.scrollToPosition(n);
        }

    }
}
