package com.example.lastfmmusic.screens.track.mvp;

import com.example.lastfmmusic.data.track.Tracks;
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
public class TrackPresenterTest {

    private String query = "track.search";
    private String track = "viva la vida";

    private String api = "9a160096a54171ae4f985c3e46b55a8d";

    @Mock
    private WebService service;
    @Mock
    private TrackContract.TrackView trackView;
    @Mock
    private Tracks tracks;


    private InOrder inOrder;

    private TrackPresenter presenter;


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
        presenter = new TrackPresenter(trackView, service);
        tracks = new Tracks();
        inOrder = inOrder(trackView, service);

    }

    @Test
    public void getTrack() {
        when(service.getTracks(query, track, api, "json"))
                .thenReturn(Observable.just(tracks));
        presenter.getTrack(track);
        inOrder.verify(trackView).showDetail(tracks);
        inOrder.verifyNoMoreInteractions();
    }
}