package rdodenbier.byui.edu.christmaswishlist;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by rdodenbier on 11/29/17.
 */

public class WishList extends AppCompatActivity {

    ItemAdapter itemAdapter;
    Context thisContext;
    ListView myListView;
    Button addItem;
    Map<String, String> wishListMap = new LinkedHashMap<String, String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view);

        Resources res = getResources();
        myListView = (ListView) findViewById(R.id.myListView);
        addItem = (Button) findViewById(R.id.button4);
        thisContext = this;

        addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent createNewAccount = new Intent(getApplicationContext(), AddListItem.class);
                startActivity(createNewAccount);
            }
        });
    }
}
