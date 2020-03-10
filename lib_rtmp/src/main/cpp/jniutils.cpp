//
// Created by 王涛 on 2020/3/8.
//

#include "jniutils.h"
#include "librtmp/rtmp.h"


int jniutils::getAge() {
    return *age;
}

char jniutils::getName() {
    return *name;
}


extern "C"
JNIEXPORT void JNICALL
Java_com_orbyun_rtmp_NativeUtils_method(JNIEnv *env, jclass clazz) {
    // TODO: implement method()
    char version[100];
    sprintf(version, "rtmp version : %d", RTMP_LibVersion());

    LOGI("%s", version);
}