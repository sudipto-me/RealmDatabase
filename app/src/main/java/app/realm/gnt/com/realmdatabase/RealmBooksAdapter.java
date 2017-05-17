package app.realm.gnt.com.realmdatabase;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import io.realm.OrderedRealmCollection;
import io.realm.RealmResults;

/**
 * Created by PC-05 on 5/17/2017.
 */

public class RealmBooksAdapter extends RealmModelAdapters<Book> {

    /*
   public RealmBooksAdapter(Context context, RealmResults<Book> realmResults,boolean automaticUpdate) {

        super(context, realmResults, automaticUpdate);
    }
     */



    public RealmBooksAdapter(@NonNull Context context, @Nullable OrderedRealmCollection<Book> data) {
        super(context, data);
    }
}
