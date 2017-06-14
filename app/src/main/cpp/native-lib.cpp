#include <jni.h>
#include <string>
#include <opencv2/opencv.hpp>

using namespace cv;
using namespace std;

extern "C"
JNIEXPORT jintArray JNICALL
Java_com_example_jucf_myjni_MainActivity_grayProc(JNIEnv *env, jobject instance, jintArray pixels,
                                                  jint w, jint h) {
    jboolean ptfalse = false;
    jint* srcBuf = env->GetIntArrayElements(pixels, &ptfalse);
    if(srcBuf == NULL){
        return 0;
    }
    int size=w * h;

    Mat srcImage(h, w, CV_8UC4, srcBuf);
    Mat grayImage;
    cvtColor(srcImage, grayImage, COLOR_BGRA2GRAY);
    cvtColor(grayImage, srcImage, COLOR_GRAY2BGRA);

    jintArray result = env->NewIntArray(size);
    env->SetIntArrayRegion(result, 0, size, srcBuf);
    env->ReleaseIntArrayElements(pixels, srcBuf, 0);
    return result;
}

extern "C"
JNIEXPORT jstring JNICALL
Java_com_example_jucf_myjni_MainActivity_stringFromJNI(
        JNIEnv *env,
        jobject /* this */) {
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}
