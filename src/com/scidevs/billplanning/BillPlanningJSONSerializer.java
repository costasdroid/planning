/*
 * This class navigates to the filename and writes or loads JSON data
 */

package com.scidevs.billplanning;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONTokener;

import android.content.Context;

public class BillPlanningJSONSerializer {

    private Context mContext;
    private String mFilename;

    public BillPlanningJSONSerializer(Context c, String s) {
        mContext = c;
        mFilename = s;
    }

    public ArrayList<Bill> loadBills() throws IOException, JSONException {
        ArrayList<Bill> bills = new ArrayList<Bill>();
        BufferedReader reader = null;
        try {
            // Open and read the file into a StringBuilder
            InputStream in = mContext.openFileInput(mFilename);
            reader = new BufferedReader(new InputStreamReader(in));
            StringBuilder jsonString = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                // Line breaks are omitted and irrelevant
                jsonString.append(line);
            }
            // Parse the JSON using JSONTokener
            JSONArray array = (JSONArray) new JSONTokener(jsonString.toString())
                .nextValue();
            // Build the array of bills from JSONObjects
            for (int i = 0; i < array.length(); i++) {
            	bills.add(new Bill(array.getJSONObject(i)));
            }
        } catch (FileNotFoundException e) {
            // Ignore this one; it happens when starting fresh
        } finally {
            if (reader != null)
                reader.close();
        }
        return bills;
    }
    
    public void saveBills(ArrayList<Bill> bills)
            throws JSONException, IOException {
        // Build an array in JSON 
        JSONArray array = new JSONArray();
        for (Bill b : bills)
            array.put(b.toJSON());

        // Write the file to disk
        Writer writer = null;
        try {
            FileOutputStream out = mContext
                .openFileOutput(mFilename, Context.MODE_PRIVATE);
            writer = new OutputStreamWriter(out);
            writer.write(array.toString());
        } finally {
            if (writer != null)
                writer.close();
        }
    }
}