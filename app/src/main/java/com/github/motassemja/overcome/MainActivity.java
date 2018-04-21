package com.github.motassemja.overcome;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.github.motassemja.overcome.ui.fragments.AuthenticationDialogFragment;
import com.github.motassemja.overcome.ui.fragments.ChooseLevelFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            replaceFragment(new ChooseLevelFragment(), false);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_parent_control) {
            showAuthenticationDialog();
            return true;
        }
        return super.onOptionsItemSelected(item);

    }

    private void showAuthenticationDialog() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        Fragment fragmentByTag = getSupportFragmentManager().findFragmentByTag(AuthenticationDialogFragment.TAG);

        if (fragmentByTag != null) {
            fragmentTransaction.remove(fragmentByTag);
        }
        fragmentTransaction.addToBackStack(null);

        AuthenticationDialogFragment authenticationDialogFragment = new AuthenticationDialogFragment();
        authenticationDialogFragment.show(fragmentTransaction, AuthenticationDialogFragment.TAG);

    }

    public void replaceFragment(Fragment fragment, boolean addToBackStack) {
        FragmentTransaction replace = getSupportFragmentManager().beginTransaction().replace(R.id.replace_me, fragment);
        if (addToBackStack) {
            replace.addToBackStack(null);
        }
        replace.commit();
    }
}
