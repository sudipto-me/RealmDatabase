package app.realm.gnt.com.realmdatabase;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;

import io.realm.OrderedRealmCollection;
import io.realm.RealmBaseAdapter;
import io.realm.RealmObject;
import io.realm.RealmResults;

/**
 * Created by PC-05 on 5/17/2017.
 */

public class RealmModelAdapters <T extends RealmObject> extends RealmBaseAdapter<T> {

    /*
     public RealmModelAdapters(Context context, RealmResults<T> realmResults, boolean automaticUpdate) {
       super(context,realmResults,automaticUpdate);
    }

     */
    public RealmModelAdapters(@NonNull Context context, @Nullable OrderedRealmCollection<T> data) {
        super(context, data);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}
