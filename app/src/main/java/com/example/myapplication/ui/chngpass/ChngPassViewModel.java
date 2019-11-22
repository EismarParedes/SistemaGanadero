package com.example.myapplication.ui.chngpass;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ChngPassViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ChngPassViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}