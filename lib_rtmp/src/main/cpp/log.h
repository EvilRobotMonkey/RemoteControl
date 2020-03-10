#ifndef __LEUDLOG_H__
#define __LEUDLOG_H__

#define ENABLE_LOG 1

#ifdef __ANDROID__

#define LOG_TAG "RESRTMP"

#ifdef ENABLE_LOG
#include <android/log.h>

// 定义info信息
#define LOGI(...) __android_log_print(ANDROID_LOG_INFO,LOG_TAG,__VA_ARGS__)
// 定义debug信息
#define LOGD(...) __android_log_print(ANDROID_LOG_DEBUG, LOG_TAG, __VA_ARGS__)
// 定义error信息
#define LOGE(...) __android_log_print(ANDROID_LOG_ERROR,LOG_TAG,__VA_ARGS__)
#else
#define LOGD(...)
#endif

#else
#include <stdio.h>

#ifdef ENABLE_LOG
#define LOGD(...) printf(__VA_ARGS__)
#else
#define LOGD(...)
#endif

#endif

#endif