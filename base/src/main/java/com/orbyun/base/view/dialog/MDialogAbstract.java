package com.orbyun.base.view.dialog;

/**
 * @package com.boci.smart.helper.dialog
 * @file MDialogAbstract
 * @date 2018/10/6  上午11:09
 * @autor wangxiongfeng
 */
public interface MDialogAbstract {
    String getContent();

    String getTitle();

    String getSubmit();

    String getCancel();

    DialogEventListener getDialogEventListener();

}
