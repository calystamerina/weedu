package com.telkom.weedu.utils;

import com.telkom.weedu.data.api.model.request.RegisterRequest;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by sidiqpermana on 5/10/17.
 */

public class EmailSuggestionGenerator {
    private RegisterRequest registerRequest;

    public EmailSuggestionGenerator(RegisterRequest registerRequest) {
        this.registerRequest = registerRequest;
    }

    private String getUsernameFromName(){
        return registerRequest.getFirstName().toLowerCase().trim()+registerRequest.getLastName().toLowerCase().trim();
    }

    private String getUsernameFromReverseFullname(){
        return registerRequest.getLastName().toLowerCase().trim()+registerRequest.getFirstName().toLowerCase().trim();
    }

    private String getUsernameFromEmail(){
        return registerRequest.getEmail().split("@")[0];
    }

    private String getUsernameFromFirstnameAndBirthdate(){
        String date[] = registerRequest.getBirthday().split("-");
        String d = registerRequest.getFirstName().toLowerCase()+date[2]+date[1];

        return d;
    }

    private String getUsernameFromFirstnameAndYear(){
        String date[] = registerRequest.getBirthday().split("-");
        String d = registerRequest.getFirstName().toLowerCase()+date[2];

        return d;
    }

    private String getUsernameFromFirstnameAndRandom(){
        Random rand = new Random();

        int  n = rand.nextInt(10000) + 1;

        return registerRequest.getFirstName().toLowerCase()+n;
    }

    public ArrayList<String> getUsernames(){
        ArrayList<String> usernames = new ArrayList<>();
        usernames.add(getUsernameFromName());
        usernames.add(getUsernameFromEmail());
        usernames.add(getUsernameFromFirstnameAndBirthdate());
        usernames.add(getUsernameFromReverseFullname());
        usernames.add(getUsernameFromFirstnameAndRandom());
        usernames.add(getUsernameFromFirstnameAndYear());

        return usernames;
    }

}
