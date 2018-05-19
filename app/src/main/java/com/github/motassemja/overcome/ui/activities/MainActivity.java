package com.github.motassemja.overcome.ui.activities;

import android.Manifest;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.github.motassemja.overcome.R;
import com.github.motassemja.overcome.model.Feeling;
import com.github.motassemja.overcome.ui.fragments.AddFeelingFragment;
import com.github.motassemja.overcome.ui.fragments.AuthenticationDialogFragment;
import com.github.motassemja.overcome.ui.fragments.ChooseLevelFragment;
import com.github.motassemja.overcome.ui.fragments.FirstLevelFragment;
import com.github.motassemja.overcome.ui.fragments.ParentControlPanelFragment;
import com.github.motassemja.overcome.viewmodel.SingleFeelingViewModel;

import java.io.ByteArrayOutputStream;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements ParentControlPanelFragment.ParentControlPanelInteractor
        , AddFeelingFragment.AddFeelingInteractor
        , ChooseLevelFragment.ChooseLevelInteractor {

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

    /**
     * Request permissions code
     */
    private static final int CODE_PERMISSION_REQUEST = 1000;

    @BindView(R.id.container_main_activity)
    ConstraintLayout mContainerMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

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

    /**
     * Builds and shows the authentication dialog fragment
     */
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

    /**
     * Replaces current fragment
     *
     * @param fragment       to show
     * @param addToBackStack true if should be added to back stack
     */
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
        /*Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), REQ_CODE_CHOOSE_PICTURE);*/

        checkRequiredPermissions();

    }

    /**
     * Checks all required permissions by the app
     */
    private void checkRequiredPermissions() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE},
                    CODE_PERMISSION_REQUEST);
        } else {
            buildChooserDialog();
        }
    }

    /**
     * Builds and shows a dialog to choose an action
     */
    private void buildChooserDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);


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

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case CODE_PERMISSION_REQUEST: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                    buildChooserDialog();
                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Snackbar.make(mContainerMain, "Permission Needed!", Snackbar.LENGTH_SHORT).show();
                }
            }

            // other 'case' lines to check for other
            // permissions this app might request.
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && data != null) {
            SingleFeelingViewModel viewModel = ViewModelProviders.of(this).get(SingleFeelingViewModel.class);
            switch (requestCode) {
                case REQ_CODE_CAPTURE_PICTURE:
                    /*Bitmap bmp = (Bitmap) data.getExtras().get("data");
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();*/
                    final Bundle extras = data.getExtras();
                    if (extras != null) {
                        //Get image
                        Bitmap bmp = extras.getParcelable("data");
                        ByteArrayOutputStream stream = new ByteArrayOutputStream();
                        bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
                        byte[] byteArray = stream.toByteArray();
                        // convert byte array to Bitmap
                    /*Bitmap bitmap = BitmapFactory.decodeByteArray(byteArray, 0,
                            byteArray.length);*/
                        Feeling feeling = new Feeling("");
                        feeling.setFeelingImage(byteArray);
                        viewModel.getFeeling().setValue(feeling);
                        break;
                    }
            }
        }
    }

    @Override
    public void onFirstLevelChosen() {
        replaceFragment(new FirstLevelFragment(), true);
    }

    @Override
    public void onSecondLevelChosen() {

    }

    @Override
    public void onThirdLevelChosen() {

    }
}
