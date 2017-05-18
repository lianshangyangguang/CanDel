package com.gwell.view.candel;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.Slide;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        img =(ImageView) findViewById(R.id.img);
//        img.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                transitionToActivity(Main2Activity.class,img);
//            }
//        });
        //setUpButton();
       // setUpRececlerView();
    }

    private void setUpRececlerView() {
        RecyclerView  recyclerView= (RecyclerView) findViewById(R.id.recleview);
        RecyclerView.LayoutManager  lm=new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(lm);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(new RecyclerView.Adapter() {
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                final View  view=View.inflate(getApplicationContext(),R.layout.test_item,null);
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        Intent  intent=new Intent(MainActivity.this,Main2Activity.class);
//                        startActivity(intent);
                        transitionToActivity(Main2Activity.class,view.findViewById(R.id.imageView2));

                    }
                });
                return new MyViewHodler(view) ;
            }

            @Override
            public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

                MyViewHodler  myViewHodler= (MyViewHodler) holder;
                myViewHodler.textView.setText(String.valueOf(position).concat("nidaye"));
            }

            @Override
            public int getItemCount() {
                return 10;
            }
        });



    }

    class  MyViewHodler  extends RecyclerView.ViewHolder{
        private TextView  textView;
        public MyViewHodler(View itemView) {
            super(itemView);
            textView= (TextView) itemView.findViewById(R.id.textView);
            Random  r=new Random();
              int  i= r.nextInt(10);
            int  col=i*100000+i*i*100;


            textView.setBackgroundColor(col);
        }
    }

    public static Pair<View, String>[] createSafeTransitionParticipants(@NonNull Activity activity,
                                                                        boolean includeStatusBar,View  imageview) {
        // Avoid system UI glitches as described here:
        // https://plus.google.com/+AlexLockwood/posts/RPtwZ5nNebb
        View decor = activity.getWindow().getDecorView();
        View statusBar = null;
        if (includeStatusBar) {
            statusBar = decor.findViewById(android.R.id.statusBarBackground);
        }
        View navBar = decor.findViewById(android.R.id.navigationBarBackground);

        // Create pair of transition participants.
        List<Pair> participants = new ArrayList<>(3);
        addNonNullViewToTransitionParticipants(imageview, participants);
//        addNonNullViewToTransitionParticipants(navBar, participants);
        // only add transition participants if there's at least one none-null element
//        if (otherParticipants != null && !(otherParticipants.length == 1
//                && otherParticipants[0] == null)) {
//            participants.addAll(Arrays.asList(otherParticipants));
//        }


        return participants.toArray(new Pair[participants.size()]);
    }




    private static void addNonNullViewToTransitionParticipants(View view, List<Pair> participants) {
        if (view == null) {
            return;
        }
        participants.add(new Pair<>(view, view.getTransitionName()));
    }


    private void transitionToActivity(Class target,View  imageview) {
        final Pair<View, String>[] pairs = createSafeTransitionParticipants(this,false,imageview);

        Log.i("TSET", "" + Arrays.toString(pairs));
        startActivity(target, pairs);
    }

    private void startActivity(Class target, Pair<View, String>[] pairs) {
        Intent i = new Intent(MainActivity.this, target);
        ActivityOptionsCompat transitionActivityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(this,pairs);

        startActivity(i, transitionActivityOptions.toBundle());
    }



    /**
     *
     * 设置，activity界面支持，左边一次滑出
     * */
    private void setupWindowAnimations() {
        // Re-enter transition is executed when returning to this activity
        Slide slideTransition = new Slide();//滑出
        slideTransition.setSlideEdge(Gravity.LEFT);//滑出的方向
        slideTransition.setInterpolator(new DecelerateInterpolator());
        slideTransition.setDuration(500);//动画持续时间
        getWindow().setReenterTransition(slideTransition);//
        getWindow().setExitTransition(slideTransition);
    }


}
