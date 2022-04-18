// Generated by Dagger (https://dagger.dev).
package com.example.expensetracker.ui;

import android.content.SharedPreferences;
import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import javax.inject.Provider;

@DaggerGenerated
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class MainActivity_MembersInjector implements MembersInjector<MainActivity> {
  private final Provider<SharedPreferences> preferencesProvider;

  public MainActivity_MembersInjector(Provider<SharedPreferences> preferencesProvider) {
    this.preferencesProvider = preferencesProvider;
  }

  public static MembersInjector<MainActivity> create(
      Provider<SharedPreferences> preferencesProvider) {
    return new MainActivity_MembersInjector(preferencesProvider);
  }

  @Override
  public void injectMembers(MainActivity instance) {
    injectPreferences(instance, preferencesProvider.get());
  }

  @InjectedFieldSignature("com.example.expensetracker.ui.MainActivity.preferences")
  public static void injectPreferences(MainActivity instance, SharedPreferences preferences) {
    instance.preferences = preferences;
  }
}