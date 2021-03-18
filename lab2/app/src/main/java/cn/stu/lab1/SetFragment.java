package cn.stu.lab1;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import java.util.concurrent.TimeUnit;


public class SetFragment extends Fragment {
    public static final String FIRST_MESSAGE = "FIRST_MESSAGE";
    public static final String SECOND_MESSAGE = "SECOND_MESSAGE";
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_set, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button buttonReset = view.findViewById(R.id.button_send);
        buttonReset.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                EditText editTextFirstName = (EditText) view.findViewById(R.id.editTextTextPersonName);
                EditText editTextSecondName = (EditText) view.findViewById(R.id.editTextTextPersonName2);
                String messageTextFirstName = editTextFirstName.getText().toString();
                String messageTextSecondName = editTextSecondName.getText().toString();
                final InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getView().getWindowToken(), 0);
                if (savedInstanceState == null){
                    Bundle bundle = new Bundle();
                    bundle.putString(FIRST_MESSAGE, messageTextFirstName);
                    bundle.putString(SECOND_MESSAGE, messageTextSecondName);
                    HomeFragment homeFragment = new HomeFragment();
                    homeFragment.setArguments(bundle);
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .setReorderingAllowed(true)
                            .add(R.id.fragmentContainer, homeFragment,null)
                            .commit();
                }
            }
        });
    }
}
