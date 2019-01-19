package playing.kolade.com.instagramclone;

import android.app.Application;

import com.parse.Parse;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("HuWcOtjCWfA6VPMuR1xF1Z178k4pLEGbl6NjbVay")
                // if defined
                .clientKey("mzu0dTSbprezrY7lfWcyPUMuA9UhlWYxEvSYZPoF")
                .server("https://parseapi.back4app.com/")
                .build()
        );
    }
}
