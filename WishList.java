package rdodenbier.byui.edu.christmaswishlist;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import org.apache.http.NameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by rdodenbier on 11/29/17.
 */

public class WishList extends AppCompatActivity {

    Context thisContext;
    Button addItem;
    Button btnLogout;

    JSONParser jParser = new JSONParser();

    JSONObject json;
    private static String url_login = "http://10.0.2.2:8080/AndroidLogin/get_wishlist";
    private ListView lv;
    ArrayList<HashMap<String, String>> wishList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view);

        addItem = (Button) findViewById(R.id.button4);
        btnLogout = (Button) findViewById(R.id.button5);
        thisContext = this;

        wishList = new ArrayList<>();
        lv = (ListView) findViewById(R.id.myListView);



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

        new GetData().execute();
    }

    private class GetData extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... arg0) {
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            json = jParser.makeHttpRequest(url_login, "GET", params);
            String jsonInput;
            try {
                //String jsonInput = "{\"itemCategory\": \"Toy\", \"itemDetail\": \"Test\"}";
                jsonInput = json.getString("info");
                JSONObject jsonObj = new JSONObject(jsonInput);

                String itemCat = jsonObj.getString("itemCategory");
                String itemDet = jsonObj.getString("itemDetail");

                HashMap<String, String> list = new HashMap<>();
                list.put("itemCategory", itemCat);
                list.put("itemDetail", itemDet);

                wishList.add(list);

                ListAdapter adapter = new SimpleAdapter(thisContext, wishList, R.layout.item_layout, new String[]{ "itemCategory","itemDetail"},
                        new int[]{R.id.itemCategoryTextView, R.id.itemDetailTextView});
                lv.setAdapter(adapter);

            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }
    }
}
