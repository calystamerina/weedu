package com.telkom.weedu.view.home;

import com.telkom.weedu.data.DataManager;
import com.telkom.weedu.data.api.model.QBacaBook;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.LinkedList;

/**
 * Created by ghiyatshanif on 5/1/17.
 */
public class HomePresenterTest {
    @Mock
    DataManager dataManager;
    @Mock
    HomePresenter<HomeView> homePresenter;
    @Mock
    LinkedList<QBacaBook> qBacaBooks;


    @Before
    public void setupHomePresenter() {
        // Mockito has a very convenient way to inject mocks by using the @Mock annotation. To
        // inject the mocks in the test the initMocks method needs to be called.
        MockitoAnnotations.initMocks(this);

        // Get a reference to the class under test
    }

    @Test
    public void loadRecomendation() throws Exception {

    }

    @Test
    public void loadUnreadMail() throws Exception {

    }

}