package com.arsabi.ishtech.irnelectionreportingapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.arsabi.ishtech.irnelectionreportingapp.adapter.ResultAdapter;
import com.arsabi.ishtech.irnelectionreportingapp.adapter.Sort;
import com.arsabi.ishtech.irnelectionreportingapp.adapter.Vote;
import com.arsabi.ishtech.irnelectionreportingapp.api.MySingleton;
import com.arsabi.ishtech.irnelectionreportingapp.database.DatabaseOperation;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ResultActivity extends AppCompatActivity {

    Context context = ResultActivity.this;
    Spinner region, district, constituency, ward;
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    List<Vote> voteList;
    ProgressDialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Intent intent = getIntent();
        final String categories = intent.getStringExtra("categories");

        region = (Spinner) findViewById(R.id.region);
        district = (Spinner) findViewById(R.id.district);
        constituency = (Spinner) findViewById(R.id.constituency);

        recyclerView = (RecyclerView) findViewById(R.id.result);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);


        voteList = new ArrayList<>();
        final String list[] = {"All"};
        String reg[] = getResources().getStringArray(R.array.region);
        Sort sort = new Sort(context, list);
        Sort region_list = new Sort(context, reg);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, "http://10.0.2.2:8080", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        MySingleton.getInstance(context).addToRequestQueue(stringRequest);
        region.setAdapter(region_list);
        /*region.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String district_list[] = new String[0];
                String constituency_list[] = new String[0];
                List<Vote> filters = new ArrayList<>();
                switch (position) {
                    case 0:
                        constituency_list = getResources().getStringArray(R.array.all);
                        Sort sort = new Sort(context, constituency_list);
                        district_list = getResources().getStringArray(R.array.east);
                        String url = "http://10.0.2.2/irn/" + categories + ".json";
                        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                List<Vote> filters = new ArrayList<>();
                                try {
                                    DatabaseOperation db = new DatabaseOperation(context);
                                    List<Vote> voteList = new ArrayList<>();
                                    JSONArray jsonArray = new JSONArray(response);
                                    for (int i = 0; i < jsonArray.length(); i++) {
                                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                                        Vote vote = new Vote(jsonObject.getString("Name"), jsonObject.getString("Vote"), jsonObject.getString("Region"), jsonObject.getString("District"), jsonObject.getString("Party"), jsonObject.getString("CenterID"), jsonObject.getString("Station"), jsonObject.getString("Constituency"), jsonObject.getString("Ward"));
                                        voteList.add(vote);
                                        db.addResult(db, vote);
                                    }
                                    for (int i = 0; i < voteList.size(); i++) {
                                        Vote vote = voteList.get(i);
                                        filters.add(vote);
                                    }
                                    adapter = new ResultAdapter(voteList, context);
                                    recyclerView.setAdapter(adapter);
                                    progress.dismiss();
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                            }

                        });
                        MySingleton.getInstance(context).addToRequestQueue(stringRequest);

                        adapter = new ResultAdapter(filters, context);
                        recyclerView.setAdapter(adapter);
                        constituency.setAdapter(sort);
                        break;
                    case 1:
                        district_list = getResources().getStringArray(R.array.east);
                        getfilter(filters, categories);
                        break;
                    case 2:
                        district_list = getResources().getStringArray(R.array.west);
                        getfilter(filters, categories);
                        break;
                    case 3:
                        district_list = getResources().getStringArray(R.array.north);
                        getfilter(filters, categories);
                        break;
                    case 4:
                        district_list = getResources().getStringArray(R.array.south);
                        getfilter(filters, categories);
                        break;
                    case 5:
                        district_list = getResources().getStringArray(R.array.northwest);
                        getfilter(filters, categories);
                        break;
                }

                Sort sort = new Sort(context, district_list);
                district.setAdapter(sort);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });*/

        DatabaseOperation databaseOperation = new DatabaseOperation(context);
        Cursor cursor = databaseOperation.getResult(databaseOperation);
        Toast.makeText(context, "" + cursor.getCount(), Toast.LENGTH_SHORT).show();

        /*district.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                setConstFilter();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        constituency.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
*/
    }

    public void setConstFilter() {

        String constituency_list[] = new String[0];
        String name = (String) district.getSelectedItem();
        List<Vote> filters = new ArrayList<Vote>();
        switch (name) {
            case "Bo":
                constituency_list = getResources().getStringArray(R.array.Bo);
                for (int i = 0; i < voteList.size(); i++) {
                    Vote vote = voteList.get(i);
                    if (region.getSelectedItem().equals(vote.getRegion())) {
                        filters.add(vote);
                    }
                }
                adapter = new ResultAdapter(filters, context);
                recyclerView.setAdapter(adapter);
                break;
            case "Bonthe":
                constituency_list = getResources().getStringArray(R.array.Bonth);
                for (int i = 0; i < voteList.size(); i++) {
                    Vote vote = voteList.get(i);
                    if (district.getSelectedItem().equals(vote.getDistrict())) {
                        filters.add(vote);
                    }
                }
                adapter = new ResultAdapter(filters, context);
                recyclerView.setAdapter(adapter);
                break;
            case "Bombali":
                constituency_list = getResources().getStringArray(R.array.Bombali);
                for (int i = 0; i < voteList.size(); i++) {
                    Vote vote = voteList.get(i);
                    if (district.getSelectedItem().equals(vote.getDistrict())) {
                        filters.add(vote);
                    }
                }
                adapter = new ResultAdapter(filters, context);
                recyclerView.setAdapter(adapter);
                break;
            case "Falaba":
                constituency_list = getResources().getStringArray(R.array.Falaba);
                for (int i = 0; i < voteList.size(); i++) {
                    Vote vote = voteList.get(i);
                    if (district.getSelectedItem().equals(vote.getDistrict())) {
                        filters.add(vote);
                    }
                }
                adapter = new ResultAdapter(filters, context);
                recyclerView.setAdapter(adapter);
                break;
            case "Kailahun":
                constituency_list = getResources().getStringArray(R.array.Kailahun);
                for (int i = 0; i < voteList.size(); i++) {
                    Vote vote = voteList.get(i);
                    if (district.getSelectedItem().equals(vote.getDistrict())) {
                        filters.add(vote);
                    }
                }
                adapter = new ResultAdapter(filters, context);
                recyclerView.setAdapter(adapter);
                break;
            case "Kambia":
                constituency_list = getResources().getStringArray(R.array.Kambia);
                for (int i = 0; i < voteList.size(); i++) {
                    Vote vote = voteList.get(i);
                    if (district.getSelectedItem().equals(vote.getDistrict())) {
                        filters.add(vote);
                    }
                }
                adapter = new ResultAdapter(filters, context);
                recyclerView.setAdapter(adapter);
                break;
            case "Kenema":
                constituency_list = getResources().getStringArray(R.array.Kenema);
                for (int i = 0; i < voteList.size(); i++) {
                    Vote vote = voteList.get(i);
                    if (district.getSelectedItem().equals(vote.getDistrict())) {
                        filters.add(vote);
                    }
                }
                adapter = new ResultAdapter(filters, context);
                recyclerView.setAdapter(adapter);
                break;
            case "Karene":
                constituency_list = getResources().getStringArray(R.array.Karene);
                for (int i = 0; i < voteList.size(); i++) {
                    Vote vote = voteList.get(i);
                    if (district.getSelectedItem().equals(vote.getDistrict())) {
                        filters.add(vote);
                    }
                }
                adapter = new ResultAdapter(filters, context);
                recyclerView.setAdapter(adapter);
                break;
            case "Moyamba":
                constituency_list = getResources().getStringArray(R.array.Moyamba);
                for (int i = 0; i < voteList.size(); i++) {
                    Vote vote = voteList.get(i);
                    if (district.getSelectedItem().equals(vote.getDistrict())) {
                        filters.add(vote);
                    }
                }
                adapter = new ResultAdapter(filters, context);
                recyclerView.setAdapter(adapter);
                break;
            case "Kono":
                constituency_list = getResources().getStringArray(R.array.Kono);
                for (int i = 0; i < voteList.size(); i++) {
                    Vote vote = voteList.get(i);
                    if (district.getSelectedItem().equals(vote.getDistrict())) {
                        filters.add(vote);
                    }
                }
                adapter = new ResultAdapter(filters, context);
                recyclerView.setAdapter(adapter);
                break;
            case "Portloko":
                constituency_list = getResources().getStringArray(R.array.Portloko);
                for (int i = 0; i < voteList.size(); i++) {
                    Vote vote = voteList.get(i);
                    if (district.getSelectedItem().equals(vote.getDistrict())) {
                        filters.add(vote);
                    }
                }
                adapter = new ResultAdapter(filters, context);
                recyclerView.setAdapter(adapter);
                break;
            case "Pujehun":
                constituency_list = getResources().getStringArray(R.array.Pujehun);
                for (int i = 0; i < voteList.size(); i++) {
                    Vote vote = voteList.get(i);
                    if (district.getSelectedItem().equals(vote.getDistrict())) {
                        filters.add(vote);
                    }
                }
                adapter = new ResultAdapter(filters, context);
                recyclerView.setAdapter(adapter);
                break;
            case "Urban":
                constituency_list = getResources().getStringArray(R.array.Urban);
                for (int i = 0; i < voteList.size(); i++) {
                    Vote vote = voteList.get(i);
                    if (district.getSelectedItem().equals(vote.getDistrict())) {
                        filters.add(vote);
                    }
                }
                adapter = new ResultAdapter(filters, context);
                recyclerView.setAdapter(adapter);
                break;
            case "Rural":
                constituency_list = getResources().getStringArray(R.array.Rural);
                for (int i = 0; i < voteList.size(); i++) {
                    Vote vote = voteList.get(i);
                    if (district.getSelectedItem().equals(vote.getDistrict())) {
                        filters.add(vote);
                    }
                }
                adapter = new ResultAdapter(filters, context);
                recyclerView.setAdapter(adapter);
                break;
            case "Tonkolili":
                constituency_list = getResources().getStringArray(R.array.Tonkolili);
                for (int i = 0; i < voteList.size(); i++) {
                    Vote vote = voteList.get(i);
                    if (district.getSelectedItem().equals(vote.getDistrict())) {
                        filters.add(vote);
                    }
                }
                adapter = new ResultAdapter(filters, context);
                recyclerView.setAdapter(adapter);
                break;
            case "Koinadugu":
                constituency_list = getResources().getStringArray(R.array.Koinadugu);
                for (int i = 0; i < voteList.size(); i++) {
                    Vote vote = voteList.get(i);
                    if (district.getSelectedItem().equals(vote.getDistrict())) {
                        filters.add(vote);
                    }
                }
                adapter = new ResultAdapter(filters, context);
                recyclerView.setAdapter(adapter);
                break;
        }

        Sort sort = new Sort(context, constituency_list);
        constituency.setAdapter(sort);
    }

    public void getfilter(List<Vote> filters, String categories) {
        String url = "http://10.0.2.2/api/demo/" + categories + ".json";
        StringRequest stringRequest1 = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                List<Vote> filters = new ArrayList<>();
                try {
                    DatabaseOperation db = new DatabaseOperation(context);
                    List<Vote> voteList = new ArrayList<>();
                    JSONArray jsonArray = new JSONArray(response);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        Vote vote = new Vote(jsonObject.getString("Name"), jsonObject.getString("Vote"), jsonObject.getString("Region"), jsonObject.getString("District"), jsonObject.getString("Party"), jsonObject.getString("CenterID"), jsonObject.getString("Station"), jsonObject.getString("Constituency"), jsonObject.getString("Ward"));
                        voteList.add(vote);
                        db.addResult(db, vote);
                    }
                    for (int i = 0; i < voteList.size(); i++) {
                        Vote vote = voteList.get(i);
                        if (region.getSelectedItem().equals(vote.getRegion())) {
                            filters.add(vote);
                        }
                    }
                    adapter = new ResultAdapter(filters, context);
                    recyclerView.setAdapter(adapter);
                    progress.dismiss();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }

        });
        MySingleton.getInstance(context).addToRequestQueue(stringRequest1);

        adapter = new ResultAdapter(filters, context);
        recyclerView.setAdapter(adapter);
    }

    public void filterDistrict(List<Vote> filters, String categories) {
        String url = "http://10.0.2.2/api/demo/" + categories + ".json";
        StringRequest stringRequest1 = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                List<Vote> filters = new ArrayList<>();
                try {
                    DatabaseOperation db = new DatabaseOperation(context);
                    List<Vote> voteList = new ArrayList<>();
                    JSONArray jsonArray = new JSONArray(response);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        Vote vote = new Vote(jsonObject.getString("Name"), jsonObject.getString("Vote"), jsonObject.getString("Region"), jsonObject.getString("District"), jsonObject.getString("Party"), jsonObject.getString("CenterID"), jsonObject.getString("Station"), jsonObject.getString("Constituency"), jsonObject.getString("Ward"));
                        voteList.add(vote);
                        db.addResult(db, vote);
                    }
                    for (int i = 0; i < voteList.size(); i++) {
                        Vote vote = voteList.get(i);
                        if (district.getSelectedItem().equals(vote.getDistrict())) {
                            filters.add(vote);
                        }
                    }
                    adapter = new ResultAdapter(filters, context);
                    recyclerView.setAdapter(adapter);
                    progress.dismiss();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }

        });
        MySingleton.getInstance(context).addToRequestQueue(stringRequest1);

        adapter = new ResultAdapter(filters, context);
        recyclerView.setAdapter(adapter);
    }
}
