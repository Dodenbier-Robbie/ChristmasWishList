package rdodenbier.byui.edu.christmaswishlist;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText uname, password;
    Button submit, createAccount;
    TextView error;

    JSONParser jParser = new JSONParser();

    JSONObject json;
    private static String url_login = "http://10.0.2.2:8080/AndroidLogin/login";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewsById();
        error.setText("");
        submit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // execute method invokes doInBackground() where we open a Http URL connection using the given Servlet URL
                //and get output response from InputStream and return it.
                new Login().execute();

            }
        });

        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent createNewAccount = new Intent(getApplicationContext(), CreateAccount.class);
                startActivity(createNewAccount);
            }
        });
    }
    private void findViewsById() {

        uname = (EditText) findViewById(R.id.txtUser);
        password = (EditText) findViewById(R.id.txtPass);
        submit = (Button) findViewById(R.id.button1);
        createAccount = (Button) findViewById(R.id.button2);
        error = (TextView) findViewById(R.id.txtError);
    }

    private class Login extends AsyncTask<String, String, String>{

        @Override
        protected String doInBackground(String... args) {
            // Getting username and password from user input
            String username = uname.getText().toString();
            String pass = password.getText().toString();

            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("username",username));
            params.add(new BasicNameValuePair("password",pass));
            json = jParser.makeHttpRequest(url_login, "GET", params);
            String s;

            try {
                s= json.getString("info");
                Log.d("Msg", json.getString("info"));
                if(s.equals("success")){
                    Intent login = new Intent(getApplicationContext(), WishList.class);
                    login.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(login);
                    finish();
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String result) {

            error.setText("Invalid Login. Please try again!");
        }

    }
}
