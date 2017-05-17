package app.realm.gnt.com.realmdatabase;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;


import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by PC-05 on 5/17/2017.
 */

public class BooksAdapter extends RealmRecyclerViewAdapter<Book> {

    final Context context;
    private Realm realm;
    private LayoutInflater layoutInflater;

    public BooksAdapter(Context context){
        this.context = context;
    }

    @Override
    public CardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_book, parent, false);
        return new CardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int position) {

        realm = RealmController.getInstance().getRealm();

        //get the article
        final  Book book = getItem(position);
        //cast the generic view holder to a specific one
        final CardViewHolder holder = (CardViewHolder) viewHolder;
        //set the title and snippet
        holder.textTitle.setText(book.getTitle());
        holder.textAuthor.setText(book.getAuthor());
        holder.textDescription.setText(book.getDescription());

        //load the background image
        if(book.getImageUrl() !=null){
            Glide.with(context)
                    .load(book.getImageUrl().replace("https","http"))
                    .asBitmap()
                    .fitCenter()
                    .into(holder.imageBackground);
        }
        //remove single match from realm
        holder.card.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                RealmResults<Book>results = realm.where(Book.class).findAll();
                //get the book title
                Book b = results.get(position);
                String title = b.getTitle();

                //all changes of data must happen in a transaction
                realm.beginTransaction();

                //remove single match
                results.removeAllChangeListeners();//check again
                realm.commitTransaction();

                if(results.size() == 0){
                    Prefs.with(context).setPreLoad(false);
                }
                notifyDataSetChanged();


                Toast.makeText(context, title + " is removed from Realm", Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        /*
        //update single match from realm
        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            }
        });
         */


    }

    @Override
    public int getItemCount() {
        if(getRealmBaseAdapter()!=null){
            return getRealmBaseAdapter().getCount();
        }
        return 0;
    }

    private class CardViewHolder extends RecyclerView.ViewHolder {

        public CardView card;
        public TextView textTitle;
        public TextView textAuthor;
        public TextView textDescription;
        public ImageView imageBackground;
        public CardViewHolder(View itemview) {
            super(itemview);

            card = (CardView)itemview.findViewById(R.id.cv_card_view);
            textTitle = (TextView)itemview.findViewById(R.id.text_books_title);
            textAuthor = (TextView)itemview.findViewById(R.id.text_books_author);
            textDescription = (TextView)itemview.findViewById(R.id.text_books_description);
            imageBackground = (ImageView)itemview.findViewById(R.id.image_background);
        }
    }
}
