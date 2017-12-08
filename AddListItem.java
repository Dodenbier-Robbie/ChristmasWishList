package rdodenbier.byui.edu.christmaswishlist;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rdodenbier on 11/29/17.
 */

public class AddListItem extends AppCompatActivity {

    Spinner category;
    EditText details;
    Button addItem;

    JSONParser jParser = new JSONParser();

    JSONObject json;
    //private static String url_login = "http://10.0.2.2:8080/AndroidLogin/add_wishlist";
    private static String url_login = "http://71.199.47.143:8080/AndroidLogin/add_wishlist";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_list_item);
        findViewsById();

        addItem.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                new AddWishListItem().execute();

            }
        });
    }

    private void findViewsById() {
        category = (Spinner) findViewById(R.id.addItemCategory);
        details = (EditText) findViewById(R.id.itemDetailsEditText);
        addItem = (Button) findViewById(R.id.addItemBtn);
    }

    private class AddWishListItem extends AsyncTask<String, String, String> {

        String success;

        @Override
        protected String doInBackground(String... args) {
            String itemCat = String.valueOf(category.getSelectedItem());
            String itemDet = details.getText().toString();

            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("itemCategory",itemCat));
            params.add(new BasicNameValuePair("itemDetail",itemDet));
            json = jParser.makeHttpRequest(url_login, "GET", params);
            String s;

            try {
                s= json.getString("info");
                Log.d("Msg", json.getString("info"));
                if(s.equals("success")){
                    success = "true";
                } else {
                    success = "false";
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

            return success;
        }

        @Override
        protected void onPostExecute(String success) {
            if (success.equals("true")) {
                Intent listItems = new Intent(getApplicationContext(), WishList.class);
                startActivity(listItems);
                finish();
            } else {
                Toast.makeText(AddListItem.this,"Error saving item. Please try again.",Toast.LENGTH_SHORT).show();
            }
        }
    }
}
