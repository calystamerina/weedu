package com.telkom.weedu.di.components;

import com.telkom.weedu.di.PerActivity;
import com.telkom.weedu.di.modules.ActivityModule;
import com.telkom.weedu.view.detailhistory.DetailHistoryActivity;
import com.telkom.weedu.view.about.AboutActivity;
import com.telkom.weedu.view.composeemail.ComposeEmailActivity;
import com.telkom.weedu.view.contactus.ContactUsActivity;
import com.telkom.weedu.view.dashboard.DashboardFragment;
import com.telkom.weedu.view.detailbook.DetailBookActivity;
import com.telkom.weedu.view.editprofile.EditProfileActivity;
import com.telkom.weedu.view.emailconfirmation.EmailConfirmationActivity;
import com.telkom.weedu.view.faq.FaqActivity;
import com.telkom.weedu.view.help.HelpFragment;
import com.telkom.weedu.view.home.HomeFragment;

import com.telkom.weedu.view.mail.MailActivity;
import com.telkom.weedu.view.mailbox.MailboxFragment;
import com.telkom.weedu.view.intro.IntroductionActivity;
import com.telkom.weedu.view.login.LoginActivity;
import com.telkom.weedu.view.maildetail.MailDetailActivity;
import com.telkom.weedu.view.main.MainActivity;
import com.telkom.weedu.view.myaccount.MyAccountFragment;
import com.telkom.weedu.view.register.RegisterActivity;
import com.telkom.weedu.view.resetpassword.ResetPasswordActivity;
import com.telkom.weedu.view.searchmail.SearchMailActivity;
import com.telkom.weedu.view.sent.SentFragment;
import com.telkom.weedu.view.splash.SplashScreenActivity;
import com.telkom.weedu.view.starred.StarredFragment;
import com.telkom.weedu.view.topup.TopUpActivity;
import com.telkom.weedu.view.webview.WebViewActivity;

import dagger.Component;

/**
 * Created by ghiyatshanif on 2/8/17.
 */

@PerActivity
@Component(modules = ActivityModule.class, dependencies = ApplicationComponent.class)
public interface ActivityComponent {

    void inject(MainActivity mainActivity);

    void inject(SplashScreenActivity splashScreenActivity);

    void inject(LoginActivity loginActivity);

    void inject(RegisterActivity registerActivity);

    void inject(EmailConfirmationActivity emailConfirmationActivity);

    void inject(HomeFragment homeFragment);

    void inject(DashboardFragment dashboardFragment);

    void inject(MyAccountFragment myAccountFragment);

    void inject(HelpFragment helpFragment);

    void inject(FaqActivity faqActivity);

    void inject(TopUpActivity topUpActivity);

    void inject(ContactUsActivity contactUsActivity);

    void inject(WebViewActivity webViewActivity);

    void inject(AboutActivity aboutActivity);

    void inject(EditProfileActivity editProfileActivity);

    void inject(ResetPasswordActivity resetPasswordActivity);

    void inject(MailActivity inboxActivity);

    void inject(IntroductionActivity introductionActivity);

    void inject(MailboxFragment mailboxFragment);

    void inject(StarredFragment starredFragment);

    void inject(SentFragment sentFragment);

    void inject(ComposeEmailActivity composeEmailActivity);

    void inject(DetailBookActivity detailBookActivity);

    void inject(MailDetailActivity mailDetailActivity);

    void inject(DetailHistoryActivity detailHistoryActivity);

    void inject(SearchMailActivity searchMailActivity);
}
