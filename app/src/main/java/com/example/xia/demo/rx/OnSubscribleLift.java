package com.example.xia.demo.rx;


/**
 * @User Xiahangli
 * @Date 2018/11/2  14:33
 * @Email xiahangli@qq.com
 * @Descrip
 */
public class OnSubscribleLift<T, R> implements OnSubscrible<R> {
    OnSubscrible<T> boy;

    private Func1<? super T, ? extends R> transfromer;

    public OnSubscribleLift(OnSubscrible<T> boy, Func1<? super T, ? extends R> transfromer) {
        this.boy = boy;
        this.transfromer = transfromer;
    }


    /**
     * 向看电影的女生
     *
     * @param subscrible
     */
    @Override
    public void call(Subscrible<? super R> subscrible) {
        Subscrible<? super T> wife = new OperaChange<>(subscrible, transfromer);
        boy.call(wife);
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
             * 自己替换闺蜜
             * @param t
             */
            R r = this.transfrom.call(t);
            actual.onNext(r);
        }
    }

}
