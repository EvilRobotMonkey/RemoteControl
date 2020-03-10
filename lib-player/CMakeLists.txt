# Sets the minimum version of CMake required to build the native
# library. You should either keep the default value or only pass a
# value of 3.4.0 or lower.
cmake_minimum_required(VERSION 3.4.1)


add_library(
        playtools
        SHARED
        src/main/cpp/playtools.cpp)

find_library(
        log-lib
        log)


#target_include_directories(native-lib PRIVATE libs/include)

target_link_libraries(playtools
        ${log-lib})
