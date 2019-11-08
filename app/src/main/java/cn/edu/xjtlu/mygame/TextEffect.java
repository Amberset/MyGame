package cn.edu.xjtlu.mygame;

import android.widget.TextView;

public class TextEffect {
    private static TextView tv;
    private static String s;
    private static long time;
    private static int length;
    private static int index;


    public TextEffect(TextView tv, String s, long time) throws InterruptedException {
        this.tv = tv;
        this.s = s;
        this.time = time;
        length = s.length();
        tvThread();
    }


    public void tvThread() throws InterruptedException {
        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        try {
                            final String sub = s.substring(0,index);
                            tv.post(new Runnable() {
                                @Override
                                public void run() {
                                    tv.setText(sub);
                                }
                            });
                            Thread.sleep(time);
                            index++;
                            if (index <= length) {
                                tvThread();
                            }
                        } catch(InterruptedException e){
                            e.printStackTrace();
                        }
                    }
                }
        ).start();
    }
}