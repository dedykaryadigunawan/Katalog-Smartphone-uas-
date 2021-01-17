package com.dedy.katalogsmartphone;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.dedy.katalogsmartphone.Smartphone;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SharedPreferenceUtility {

    private static final String PREF_FILE = "simple.example.catatankas.DATA";
    // PREF_FILE -> Nama file utk penyimpanan,
    // biasanya menyertakan app.id agar tidak bentrok dgn app lain
    private static final String FAVOR_KEY = "TRANS"; // KEY utk daftar transaksi
    private static final String USER_NAME_KEY = "USERNAME"; // KEY untuk nama user

    private static SharedPreferences getSharedPref(Context ctx) {
        SharedPreferences sharedPref = ctx.getSharedPreferences(
                PREF_FILE, Context.MODE_PRIVATE);
        return sharedPref;
    }

    public static String getUserName(Context ctx) {
        return getSharedPref(ctx).getString(USER_NAME_KEY,"NO NAME");
    }

    public static void saveUserName(Context ctx, String userName) {
        Log.d("SH PREF","Change user name to"+userName);
        getSharedPref(ctx).edit().putString(USER_NAME_KEY,userName).apply();
    }

    public static List<Smartphone> getAllMenuRestoran(Context ctx) {
        String jsonString = getSharedPref(ctx).getString(FAVOR_KEY, null);
        List<Smartphone> trs = new ArrayList<Smartphone>();
        if (jsonString != null) {
            Log.d("SH PREF","json string is:"+jsonString);
            try {
                JSONArray jsonArray = new JSONArray(jsonString);
                for (int i=0;i<jsonArray.length();i++) {
                    JSONObject obj = jsonArray.getJSONObject(i);
                    trs.add(Smartphone.fromJSONObject(obj));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        Collections.sort(trs,(menuRestoran, t1) -> {
            return menuRestoran.getTanggal().compareTo(t1.getTanggal());
        });
        return trs;
    }
    private static void saveAllMenuRestoran(Context ctx, List<Smartphone> trs) {
        List<JSONObject> jsonTrs = new ArrayList<JSONObject>();
        JSONArray jsonArr = new JSONArray();
        for (Smartphone tr : trs) {
            jsonArr.put(tr.toJSONObject());
        }
        getSharedPref(ctx).edit().putString(FAVOR_KEY,jsonArr.toString()).apply();
    }

    public static void addMenuRestoran(Context ctx, Smartphone tr) {
        List<Smartphone> trs = getAllMenuRestoran(ctx);
        trs.add(tr);
        saveAllMenuRestoran(ctx,trs);
    }

    public static void editFavorite(Context ctx, Smartphone tr) {
        List<Smartphone> trs = getAllMenuRestoran(ctx);
        for (Smartphone tre:trs) {
            if (tr.getId().equals(tre.getId())) {
                trs.remove(tre);
                trs.add(tr);
            }
        }
        saveAllMenuRestoran(ctx,trs);
    }

    public static void deleteFavorite(Context ctx, String id) {
        List<Smartphone> trs = getAllMenuRestoran(ctx);
        for (Smartphone tr:trs) {
            if (tr.getId().equals(id)) {
                trs.remove(tr);
            }
        }
        saveAllMenuRestoran(ctx,trs);
    }

}