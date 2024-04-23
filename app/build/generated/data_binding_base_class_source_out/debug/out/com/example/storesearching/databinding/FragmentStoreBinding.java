// Generated by view binder compiler. Do not edit!
package com.example.storesearching.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.storesearching.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentStoreBinding implements ViewBinding {
  @NonNull
  private final FrameLayout rootView;

  @NonNull
  public final Button confirmButton;

  @NonNull
  public final ImageButton ratingButton1;

  @NonNull
  public final ImageButton ratingButton2;

  @NonNull
  public final ImageButton ratingButton3;

  @NonNull
  public final ImageButton ratingButton4;

  @NonNull
  public final ImageButton ratingButton5;

  @NonNull
  public final TextView textViewItemDescription;

  @NonNull
  public final TextView textViewItemName;

  private FragmentStoreBinding(@NonNull FrameLayout rootView, @NonNull Button confirmButton,
      @NonNull ImageButton ratingButton1, @NonNull ImageButton ratingButton2,
      @NonNull ImageButton ratingButton3, @NonNull ImageButton ratingButton4,
      @NonNull ImageButton ratingButton5, @NonNull TextView textViewItemDescription,
      @NonNull TextView textViewItemName) {
    this.rootView = rootView;
    this.confirmButton = confirmButton;
    this.ratingButton1 = ratingButton1;
    this.ratingButton2 = ratingButton2;
    this.ratingButton3 = ratingButton3;
    this.ratingButton4 = ratingButton4;
    this.ratingButton5 = ratingButton5;
    this.textViewItemDescription = textViewItemDescription;
    this.textViewItemName = textViewItemName;
  }

  @Override
  @NonNull
  public FrameLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentStoreBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentStoreBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_store, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentStoreBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.confirmButton;
      Button confirmButton = ViewBindings.findChildViewById(rootView, id);
      if (confirmButton == null) {
        break missingId;
      }

      id = R.id.ratingButton1;
      ImageButton ratingButton1 = ViewBindings.findChildViewById(rootView, id);
      if (ratingButton1 == null) {
        break missingId;
      }

      id = R.id.ratingButton2;
      ImageButton ratingButton2 = ViewBindings.findChildViewById(rootView, id);
      if (ratingButton2 == null) {
        break missingId;
      }

      id = R.id.ratingButton3;
      ImageButton ratingButton3 = ViewBindings.findChildViewById(rootView, id);
      if (ratingButton3 == null) {
        break missingId;
      }

      id = R.id.ratingButton4;
      ImageButton ratingButton4 = ViewBindings.findChildViewById(rootView, id);
      if (ratingButton4 == null) {
        break missingId;
      }

      id = R.id.ratingButton5;
      ImageButton ratingButton5 = ViewBindings.findChildViewById(rootView, id);
      if (ratingButton5 == null) {
        break missingId;
      }

      id = R.id.textView_itemDescription;
      TextView textViewItemDescription = ViewBindings.findChildViewById(rootView, id);
      if (textViewItemDescription == null) {
        break missingId;
      }

      id = R.id.textView_itemName;
      TextView textViewItemName = ViewBindings.findChildViewById(rootView, id);
      if (textViewItemName == null) {
        break missingId;
      }

      return new FragmentStoreBinding((FrameLayout) rootView, confirmButton, ratingButton1,
          ratingButton2, ratingButton3, ratingButton4, ratingButton5, textViewItemDescription,
          textViewItemName);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
