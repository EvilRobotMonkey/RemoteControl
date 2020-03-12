package com.orbyun.net;

/**
 * @package com.orbyun.base.net
 * @file IFileProgress
 * @date 2019/4/29  3:34 PM
 * @autor wangxiongfeng
 */
interface ProgressListener {
    void onRequestProgress(long bytesWritten, long contentLength, boolean done);
}
