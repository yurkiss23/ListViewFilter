package com.example.hwadaptation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ListActivity extends AppCompatActivity {

    private static final String TAG = "null";
    String query = null;

    final String ATTRIBUTE_NAME_FNAME = "firstName";
    final String ATTRIBUTE_NAME_LNAME = "lastName";
    final String ATTRIBUTE_NAME_GROUP = "group";
    final String ATTRIBUTE_NAME_RANGE = "range";
    final String ATTRIBUTE_NAME_COIN = "coin";
    final String ATTRIBUTE_NAME_PHOTO = "photo";

    ListView lvStud;
    SimpleAdapter sAdapter;
    ArrayList<Map<String, Object>> data, find;

    final String[]fsts = {"Юрій", "Олександр", "Ліна", "Святослав", "Володимир", "Олександр",
            "Іван", "Микола", "Іван", "Михаил"};
    final String[]lsts = {"Сафронюк", "Гальчин", "Богута", "Дубов", "Швець", "Бугаєнко",
            "Гмирак", "Харковець", "Кравчук", "Югов"};
    String grp = "СПУ714";
    int[]cns = {2197, 2143, 2120, 1954, 1391, 1169, 1154, 1115, 1100, 378};
    int pht = R.mipmap.ic_photo_stud;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        GetListAll(fsts, lsts, grp, cns, pht);
    }

    public void PutMap(ArrayList<Map<String, Object>> arr, Map<String, Object> m, int i){
        m.put(ATTRIBUTE_NAME_FNAME, fsts[i]);
        m.put(ATTRIBUTE_NAME_LNAME, lsts[i]);
        m.put(ATTRIBUTE_NAME_GROUP, grp);
        m.put(ATTRIBUTE_NAME_RANGE, i + 1);
        m.put(ATTRIBUTE_NAME_COIN, cns[i]);
        m.put(ATTRIBUTE_NAME_PHOTO, pht);
        arr.add(m);
    }

    public void GetListAll(String[]fsts, String[]lsts, String grp, int[]cns, int pht){
        data = new ArrayList<Map<String, Object>>(fsts.length);
        Map<String, Object> m;
        for(int i = 0; i < fsts.length; i++){
            m = new HashMap<>();
            PutMap(data, m, i);
        }

        String[]from = {ATTRIBUTE_NAME_FNAME, ATTRIBUTE_NAME_LNAME,
                ATTRIBUTE_NAME_GROUP, ATTRIBUTE_NAME_RANGE,
                ATTRIBUTE_NAME_PHOTO, ATTRIBUTE_NAME_COIN};
        int[]to = {R.id.tvfName, R.id.tvlName, R.id.tvgroup, R.id.tvrange, R.id.ivImg, R.id.tvcoin};

        sAdapter = new SimpleAdapter(this, data, R.layout.item, from, to);

        lvStud = findViewById(R.id.lvStud);
        lvStud.setAdapter(sAdapter);
    }

    public void GetFind(String[]fsts, String[]lsts, String grp, int[]cns, int pht) {
        int count = 0;
        find = new ArrayList<Map<String, Object>>(fsts.length);
        Map<String, Object> m;
        for(int i = 0; i < fsts.length; i++){
            if (fsts[i].toUpperCase().equals(query.toUpperCase())){
                m = new HashMap<>();
                PutMap(find, m, i);
                count += 1;
            }else if (lsts[i].toUpperCase().equals(query.toUpperCase())){
                m = new HashMap<>();
                PutMap(find, m, i);
                count += 1;
            }
        }
        Toast.makeText(this, "Знайдено " + Integer.toString(count) + "збіг(а, ів)",
                Toast.LENGTH_LONG).show();

        String[]from = {ATTRIBUTE_NAME_FNAME, ATTRIBUTE_NAME_LNAME,
                ATTRIBUTE_NAME_GROUP, ATTRIBUTE_NAME_RANGE,
                ATTRIBUTE_NAME_PHOTO, ATTRIBUTE_NAME_COIN};
        int[]to = {R.id.tvfName, R.id.tvlName, R.id.tvgroup, R.id.tvrange, R.id.ivImg, R.id.tvcoin};

        sAdapter = new SimpleAdapter(this, find, R.layout.item_find, from, to);

        lvStud = findViewById(R.id.lvStud);
        lvStud.setAdapter(sAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);

        MenuItem search = menu.findItem(R.id.app_bar_search);
        SearchView searchView = (SearchView) search.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String qrt) {
                query = qrt;
                GetFind(fsts, lsts, grp, cns, pht);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return true;
            }
        });
        return true;
    }
}
