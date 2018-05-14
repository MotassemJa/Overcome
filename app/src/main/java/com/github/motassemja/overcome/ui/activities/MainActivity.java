package com.github.motassemja.overcome.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.github.motassemja.overcome.R;
import com.github.motassemja.overcome.ui.fragments.AddFeelingFragment;
import com.github.motassemja.overcome.ui.fragments.AuthenticationDialogFragment;
import com.github.motassemja.overcome.ui.fragments.ChooseLevelFragment;
import com.github.motassemja.overcome.ui.fragments.ParentControlPanelFragment;

public class MainActivity extends AppCompatActivity implements ParentControlPanelFragment.ParentControlPanelInteractor
        , AddFeelingFragment.AddFeelingInteractor {

    /**
     * For debugging
     */
    private static final String TAG = MainActivity.class.getSimpleName();

    /**
     * Pick image from camera intent code
     */
    private static final int REQ_CODE_CAPTURE_PICTURE = 100;

    /**
     * Pick image from gallery intent code
     */
    private static final int REQ_CODE_CHOOSE_PICTURE = 200;

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

        AuthenticationDialogFragment authenticationDialogFragment = AuthenticationDialogFragment.newInstance(() ->
                replaceFragment(new ParentControlPanelFragment(), true)
        );
        authenticationDialogFragment.show(fragmentTransaction, AuthenticationDialogFragment.TAG);

    }

    public void replaceFragment(Fragment fragment, boolean addToBackStack) {
        FragmentTransaction replace = getSupportFragmentManager().beginTransaction().replace(R.id.replace_me, fragment);
        if (addToBackStack) {
            replace.addToBackStack(null);
        }
        replace.commit();
        Log.d(TAG, "Fragment replaced!");
    }

    @Override
    public void onFabClicked() {
        replaceFragment(new AddFeelingFragment(), true);
    }

    @Override
    public void onTakePictureButtonClicked() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        /*setMessage(R.string.dialog_msg_add_img)*/
        builder.setItems(new CharSequence[]{"Take a picture with camera", "Choose from gallery"}, (dialogInterface, i) -> {
            if (i == 0) {
                Intent pick = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(pick, REQ_CODE_CAPTURE_PICTURE);
            } else if (i == 1) {
                Intent pickPhoto = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(pickPhoto, REQ_CODE_CHOOSE_PICTURE);
            }
        }).setTitle(R.string.title_dialog_add_img);

        AlertDialog alertDialog = builder.create();

        alertDialog.show();
    }
}
