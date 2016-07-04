package com.santanderbici.amadav.santanderbici.settings;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.santanderbici.amadav.santanderbici.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by dgago on 06/06/2016.
 */
public class SettingsFragment extends Fragment {
    @BindView(R.id.relaSupport)
    RelativeLayout relaSupport;
    @BindView(R.id.relaRate)
    RelativeLayout relaRate;
    @BindView(R.id.relaApps)
    RelativeLayout relaApps;
    @BindView(R.id.relaCredits)
    RelativeLayout relaCredits;

    private Unbinder unbinder;

    private final String EMAIL = "amadavapps@gmail.com";
    private final String PACKAGENAME = "com.santanderbici.amadav.santanderbici";
    private final String DEVELOPER = "Amadav";
    private final String MARKET_ID = "market://details?id=";
    private final String WEB_ID = "https://play.google.com/store/apps/details?id=";
    private final String MARKET_PUB = "market://search?q=pub:";
    private final String WEB_PUB = "http://play.google.com/store/search?q=pub:";

    public SettingsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @OnClick({R.id.relaSupport, R.id.relaApps, R.id.relaRate, R.id.relaCredits})
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.relaSupport:
                launchEmail();
                break;

            case R.id.relaRate:
                launchPlayStore(MARKET_ID + PACKAGENAME, WEB_ID + PACKAGENAME);
                break;

            case R.id.relaApps:
                launchPlayStore(MARKET_PUB + DEVELOPER, WEB_PUB + DEVELOPER);
                break;

            case R.id.relaCredits:
                startActivity(new Intent(getActivity(), CreditsActivity.class));
                break;
        }
    }

    public void launchEmail() {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_EMAIL, EMAIL);
        intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.app_name));
        if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    public void launchPlayStore(String marketLink, String webLink) {
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(marketLink));
            startActivity(intent);
        } catch (android.content.ActivityNotFoundException anfe) {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(webLink));
            startActivity(intent);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
