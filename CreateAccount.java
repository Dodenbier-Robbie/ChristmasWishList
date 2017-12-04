package rdodenbier.byui.edu.christmaswishlist;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rdodenbier on 11/28/17.
 */

public class CreateAccount extends AppCompatActivity {

    EditText firstname, lastname, email, age, uname, password;
    Button submit;

    JSONParser jParser = new JSONParser();

    JSONObject json;
    //private static String url_login = "http://10.0.2.2:8080/AndroidLogin/add_user";
    private static String url_login = "http://192.168.1.15:8080/AndroidLogin/add_user";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_account);
        findViewsById();
        submit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                new CreateUserAccount().execute();

            }
        });
    }

    private void findViewsById() {

        firstname = (EditText) findViewById(R.id.txFirstName);
        lastname = (EditText) findViewById(R.id.txtLastName);
        email = (EditText) findViewById(R.id.txtEmail);
        age = (EditText) findViewById(R.id.txtAge);
        uname = (EditText) findViewById(R.id.txtUser);
        password = (EditText) findViewById(R.id.txtPass);
        submit = (Button) findViewById(R.id.button3);
    }

    private class CreateUserAccount extends AsyncTask<String,String,String> {

        @Override
        protected String doInBackground(String... args) {
            String FirstName = firstname.getText().toString();
            String LastName = lastname.getText().toString();
            String Email = email.getText().toString();
            String Age = age.getText().toString();
            String username = uname.getText().toString();
            String pass = password.getText().toString();

            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("firstname",FirstName));
            params.add(new BasicNameValuePair("lastname",LastName));
            params.add(new BasicNameValuePair("email",Email));
            params.add(new BasicNameValuePair("age",Age));
            params.add(new BasicNameValuePair("username", username));
            params.add(new BasicNameValuePair("password", pass));
            json = jParser.makeHttpRequest(url_login, "GET", params);
            String s;

            try {
                s= json.getString("info");
                Log.d("Msg", json.getString("info"));
                if(s.equals("success")){
                    Intent list = new Intent(getApplicationContext(), WishList.class);
                    list.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(list);
                    finish();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

    }

}
