LOCAL_PATH:= $(call my-dir)
include $(CLEAR_VARS)

LOCAL_MODULE_TAGS := optional

appcompat_dir := $(LOCAL_PATH)/../../../prebuilts/sdk/current/support/v7/appcompat/res

LOCAL_STATIC_JAVA_LIBRARIES += android-support-v4
LOCAL_STATIC_JAVA_LIBRARIES += android-support-v7-appcompat

LOCAL_SRC_FILES := $(call all-java-files-under, src) \
                   $(call all-java-files-under, libs/fotolib2/src) \
                   $(call all-java-files-under, libs/Android-Universal-Image-Loader-1.9.5/src) \
                   $(call all-java-files-under, libs//androidsvg-Release_1.2.2-beta-1/src) \
                   $(call all-java-files-under, libs/k3b-geoHelper-1.1.5/src) \
                   $(call all-java-files-under, libs/kxml2-kxml-parent-2.2.3/src) \
                   $(call all-java-files-under, libs/metadata-extractor-2.10.1/src) \
                   $(call all-java-files-under, libs/PhotoView-1.2.6.k3b-SNAPSHOT/src) \
                   $(call all-java-files-under, libs/slf4j-android-1.7.7-1/src) \
                   $(call all-java-files-under, libs/mapsforge-0.8.0/mapsforge-core/src) \
                   $(call all-java-files-under, libs/mapsforge-0.8.0/mapsforge-map/src) \
                   $(call all-java-files-under, libs/mapsforge-0.8.0/mapsforge-map-android/src) \
                   $(call all-java-files-under, libs/mapsforge-0.8.0/mapsforge-map-reader/src) \
                   $(call all-java-files-under, libs/mapsforge-0.8.0/mapsforge-themes/src) \
                   $(call all-java-files-under, libs/osmdroid-osmdroid-parent-6.0.1/osmdroid-android/src) \
                   $(call all-java-files-under, libs/osmdroid-osmdroid-parent-6.0.1/osmdroid-mapsforge/src) \
                   $(call all-java-files-under, libs/slf4j-1.7.7/slf4j-api/src) \
                   $(call all-java-files-under, libs/slf4j-1.7.7/slf4j-simple/src) \
                   $(call all-java-files-under, libs/adobe-xmp-core-5.1.2/src)

LOCAL_RESOURCE_DIR := $(LOCAL_PATH)/libs/osmdroid-osmdroid-parent-6.0.1/osmdroid-android/res \
        $(LOCAL_PATH)/libs/androidsvg-Release_1.2.2-beta-1/res \
        $(LOCAL_PATH)/libs/PhotoView-1.2.6.k3b-SNAPSHOT/res \
        $(appcompat_dir) $(LOCAL_PATH)/res

LOCAL_AAPT_FLAGS := --auto-add-overlay

LOCAL_PACKAGE_NAME := OtoPhoto

LOCAL_PROGUARD_FLAG_FILES := proguard.flags
LOCAL_CERTIFICATE := platform
LOCAL_PRIVILEGED_MODULE := true
LOCAL_OVERRIDES_PACKAGES := Gallery2 \
                            Music

include $(BUILD_PACKAGE)

# Use the folloing include to make our test apk.
include $(call all-makefiles-under,$(LOCAL_PATH))
