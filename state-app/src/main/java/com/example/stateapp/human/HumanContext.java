package com.example.stateapp.human;

import com.example.stateapp.state.HappyState;
import com.example.stateapp.state.HumanState;

public class HumanContext {
    private HumanState currentState;

    public HumanContext() {
        this.currentState = new HappyState(this);
    }

    public void setState(HumanState state) {
        this.currentState = state;
    }

    public HumanState getState() {
        return currentState;
    }

    public void changeToHappy() {
        currentState.handleHappy();
    }

    public void changeToSad() {
        currentState.handleSad();
    }

    public void changeToSleeping() {
        currentState.handleSleeping();
    }
}
