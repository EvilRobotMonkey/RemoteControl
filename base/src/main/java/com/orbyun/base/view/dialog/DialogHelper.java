package com.orbyun.base.view.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.text.TextUtils;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.orbyun.base.basemoudle.R;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @package com.boci.smart.helper
 * @file DialogHelper
 * @date 2018/10/6  上午11:06
 * @autor wangxiongfeng
 */
public class DialogHelper {


    private static ScheduledThreadPoolExecutor executor;
    private static Dialog dialog;
    private static MDialog dialogEventData;

    public static void showDialogCommon(final Context context, final MDialogAbstract mDialogAbstract) {
        MDialog mDialog = new MDialog();
        mDialog.setContent(mDialogAbstract.getContent());
        mDialog.setSubmit(mDialogAbstract.getSubmit());
        mDialog.setCancel(mDialogAbstract.getCancel());
        mDialog.setTitle(mDialogAbstract.getTitle());
        if (mDialogAbstract.getDialogEventListener() != null) {
            mDialog.setDialogEvent(mDialogAbstract.getDialogEventListener());
        }


        showDialog2(context, mDialog);
    }

    public static void showDialog2(Context context, final MDialog data) {
        // 布局文件转换为view对象
        if (context == null) {
            return;
        }
        LayoutInflater inflater = LayoutInflater.from(context);

        View layout = inflater.inflate(R.layout.cutom_dialog, null);
        // 对话框

        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }


        dialog = new Dialog(context, R.style.dialog);
        dialogEventData = data;

        dialog.setCancelable(false);
        dialog.getWindow().setContentView(layout);
        dialog.show();


        Window window = dialog.getWindow();
        window.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#20000000")));
        WindowManager.LayoutParams attributes = window.getAttributes();
        window.setBackgroundDrawable(new ColorDrawable());
        attributes.dimAmount = 0f;
        attributes.gravity = Gravity.CENTER;
        window.setAttributes(attributes);

        Display d = window.getWindowManager().getDefaultDisplay();
        int mWidth = d.getWidth();


        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(mWidth, ViewGroup.LayoutParams.MATCH_PARENT);
        layout.setLayoutParams(params);

        TextView content = (TextView) layout.findViewById(R.id.content_txt);
        final TextView btnCancel = (TextView) layout.findViewById(R.id.cancel_txt);
        TextView btnConfirm = (TextView) layout.findViewById(R.id.confirm_txt);


        content.setText(data.getContent());


        if (TextUtils.isEmpty(data.getContent())) {
            content.setVisibility(View.GONE);
        }

        boolean b = false;
        if (TextUtils.isEmpty(data.getCancel())) {
            btnCancel.setVisibility(View.GONE);
        } else {

            btnCancel.setVisibility(View.VISIBLE);
            btnCancel.setText(data.getCancel());
            btnCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    cancelClick();

                }
            });

            b = cancelCountDown(data, dialog, btnCancel, context, "cancel");


        }

        if (TextUtils.isEmpty(data.getSubmit())) {
            btnConfirm.setVisibility(View.GONE);
        } else {
            // 确定按钮
            btnConfirm.setVisibility(View.VISIBLE);
            btnConfirm.setText(data.getSubmit());
            btnConfirm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    submitClick();


                }
            });
            if (!b) {
                cancelCountDown(data, dialog, btnConfirm, context, "submit");

            }


        }


    }

    /*
     * 弹框的展示
     */
    public static Dialog showWait(Activity activity, String showText) {
        if (null != dialog) {
            dialog.dismiss();
            dialog = null;
        }
        dialog = new Dialog(activity, R.style.progress_dialog);
        LayoutInflater inflater = LayoutInflater.from(activity);
        LinearLayout view = (LinearLayout) inflater.inflate(R.layout.dialog_wait, null);
        dialog.getWindow().setContentView(view);
        Window window = dialog.getWindow();
        window.setBackgroundDrawableResource(android.R.color.transparent);
        WindowManager.LayoutParams params = window.getAttributes();
        window.setGravity(Gravity.CENTER);
        window.setAttributes(params);

        TextView msg = (TextView) dialog.findViewById(R.id.id_tv_loadingmsg);
        if (TextUtils.isEmpty(showText)) {
            msg.setText("");
        } else {
            msg.setText(showText);
        }
        dialog.show();
        return dialog;
    }


    public static void cancelClick() {
        if (dialog == null)
            return;
        if (dialogEventData == null)
            return;
        dialog.dismiss();
        if (executor != null) {
            executor.shutdown();
            executor = null;
        }
        if (dialogEventData.getDialogEvent() != null) {
            dialogEventData.getDialogEvent().cancel();

        }
    }

    public static void submitClick() {
        if (dialog == null)
            return;
        if (dialogEventData == null)
            return;
        dialog.dismiss();
        if (executor != null) {
            executor.shutdown();
            executor = null;
        }
        if (dialogEventData.getDialogEvent() != null) {
            dialogEventData.getDialogEvent().submit();
        }
    }

    private static int count = 0;

    private static boolean cancelCountDown(final MDialog data, final Dialog dialog, final TextView btnCancel, final Context context, final String flag) {
        count = 0;
        Pattern compile = Pattern.compile("-?[1-9]\\d*");
        Matcher matcher01 = compile.matcher(btnCancel.getText().toString());

        while (matcher01.find()) {
            String group = matcher01.group();
            count = Integer.valueOf(group);

        }
        final String remind = btnCancel.getText().toString().replace("(" + count + ")", "");


        if (count > 0) {

            executor = new ScheduledThreadPoolExecutor(1);
            executor.scheduleAtFixedRate(new Runnable() {
                @Override
                public void run() {
                    count--;
                    ((Activity) context).runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            btnCancel.setText(String.format(remind + "（%d)", count));
                        }
                    });

                    if (count <= 0) {
                        dialog.dismiss();


                        if (data.getDialogEvent() != null) {
                            if (flag.equals("cancel")) {
                                data.getDialogEvent().cancel();

                            } else {
                                flag.equals("submit");
                                data.getDialogEvent().submit();
                            }
                        }
                        executor.shutdown();
                        executor = null;
                    }
                }
            }, 1, 1, TimeUnit.SECONDS);
            return true;
        } else {
            return false;

        }
    }

    public static void closeDialog() {
        if (dialog != null) {
            dialog.dismiss();
        }
    }

    public static boolean isShowing() {
        if (dialog != null) {
            return dialog.isShowing();
        }
        return false;
    }

    public static Dialog getDialog() {
        return dialog;
    }

    public static void setDialog(Dialog dialog) {
        DialogHelper.dialog = dialog;
    }

    public static MDialog getDialogEventData() {
        return dialogEventData;
    }

    public static void setDialogEventData(MDialog dialogEventData) {
        DialogHelper.dialogEventData = dialogEventData;
    }

}
