package amu.zhcetstudent.contract;

public class MainContract {

    public interface MainPresenter {

        void loadData();

    }

    public interface MainView {

        void showData(String data);

    }

}