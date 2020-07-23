package com.example.a2048app;

public interface GameManagerCallback {
    void gameOver();
    void updateScore(int delta);
    void reached2048();
}
