package com.zrquan.mobile.ui.feed;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.zrquan.mobile.R;
import com.zrquan.mobile.controller.AccountController;
import com.zrquan.mobile.support.util.ToastUtils;
import com.zrquan.mobile.ui.MainActivity;
import com.zrquan.mobile.ui.common.CommonFragment;
import com.zrquan.mobile.ui.demo.DemoPlayMusicActivity;
import com.zrquan.mobile.ui.demo.GalleryViewPagerSampleActivity;
import com.zrquan.mobile.widget.switchbutton.SwitchButton;

//问答 动态
public class QuestionFragment extends CommonFragment {

    private Context context;
    private View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_question, null);
            context = getActivity().getApplicationContext();

            SwitchButton sbDefault = (SwitchButton) rootView.findViewById(R.id.sb_default);

            sbDefault.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    Toast.makeText(getActivity(), "Default style button, new state: " + (isChecked ? "on" : "off"), Toast.LENGTH_SHORT).show();
                }
            });

            TextView tv_test_photo = (TextView) rootView.findViewById(R.id.tv_test_photo);
            tv_test_photo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    createDialog(R.layout.dialog_select_pic);
                }
            });

            TextView tv_test_share = (TextView) rootView.findViewById(R.id.tv_test_share);
            tv_test_share.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    createDialog(R.layout.dialog_share);
                }
            });

            TextView tv_test_select_location = (TextView) rootView.findViewById(R.id.tv_test_select_location);
            tv_test_select_location.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    createDialog(R.layout.dialog_select_location);
                }
            });

            TextView tv_test_select_gender = (TextView) rootView.findViewById(R.id.tv_test_select_gender);
            tv_test_select_gender.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    createDialog(R.layout.dialog_select_gender);
                }
            });

            TextView tv_test_image_gallery = (TextView) rootView.findViewById(R.id.tv_test_image_gallery);
            tv_test_image_gallery.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(QuestionFragment.this.getActivity(), GalleryViewPagerSampleActivity.class);
                    startActivity(intent);
                    QuestionFragment.this.getActivity().overridePendingTransition(R.anim.right2left_enter, R.anim.right2left_exit);
                }
            });

            TextView tv_test_logout_account = (TextView) rootView.findViewById(R.id.tv_test_logout_account);
            tv_test_logout_account.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AccountController.logoutAccount(context);
                    Intent intent = new Intent(QuestionFragment.this.getActivity(), MainActivity.class);
                    //http://stackoverflow.com/questions/16217917/close-an-specific-activity-android
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    QuestionFragment.this.getActivity().overridePendingTransition(R.anim.right2left_enter, R.anim.right2left_exit);
                    ToastUtils.show(context, "成功注销账户");
                }
            });

            TextView tv_test_play_music = (TextView) rootView.findViewById(R.id.tv_test_play_music);
            tv_test_play_music.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(QuestionFragment.this.getActivity(), DemoPlayMusicActivity.class);
                    startActivity(intent);
                    QuestionFragment.this.getActivity().overridePendingTransition(R.anim.right2left_enter, R.anim.right2left_exit);
                }
            });
        } else {
            ((ViewGroup) rootView.getParent()).removeView(rootView);
        }

        return rootView;

    }

    public void createDialog(int Resource) {
//        startActivity(new Intent(getActivity(), SelectPicPopupWindow.class));
//        BottomMenuFragment df = new BottomMenuFragment();
//        FragmentTransaction ft = getChildFragmentManager().beginTransaction();
//        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
//        df.show(ft, "df");

        final Dialog infoDialog = new Dialog(getActivity(), R.style.BottomMenuDialogTheme2);
        infoDialog.setContentView(Resource);
//        infoDialog.show();

//        AlertDialog infoDialog = new AlertDialog.Builder(getActivity(), R.style.BottomMenuDialogTheme2)
//                .setView(view)
//                .create();
//        infoDialog.getWindow().setBackgroundDrawableResource(R.drawable.btn_style_alert_dialog_background);
//        infoDialog.getWindow().getDecorView().getRootView().setBackgroundColor(Color.TRANSPARENT);
        infoDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        infoDialog.getWindow().getAttributes().windowAnimations = R.style.AnimBottom;
        infoDialog.getWindow().setGravity(Gravity.BOTTOM);
        infoDialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        infoDialog.show();

//        RelativeLayout layout=(RelativeLayout) infoDialog.findViewById(R.id.pop_layout);

//        添加选择窗口范围监听可以优先获取触点，即不再执行onTouchEvent()函数，点击其他地方时执行onTouchEvent()函数销毁Activity
//        layout.setOnClickListener(new View.OnClickListener() {
//
//            public void onClick(View v) {
//                infoDialog.dismiss();
//                Toast.makeText(getActivity().getApplicationContext(), "提示：点击窗口外部关闭窗口！",
//                        Toast.LENGTH_SHORT).show();
//            }
//        });

//        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//        builder.setMessage("Are you sure you want to exit?")
//                .setCancelable(false)
//                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int id) {
////                        MyActivity.this.finish();
//                    }
//                })
//                .setNegativeButton("No", new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int id) {
//                        dialog.cancel();
//                    }
//                });
//        builder.show();
    }
}
