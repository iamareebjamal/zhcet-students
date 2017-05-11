package amu.zhcetstudent;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.Arrays;

import amu.zhcetstudent.contract.presenter.MainPresenter;
import amu.zhcetstudent.contract.view.MainView;
import amu.zhcetstudent.ui.presenter.MainActivityPresenter;

public class MainPresenterTest {

    @Mock
    MainView mainView;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    private MainPresenter mainPresenter;

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
