package amu.zhcetstudent.ui.presenter;

import amu.zhcetstudent.contract.presenter.MainPresenter;
import amu.zhcetstudent.contract.view.MainView;

public class MainActivityPresenter implements MainPresenter {

    private MainView mainView;

    private boolean error;

    public MainActivityPresenter(MainView mainView) {
        this.mainView = mainView;
    }

    @Override
    public void loadData() {
        mainView.showData("Honestly Brutal");
    }
}
