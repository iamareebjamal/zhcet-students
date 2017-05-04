package amu.zhcetstudent.ui.presenter;

import amu.zhcetstudent.contract.MainContract.MainPresenter;
import amu.zhcetstudent.contract.MainContract.MainView;

public class MainActivityPresenter implements MainPresenter {

    private MainView mainView;

    public MainActivityPresenter(MainView mainView) {
        this.mainView = mainView;
    }

    @Override
    public void loadData() {
        mainView.showData("Honestly Brutal");
    }

}
