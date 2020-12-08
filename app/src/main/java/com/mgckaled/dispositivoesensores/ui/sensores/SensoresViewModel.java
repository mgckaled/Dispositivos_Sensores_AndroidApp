package com.mgckaled.dispositivoesensores.ui.sensores;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SensoresViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public SensoresViewModel() {
        mText = new MutableLiveData<>();
    }

    public LiveData<String> getText() {
        return mText;
    }
}