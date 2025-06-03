#include <jni.h>
#include <string>
#include <android/bitmap.h>

#include "qrencode.h"
#include "qrcode/app_qrcode.hpp"
#include "utils/jni_utils.hpp"

extern "C"
JNIEXPORT jobject JNICALL
Java_cc_kafuu_bilidownload_common_jni_NativeLib_generateQrCode(
        JNIEnv *env, jobject thiz, jstring text
) {
    // 构建二维码矩阵
    QRCodeMatrix qrcode = GeneralQrCode(JStringToCString(env, text));
    if (qrcode.size == 0 || qrcode.matrix.empty()) return nullptr;
    // 构建结果
    auto boolArray = CreateJBooleanArray(
            env, qrcode.matrix.cbegin(), qrcode.matrix.cend()
    );
    if (boolArray == nullptr) return nullptr;
    jobject size_object = CreateJavaInteger(env, (jint) qrcode.size);
    jobject result_pair = CreateKotlinPair(env, boolArray, size_object);
    return result_pair;
}
