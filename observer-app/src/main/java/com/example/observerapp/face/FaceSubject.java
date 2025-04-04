package com.example.observerapp.face;

public interface FaceSubject {
    void registerObserver(FaceObserver observer);
    void removeObserver(FaceObserver observer);
    void notifyObservers();
}
