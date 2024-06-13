// Write C++ code here.
//
// Do not forget to dynamically load the C++ library into your application.
//
// For instance,
//
// In MainActivity.java:
//    static {
//       System.loadLibrary("jobdemo");
//    }
//
// Or, in MainActivity.kt:
//    companion object {
//      init {
//         System.loadLibrary("jobdemo")
//      }
//    }

#include <jni.h>
#include <string>

extern "C"
JNIEXPORT jstring JNICALL
Java_com_example_jobdemo_activity_InfoShow_getString(JNIEnv *env, jobject thiz) {
    std::string hello = "Hello from C++啊";
    return env->NewStringUTF(hello.c_str());
}