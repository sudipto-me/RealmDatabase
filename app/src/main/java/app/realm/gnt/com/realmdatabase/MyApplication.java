package app.realm.gnt.com.realmdatabase;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by PC-05 on 5/17/2017.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();


        RealmConfiguration config = new RealmConfiguration.Builder().
                name(Realm.DEFAULT_REALM_NAME)
                .schemaVersion(0)
                .deleteRealmIfMigrationNeeded()
                .build();

        Realm.setDefaultConfiguration(config);
    }
}
