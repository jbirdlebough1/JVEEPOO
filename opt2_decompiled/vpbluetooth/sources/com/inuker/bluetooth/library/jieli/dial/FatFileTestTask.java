package com.inuker.bluetooth.library.jieli.dial;

import android.content.Context;
import com.inuker.bluetooth.library.BluetoothContext;
import com.jieli.jl_fatfs.interfaces.OnFatFileProgressListener;
import com.jieli.jl_fatfs.model.FatFile;
import com.jieli.jl_fatfs.utils.FatUtil;
import com.jieli.jl_rcsp.impl.WatchOpImpl;
import com.jieli.jl_rcsp.interfaces.watch.OnWatchOpCallback;
import com.jieli.jl_rcsp.model.base.BaseError;
import com.jieli.jl_rcsp.model.device.DeviceInfo;
import com.jieli.jl_rcsp.util.JL_Log;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Random;

/* JADX INFO: loaded from: vpbluetooth-1.20.aar:classes.jar:com/inuker/bluetooth/library/jieli/dial/FatFileTestTask.class */
public class FatFileTestTask extends AbstractTestTask {
    private final WatchOpImpl mWatchOp;
    private final String testResDir;
    private final boolean isNoNeedCheck;
    private boolean isStopTest;
    private boolean tryToInsert;

    public FatFileTestTask(WatchOpImpl mWatchOp, String testResDir, boolean isNoNeedCheck) {
        this.mWatchOp = mWatchOp;
        this.testResDir = testResDir;
        this.isNoNeedCheck = isNoNeedCheck;
    }

    @Override // com.inuker.bluetooth.library.jieli.dial.ITestTask
    public void startTest() {
        File file = getRandomFile(this.testResDir);
        if (file == null) {
            next(new TestError(5, String.format(Locale.CHINA, "----开始测试失败----\n原因：%s,\t文件路径：%s", FatUtil.getFatFsErrorCodeMsg(5), this.testResDir)));
            return;
        }
        this.isStopTest = false;
        JL_Log.d("fat_test", "startTest : " + file);
        insertFile(file.getPath());
    }

    @Override // com.inuker.bluetooth.library.jieli.dial.ITestTask
    public void stopTest() {
        this.isStopTest = true;
    }

    @Override // com.inuker.bluetooth.library.jieli.dial.AbstractTestTask, com.inuker.bluetooth.library.jieli.dial.ITestTask
    public void onTestLog(String msg) {
        JL_Log.d("fat_test", "onTestLog : " + msg);
        super.onTestLog(msg);
    }

    @Override // com.inuker.bluetooth.library.jieli.dial.AbstractTestTask, com.inuker.bluetooth.library.jieli.dial.INextTask
    public void next(TestError error) {
        if (this.isStopTest) {
            this.isStopTest = false;
        }
        super.next(error);
    }

    private void insertFile(final String filePath) {
        if (stopTestCallback()) {
            return;
        }
        this.mWatchOp.createWatchFile(filePath, this.isNoNeedCheck, new OnFatFileProgressListener() { // from class: com.inuker.bluetooth.library.jieli.dial.FatFileTestTask.1
            public void onStart(String s) {
                Locale locale = Locale.CHINA;
                Object[] objArr = new Object[2];
                objArr[0] = FatFileTestTask.this.isNoNeedCheck ? "自定义背景" : "表盘";
                objArr[1] = FatUtil.getFatFilePath(s);
                String msg = String.format(locale, "----插入%s开始----\n路径: %s", objArr);
                FatFileTestTask.this.onTestLog(msg);
            }

            public void onProgress(float v) {
                int progress = Math.round(v);
                if (progress > 100) {
                    progress = 99;
                }
                Locale locale = Locale.CHINA;
                Object[] objArr = new Object[2];
                objArr[0] = FatFileTestTask.this.isNoNeedCheck ? "自定义背景" : "表盘";
                objArr[1] = Integer.valueOf(progress);
                String msg = String.format(locale, "----插入%s进度---- %d", objArr);
                FatFileTestTask.this.onTestLog(msg);
            }

            public void onStop(int i) {
                Locale locale = Locale.CHINA;
                Object[] objArr = new Object[1];
                objArr[0] = FatFileTestTask.this.isNoNeedCheck ? "自定义背景" : "表盘";
                String msg = String.format(locale, "----插入%s结束----\n结果：", objArr);
                if (i == 0) {
                    String fatFilePath = FatUtil.getFatFilePath(filePath);
                    FatFileTestTask.this.onTestLog(msg + "成功，路径：" + fatFilePath);
                    if (FatFileTestTask.this.tryToInsert) {
                        FatFileTestTask.this.tryToInsert = false;
                    }
                    FatFileTestTask.this.enableFatFile(fatFilePath);
                    return;
                }
                if (i == 20) {
                    FatFileTestTask.this.onTestLog(msg + "失败， 空间不足");
                    FatFileTestTask.this.listAndDelFatFiles(filePath);
                } else {
                    FatFileTestTask.this.next(new TestError(i, msg + "失败， " + i + " " + FatUtil.getFatFsErrorCodeMsg(i)));
                }
            }
        });
    }

    public void listAndDelFatFiles(final String path) {
        if (stopTestCallback()) {
            return;
        }
        this.mWatchOp.listWatchList(new OnWatchOpCallback<ArrayList<FatFile>>() { // from class: com.inuker.bluetooth.library.jieli.dial.FatFileTestTask.2
            public void onSuccess(ArrayList<FatFile> fatFiles) {
                if (fatFiles == null) {
                    FatFileTestTask.this.next(new TestError(12288, "----查询手表文件列表----\n结果：失败，数据异常"));
                    return;
                }
                if (fatFiles.isEmpty()) {
                    if (!FatFileTestTask.this.tryToInsert) {
                        FatFileTestTask.this.tryToInsert = true;
                        FatFileTestTask.this.insertFile(path);
                        return;
                    } else {
                        FatFileTestTask.this.next(new TestError(20, "失败， 空间不足"));
                        return;
                    }
                }
                if (FatFileTestTask.this.stopTestCallback()) {
                    return;
                }
                Iterator<FatFile> it = fatFiles.iterator();
                while (it.hasNext()) {
                    JL_Log.v("fat_test", "fatFile = " + it.next());
                }
                int random = FatFileTestTask.this.getRandom(fatFiles.size());
                FatFile fatFile = fatFiles.get(random);
                if (fatFile.getName().equalsIgnoreCase("SIDEBAR") || fatFile.getName().equalsIgnoreCase("font") || fatFile.getName().equalsIgnoreCase("jl")) {
                    JL_Log.w("fat_test", "delete fat file error , path = " + fatFile.getPath());
                } else {
                    JL_Log.i("fat_test", "delete fat file, path = " + fatFile.getPath());
                    FatFileTestTask.this.mWatchOp.deleteWatchFile(fatFile.getPath(), new OnFatFileProgressListener() { // from class: com.inuker.bluetooth.library.jieli.dial.FatFileTestTask.2.1
                        public void onStart(String s) {
                            Locale locale = Locale.CHINA;
                            Object[] objArr = new Object[2];
                            objArr[0] = FatFileTestTask.this.isNoNeedCheck ? "自定义背景" : "表盘";
                            objArr[1] = s;
                            String msg = String.format(locale, "----删除%s开始----\n路径: %s", objArr);
                            FatFileTestTask.this.onTestLog(msg);
                        }

                        public void onProgress(float v) {
                            int progress = Math.round(v);
                            if (progress > 100) {
                                progress = 99;
                            }
                            Locale locale = Locale.CHINA;
                            Object[] objArr = new Object[2];
                            objArr[0] = FatFileTestTask.this.isNoNeedCheck ? "自定义背景" : "表盘";
                            objArr[1] = Integer.valueOf(progress);
                            String msg = String.format(locale, "----删除%s进度----%d", objArr);
                            FatFileTestTask.this.onTestLog(msg);
                        }

                        public void onStop(int i) {
                            if (i == 0) {
                                FatFileTestTask.this.insertFile(path);
                                return;
                            }
                            Locale locale = Locale.CHINA;
                            Object[] objArr = new Object[2];
                            objArr[0] = FatFileTestTask.this.isNoNeedCheck ? "自定义背景" : "表盘";
                            objArr[1] = FatUtil.getFatFsErrorCodeMsg(i);
                            String msg = String.format(locale, "----删除%s结束----\n结果: 失败，%s", objArr);
                            FatFileTestTask.this.next(new TestError(i, msg));
                        }
                    });
                }
            }

            public void onFailed(BaseError baseError) {
                String msg = "----查询手表文件列表----\n结果：失败，发送异常， " + baseError.getMessage();
                FatFileTestTask.this.next(new TestError(baseError.getSubCode(), msg));
            }
        });
    }

    public void enableFatFile(String fatFilePath) {
        if (!this.isNoNeedCheck) {
            this.mWatchOp.setCurrentWatchInfo(fatFilePath, new OnWatchOpCallback<FatFile>() { // from class: com.inuker.bluetooth.library.jieli.dial.FatFileTestTask.3
                public void onSuccess(FatFile fatFile) {
                    String msg = String.format(Locale.CHINA, "----设置当前表盘----\n结果: 成功，路径：%s", fatFile.getPath());
                    FatFileTestTask.this.next(new TestError(0, msg));
                }

                public void onFailed(BaseError baseError) {
                    String msg = String.format(Locale.CHINA, "----设置当前表盘----\n结果: 失败，发送异常，%s", baseError.getMessage());
                    FatFileTestTask.this.next(new TestError(baseError.getSubCode(), msg));
                }
            });
        } else {
            this.mWatchOp.enableCustomWatchBg(fatFilePath, new OnWatchOpCallback<FatFile>() { // from class: com.inuker.bluetooth.library.jieli.dial.FatFileTestTask.4
                public void onSuccess(FatFile fatFile) {
                    String msg = String.format(Locale.CHINA, "----激活自定义表盘----\n结果: 成功，路径：%s", fatFile.getPath());
                    FatFileTestTask.this.next(new TestError(0, msg));
                }

                public void onFailed(BaseError baseError) {
                    String msg = String.format(Locale.CHINA, "----激活自定义表盘----\n结果: 失败，发送异常，%s", baseError.getMessage());
                    FatFileTestTask.this.next(new TestError(baseError.getSubCode(), msg));
                }
            });
        }
    }

    private int getRandom(int bound) {
        return new Random().nextInt(bound) % bound;
    }

    private File getRandomFile(String dirPath) {
        File resDir = new File(dirPath);
        File[] files = resDir.listFiles();
        List<File> watchList = new ArrayList<>();
        if (files != null) {
            for (File file : files) {
                if (!this.isNoNeedCheck && file.getName().toLowerCase().startsWith(WatchConstant.DIR_WATCH)) {
                    watchList.add(file);
                } else if (this.isNoNeedCheck && file.getName().toLowerCase().startsWith("bgp_")) {
                    watchList.add(file);
                }
            }
        }
        if (watchList.isEmpty()) {
            return null;
        }
        int random = getRandom(watchList.size());
        return watchList.get(random);
    }

    private boolean stopTestCallback() {
        if (this.isStopTest) {
            next(new TestError(8, "----用户停止测试----"));
            return true;
        }
        return false;
    }

    @Override // com.inuker.bluetooth.library.jieli.dial.ITestTask
    public String getName() {
        return this.isNoNeedCheck ? "插入表盘背景" : "插入表盘";
    }

    /* JADX INFO: loaded from: vpbluetooth-1.20.aar:classes.jar:com/inuker/bluetooth/library/jieli/dial/FatFileTestTask$Factory.class */
    public static class Factory implements ITaskFactory {
        private final boolean isNoNeedCheck;

        public Factory(boolean isNoNeedCheck) {
            this.isNoNeedCheck = isNoNeedCheck;
        }

        @Override // com.inuker.bluetooth.library.jieli.dial.ITaskFactory
        public ITestTask create() {
            DeviceInfo deviceInfo = WatchManager.getInstance().getDeviceInfo();
            String str = (null != deviceInfo && deviceInfo.getSdkType() == 9) ? WatchConstant.DIR_BR28 : WatchConstant.DIR_BR23;
            String subDir = str;
            Context context = BluetoothContext.get();
            String[] strArr = new String[2];
            strArr[0] = this.isNoNeedCheck ? WatchConstant.DIR_WATCH_BG : WatchConstant.DIR_WATCH;
            strArr[1] = subDir;
            String path = AppUtil.createFilePath(context, strArr);
            return new FatFileTestTask(WatchManager.getInstance(), path, this.isNoNeedCheck);
        }
    }
}
