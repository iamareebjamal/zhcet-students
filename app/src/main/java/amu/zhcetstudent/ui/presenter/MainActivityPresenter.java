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
    public void loadResult(String facultyNo, String enrollmentNo) {
        mainView.showProgress(true);
        resultRepository.getResult(facultyNo, enrollmentNo)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> {
                            if (result.getError()) {
                                mainView.showError(result.getMessage());
                            } else {
                                mainView.showResult(result);
                            }
                        },
                        throwable -> mainView.showError(throwable.getMessage()));
        mainView.showProgress(false);
    }
}
