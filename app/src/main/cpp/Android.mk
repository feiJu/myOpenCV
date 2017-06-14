LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

#opencv
#include E:\\dev-lib\\OpenCV-android-sdk\\sdk\\native\\jni\\OpenCV.mk
OPENCVROOT:= D:\\download\\OpenCV-2.4.9-android-sdk\\OpenCV-2.4.9-android-sdk
OPENCV_CAMERA_MODULES:=off # on/off
OPENCV_INSTALL_MODULES:=on # on/off
OPENCV_LIB_TYPE:=STATIC # SHARED/STATIC
include ${OPENCVROOT}/sdk/native/jni/OpenCV.mk

LOCAL_MODULE := native-lib
LOCAL_SRC_FILES := native-lib.cpp
LOCAL_LDLIBS += -llog


include $(BUILD_SHARED_LIBRARY)