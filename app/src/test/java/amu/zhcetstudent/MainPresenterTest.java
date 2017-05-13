package amu.zhcetstudent;

import org.junit.After;
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
    ResultRepository resultRepository;

    @Mock
    MainView mainView;

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
    public void shouldShowResults() {
        Result testResult = new Result("14PEB049", "GF1032");

        Mockito.when(resultRepository
                .getResult("14PEB049", "GF1032"))
                .thenReturn(Observable.just(testResult));

        mainPresenter.loadResult("14PEB049", "GF1032");

        InOrder inOrder = Mockito.inOrder(mainView, resultRepository);

        inOrder.verify(mainView).showProgress(true);
        inOrder.verify(resultRepository).getResult("14PEB049", "GF1032");
        inOrder.verify(mainView).showResult(testResult);
        inOrder.verify(mainView).showProgress(false);
    }

    @Test
    public void shouldShowError() {
        Mockito.when(resultRepository
                .getResult("14PEB049", "GF1032"))
                .thenReturn(Observable.error(new RuntimeException()));

        mainPresenter.loadResult("14PEB049", "GF1032");

        InOrder inOrder = Mockito.inOrder(mainView, resultRepository);

        inOrder.verify(mainView).showProgress(true);
        inOrder.verify(resultRepository).getResult("14PEB049", "GF1032");
        inOrder.verify(mainView).showError(Mockito.anyString());
        inOrder.verify(mainView).showProgress(false);
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

    @After
    public void tearDown() {
        RxJavaPlugins.reset();
        RxAndroidPlugins.reset();
    }

}
