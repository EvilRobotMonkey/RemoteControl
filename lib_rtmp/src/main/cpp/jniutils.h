//
// Created by 王涛 on 2020/3/8.
//

#ifndef NDKLEARN_NATIVE_LIB_H
#define NDKLEARN_NATIVE_LIB_H

#include "com_orbyun_rtmp_NativeUtils.h"
#include <string>

#include "log.h"

class jniutils {
public:
    char *name;
    int *age;
public:
    char getName();

    int getAge();

    void setName(char *temp) {
        name = name;
    }

    void setAge(int *temp) {
        age = temp;
    }

};


#endif //NDKLEARN_NATIVE_LIB_H
