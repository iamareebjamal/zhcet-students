package amu.zhcetstudent;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import amu.zhcetstudent.contract.model.ResultRepository;
import amu.zhcetstudent.contract.presenter.MainPresenter;
import amu.zhcetstudent.contract.view.MainView;
import amu.zhcetstudent.data.model.Result;
import amu.zhcetstudent.ui.presenter.MainActivityPresenter;
import io.reactivex.Observable;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.Schedulers;

public class MainPresenterTest {

    @Mock
    MainView mainView;
    @Mock
    ResultRepository resultRepository;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    private MainPresenter mainPresenter;

    @Before
    public void setUp() {

        mainPresenter = new MainActivityPresenter(mainView, resultRepository);
        RxJavaPlugins.setIoSchedulerHandler(scheduler -> Schedulers.trampoline());
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(schedulerCallable -> Schedulers.trampoline());
    }

    @Test
    public void shouldShowResult() {
        Result res = new Result("14peb250", "gh0022");
        Mockito.when(resultRepository
                .getResult("14peb250", "gh0022"))
                .thenReturn(Observable.just(res));
        mainPresenter.loadResult("14peb250", "gh0022");
        InOrder inorder = Mockito.inOrder(mainView);
        inorder.verify(mainView).showProgress(true);
        inorder.verify(mainView).showResult(res);
        inorder.verify(mainView).showProgress(false);
    }

    @Test
    public void shouldShowError() {
        Mockito.when(resultRepository
                .getResult("14peb250", "gh0022"))
                .thenReturn(Observable.error(new RuntimeException("This is an error")));
        mainPresenter.loadResult("14peb250", "gh0022");
        InOrder inorder = Mockito.inOrder(mainView);
        inorder.verify(mainView).showProgress(true);
        inorder.verify(mainView).showError("This is an error");
        inorder.verify(mainView).showProgress(false);
    }

    @Test
    public void shouldShowNoResult() {
        Mockito.when(resultRepository
                .getResult("14peb250", "gh0022"))
                .thenReturn(null);
        mainPresenter.loadResult("14peb250", "gh0022");
        Mockito.verify(mainView).showError("NO_RESULT");
    }

    @Test
    public void shouldShowErrorOnReceivingError() {
        Result result = new Result("14peb250", "gh0022");
        result.setError(true);
        result.setMessage("Wrong Credential");
        Mockito.when(resultRepository
                .getResult("14peb250", "gh0022"))
                .thenReturn(Observable.just(result));
        mainPresenter.loadResult("14peb250", "gh0022");
        Mockito.verify(mainView).showError(result.getMessage());
    }

}
