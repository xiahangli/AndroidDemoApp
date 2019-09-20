package com.example.xia.demo.rx;


/**
 * @User Xiahangli
 * @Date 2018/11/2  14:33
 * @Email xiahangli@qq.com
 * @Descrip
 */
public class OnSubscribleLift<T, R> implements OnSubscrible<R> {
    //好兄弟持有上一个男生的引用
    OnSubscrible<T> onSubscribe;

    private Func1<? super T, ? extends R> transfromer;

    public OnSubscribleLift(OnSubscrible<T> onSubscribe, Func1<? super T, ? extends R> transfromer) {
        this.onSubscribe = onSubscribe;
        this.transfromer = transfromer;
    }


    /**
     * 向看电影的女生
     *
     * @param subscrible
     */
    @Override
    public void call(Subscrible<? super R> subscrible) {
        /**
         * 第一个subscrible为actual,即我们new的Subscrible<Bitmap>()对象
         */
        Subscrible<? super T> wife = new OperaChange<>(subscrible, transfromer);
        onSubscribe.call(wife);
    }


    /**
     * 他老婆
     *
     * @param <T>
     * @param <R>
     */
    class OperaChange<T, R> extends Subscrible<T> {

        Subscrible<? super R> actual;
        private Func1<? super T, ? extends R> transfrom;

        public OperaChange(Subscrible<? super R> actual, Func1<? super T, ? extends R> transfrom) {
            this.actual = actual;
            this.transfrom = transfrom;
        }


        @Override
        public void onNext(T t) {
            /**
             * 自己替换闺蜜,通过回调将T变成R
             * @param t
             */
            R r = this.transfrom.call(t);
            //责任链的使用
            actual.onNext(r);
        }
    }

}
