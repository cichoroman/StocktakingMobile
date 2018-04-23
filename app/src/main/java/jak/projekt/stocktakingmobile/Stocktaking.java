package jak.projekt.stocktakingmobile;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Stocktaking  extends AppCompatActivity {
    private ListView listView;
    private ArrayAdapter<String> arrayAdapter;
    private ArrayList<String>  list_of_items = new ArrayList<>();
    private DatabaseReference root = FirebaseDatabase.getInstance().getReference("Produkty");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stocktaking_layout);


        listView = (ListView) findViewById(R.id.itemList);
        arrayAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_expandable_list_item_1,list_of_items);
        listView.setAdapter(arrayAdapter);

//pobieranie listy przedmiotów w czasie rzeczywistym
        root.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Set<String> set = new HashSet<String>();
                Iterator i = dataSnapshot.getChildren().iterator();

                while (i.hasNext()){
                    set.add(((DataSnapshot)i.next()).getKey());
                }
                list_of_items.clear();
                list_of_items.addAll(set);

                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        //metoda przechytująca kliknięcie w dany produkt

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?>adapterView, View view, int i, long l) {
                //przejście do następnego layoutu i zdefiniowanie jakie dane przekazać
                Intent intent = new Intent(getApplicationContext(),StocktakingAction.class);
                intent.putExtra("room_name",((TextView)view).getText().toString());
                startActivity(intent);
            }
        });
    }


}
