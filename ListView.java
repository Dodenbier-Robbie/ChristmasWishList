package rdodenbier.byui.edu.christmaswishlist;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by rdodenbier on 11/29/17.
 */

public class ListView extends AppCompatActivity {

    ItemAdapter itemAdapter;
    Context thisContext;
    ListView myListView;
    Map<String, String> wishListMap = new new LinkedHashMap<String, String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.);
    }
}
