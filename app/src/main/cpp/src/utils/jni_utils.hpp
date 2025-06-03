//
// Created by kafuu on 2025/6/4.
//

#ifndef BILIDOWNLOAD_JNI_UTILS_HPP
#define BILIDOWNLOAD_JNI_UTILS_HPP

#include <jni.h>
#include <vector>
#include <type_traits>
#include <string>

/**
 * @brief 创建Java整型数据包装类
 */
jobject CreateJavaInteger(JNIEnv *env, jint value) {
    jclass integer_class = env->FindClass("java/lang/Integer");
    jmethodID int_ctor = env->GetMethodID(integer_class, "<init>", "(I)V");
    return env->NewObject(integer_class, int_ctor, value);
}

/**
 * @brief 创建Kotlin二元组
 */
jobject CreateKotlinPair(JNIEnv *env, jobject first, jobject second) {
    jclass pair_class = env->FindClass("kotlin/Pair");
    jmethodID pair_ctor = env->GetMethodID(
            pair_class, "<init>", "(Ljava/lang/Object;Ljava/lang/Object;)V"
    );
    return env->NewObject(pair_class, pair_ctor, first, second);
}

/**
 * @brief 创建Java布尔型数组
 */
template<
        class Iterator,
        typename = std::enable_if_t<std::is_convertible<decltype(*std::declval<Iterator>()), bool>::value>
>
jbooleanArray CreateJBooleanArray(JNIEnv *env, Iterator begin, Iterator end) {
    // 取结果长度
    auto size = std::distance(begin, end);
    // 构建JBooleanArray
    jbooleanArray array = env->NewBooleanArray(size);
    if (array == nullptr) return nullptr;
    // 构建成员数据
    std::vector<jboolean> j_booleans(size);
    std::transform(begin, end, j_booleans.begin(), [](auto v) { return static_cast<jboolean>(v); });
    // 将成员数据设置到JBooleanArray中
    env->SetBooleanArrayRegion(array, 0, static_cast<jsize>(size), j_booleans.data());
    return array;
}

/**
 * @brief 基于Java String构建std::string
 */
std::string JStringToCString(JNIEnv *env, jstring text) {
    if (text == nullptr) return {};
    const char *c_text = env->GetStringUTFChars(text, nullptr);
    if (c_text == nullptr) return {};
    std::unique_ptr<const char, std::function<void(const char *)>> guard(
            c_text,
            [env, text](const char *p) { env->ReleaseStringUTFChars(text, p); }
    );
    return {c_text};
}


#endif //BILIDOWNLOAD_JNI_UTILS_HPP
