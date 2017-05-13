package amu.zhcetstudent.ui.presenter;

import amu.zhcetstudent.contract.model.ResultRepository;
import amu.zhcetstudent.contract.presenter.MainPresenter;
import amu.zhcetstudent.contract.view.MainView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MainActivityPresenter implements MainPresenter {

    private MainView mainView;
    private ResultRepository resultRepository;

    public MainActivityPresenter(MainView mainView, ResultRepository resultRepository) {
        this.mainView = mainView;
        this.resultRepository = resultRepository;
    }

    @Override
    public void loadResult(String facultyNo, String enrolmentNo) {

        mainView.showProgress(true);

        resultRepository.getResult(facultyNo, enrolmentNo)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> {
                    if (result.getError()) {
                        mainView.showError(result.getMessage());
                    } else {
                        mainView.showResult(result);
                    }
                    mainView.showProgress(false);
                }, throwable -> {
                    mainView.showError("Network Error Occurred");
                    mainView.showProgress(false);
                });
    }
}
