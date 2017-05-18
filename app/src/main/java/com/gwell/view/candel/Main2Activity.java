package com.gwell.view.candel;

import android.animation.Animator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.ArcMotion;
import android.transition.ChangeBounds;
import android.transition.Transition;
import android.util.Log;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.AccelerateInterpolator;
import android.widget.ImageView;

public class Main2Activity extends AppCompatActivity {
   ImageView  imageview;
    Toolbar  toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        toolbar = (Toolbar) findViewById(R.id.view_toolbar);
        setSupportActionBar(toolbar);
        setUpWindowTransition();

    }


    private void animateRevealShow(View viewRoot) {
        int cx = (viewRoot.getLeft() + viewRoot.getRight()) / 2;
        int cy = (viewRoot.getTop() + viewRoot.getBottom()) / 2;
        int finalRadius = Math.max(viewRoot.getWidth(), viewRoot.getHeight());
           /*
           viewRoot 构造一个揭露动画，第一个参数为使用该动画的布局
           cx  动画的中心点，x坐标
           cy,y坐标
           startRadius 开始半径
           finalRadius 结束半径
           ，*/
        Animator anim = ViewAnimationUtils.createCircularReveal(viewRoot, cx, cy, 0, finalRadius);
        viewRoot.setVisibility(View.VISIBLE);
        anim.setDuration(500);
        anim.setInterpolator(new AccelerateInterpolator());
        anim.start();
    }

    private void setUpWindowTransition() {
        final ChangeBounds  ts=new ChangeBounds();
        ts.setPathMotion(new ArcMotion());



        ts.addListener(new Transition.TransitionListener() {
            @Override
            public void onTransitionStart(Transition transition) {


            }

            @Override
            public void onTransitionEnd(Transition transition) {
                ts.removeListener(this);
                findViewById(R.id.imageView6fffffffff).setVisibility(View.GONE);
                findViewById(R.id.tag_view).setVisibility(View.GONE);
                Log.i("onTransitionEnd", "onTransitionEnd");
                animateRevealShow(toolbar);
            }

            @Override
            public void onTransitionCancel(Transition transition) {

            }

            @Override
            public void onTransitionPause(Transition transition) {

            }

            @Override
            public void onTransitionResume(Transition transition) {

            }
        });
        getWindow().setSharedElementEnterTransition(ts);


    }
}
