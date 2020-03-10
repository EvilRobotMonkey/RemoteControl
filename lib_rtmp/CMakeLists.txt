cmake_minimum_required(VERSION 3.4.1)
#预编译宏
set(CMAKE_C_FLAGS "${CMAKE_C_FLAGS} -DNO_CRYPTO")

#如果需要使用第三方库，则可以使用 find_library 来找到，比如这里的 log 这个库
find_library(
        # so库的变量路径名字，在关联的时候是使用
        log-lib
        #你需要关联的so名字
        log)

file(GLOB librtmp "src/main/cpp/librtmp/*.c")


#这里会把  native-lib.cpp 转换成共享库，并命名为  native-lib
add_library( # 库的名字
        jniutils

        # 设置成共享库
        SHARED

        # 库的原文件
        src/main/cpp/jniutils.cpp
        src/main/cpp/log.h
        ${librtmp} )



#导入第三方so包，并声明为 IMPORTED 属性，指明只是想把 so 导入到项目中
#add_library( vvw
#        SHARED
#        IMPORTED )

#指明 so 库的路径，CMAKE_SOURCE_DIR 表示 CMakeLists.txt 的路径
#set_target_properties(
#        vvw
#        PROPERTIES IMPORTED_LOCATION
#        ${CMAKE_SOURCE_DIR}/src/main/jniLibs/${ANDROID_ABI}/libvvw.so )
#指明头文件路径，不然会提示找不到 so 的方法

target_link_libraries( # Specifies the target library.
        jniutils
        #关联第三方 so
        #        vvw
        ${log-lib})