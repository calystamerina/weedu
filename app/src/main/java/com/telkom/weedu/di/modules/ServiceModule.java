package com.telkom.weedu.di.modules;

import android.app.Service;
import android.content.Context;

import com.telkom.weedu.service.downloadattachment.DownloadAttachmentPresenter;
import com.telkom.weedu.service.downloadattachment.DownloadAttachmentView;
import com.telkom.weedu.service.downloadattachment.IDownloadAttamentPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by sidiqpermana on 5/14/17.
 */

@Module
public class ServiceModule {
    private Service service;

    public ServiceModule(Service service) {
        this.service = service;
    }

    @Provides
    Context proviceContext(){
        return service;
    }

    @Provides
    IDownloadAttamentPresenter<DownloadAttachmentView> provideDownloadAttamentPresenter(DownloadAttachmentPresenter<DownloadAttachmentView> presenter) {
        return presenter;
    }
}
