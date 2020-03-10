//
// Created by 王涛 on 2020/3/10.
//

#include "playtools.h"


extern "C"
JNIEXPORT jint JNICALL
Java_com_orbyun_player_PlayManager_play(JNIEnv *env, jobject thiz, jobject surface,
                                        jstring rtmp_url) {
    // TODO: implement play()

    LOGD("play");

    // sd卡中的视频文件地址,可自行修改或者通过jni传入

    const char *file_name = env->GetStringUTFChars(rtmp_url, 0);
//    char *file_name = "/storage/emulated/0/video.avi";
    LOGI("视频文件地址是%s", file_name);
    return -1;

}

