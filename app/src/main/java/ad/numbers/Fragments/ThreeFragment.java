package ad.numbers.Fragments;

/**
 * Created by ADITYA on 25-09-2017.
 */

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import ad.numbers.R;


public class ThreeFragment extends Fragment {

    private ListView listView;
    ArrayAdapter adapter;

    public ThreeFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.favorites, container, false);
        listView = (ListView) view.findViewById(R.id.posts_list);

        final Set<String> tasksSet = PreferenceManager.getDefaultSharedPreferences(getActivity())
                .getStringSet("tasks_set", new HashSet<String>());
        final ArrayList<String> tasksList = new ArrayList<String>(tasksSet);

        adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1,
                tasksList);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        return view;
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            getFragmentManager().beginTransaction().detach(this).attach(this).commit();
        }
    }


}
