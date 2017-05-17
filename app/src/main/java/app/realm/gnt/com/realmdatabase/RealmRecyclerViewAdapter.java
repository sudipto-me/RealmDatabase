package app.realm.gnt.com.realmdatabase;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import io.realm.RealmObject;
import io.realm.RealmBaseAdapter;

/**
 * Created by PC-05 on 5/17/2017.
 */

public abstract  class RealmRecyclerViewAdapter<T extends RealmObject>extends RecyclerView.Adapter {

    private RealmBaseAdapter<T>realmBaseAdapter;

    public T getItem(int position){
        return realmBaseAdapter.getItem(position);
    }

    public RealmBaseAdapter<T> getRealmBaseAdapter() {
        return realmBaseAdapter;
    }

    public void setRealmBaseAdapter(RealmBaseAdapter<T> realmBaseAdapter) {
        this.realmBaseAdapter = realmBaseAdapter;
    }


}
