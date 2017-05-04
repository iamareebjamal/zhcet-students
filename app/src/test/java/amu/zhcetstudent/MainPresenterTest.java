package amu.zhcetstudent;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import amu.zhcetstudent.contract.MainContract;
import amu.zhcetstudent.ui.presenter.MainActivityPresenter;

@RunWith(JUnit4.class)
public class MainPresenterTest {

    @Mock
    MainContract.MainView mainView;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    public MainContract.MainPresenter mainPresenter;

    @Before
    public void setUp() {
        mainPresenter = new MainActivityPresenter(mainView);
    }

    @Test
    public void shouldShowData() {
        mainPresenter.loadData();

        Mockito.verify(mainView).showData("Honestly Brutal");
    }

}
