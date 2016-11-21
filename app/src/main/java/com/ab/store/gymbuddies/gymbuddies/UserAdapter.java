package com.ab.store.gymbuddies.gymbuddies;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import java.util.*;

import android.widget.ImageView;
import android.widget.TextView;
import android.view.LayoutInflater;

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

        userViewHolder.email = u.getEmail();
        userViewHolder.firstName = u.getFirstName();
        userViewHolder.pPic.setImageDrawable(communitiesActivity.getResources().getDrawable(communitiesActivity.getResources().getIdentifier(u.getFirstName().substring(0,1).toLowerCase(), "drawable", communitiesActivity.getPackageName())));

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
        protected ImageView pPic;
        CommunitiesActivity cAct;
        protected TextView vBio;
        public String email;
        public String firstName;

        public UserViewHolder(View v, CommunitiesActivity c) {
            super(v);
            cAct = c;
            vFullName =  (TextView) v.findViewById(R.id.txtFullName);
            vAge = (TextView)  v.findViewById(R.id.txtAge);
            vBio = (TextView) v.findViewById(R.id.txtBio);
            pPic = (ImageView) v.findViewById(R.id.userpic);
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent = new Intent(cAct, ProfileActivity.class);
                    intent.putExtra("userEmail", email);
                    intent.putExtra("isCurrentUser", false);
                    intent.putExtra("matchEmail", cAct.personalEmail);
                    cAct.startActivity(intent);
                }
            });

            if (firstName != null)
            Log.d("sdfhasdjkgh", firstName.substring(0,1).toLowerCase());
        }
    }
}
