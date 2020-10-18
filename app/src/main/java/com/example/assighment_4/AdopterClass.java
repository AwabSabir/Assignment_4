package com.example.assighment_4;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class AdopterClass extends RecyclerView.Adapter<AdopterClass.listDocter> implements Filterable {
    private ArrayList<String> docers;
    private List<String> doctersAlllist;
    public  AdopterClass(ArrayList<String> docers){
        this.docers=docers;
        this.doctersAlllist=new ArrayList<>(docers);
    }
    @NonNull
    @Override
    public listDocter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View view=layoutInflater.inflate(R.layout.list_docter_layout,parent,false);
        return new listDocter(view);

    }

    @Override
    public void onBindViewHolder(@NonNull listDocter holder, int position) {
           String val=docers.get(position);
           holder.names.setText(val);
    }

    @Override
    public int getItemCount() {
        return docers.size();
    }

    @Override
    public Filter getFilter() {
        return  filter;
    }
    Filter filter=new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
                List<String> filteredList=new ArrayList<>();
                if(constraint.toString().isEmpty()){
                    filteredList.addAll(doctersAlllist);
                }
                else{
                    for(String doct :  doctersAlllist){
                        if(doct.toLowerCase().contains(constraint.toString().toLowerCase())){
                            filteredList.add(doct);
                        }
                    }//forLoop
                }
                FilterResults filterResults=new FilterResults();
                filterResults.values=filteredList;

                return  filterResults;
        }



        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
                  docers.clear();
                  docers.addAll((Collection<? extends String>)results.values);
                  notifyDataSetChanged();



        }
    };


    public  class listDocter extends RecyclerView.ViewHolder{
        ImageView icons;
        TextView names;
        public listDocter(@NonNull View itemView) {
            super(itemView);
            icons=(ImageView)itemView.findViewById(R.id.iconDocter);
            names=(TextView) itemView.findViewById(R.id.nameListTxt);
        }
    }
}
