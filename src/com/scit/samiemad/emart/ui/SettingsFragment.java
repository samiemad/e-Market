package com.scit.samiemad.emart.ui;

import java.util.Locale;

import com.scit.samiemad.emart.R;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.PreferenceCategory;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.preference.RingtonePreference;
import android.text.TextUtils;
import android.util.DisplayMetrics;

public class SettingsFragment extends PreferenceFragment {

	private Context context;
	private static Locale myLocale;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setupSimplePreferencesScreen();
	}

	@TargetApi(23)
	@Override
	public void onAttach(Context c) {
		super.onAttach(c);
		context = c;
	}

	@Override
	public void onAttach(Activity c) {
		super.onAttach(c);
		context = c;
	}

	/**
	 * Shows the simplified settings UI if the device configuration if the
	 * device configuration dictates that a simplified, single-pane UI should be
	 * shown.
	 */
	private void setupSimplePreferencesScreen() {

		// In the simplified UI, fragments are not used at all and we instead
		// use the older PreferenceActivity APIs.

		// Add 'general' preferences.
		addPreferencesFromResource(R.xml.pref_general);

		// Add 'notifications' preferences, and a corresponding header.
		PreferenceCategory fakeHeader = new PreferenceCategory(context);
		fakeHeader.setTitle(R.string.pref_header_notifications);
		getPreferenceScreen().addPreference(fakeHeader);
		addPreferencesFromResource(R.xml.pref_notification);

		// Add 'data and sync' preferences, and a corresponding header.
		fakeHeader = new PreferenceCategory(context);
		fakeHeader.setTitle(R.string.pref_header_data_sync);
		getPreferenceScreen().addPreference(fakeHeader);
		addPreferencesFromResource(R.xml.pref_data_sync);

		// Bind the summaries of EditText/List/Dialog/Ringtone preferences to
		// their values. When their values change, their summaries are updated
		// to reflect the new value, per the Android Design guidelines.
		bindPreferenceSummaryToValue(findPreference("name"));
		bindPreferenceSummaryToValue(findPreference("email"));
		bindPreferenceSummaryToValue(findPreference("notifications_new_message_ringtone"));
		bindPreferenceSummaryToValue(findPreference("sync_frequency"));
		findPreference("language").setOnPreferenceChangeListener(languageListener);
	}

	/**
	 * A preference value change listener that updates the preference's summary
	 * to reflect its new value.
	 */
	private static Preference.OnPreferenceChangeListener sBindPreferenceSummaryToValueListener = new Preference.OnPreferenceChangeListener() {
		@Override
		public boolean onPreferenceChange(Preference preference, Object value) {
			String stringValue = value.toString();

			if (preference instanceof ListPreference) {
				// For list preferences, look up the correct display value in
				// the preference's 'entries' list.
				ListPreference listPreference = (ListPreference) preference;
				int index = listPreference.findIndexOfValue(stringValue);

				// Set the summary to reflect the new value.
				preference.setSummary(index >= 0 ? listPreference.getEntries()[index] : null);

			} else if (preference instanceof RingtonePreference) {
				// For ringtone preferences, look up the correct display value
				// using RingtoneManager.
				if (TextUtils.isEmpty(stringValue)) {
					// Empty values correspond to 'silent' (no ringtone).
					preference.setSummary(R.string.pref_ringtone_silent);

				} else {
					Ringtone ringtone = RingtoneManager.getRingtone(preference.getContext(), Uri.parse(stringValue));

					if (ringtone == null) {
						// Clear the summary if there was a lookup error.
						preference.setSummary(null);
					} else {
						// Set the summary to reflect the new ringtone display
						// name.
						String name = ringtone.getTitle(preference.getContext());
						preference.setSummary(name);
					}
				}

			} else {
				// For all other preferences, set the summary to the value's
				// simple string representation.
				preference.setSummary(stringValue);
			}
			return true;
		}
	};

	OnPreferenceChangeListener languageListener = new OnPreferenceChangeListener() {

		@Override
		public boolean onPreferenceChange(Preference preference, Object newValue) {
			if (preference.getKey().equals("language")) {
				setLocale((String) newValue);
			}
			ListPreference listPreference = (ListPreference) preference;
			int index = listPreference.findIndexOfValue(newValue.toString());

			// Set the summary to reflect the new value.
			preference.setSummary(index >= 0 ? listPreference.getEntries()[index] : null);
			return true;
		}
	};

	/**
	 * Binds a preference's summary to its value. More specifically, when the
	 * preference's value is changed, its summary (line of text below the
	 * preference title) is updated to reflect the value. The summary is also
	 * immediately updated upon calling this method. The exact display format is
	 * dependent on the type of preference.
	 *
	 * @see #sBindPreferenceSummaryToValueListener
	 */
	private static void bindPreferenceSummaryToValue(Preference preference) {
		// Set the listener to watch for value changes.
		preference.setOnPreferenceChangeListener(sBindPreferenceSummaryToValueListener);

		// Trigger the listener immediately with the preference's
		// current value.
		sBindPreferenceSummaryToValueListener.onPreferenceChange(preference, PreferenceManager
				.getDefaultSharedPreferences(preference.getContext()).getString(preference.getKey(), ""));
	}

	public void setLocale(String lang) {
		myLocale = new Locale(lang);
		Resources res = getResources();
		DisplayMetrics dm = res.getDisplayMetrics();
		Configuration conf = res.getConfiguration();
		conf.locale = myLocale;
		res.updateConfiguration(conf, dm);
		Intent refresh = new Intent(getActivity(), getActivity().getClass());
		refresh.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(refresh);

	}

}
