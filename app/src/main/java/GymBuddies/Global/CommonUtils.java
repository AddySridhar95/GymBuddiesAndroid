package GymBuddies.Global;


import java.util.ArrayList;

/**
 * Created by Akash on 2016-11-22.
 */

public class CommonUtils {
    public static final CharSequence[] options
                = {Constants.LEAN, Constants.STRENGTH, Constants.ENDURANCE, Constants.GAIN};

    public static String listToString(ArrayList<CharSequence> list) {
        String toRet = "";
        for (int i = 0; i < list.size(); i++) {
            toRet += (list.get(i)).toString();
            if ( i < list.size() - 1 ) toRet += ",";
        }
        return toRet;
    }
}
