package com.example.a2048app.sprites;

import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.example.a2048app.R;

public class Score implements Sprite {

    private static final String SCORE_PREF = "Score pref";

    private Resources resources;
    private int screenWidth, screenHeight, standrardSize;
    private Bitmap bmpScore, bmpTopScore;
    private Bitmap bmpTopScoreBonus,bmp2048Bonus;
    private int score, topScore;
    private SharedPreferences prefs;
    private Paint paint;
    private boolean topScoreBonus = false;
    private boolean a2048Bonus = false;

    public Score(Resources resources, int screenWidth, int screenHeight, int standrardSize, SharedPreferences prefs) {

        this.resources = resources;
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.standrardSize = standrardSize;
        this.prefs = prefs;

        topScore = prefs.getInt(SCORE_PREF, 0);
        int width = (int) resources.getDimension(R.dimen.score_label_width);
        int height = (int) resources.getDimension(R.dimen.score_label_height);

        Bitmap sc = BitmapFactory.decodeResource(resources, R.drawable.score);
        bmpScore = Bitmap.createScaledBitmap(sc, width, height, false);

        Bitmap tsc = BitmapFactory.decodeResource(resources, R.drawable.topscore);
        bmpTopScore = Bitmap.createScaledBitmap(tsc, width, height, false);
        paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.FILL);
        paint.setTextSize(resources.getDimension(R.dimen.score_text_size));
    }


    //Draw ta Bitmaps
    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(bmpScore, screenWidth / 4 - bmpScore.getWidth() / 2, bmpScore.getHeight(), null);
        canvas.drawBitmap(bmpTopScore, 3 * screenWidth / 4 - bmpTopScore.getWidth() / 2, bmpTopScore.getHeight(), null);

        int width1 = (int) paint.measureText(String.valueOf(score));
        int width2 = (int) paint.measureText(String.valueOf(topScore));
        canvas.drawText(String.valueOf(topScore), screenWidth / 4 - width1 / 2, bmpScore.getHeight() * 4, paint);
        canvas.drawText(String.valueOf(topScore), 3 * screenWidth / 4 - width2 / 2, bmpTopScore.getHeight() * 4, paint);

        if(topScoreBonus){
            canvas.drawBitmap(bmpTopScoreBonus, screenWidth/2 - 2*standrardSize,screenHeight/2-2*standrardSize - 2 * bmpTopScoreBonus.getHeight(),null);
        }

        if(a2048Bonus){
            canvas.drawBitmap(bmp2048Bonus,screenWidth/2 - 2*standrardSize,screenHeight/2-2*standrardSize - 4 * bmp2048Bonus.getHeight(),null );
        }
    }

    @Override
    public void update() {

    }

    //method to update the score during game
    public void updateScore(int delta) {
        score += delta;
        checkTopScore();
    }

    //We update the topscore during the game when the current score surpasses it.
    public void checkTopScore() {
        topScore = prefs.getInt(SCORE_PREF, 0);
        if (topScore < score) {
            prefs.edit().putInt(SCORE_PREF, score).apply();
            topScore = score;

            int width = (int)resources.getDimension(R.dimen.score_bonus_width);
            int height = (int) resources.getDimension(R.dimen.score_bonus_height);
            Bitmap tsb = BitmapFactory.decodeResource(resources,R.drawable.highscore);
            bmpTopScoreBonus = Bitmap.createScaledBitmap(tsb, width,height,false);

            topScoreBonus = true;
        }
    }

    public void reached2048(){
        int width = (int)resources.getDimension(R.dimen.score_bonus_width);
        int height = (int) resources.getDimension(R.dimen.score_bonus_height);
        Bitmap bmp = BitmapFactory.decodeResource(resources,R.drawable.a2048);
       bmp2048Bonus = Bitmap.createScaledBitmap(bmp, width,height,false);
        a2048Bonus = true;
    }
}
