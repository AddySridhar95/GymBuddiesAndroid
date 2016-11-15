package com.ab.store.gymbuddies.gymbuddies;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import java.util.*;
import android.widget.TextView;
import android.view.LayoutInflater;
/**
 * Created by adityasridhar on 2016-11-14.
 */

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

    private List<User> contactList;

    public UserAdapter(List<User> contactList) {
        this.contactList = contactList;
    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }

    @Override
    public void onBindViewHolder(UserViewHolder userViewHolder, int i) {
        User u = contactList.get(i);
        userViewHolder.vFirstName.setText(u.getFirstName());
        userViewHolder.vLastName.setText(u.getLastName());
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.communities_list_item, viewGroup, false);

        return new UserViewHolder(itemView);
    }

    public static class UserViewHolder extends RecyclerView.ViewHolder {
        protected TextView vFirstName;
        protected TextView vLastName;


        public UserViewHolder(View v) {
            super(v);
            vFirstName =  (TextView) v.findViewById(R.id.txtFirstName);
            vLastName = (TextView)  v.findViewById(R.id.txtLastName);
        }
    }
}