package com.example.japanese;

import static com.example.japanese.MainActivity.typeface;

import android.content.Context;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    final int view_type_loading = 0;
    final int view_type_item = 1;
    public List<Spanned> itemlist;
    Context context;
    //int lastPosition = -1;

    public RecyclerViewAdapter(List<Spanned> itemlist, Context context) {
        this.itemlist = itemlist;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //if (viewType == view_type_item) {
        return new ItemViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row, parent, false));
        //}
        /*/else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_loading,parent,false);
            return new LoadingHolder(view);
        }/**/
    }
/*/
    private void deleteItem(int position) {
        item_row.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, item_row.size());
        item_row.itemView.setVisibility(View.GONE);
    }/**/

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        if (viewHolder instanceof ItemViewHolder) {
            populateItemRows((ItemViewHolder) viewHolder, position);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return view_type_item;
        //return itemlist.get(position) == null ? view_type_loading : view_type_item;
    }

    @Override
    public int getItemCount() {
        return itemlist == null ? 0 : itemlist.size();
    }



    private class ItemViewHolder extends RecyclerView.ViewHolder {

        TextView textView;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.row_text);
        }
    }

    private class LoadingHolder extends RecyclerView.ViewHolder {

        public LoadingHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    private void populateItemRows(ItemViewHolder viewHolder, int position) {
        viewHolder.textView.setText(itemlist.get(position));
        viewHolder.textView.setMovementMethod(LinkMovementMethod.getInstance());

        try {
            viewHolder.textView.setTypeface(typeface);
        }catch (Exception e){}
        viewHolder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(context,view.)
            }
        });
    }

}
