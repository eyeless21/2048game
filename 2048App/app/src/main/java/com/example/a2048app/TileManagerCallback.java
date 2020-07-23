package com.example.a2048app;

import android.graphics.Bitmap;

import com.example.a2048app.sprites.Tile;

public interface TileManagerCallback {

    Bitmap getBitmap(int count);
    void finishedMoving(Tile t);
    void updateScore(int delta);
    void reached2048();
}
