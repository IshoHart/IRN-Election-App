package com.arsabi.ishtech.irnelectionreportingapp.database;

import android.provider.BaseColumns;

/**
 * Created by Isho on 2/11/2018.
 */

public class Result {
    public Result() {
    }

    public class ResultInfo implements BaseColumns {
        public static final String ID = "id";
        public static final String Name = "name";
        public static final String Party = "party";
        public static final String Vote = "vote";
        public static final String Region = "region";
        public static final String Distirct = "district";
        public static final String Constituency = "constituency";
        public static final String Ward = "ward";
        public static final String Center = "center";
        public static final String Section = "section";
        public static final String Table = "result";
    }
}
