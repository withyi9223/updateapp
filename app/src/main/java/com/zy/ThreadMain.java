package com.zy;

/**
 * ━━━━━━神兽出没━━━━━━
 * 　　　┏┓　　　┏┓
 * 　　┏┛┻━━━┛┻┓
 * 　　┃　　　　　　　┃
 * 　　┃　　　━　　　┃
 * 　　┃　┳┛　┗┳　┃
 * 　　┃　　　　　　　┃
 * 　　┃　　　┻　　　┃
 * 　　┃　　　　　　　┃
 * 　　┗━┓　　　┏━┛Code is far away from bug with the animal protecting
 * 　　　　┃　　　┃    神兽保佑,代码无bug
 * 　　　　┃　　　┃
 * 　　　　┃　　　┗━━━┓
 * 　　　　┃　　　　　　　┣┓
 * 　　　　┃　　　　　　　┏┛
 * 　　　　┗┓┓┏━┳┓┏┛
 * 　　　　　┃┫┫　┃┫┫
 * 　　　　　┗┻┛　┗┻┛
 * ━━━━━━感觉萌萌哒━━━━━━
 * <p>
 * Created by yi on 2019/12/19.
 */
public class ThreadMain extends UserTest<PresenterTest> {

    
    private PresenterTest getPresenter(){
        return Utest;
    }
    
    public static void main(String[] args) {

        /*ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                3, 10, 10, TimeUnit.SECONDS,
                new LinkedBlockingDeque<Runnable>(50));

        ExecutorService fixedThreadPool= Executors.newFixedThreadPool(3);
        ExecutorService singleThreadExecutor=Executors.newSingleThreadExecutor();
        ExecutorService cachedThreadPool=Executors.newCachedThreadPool();
        for (int i = 0; i < 30; i++) {
            final int num = i;
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    try {

                        Thread.sleep(2000);
                        System.out.println("run:" + num + "当前线程：" + Thread.currentThread().getName());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };
            cachedThreadPool.execute(runnable);
        }*/
        new ThreadMain().getPresenter().request();
        new ThreadMain().getPresenter().getData();
        
        /*try {
            PresenterTest presenterTest = PresenterTest.class.newInstance();
            presenterTest.getData();
            presenterTest.request();
        } catch (IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }*/

    }

}
