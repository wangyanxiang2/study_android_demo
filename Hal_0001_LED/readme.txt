1】编译动态库：
arm-linux-gcc -fPIC -shared hardcontrol.c -o libhardcontrol.so 
报错：hardcontrol.c:2:71: error: jni.h: No such file or directory

2】修改编译命令
arm-linux-gcc -fPIC -shared hardcontrol.c -o libhardcontrol.so -I /usr/lib/jvm/java-1.8.0-openjdk/include/ -----会出错

arm-linux-gcc -fPIC -shared hardcontrol.c -o libhardcontrol.so -I /usr/lib/jvm/java-1.7.0-openjdk-amd64/include/  ---编译通过

3】
arm-linux-gcc -fPIC -shared hardcontrol.c -o libhardcontrol.so -I /usr/lib/jvm/java-1.7.0-openjdk-amd64/include/  -nostdlib ../prebuilts/ndk/current/platforms/android-19/arch-arm/usr/lib/libc.so

-nostdlib制定不使用标准的c库，可以自己指定使用哪一个库,我使用了/prebuilts/ndk/current/platforms/android-19/arch-arm/usr/lib/libc.so

4】
arm-linux-gcc -fPIC -shared hardcontrol.c -o libhardcontrol.so -I /usr/lib/jvm/java-1.7.0-openjdk-amd64/include/  -nostdlib ../prebuilts/ndk/current/platforms/android-19/arch-arm/usr/lib/libc.so -I ../prebuilts/ndk/current/platforms/android-19/arch-arm/usr/include ../prebuilts/ndk/current/platforms/android-19/arch-arm/usr/lib/liblog.so 
