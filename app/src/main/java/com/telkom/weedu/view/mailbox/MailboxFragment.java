package com.telkom.weedu.view.mailbox;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.telkom.weedu.R;
import com.telkom.weedu.base.BaseFragment;
import com.telkom.weedu.data.mapper.edumail.Mail;
import com.telkom.weedu.event.AddRemoveFavoriteMailEvent;
import com.telkom.weedu.event.RefreshMailboxEvent;
import com.telkom.weedu.event.RefreshMailboxItemEvent;
import com.telkom.weedu.event.RemoveMailEvent;
import com.telkom.weedu.utils.AppConstants;
import com.telkom.weedu.utils.BundleKeys;
import com.telkom.weedu.utils.ContextProvider;
import com.telkom.weedu.utils.MessageFactory;
import com.telkom.weedu.utils.listener.EndlessRecyclerOnScrollListener;
import com.telkom.weedu.view.adapters.InboxAdapter;
import com.telkom.weedu.view.composeemail.ComposeEmailActivity;
import com.telkom.weedu.view.maildetail.MailDetailActivity;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.LinkedList;

import javax.inject.Inject;

import butterknife.BindView;
import timber.log.Timber;

/**
 * A simple {@link Fragment} subclass.
 */
public class MailboxFragment extends BaseFragment implements MailboxView,
        SwipeRefreshLayout.OnRefreshListener, View.OnClickListener, InboxAdapter.OnItemClickListener,
        InboxAdapter.OnLongItemClickListener{
    private static final String TAG = MailboxFragment.class.getSimpleName();
    public static final int MINIMUM_ITEM_LIMIT = 10;

    private int page = 1;
    private boolean isRefresh = false;
    private boolean isLoadmore = false;
    private boolean hasAllDataLoaded = false;
    private String mailType = null;

    @BindView(R.id.fab_compose)
    FloatingActionButton fabInbox;
    @BindView(R.id.rv_inbox)
    RecyclerView rvInbox;
    @BindView(R.id.swipe_refresh_layout_inbox)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.tv_error_message)
    TextView tvErrorMessage;

    @Inject
    IMailboxPresenter<MailboxView> presenter;

    private LinearLayoutManager linearLayoutManager;
    private InboxAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_inbox;
    }

    private void getSavedLastData(@Nullable Bundle savedInstanceState) {
        if (savedInstanceState != null){
            if (savedInstanceState.containsKey(BundleKeys.KEY_EMAIL_SAVED_PAGE)){
                page = savedInstanceState.getInt(BundleKeys.KEY_EMAIL_SAVED_PAGE);
            }

            if (savedInstanceState.containsKey(BundleKeys.KEY_EMAIL_LIST)){
                LinkedList<Mail> l = new LinkedList<>();
                ArrayList<Mail> mails = savedInstanceState.getParcelableArrayList(BundleKeys.KEY_EMAIL_LIST);
                for (Mail mail : mails){
                    l.add(mail);
                }

                adapter.add(l);
            }
        }
    }

    @Override
    protected void initLib() {
        getActivityComponent().inject(this);
        presenter.onAttach(this);

        registerEventBusSubscriber(this);

        setRetainInstance(true);
    }

    @Override
    protected void initIntent() {
        mailType = getArguments().getString(BundleKeys.KEY_EMAIL_TYPE);
    }

    @Override
    protected void initUI() {
        initializeRecyclerView();
        initializeSwipeRefreshLayout();
    }

    private void initializeSwipeRefreshLayout() {
        swipeRefreshLayout.setColorSchemeColors(ContextCompat.getColor(getActivity(), R.color.colorPrimary),
                ContextCompat.getColor(getActivity(), R.color.amber600),
                        ContextCompat.getColor(getActivity(), R.color.red600),
                ContextCompat.getColor(getActivity(), R.color.colorAccent));
    }

    private void initializeRecyclerView() {
        linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        rvInbox.setAdapter(new InboxAdapter(getActivity(), new LinkedList<Mail>()));
        rvInbox.setHasFixedSize(true);
        rvInbox.setLayoutManager(linearLayoutManager);
        rvInbox.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
    }

    @Override
    protected void initAction() {
        swipeRefreshLayout.setOnRefreshListener(this);
        fabInbox.setOnClickListener(this);
        rvInbox.addOnScrollListener(new EndlessRecyclerOnScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int current_page) {
                if (!hasAllDataLoaded){
                    isLoadmore = true;
                    page +=1;
                    //notifyWhenLoadmore();
                    getInboxRequest(page);
                }
            }
        });
    }

    @Override
    protected void initProcess() {
        if (!mailType.equalsIgnoreCase(AppConstants.EDUMAIL_TYPE_INBOX)){
            fabInbox.setVisibility(View.GONE);
        }

        getInboxRequest(page);
    }

    private void getInboxRequest(int page) {
        if (tvErrorMessage.getVisibility() == View.VISIBLE){
            tvErrorMessage.setVisibility(View.GONE);
        }

        presenter.loadMail(mailType, page);
    }

    public static MailboxFragment newInstance(String type){
        Bundle args = new Bundle();
        args.putString(BundleKeys.KEY_EMAIL_TYPE, type);

        MailboxFragment fragment = new MailboxFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void showInitialLoading() {
        if (!isLoadmore){
            swipeRefreshLayout.setRefreshing(true);
        }
    }

    @Override
    public void hideInitialLoading() {
        Timber.d("Mailbox", mailType);
        if (!isLoadmore){
            if (swipeRefreshLayout != null){
                swipeRefreshLayout.setRefreshing(false);
            }
        }
    }

    @Override
    public void toComposeActivity() {
        ComposeEmailActivity.start(getContext(), AppConstants.COMPOSE_TYPE_NEW);
    }

    @Override
    public void showInboxList(LinkedList<Mail> list) {
        verifyWhenRefresh(list);

        setAdapterWithData(list);

        verifyWhenLoadmore(list);
    }

    private void verifyWhenLoadmore(LinkedList<Mail> list) {
        if (isLoadmore){
            int prevSize = adapter.getDatas().size();
            isLoadmore = false;
            adapter.add(list);
            if (rvInbox != null){
                rvInbox.scrollToPosition(prevSize - 3);
            }
        }
    }

    private void verifyWhenRefresh(LinkedList<Mail> list) {
        if (isRefresh){
            if (adapter.getDatas().size() > 0){
                isRefresh = false;
                if (list.size() > 0){
                    adapter.addFirst(list);
                }

                if (rvInbox != null){
                    rvInbox.scrollToPosition(0);
                }
            }
        }
    }

    private void setAdapterWithData(LinkedList<Mail> list) {
        if (adapter == null){
            adapter = new InboxAdapter(getContext(), list);
        }

        if (rvInbox != null){
            rvInbox.setAdapter(adapter);
            adapter.setOnItemClickListener(this);
            adapter.setOnLongItemClickListener(this);

            if (list.size() < 10){
                hasAllDataLoaded = true;
            }
        }
    }

    @Override
    public void showEmptyInbox(String message) {
        if (!isLoadmore){
            showErrorMessage(message);
        }

        if (isLoadmore){
            hasAllDataLoaded = true;
        }
    }

    private void showErrorMessage(String message) {
        if (tvErrorMessage != null){
            tvErrorMessage.setVisibility(View.VISIBLE);
            tvErrorMessage.setText(message);
        }
    }

    @Override
    public void showErrorInInbox(String message) {
        if (!isLoadmore){
            showErrorMessage(message);
        }
    }

    @Override
    public void toMailDetailActivity(Mail mail) {
        MailDetailActivity.start(getActivity(), mailType, mail);
    }

    @Override
    public void onRemoveMailToTrashSuccess(String message, String mailId) {
        showToast(message);
        removeSelectedMailFromList(mailId);
    }

    @Override
    public void onRemoveMailToTrashFailed(String message) {
        showErrorMessage(message);
    }

    @Override
    public void onRemoveMailPermanentlySuccess(String message, String mailId) {
        showToast(message);
        removeSelectedMailFromList(mailId);
    }

    private void removeSelectedMailFromList(String mailId) {
        Mail selectedMail = null;
        for (Mail mail : adapter.getDatas()){
            if (mail.getMailId().equalsIgnoreCase(mailId)){
                selectedMail = mail;
                break;
            }
        }

        if (selectedMail != null){
            adapter.remove(selectedMail);
        }
    }

    @Override
    public void onRemoveMailPermanentlyFailed(String message) {
        showErrorMessage(message);
    }

    @Override
    public void onRefresh() {
        if (!isRefresh){
            isRefresh = true;
            getInboxRequest(1);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.fab_compose:
                presenter.openComposeActivity();
                break;
        }
    }

    @Override
    public void onItemClick(View view, int position) {
        switch (view.getId()){
            case R.id.item_mail_row:
                presenter.openMailDetailActivity(adapter.getDatas().get(position));
                break;

            case R.id.img_item_star:
                showErrorMessage("Star Clicked");
                break;
        }
    }

    @Override
    public void onLongItemClick(View view, int position) {
        switch (view.getId()){
            case R.id.item_mail_row:
                if (mailType.equalsIgnoreCase(AppConstants.EDUMAIL_TYPE_TRASH)){
                    showDeleteMailPermanentlyConfirmationDialog(position);
                }else{
                    showRemoveMailToTrashConfirmationDialog(position);
                }
                break;
        }
    }

    private void showRemoveMailToTrashConfirmationDialog(final int position) {
        MessageFactory.showAlertDialog(getActivity(), getString(R.string.dialog_title_delete),
                getString(R.string.dialog_question_delete_mail),
                getString(R.string.dialog_answer_yes),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Mail selectedMail =  adapter.getDatas().get(position);
                        presenter.removeMailToTrash(selectedMail.getMailId());
                        dialogInterface.dismiss();
                    }
                }, getString(R.string.dialog_answer_cancel),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
    }

    private void showDeleteMailPermanentlyConfirmationDialog(final int position) {

        MessageFactory.showAlertDialog(getActivity(), getString(R.string.dialog_title_delete),
                getString(R.string.dialog_question_delete_mail),
                getString(R.string.dialog_answer_yes_permanently),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Mail selectedMail =  adapter.getDatas().get(position);
                        presenter.removeMailPermanently(selectedMail.getMailId());
                        dialogInterface.dismiss();
                    }
                }, getString(R.string.dialog_answer_cancel),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        if (adapter != null){
            ArrayList<Mail> list = new ArrayList<>();
            list.addAll(adapter.getDatas());

            outState.putParcelableArrayList(BundleKeys.KEY_EMAIL_LIST, list);
            outState.putInt(BundleKeys.KEY_EMAIL_SAVED_PAGE, page);
        }
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
    }

    private void notifyWhenLoadmore(){
        Toast toast = Toast.makeText(getContext(), "message", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.BOTTOM, 0, 0);
        toast.setText("Loading for more emails");
        toast.show();
    }

    @Subscribe
    public void onRefreshMailboxItem(RefreshMailboxItemEvent e){
        if (adapter.getDatas() != null){
            Mail updatedMail = null;
            for (Mail mail : adapter.getDatas()){
                if (mail.getMailId().equalsIgnoreCase(e.getMailId())){
                    updatedMail = mail;
                    break;
                }
            }

            if (updatedMail != null){
                if (updatedMail.isUnread()){
                    updatedMail.setUnread(false);
                    adapter.addOrUpdate(updatedMail);
                }
            }
        }
    }

    @Subscribe
    public void onRemoveMailboxItem(RemoveMailEvent e){
        removeSelectedMailFromList(e.getMailId());
    }

    @Subscribe
    public void onStarChangesMailboxItem(AddRemoveFavoriteMailEvent e){
        if (adapter.getDatas() != null){
            Mail updatedMail = null;
            for (Mail mail : adapter.getDatas()){
                if (mail.getMailId().equalsIgnoreCase(e.getMail().getMailId())){
                    updatedMail = mail;
                    break;
                }
            }

            if (updatedMail != null){
                updatedMail.setStarred(e.getMail().isStarred());
                adapter.addOrUpdate(updatedMail);
            }

            if (mailType.equalsIgnoreCase(AppConstants.EDUMAIL_TYPE_STARRED)){
                if (!e.getMail().isStarred()){
                    adapter.remove(updatedMail);
                }else{
                    adapter.add(e.getMail());
                }
            }
        }
    }

    @Subscribe
    public void onRefreshMailboxEvent(RefreshMailboxEvent e){
        page = 1;
        initProcess();
    }

    @Override
    public void onDestroy() {
        unregisterEventBusSubscriber(this);
        super.onDestroy();
    }
}
