package com.gptm.app.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ScoreTrackMap {

    private static ScoreTrackMap scoreTrackMap;
    private List<HashMap<Integer, Integer>> mScoreMapList;

    public static ScoreTrackMap getInstance() {
        init_obj();

        return scoreTrackMap;
    }

    private static void init_obj() {
        if (scoreTrackMap == null)
            scoreTrackMap = new ScoreTrackMap();
    }

    public List<HashMap<Integer, Integer>> getmScoreMapList() {
        return mScoreMapList;
    }

    private ScoreTrackMap() {
        mScoreMapList = new ArrayList<>();
    }
}
