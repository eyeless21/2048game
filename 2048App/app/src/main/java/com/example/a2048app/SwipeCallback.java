package com.example.a2048app;


public interface SwipeCallback {
    void onSwipe(Direction direction);

    enum Direction {
        LEFT,
        RIGHT,
        UP,
        DOWN
    }
}
