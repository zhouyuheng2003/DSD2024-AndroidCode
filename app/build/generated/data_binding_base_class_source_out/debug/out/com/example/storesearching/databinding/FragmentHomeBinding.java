// Generated by view binder compiler. Do not edit!
package com.example.storesearching.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.storesearching.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentHomeBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final Button buttonSwitchSearchMode;

  @NonNull
  public final SearchView mainSearchView;

  @NonNull
  public final TextView textHome;

  @NonNull
  public final TextView textViewSearchMode;

  private FragmentHomeBinding(@NonNull ConstraintLayout rootView,
      @NonNull Button buttonSwitchSearchMode, @NonNull SearchView mainSearchView,
      @NonNull TextView textHome, @NonNull TextView textViewSearchMode) {
    this.rootView = rootView;
    this.buttonSwitchSearchMode = buttonSwitchSearchMode;
    this.mainSearchView = mainSearchView;
    this.textHome = textHome;
    this.textViewSearchMode = textViewSearchMode;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentHomeBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentHomeBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_home, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentHomeBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.button_SwitchSearchMode;
      Button buttonSwitchSearchMode = ViewBindings.findChildViewById(rootView, id);
      if (buttonSwitchSearchMode == null) {
        break missingId;
      }

      id = R.id.mainSearchView;
      SearchView mainSearchView = ViewBindings.findChildViewById(rootView, id);
      if (mainSearchView == null) {
        break missingId;
      }

      id = R.id.text_home;
      TextView textHome = ViewBindings.findChildViewById(rootView, id);
      if (textHome == null) {
        break missingId;
      }

      id = R.id.textView_SearchMode;
      TextView textViewSearchMode = ViewBindings.findChildViewById(rootView, id);
      if (textViewSearchMode == null) {
        break missingId;
      }

      return new FragmentHomeBinding((ConstraintLayout) rootView, buttonSwitchSearchMode,
          mainSearchView, textHome, textViewSearchMode);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
