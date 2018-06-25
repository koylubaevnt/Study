#include <string.h>
#include "my_mega_pack_NativeCallsClass.h"
 

JNIEXPORT jint JNICALL Java_my_mega_pack_NativeCallsClass_printString
  (JNIEnv * jenv, jobject jobj, jstring jstr) {
		const char *string = jenv->GetStringUTFChars(jstr, 0);
		const int size = (unsigned)strlen(string);
		printf("DLL => size: %d, msg: %s\n", size, string);
		jenv->ReleaseStringUTFChars(jstr, string);
		return size;
  };

JNIEXPORT void JNICALL Java_my_mega_pack_NativeCallsClass_printOne
  (JNIEnv * jenv, jobject jobj) {
	  printf("DLL => You are called printOne\n");
  };

JNIEXPORT void JNICALL Java_my_mega_pack_NativeCallsClass_printTwo
  (JNIEnv * jenv, jobject jobj, jint num) {
	  printf("DLL => You are called printTwo with input param: %d\n", num);		
  };