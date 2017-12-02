package rdodenbier.byui.edu.christmaswishlist;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by rdodenbier on 11/29/17.
 */

public class WishList extends AppCompatActivity {

    ItemAdapter itemAdapter;
    Context thisContext;
    ListView myListView;
    Button addItem;
    Button btnLogout;
    Map<String, String> wishListMap = new LinkedHashMap<String, String>();

    JSONParser jParser = new JSONParser();

    JSONObject json;
    private static String url_login = "http://10.0.2.2:8080/AndroidLogin/get_wishlist";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view);

        Resources res = getResources();
        myListView = (ListView) findViewById(R.id.myListView);
        addItem = (Button) findViewById(R.id.button4);
        btnLogout = (Button) findViewById(R.id.button5);
        thisContext = this;

        addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent createNewAccount = new Intent(getApplicationContext(), AddListItem.class);
                startActivity(createNewAccount);
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {
                Intent login = new Intent(getApplicationContext(), MainActivity.class);
                login.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(login);
                finish();
            }
        });

        try {
            String jsonInput = "[\"itemCategory\", \"Toy\", \"itemDetail\", \"Test\"]";
            JSONArray jsonArray = new JSONArray(jsonInput);
            JSONObject jsonObject;
            int length = jsonArray.length();
            List<String> listContents = new ArrayList<String>(length);

            for (int i = 0; i < length; i++) {
                jsonArray = jsonArray.getJSONArray(i);
                jsonObject = jsonArray.getJSONObject(0);;
            }

            //ListView myListView = (ListView) findViewById(R.id.myListView);
            //myListView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listContents));

            //itemAdapter = new ItemAdapter(thisContext, listContents);
            //myListView.setAdapter(itemAdapter);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
