package utils;

import events.listener.BallEventListener;
import model.stats.ScoreBoardSnapShot;

public interface ScoreBoard extends BallEventListener {
    ScoreBoardSnapShot getSnapShotOfCompletedOvers();
    ScoreBoardSnapShot latestSnapShot();
}