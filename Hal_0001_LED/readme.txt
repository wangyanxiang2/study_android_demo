1�����붯̬�⣺
arm-linux-gcc -fPIC -shared hardcontrol.c -o libhardcontrol.so 
����hardcontrol.c:2:71: error: jni.h: No such file or directory

2���޸ı�������
arm-linux-gcc -fPIC -shared hardcontrol.c -o libhardcontrol.so -I /usr/lib/jvm/java-1.8.0-openjdk/include/ -----�����

arm-linux-gcc -fPIC -shared hardcontrol.c -o libhardcontrol.so -I /usr/lib/jvm/java-1.7.0-openjdk-amd64/include/  ---����ͨ��

3��
arm-linux-gcc -fPIC -shared hardcontrol.c -o libhardcontrol.so -I /usr/lib/jvm/java-1.7.0-openjdk-amd64/include/  -nostdlib ../prebuilts/ndk/current/platforms/android-19/arch-arm/usr/lib/libc.so

-nostdlib�ƶ���ʹ�ñ�׼��c�⣬�����Լ�ָ��ʹ����һ����,��ʹ����/prebuilts/ndk/current/platforms/android-19/arch-arm/usr/lib/libc.so

4��
arm-linux-gcc -fPIC -shared hardcontrol.c -o libhardcontrol.so -I /usr/lib/jvm/java-1.7.0-openjdk-amd64/include/  -nostdlib ../prebuilts/ndk/current/platforms/android-19/arch-arm/usr/lib/libc.so -I ../prebuilts/ndk/current/platforms/android-19/arch-arm/usr/include ../prebuilts/ndk/current/platforms/android-19/arch-arm/usr/lib/liblog.so 
