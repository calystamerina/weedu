package com.telkom.weedu.di.modules;

import android.app.Activity;
import android.content.Context;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.telkom.weedu.view.detailhistory.DetailHistoryPresenter;
import com.telkom.weedu.view.detailhistory.DetailHistoryView;
import com.telkom.weedu.view.detailhistory.IDetailHistoryPresenter;
import com.telkom.weedu.view.about.AboutPresenter;
import com.telkom.weedu.view.about.AboutView;
import com.telkom.weedu.view.about.IAboutPresenter;
import com.telkom.weedu.view.composeemail.ComposePresenter;
import com.telkom.weedu.view.composeemail.ComposeView;
import com.telkom.weedu.view.composeemail.IComposePresenter;
import com.telkom.weedu.view.contactus.ContactUsPresenter;
import com.telkom.weedu.view.contactus.ContactUsView;
import com.telkom.weedu.view.contactus.IContactUsPresenter;
import com.telkom.weedu.view.dashboard.DashboardPresenter;
import com.telkom.weedu.view.dashboard.DashboardView;
import com.telkom.weedu.view.dashboard.IDashboardPresenter;
import com.telkom.weedu.view.detailbook.DetailBookPresenter;
import com.telkom.weedu.view.detailbook.DetailBookView;
import com.telkom.weedu.view.detailbook.IDetailBookPresenter;
import com.telkom.weedu.view.editprofile.EditProfilePresenter;
import com.telkom.weedu.view.editprofile.EditProfileView;
import com.telkom.weedu.view.editprofile.IEditProfilePresenter;
import com.telkom.weedu.view.emailconfirmation.EmailConfirmationPresenter;
import com.telkom.weedu.view.emailconfirmation.EmailConfirmationView;
import com.telkom.weedu.view.emailconfirmation.IEmailConfirmationPresenter;
import com.telkom.weedu.view.faq.FaqPresenter;
import com.telkom.weedu.view.faq.FaqView;
import com.telkom.weedu.view.faq.IFaqPresenter;
import com.telkom.weedu.view.help.HelpPresenter;
import com.telkom.weedu.view.help.HelpView;
import com.telkom.weedu.view.help.IHelpPresenter;
import com.telkom.weedu.view.home.HomePresenter;
import com.telkom.weedu.view.home.HomeView;
import com.telkom.weedu.view.home.IHomePresenter;
import com.telkom.weedu.view.intro.IIntroItemPresenter;
import com.telkom.weedu.view.intro.IIntroductionPresenter;
import com.telkom.weedu.view.intro.IntroItemPresenter;
import com.telkom.weedu.view.intro.IntroItemView;
import com.telkom.weedu.view.intro.IntroductionPresenter;
import com.telkom.weedu.view.intro.IntroductionView;
import com.telkom.weedu.view.login.ILoginPresenter;
import com.telkom.weedu.view.login.LoginPresenter;
import com.telkom.weedu.view.login.LoginView;
import com.telkom.weedu.view.mail.IMailPresenter;
import com.telkom.weedu.view.mail.MailPresenter;
import com.telkom.weedu.view.mail.MailView;
import com.telkom.weedu.view.mailbox.IMailboxPresenter;
import com.telkom.weedu.view.mailbox.MailboxPresenter;
import com.telkom.weedu.view.mailbox.MailboxView;
import com.telkom.weedu.view.maildetail.IMailDetailPresenter;
import com.telkom.weedu.view.maildetail.MailDetailPresenter;
import com.telkom.weedu.view.maildetail.MailDetailView;
import com.telkom.weedu.view.main.IMainPresenter;
import com.telkom.weedu.view.main.MainPresenter;
import com.telkom.weedu.view.main.MainView;
import com.telkom.weedu.view.myaccount.IMyAccountPresenter;
import com.telkom.weedu.view.myaccount.MyAccountPresenter;
import com.telkom.weedu.view.myaccount.MyAccountView;
import com.telkom.weedu.view.register.IRegisterPresenter;
import com.telkom.weedu.view.register.RegisterPresenter;
import com.telkom.weedu.view.register.RegisterView;
import com.telkom.weedu.view.resetpassword.IResetPasswordPresenter;
import com.telkom.weedu.view.resetpassword.ResetPasswordPresenter;
import com.telkom.weedu.view.resetpassword.ResetPasswordView;
import com.telkom.weedu.view.searchmail.ISearchMailPresenter;
import com.telkom.weedu.view.searchmail.SearchMailPresenter;
import com.telkom.weedu.view.searchmail.SearchMailView;
import com.telkom.weedu.view.sent.ISentPresenter;
import com.telkom.weedu.view.sent.SentPresenter;
import com.telkom.weedu.view.sent.SentView;
import com.telkom.weedu.view.splash.ISplashPresenter;
import com.telkom.weedu.view.splash.SplashPresenter;
import com.telkom.weedu.view.splash.SplashView;
import com.telkom.weedu.view.starred.IStarredPresenter;
import com.telkom.weedu.view.starred.StarredPresenter;
import com.telkom.weedu.view.starred.StarredView;
import com.telkom.weedu.view.topup.ITopUpPresenter;
import com.telkom.weedu.view.topup.TopUpPresenter;
import com.telkom.weedu.view.topup.TopUpView;
import com.telkom.weedu.view.webview.IWebViewPresenter;
import com.telkom.weedu.view.webview.WebViewPresenter;
import com.telkom.weedu.view.webview.WebViewView;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ghiyatshanif on 2/21/17.
 */

@Module
public class ActivityModule {
    private Activity mActivity;

    public ActivityModule(Activity mActivity) {
        this.mActivity = mActivity;
    }

    @Provides
    Context provideContext() {
        return mActivity;
    }

    @Provides
    Activity provideActivity(Activity mActivity) {
        return mActivity;
    }

    @Provides
    FirebaseAnalytics provideFirebaseAnalytics(Context context) {
        return FirebaseAnalytics.getInstance(context);
    }

    @Provides
    IMainPresenter<MainView> provideMainPresenter(MainPresenter<MainView> mainPresenter) {
        return mainPresenter;
    }

    @Provides
    ISplashPresenter<SplashView> provideSplashPresenter(SplashPresenter<SplashView> splashPresenter) {
        return splashPresenter;
    }

    @Provides
    ILoginPresenter<LoginView> provideLoginPresenter(LoginPresenter<LoginView> loginPresenter) {
        return loginPresenter;
    }

    @Provides
    IRegisterPresenter<RegisterView> provideRegisterPresenter(RegisterPresenter<RegisterView> registerPresenter) {
        return registerPresenter;
    }

    @Provides
    IEmailConfirmationPresenter<EmailConfirmationView> provideEmailConfirmationPresenter(EmailConfirmationPresenter<EmailConfirmationView> emailConfirmationPresenter) {
        return emailConfirmationPresenter;
    }

    @Provides
    IHomePresenter<HomeView> provideHomePresenter(HomePresenter<HomeView> homePresenter) {
        return homePresenter;
    }

    @Provides
    IDashboardPresenter<DashboardView> provideDashboardPresenter(DashboardPresenter<DashboardView> dashboardPresenter) {
        return dashboardPresenter;
    }

    @Provides
    IHelpPresenter<HelpView> provideHelpPresenter(HelpPresenter<HelpView> helpPresenter) {
        return helpPresenter;
    }

    @Provides
    IMyAccountPresenter<MyAccountView> provideMyAccountPresenter(MyAccountPresenter<MyAccountView> myAccountPresenter) {
        return myAccountPresenter;
    }

    @Provides
    ITopUpPresenter<TopUpView> provideTopupPresenter(TopUpPresenter<TopUpView> topUpPresenter) {
        return topUpPresenter;
    }

    @Provides
    IFaqPresenter<FaqView> provideFaqPresenter(FaqPresenter<FaqView> faqPresenter) {
        return faqPresenter;
    }

    @Provides
    IContactUsPresenter<ContactUsView> provideContactUsPresenter(ContactUsPresenter<ContactUsView> contactUsPresenter) {
        return contactUsPresenter;
    }

    @Provides
    IWebViewPresenter<WebViewView> provideWebViewPresenter(WebViewPresenter<WebViewView> webViewPresenter) {
        return webViewPresenter;
    }

    @Provides
    IAboutPresenter<AboutView> provideAboutPresenter(AboutPresenter<AboutView> aboutPresenter) {
        return aboutPresenter;
    }

    @Provides
    IEditProfilePresenter<EditProfileView> provideEditProfilePresenter(EditProfilePresenter<EditProfileView> editProfilePresenter) {
        return editProfilePresenter;
    }

    @Provides
    IResetPasswordPresenter<ResetPasswordView> provideResetPasswordPresenter(ResetPasswordPresenter<ResetPasswordView> resetPasswordPresenter) {
        return resetPasswordPresenter;
    }

    @Provides
    IIntroductionPresenter<IntroductionView> provideIntroductionPresenter(IntroductionPresenter<IntroductionView> introductionPresenter) {
        return introductionPresenter;
    }

    @Provides
    IIntroItemPresenter<IntroItemView> provideIntroductionItemPresenter(IntroItemPresenter<IntroItemView> introItemPresenter) {
        return introItemPresenter;
    }

    @Provides
    IMailPresenter<MailView> provideMailViewPresenter(MailPresenter<MailView> mailPresenter) {
        return mailPresenter;
    }

    @Provides
    IMailboxPresenter<MailboxView> provideInboxPresenter(MailboxPresenter<MailboxView> inboxPresenter) {
        return inboxPresenter;
    }

    @Provides
    IStarredPresenter<StarredView> provideStarredPresenter(StarredPresenter<StarredView> starredPresenter) {
        return starredPresenter;
    }

    @Provides
    ISentPresenter<SentView> provideSentPresenter(SentPresenter<SentView> sentPresenter) {
        return sentPresenter;
    }

    @Provides
    IComposePresenter<ComposeView> provideComposePresenter(ComposePresenter<ComposeView> composePresenter) {
        return composePresenter;
    }

    @Provides
    IDetailBookPresenter<DetailBookView> provideDetailBukuPresenter(DetailBookPresenter<DetailBookView> detailBookPresenter) {
        return detailBookPresenter;
    }

    @Provides
    IMailDetailPresenter<MailDetailView> provideMailDetailPresenter(MailDetailPresenter<MailDetailView> mailDetailPresenter) {
        return mailDetailPresenter;
    }

    @Provides
    IDetailHistoryPresenter<DetailHistoryView> provideDetailHistoryPresenter(DetailHistoryPresenter<DetailHistoryView> presenter) {
        return presenter;
    }

    @Provides
    ISearchMailPresenter<SearchMailView> provideSearchMailPresenter(SearchMailPresenter<SearchMailView> presenter) {
        return presenter;
    }
}
