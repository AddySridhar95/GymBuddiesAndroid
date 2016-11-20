package com.ab.store.gymbuddies.gymbuddies;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import java.util.*;
import android.widget.TextView;
import android.view.LayoutInflater;

import org.w3c.dom.Text;

/**
 * Created by adityasridhar on 2016-11-14.
 */

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

    private List<User> contactList;
    private CommunitiesActivity communitiesActivity;
    public UserAdapter(List<User> contactList, CommunitiesActivity c) {
        this.contactList = contactList;
        this.communitiesActivity = c;
    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }

    @Override
    public void onBindViewHolder(UserViewHolder userViewHolder, int index) {
        User u = contactList.get(index);
        userViewHolder.vFullName.setText(u.getFirstName() + " " + u.getLastName());
        userViewHolder.vAge.setText("Age: " + u.getAge());
        userViewHolder.vBio.setText(u.getBio());
        //userViewHolder.vAge.setText(u.getAge());
        // userViewHolder.vGoals.setText(u.getGoals());
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.communities_list_item, viewGroup, false);

        return new UserViewHolder(itemView, communitiesActivity);
    }

    public static class UserViewHolder extends RecyclerView.ViewHolder {
        protected TextView vFullName;
        protected TextView vAge;
        protected TextView vGoals;
        CommunitiesActivity cAct;
        protected TextView vBio;

        public UserViewHolder(View v, CommunitiesActivity c) {
            super(v);
            cAct = c;
            vFullName =  (TextView) v.findViewById(R.id.txtFullName);
            vAge = (TextView)  v.findViewById(R.id.txtAge);
            vBio = (TextView) v.findViewById(R.id.txtBio);
            vGoals = (TextView) v.findViewById(R.id.txtGoal);

            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(cAct, ProfileActivity.class);
                    cAct.startActivity(intent);
                }
            });
            // TODO: need to add age and goals to the view holder
        }

        // TODO: create a function that dynamically adds new text views, one for each goal
    }
}
