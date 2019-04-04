package com.example.lastfmmusic.screens.music.mvp;

import com.example.lastfmmusic.data.artist.Artists;
import com.example.lastfmmusic.network.WebService;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.internal.schedulers.ExecutorScheduler;
import io.reactivex.plugins.RxJavaPlugins;

import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MusicPresenterTest {

    private String query = "artist.search";
    private String artistName = "cher";
    private String api = "9a160096a54171ae4f985c3e46b55a8d";
    private String error = "error message";

    @Mock
    private WebService service;
    @Mock
    private MusicContract.MusicView view;
    @Mock
    private Artists artists;

    private InOrder inOrder;

    private MusicPresenter presenter;


    @BeforeClass
    public static void setupRxSchedulers() {
        Scheduler scheduler = new Scheduler() {
            @Override
            public Worker createWorker() {
                return new ExecutorScheduler.ExecutorWorker(Runnable::run);
            }
        };
        RxJavaPlugins.setInitIoSchedulerHandler(schedulerCallable -> scheduler);
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(schedulerCallable -> scheduler);
    }

    @Before
    public void setUp() throws Exception {
        presenter = new MusicPresenter(view, service);
        artists = new Artists();

        inOrder = inOrder(view, service);

    }

    @Test
    public void getArtists_IsDataFetched_displayResults() {

        when(service.getArtist(query, artistName, api, "json"))
                .thenReturn(Observable.just(artists));
        presenter.getArtists(artistName);
        inOrder.verify(view).showArtists(artists);
        inOrder.verifyNoMoreInteractions();
    }

    @Test
    public void handleError_GetErrorMessage_displayMessage() {
        when(service.getArtist(query, artistName, api, "json"))
                .thenReturn(Observable.error(new Throwable(error)));
        presenter.getArtists(artistName);
        inOrder.verify(view).showMessages(error);
        inOrder.verifyNoMoreInteractions();

    }

}