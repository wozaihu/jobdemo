
# For more information about using CMake with Android Studio, read the
# documentation: https://d.android.com/studio/projects/add-native-code.html.
# For more examples on how to use CMake, see https://github.com/android/ndk-samples.


# 设置CMake的最低版本要求为3.22.1
cmake_minimum_required(VERSION 3.22.1)

# 定义项目名称为"jobdemo"
project("jobdemo")

# 添加一个共享库，库名与项目名相同，源文件包括secondC.cpp和jobdemo.cpp
add_library(${CMAKE_PROJECT_NAME} SHARED
        secondC.cpp
        jobdemo.cpp)

# 将库链接到android和log库
target_link_libraries(${CMAKE_PROJECT_NAME}
        android
        log)

