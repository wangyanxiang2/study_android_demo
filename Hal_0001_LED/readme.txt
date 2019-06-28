1¡¿
arm-linux-gcc -fPIC -shared hardcontrol.c -o libhardcontrol.so -I /usr/lib/jvm/java-1.7.0-openjdk-amd64/include/  -nostdlib ../prebuilts/ndk/9/platforms/android-19/arch-arm/usr/lib/libc.so
2¡¿
arm-linux-gcc -fPIC -shared hardcontrol.c -o libhardcontrol.so -I /usr/lib/jvm/java-1.7.0-openjdk-amd64/include/  -nostdlib ../prebuilts/ndk/9/platforms/android-19/arch-arm/usr/lib/libc.so -I ../prebuilts/ndk/9/platforms/android-19/arch-arm/usr/include ../prebuilts/ndk/9/platforms/android-19/arch-arm/usr/lib/liblog.so
3¡¿
arm-linux-gcc -fPIC -shared hardcontrol.c -o libhardcontrol.so -I /usr/lib/jvm/java-1.7.0-openjdk-amd64/include/  -nostdlib ../prebuilts/ndk/9/platforms/android-19/arch-arm/usr/lib/libc.so -I ../prebuilts/ndk/9/platforms/android-19/arch-arm/usr/include ../prebuilts/ndk/9/platforms/android-19/arch-arm/usr/lib/liblog.so
