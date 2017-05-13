package amu.zhcetstudent.contract.view;

import amu.zhcetstudent.data.model.Result;

public interface MainView {

    void showResult(Result result);

    void showError(String error);

    void showProgress(boolean show);

}