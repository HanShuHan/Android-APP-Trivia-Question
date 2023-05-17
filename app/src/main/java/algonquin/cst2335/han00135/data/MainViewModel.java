package algonquin.cst2335.han00135.data;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainViewModel extends ViewModel {
    public MutableLiveData<String> editStr = new MutableLiveData<>();
    public MutableLiveData<Boolean> isChecked = new MutableLiveData<>();
}
