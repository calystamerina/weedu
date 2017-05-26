package com.telkom.weedu.di.components;

import com.telkom.weedu.di.PerService;
import com.telkom.weedu.di.modules.ServiceModule;
import com.telkom.weedu.service.downloadattachment.DownloadAttachmentService;

import dagger.Component;

/**
 * Created by sidiqpermana on 5/14/17.
 */

@PerService
@Component(modules = ServiceModule.class, dependencies = ApplicationComponent.class)
public interface ServiceComponent {
    void inject(DownloadAttachmentService downloadAttachmentService);
}
