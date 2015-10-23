package legolu.servicescheduler;

import android.util.Log;

/**
 * Created by leo on 23/10/15.
 */
public class ExecuteAction {

    public void notify(String message) {

        switch (message) {

            case("prova"): {

                Log.d("switch", message);

            }

        }
    }
}

