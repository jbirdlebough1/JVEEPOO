package com.veepoo.protocol.listener.data;

import com.veepoo.protocol.listener.base.IListener;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/listener/data/IImageMsgPushListener.class */
public interface IImageMsgPushListener extends IListener {

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/listener/data/IImageMsgPushListener$ErrorCode.class */
    public enum ErrorCode {
        IMAGE_PATH_ERROR("图片地址错误，不存在"),
        IMAGE_SIZE_ERROR("图片尺寸异常"),
        IMAGE_TOO_LARGE("图片内存太大了"),
        TERMINATION("传输终止"),
        LISTENER_IS_NULL("监听为空"),
        NEED_READ_BASE_INFO("没有先执行读取UI信息"),
        FILE_NOT_EXIST("文件不存在"),
        LOW_BATTERY("电量过低"),
        INTO_UPDATE_MODE_FAIL("进入UI模式失败"),
        FILE_LENGTH_NOT_4_POWER("文件没有4字节对齐"),
        CHECK_CRC_FAIL("crc校验失败"),
        FUNCTION_NOT_SUPPORT("功能不支持"),
        UNKNOWN("未知错误");

        public String info;

        ErrorCode(String str) {
            this.info = str;
        }
    }

    void onImageMsgPushSuccess();

    void onImageMsgPushProgress(int i, int i2, int i3);

    void onImageMsgPushFailed(ErrorCode errorCode);
}
