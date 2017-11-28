package rdodenbier.byui.edu.christmaswishlist;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class MainActivity extends AppCompatActivity {
    EditText uname, password;
    Button submitLogin, submitCreateAccount;

    JSONParser jParser = new JSONParser();

    JSONObject json;
    private static String url_login = "http://10.0.2.2:8080/AndroidLogin/login_servlet";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewsById();
        submitLogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                new getLogin().execute();
            }
        });

        submitCreateAccount.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent createAccount = new Intent(getApplicationContext(), CreateAccount.class);
                createAccount.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(createAccount);
                finish();
            }
        });
    }

    private void findViewsById() {

        uname = (EditText) findViewById(R.id.txtUser);
        password = (EditText) findViewById(R.id.txtPass);
    }

    private class getLogin extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... String) {
            String username = uname.getText().toString();
            String pass = password.getText().toString();

            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("username", username));
            params.add(new BasicNameValuePair("password", pass));
            json = jParser.makeHttpRequest(url_login, "POST", params);
            String s;

            try {
                s = json.getString("info");
                Log.d("Msg", json.getString("info"));
                if (s.equals("success")) {
                    Intent login = new Intent(getApplicationContext(), CreateAccount.class);
                    login.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    finish();
                }
            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            return null;
        }
    }
}
