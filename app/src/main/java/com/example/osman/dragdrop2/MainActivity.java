package com.example.osman.dragdrop2;

import android.content.ClipData;
import android.content.ClipDescription;
import android.graphics.drawable.Drawable;
import android.support.constraint.solver.widgets.ConstraintTableLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsoluteLayout;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity
{
    ImageView img;
    String msg;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//create private class MyTouchListener
        findViewById(R.id.imageView).setOnTouchListener(new MyTouchListener());
//Create private class MyDragListner
        findViewById(R.id.rel).setOnDragListener(new MyDragListener());



    }


    private final class MyTouchListener implements View.OnTouchListener
    {
//view = imageview
        public boolean onTouch(View view, MotionEvent motionEvent)
        {
            //if clicked or tab
            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN)
            {
                ClipData data = ClipData.newPlainText("jawad", "jawad");
                //seen viewing objects
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
                view.startDrag(data, shadowBuilder, view, 0);
                view.setVisibility(View.VISIBLE);
                return true;
            }
            else
            {
                return false;
            }
        }

    }


    class MyDragListener implements View.OnDragListener
    {
        @Override
        //v=rel rekative view
        public boolean onDrag(View v, DragEvent event)
        {
            int a;
            int action = event.getAction();
            switch (event.getAction())
            {
                case DragEvent.ACTION_DRAG_STARTED:
                    // do nothing
                    a=10;
                    break;

                case DragEvent.ACTION_DRAG_ENTERED:
                    a=20;
                    break;

                case DragEvent.ACTION_DRAG_EXITED:
                    a=30;
                    break;

                case DragEvent.ACTION_DROP:
                    a=40;
                    // Dropped, reassign View to ViewGroup
                    View view = (View) event.getLocalState();
                    ViewGroup owner = (ViewGroup) view.getParent();
                    owner.removeView(view);
//v=rel;
                    RelativeLayout container = (RelativeLayout) v;
//pick coordinates
                    int    x_cord = (int) event.getX();
                    int    y_cord = (int) event.getY();
//target where to place
                    view.setX(x_cord);
                    view.setY(y_cord);
                    //where to add it should be in container
                    container.addView(view);
                    view.setVisibility(View.VISIBLE);
                    break;

                case DragEvent.ACTION_DRAG_ENDED:
                    break;

                default:
                    break;
            }
            return true;
        }
    }
}

