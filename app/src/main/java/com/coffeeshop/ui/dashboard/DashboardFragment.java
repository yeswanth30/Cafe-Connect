package com.coffeeshop.ui.dashboard;

import static android.content.Context.MODE_PRIVATE;
import static com.coffeeshop.DBHandler.sharedpreference.clearPreference;
import static com.coffeeshop.DBHandler.sharedpreference.getSharedPreferences;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.coffeeshop.R;
import com.coffeeshop.databinding.FragmentDashboardBinding;
import com.coffeeshop.login;
import com.coffeeshop.mycart;

public class DashboardFragment extends Fragment {

    Button logout;
    private FragmentDashboardBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState)
    {
        DashboardViewModel dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);


        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        logout = root.findViewById(R.id.logout);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedpreferences = getContext().getSharedPreferences("my preferences", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.clear();
                editor.apply();
                Intent intent = new Intent(getContext(), login.class);
                startActivity(intent);
                //Toast.makeText(getContext(), "Welcome to Cart Page", Toast.LENGTH_LONG).show();
            }
        });
//        final TextView textView = binding.textDashboard;
//        dashboardViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}