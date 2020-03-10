package com.orbyun.base.view.dialog;

/**
 * @package com.boci.smart.helper
 * @file MDialog
 * @date 2018/10/6  上午11:07
 * @autor wangxiongfeng
 */
public class MDialog {
    private String title;
    private String content;
    private String submit;
    private String cancel;
    private DialogEventListener dialogEvent;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSubmit() {
        return submit;
    }

    public void setSubmit(String submit) {
        this.submit = submit;
    }

    public String getCancel() {
        return cancel;
    }

    public void setCancel(String cancel) {
        this.cancel = cancel;
    }

    public DialogEventListener getDialogEvent() {
        return dialogEvent;
    }

    public void setDialogEvent(DialogEventListener dialogEvent) {
        this.dialogEvent = dialogEvent;
    }



}
