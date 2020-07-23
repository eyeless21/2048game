package com.example.a2048app;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class MainThread extends Thread{

    private SurfaceHolder surfaceHolder;
    private GameManager gameManager;
    private int targetFPS = 60;
    private Canvas canvas;
    private boolean running;

    public MainThread(SurfaceHolder surfaceHolder,GameManager gameManager) {
        super();
        this.surfaceHolder = surfaceHolder;
        this.gameManager = gameManager;
    }

    public void setRunning(boolean isRunning)
    {
        running = isRunning;
    }

    public void setSurfaceHolder(SurfaceHolder surfaceHolder)
    {
        this.surfaceHolder = surfaceHolder;
    }

    @Override
    public void run(){
        long startTime,timeMillis,waitTime;
        long totalTime = 0;
        int frameCount = 0;
        long targetTime = 1000/ targetFPS;

        while (running)
        {
            startTime = System.nanoTime();
            canvas = null;
            try{
                canvas = surfaceHolder.lockCanvas();
                synchronized (surfaceHolder){
                    gameManager.update();
                    gameManager.draw(canvas);
                }
            }catch(Exception e)
            {
                e.printStackTrace();
            }finally {
                if(canvas != null){
                    try{
                        surfaceHolder.unlockCanvasAndPost(canvas);
                    }catch(Exception e)
                    {
                        e.printStackTrace();
                    }
                }
            }
            // HANDLES THE CALLING OF THE GAME MANAGER FOR A NEW THREAD
            timeMillis = (System.nanoTime() - startTime)/1000000;
            waitTime = targetTime - timeMillis;
            //Gia na min kanei sinexeia update to thread
            try{
                if(waitTime > 0) {
                    sleep(waitTime);
                }
                }catch(Exception e)
                {
                    e.printStackTrace();
                }
            }
        }

    }


