// Generated by view binder compiler. Do not edit!
package com.example.storesearching.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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

public final class FragmentUserinfochangeBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final EditText BirthdayEdit;

  @NonNull
  public final TextView BirthdayLabel;

  @NonNull
  public final EditText DescriptionTextEdit;

  @NonNull
  public final EditText PasswordEdit;

  @NonNull
  public final EditText UserNameEdit;

  @NonNull
  public final TextView UserpreferenLabel;

  @NonNull
  public final Button confirmButton;

  @NonNull
  public final TextView passwordLabel;

  @NonNull
  public final TextView textView6;

  @NonNull
  public final TextView usernameLabel;

  private FragmentUserinfochangeBinding(@NonNull ConstraintLayout rootView,
      @NonNull EditText BirthdayEdit, @NonNull TextView BirthdayLabel,
      @NonNull EditText DescriptionTextEdit, @NonNull EditText PasswordEdit,
      @NonNull EditText UserNameEdit, @NonNull TextView UserpreferenLabel,
      @NonNull Button confirmButton, @NonNull TextView passwordLabel, @NonNull TextView textView6,
      @NonNull TextView usernameLabel) {
    this.rootView = rootView;
    this.BirthdayEdit = BirthdayEdit;
    this.BirthdayLabel = BirthdayLabel;
    this.DescriptionTextEdit = DescriptionTextEdit;
    this.PasswordEdit = PasswordEdit;
    this.UserNameEdit = UserNameEdit;
    this.UserpreferenLabel = UserpreferenLabel;
    this.confirmButton = confirmButton;
    this.passwordLabel = passwordLabel;
    this.textView6 = textView6;
    this.usernameLabel = usernameLabel;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentUserinfochangeBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentUserinfochangeBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_userinfochange, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentUserinfochangeBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.BirthdayEdit;
      EditText BirthdayEdit = ViewBindings.findChildViewById(rootView, id);
      if (BirthdayEdit == null) {
        break missingId;
      }

      id = R.id.BirthdayLabel;
      TextView BirthdayLabel = ViewBindings.findChildViewById(rootView, id);
      if (BirthdayLabel == null) {
        break missingId;
      }

      id = R.id.DescriptionTextEdit;
      EditText DescriptionTextEdit = ViewBindings.findChildViewById(rootView, id);
      if (DescriptionTextEdit == null) {
        break missingId;
      }

      id = R.id.PasswordEdit;
      EditText PasswordEdit = ViewBindings.findChildViewById(rootView, id);
      if (PasswordEdit == null) {
        break missingId;
      }

      id = R.id.UserNameEdit;
      EditText UserNameEdit = ViewBindings.findChildViewById(rootView, id);
      if (UserNameEdit == null) {
        break missingId;
      }

      id = R.id.UserpreferenLabel;
      TextView UserpreferenLabel = ViewBindings.findChildViewById(rootView, id);
      if (UserpreferenLabel == null) {
        break missingId;
      }

      id = R.id.confirmButton;
      Button confirmButton = ViewBindings.findChildViewById(rootView, id);
      if (confirmButton == null) {
        break missingId;
      }

      id = R.id.passwordLabel;
      TextView passwordLabel = ViewBindings.findChildViewById(rootView, id);
      if (passwordLabel == null) {
        break missingId;
      }

      id = R.id.textView6;
      TextView textView6 = ViewBindings.findChildViewById(rootView, id);
      if (textView6 == null) {
        break missingId;
      }

      id = R.id.usernameLabel;
      TextView usernameLabel = ViewBindings.findChildViewById(rootView, id);
      if (usernameLabel == null) {
        break missingId;
      }

      return new FragmentUserinfochangeBinding((ConstraintLayout) rootView, BirthdayEdit,
          BirthdayLabel, DescriptionTextEdit, PasswordEdit, UserNameEdit, UserpreferenLabel,
          confirmButton, passwordLabel, textView6, usernameLabel);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
