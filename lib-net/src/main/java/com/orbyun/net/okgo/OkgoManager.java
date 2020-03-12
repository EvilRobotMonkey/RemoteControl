package com.orbyun.net.okgo;

import android.app.Application;
import android.text.TextUtils;
import android.util.Log;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.https.HttpsUtils;
import com.lzy.okgo.interceptor.HttpLoggingInterceptor;
import com.lzy.okgo.model.HttpHeaders;
import com.lzy.okgo.model.Progress;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.GetRequest;
import com.lzy.okserver.OkDownload;
import com.lzy.okserver.download.DownloadListener;
import com.orbyun.base.api.net.NetRequestAPI;
import com.orbyun.base.api.net.NetResultInterFace;
import com.orbyun.base.api.net.ResultInfo;
import com.orbyun.utils.LOG;
import com.orbyun.utils.helper.FileHelper;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import okhttp3.OkHttpClient;

import static com.orbyun.net.okgo.ExceptionEngine.covert;
import static com.orbyun.net.okgo.ExceptionEngine.handleException;


/**
 * @package com.orbyun.net.okgo
 * @file OkgoManager
 * @date 2019/4/29  5:05 PM
 * @autor wangxiongfeng
 */
public class OkgoManager implements NetRequestAPI {
    private static final String TAG = "OkgoManager";

    private static OkgoManager okgoManager;
    private OkDownload okDownload;
    private static String filePath;

    public static OkgoManager getInstance() {
        synchronized (Object.class) {
            if (okgoManager == null) {
                okgoManager = new OkgoManager();
            }
            return okgoManager;
        }
    }

    public static void init(Application app, HashMap<String, String> headers, boolean isShowLog, String APP_NAME, long NET_TIME_OUT, String filePathTemp) {
        mapChange(headers);

        OkGo.getInstance().init(app).addCommonHeaders(mapChange(headers));
        HttpLoggingInterceptor logInterceptor = new HttpLoggingInterceptor(APP_NAME);
        logInterceptor.setColorLevel(Level.INFO);
        if (isShowLog)//显示日志
            logInterceptor.setPrintLevel(HttpLoggingInterceptor.Level.BODY);
        else
            logInterceptor.setPrintLevel(HttpLoggingInterceptor.Level.NONE);
        HttpsUtils.SSLParams sslParams1 = HttpsUtils.getSslSocketFactory();
        OkGo.getInstance().setOkHttpClient(new OkHttpClient.Builder().readTimeout(NET_TIME_OUT, TimeUnit.SECONDS).writeTimeout(NET_TIME_OUT, TimeUnit.SECONDS)
                .connectTimeout(NET_TIME_OUT, TimeUnit.SECONDS).addInterceptor(logInterceptor)
                .sslSocketFactory(sslParams1.sSLSocketFactory, sslParams1.trustManager).build());
        filePath = filePathTemp;
        getInstance();
    }

    private static HttpHeaders mapChange(HashMap<String, String> headers) {
        HttpHeaders httpHeaders = new HttpHeaders();
        if (headers != null && headers.size() > 0) {
            Iterator<Map.Entry<String, String>> iterator = headers.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, String> next = iterator.next();
                String key = next.getKey();
                String value = next.getValue();
                httpHeaders.put(key, value);
            }

        }

        return httpHeaders;

    }

    @Override
    public void doGet(final String tag, final String url, HashMap<String, String> parameter, boolean prompt, final NetResultInterFace mNetResultInterFace) {

        OkGo.<ResultInfo<String>>get(url).tag(tag).params(parameter).execute(new ResultCallback(tag) {
            @Override
            public void onSuccess(Response<ResultInfo<String>> response) {

                try {
                    mNetResultInterFace.netWork(response.body(), tag);
                    return;
                } catch (Exception e) {
                    e.printStackTrace();

                }

                try {
                    mNetResultInterFace.netWork(covert(handleException(response.getException()), tag), tag);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (response.body() != null) {
                    LOG.d("okgo--get-请求结果", "请求成功  地址->" + url);
                }
            }

            @Override
            public void onError(Response<ResultInfo<String>> response) {
                try {
                    mNetResultInterFace.netWork(covert(handleException(response.getException()), tag), tag);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                LOG.d("okgo--get-请求结果", "请求失败  地址->" + url);

            }
        });
    }

    @Override
    public void doPost(String tag, String url, HashMap<String, String> params, Boolean prompt, final NetResultInterFace mNetResultInterFace) {


    }

    @Override
    public void doJson(final String tag, final String url, String json, Boolean prompt, String showText, final NetResultInterFace mNetResultInterFace) {
        LOG.d("okgo--json-开始请求" + tag, "请求地址->" + url + " ======" + "请求参数  ->" + json);

        OkGo.<ResultInfo<String>>post(url).tag(tag).upJson(json).execute(new ResultCallback(tag) {
            @Override
            public void onSuccess(Response<ResultInfo<String>> response) {
                try {

                    mNetResultInterFace.netWork(response.body(), tag);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (response.body() != null) {
                    String msg = response.body().getData().toString();
                    if (TextUtils.isEmpty(msg)) {
                        msg = response.body().getMessage();
                    }

                    LOG.d("okgo--json-请求结果" + tag, "请求成功 返回 ->" + msg);
                }

            }

            @Override
            public void onError(Response<ResultInfo<String>> response) {
                try {
                    mNetResultInterFace.netWork(covert(handleException(response.getException()), tag), tag);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                LOG.d("okgo--json-请求结果" + tag, "请求失败地址->" + url);

            }
        });
    }

    @Override
    public void doFileLoad(final String tag, String url, int loadFielType, final String path, final String name, final NetResultInterFace mNetResultInterFace) {

        doFileLoad(tag, url, path, name, mNetResultInterFace);
    }

    public void doFileLoad(final String tag, String url, final String path, final String name, final NetResultInterFace mNetResultInterFace) {

        GetRequest<File> task = OkGo.<File>get(url);
        OkDownload.getInstance().removeTask(url);
        OkDownload.request(url, task)
                .fileName(name)
                .folder(path)
                .save()
                .register(new ListDownloadListener(tag, mNetResultInterFace)).start();
    }

    @Override
    public void doFileUpLoad(final String tag, String url, int loadFielType, String fileName, final NetResultInterFace mNetResultInterFace) {
        File file = new File(fileName);
        OkGo.<String>post(url)
                .tag(this)
                .params("file", file)
                .isMultipart(true)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Log.i(TAG, "onSuccess: 成功");
                        ResultInfo resultInfo = new ResultInfo("true");
                        resultInfo.code = 200;
                        resultInfo.setTag(tag);
                        mNetResultInterFace.netWork(resultInfo, tag);
                    }


                });
    }

    public OkDownload getOkDownLoader(String dir) {
        if (okDownload == null) {
            FileHelper.getInstance().createNewFile(filePath);
            okDownload = OkDownload.getInstance();
            okDownload.setFolder(dir); //设置全局下载目录
            okDownload.getThreadPool().setCorePoolSize(3); //设置同时下载数量
        }
        return okDownload;

    }

    private class ListDownloadListener extends DownloadListener {
        private NetResultInterFace netResultInterFace;

        public ListDownloadListener(Object tag, NetResultInterFace netResultInterFace) {
            super(tag);
            this.netResultInterFace = netResultInterFace;
        }

        @Override
        public void onStart(Progress progress) {
            LOG.d(TAG, "onStart: tag    " + progress);
        }

        @Override
        public void onProgress(Progress progress) {
            ResultInfo resultInfo = new ResultInfo(progress.totalSize + "@" + progress.currentSize);

            resultInfo.setTag("progress");
            netResultInterFace.netWork(resultInfo, resultInfo.getTag());

        }

        @Override
        public void onError(Progress progress) {
            LOG.d("文件下载+错误" + progress.fileName, progress.exception.getMessage());
            ResultInfo resultInfo = new ResultInfo(progress.totalSize + "@" + progress.currentSize);
            resultInfo.setTag("error");
            netResultInterFace.netWork(resultInfo, resultInfo.getTag());

        }

        @Override
        public void onFinish(File file, Progress progress) {
            LOG.d("文件下载+完成" + progress.fileName, file.getAbsolutePath());
            ResultInfo resultInfo = new ResultInfo(file.getAbsolutePath());
            netResultInterFace.netWork(resultInfo, (String) tag);
        }

        @Override
        public void onRemove(Progress progress) {


        }
    }

    public void cancleDownload() {

        if (okDownload != null) {
            okDownload.removeAll(true);
        }

    }


}
