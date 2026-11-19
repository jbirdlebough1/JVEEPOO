package com.veepoo.protocol.operate;

import com.inuker.bluetooth.library.BluetoothClient;
import com.inuker.bluetooth.library.connect.response.BleWriteResponse;
import com.veepoo.protocol.listener.base.IListener;
import com.veepoo.protocol.listener.data.IOriginData3Listener;
import com.veepoo.protocol.model.datas.BloodComponent;
import com.veepoo.protocol.model.datas.HRVOriginData;
import com.veepoo.protocol.model.datas.OriginData3;
import com.veepoo.protocol.model.datas.OriginHalfHourData;
import com.veepoo.protocol.model.datas.Spo2hOriginData;
import com.veepoo.protocol.model.datas.TimeData;
import com.veepoo.protocol.model.datas.ecg.FiveMinuteData;
import com.veepoo.protocol.model.datas.ecg.HRVFiveMinuteData;
import com.veepoo.protocol.model.datas.ecg.Spo2hMinuteData;
import com.veepoo.protocol.model.datas.ecg.SportFiveMinuteData;
import com.veepoo.protocol.model.enums.EBloodGlucoseRiskLevel;
import com.veepoo.protocol.model.settings.ReadOriginSetting;
import com.veepoo.protocol.util.SportUtil;
import com.veepoo.protocol.util.VpBleByteUtil;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/operate/vp_bu.class */
public class vp_bu extends com.veepoo.protocol.vp_a {
    private static int vp_t = 3;
    public static final SimpleDateFormat vp_u = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss||SSS", Locale.CHINA);
    BluetoothClient vp_e;
    String vp_f;
    BleWriteResponse vp_g;
    IOriginData3Listener vp_h;
    Timer vp_k;
    vp_ar vp_m;
    List vp_c = Collections.synchronizedList(new ArrayList());
    private boolean vp_d = false;
    private final byte vp_i = 0;
    private float vp_j = 0.0f;
    final int vp_l = 60000;
    boolean vp_n = false;
    boolean vp_o = false;
    int vp_p = 0;
    int vp_q = 1;
    int vp_r = -1;
    private volatile boolean vp_s = false;

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/operate/vp_bu$vp_a.class */
    class vp_a extends TimerTask {
        int vp_a;

        public vp_a(int i) {
            this.vp_a = i;
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            if (vp_bu.this.vp_s) {
                vp_bu.this.vp_d(this.vp_a);
                return;
            }
            vp_bu vp_buVar = vp_bu.this;
            if (vp_buVar.vp_h == null) {
                return;
            }
            List list = vp_buVar.vp_c;
            if (list != null) {
                list.clear();
            }
            vp_bu.this.vp_h.onReadTimeout(this.vp_a);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0, types: [com.veepoo.protocol.operate.vp_bu] */
    /* JADX WARN: Type inference failed for: r0v1, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v4, types: [com.veepoo.protocol.operate.vp_bu] */
    private void vp_b(int i) {
        ?? r0 = this;
        r0.vp_c(i);
        synchronized (r0) {
            try {
                if (!r0.vp_c.isEmpty()) {
                    this.vp_c.clear();
                }
                r0 = this;
                r0.vp_m = new vp_ar();
            } catch (Throwable unused) {
                throw r0;
            }
        }
    }

    private void vp_c(int i) {
        Timer timer = this.vp_k;
        if (timer != null) {
            timer.cancel();
            this.vp_k = null;
        }
        Timer timer2 = new Timer();
        this.vp_k = timer2;
        timer2.schedule(new vp_a(i), 60000L);
        new StringBuilder().append("数据DF读取处理，添加").append(i).append("天的超时判断");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v5, types: [com.veepoo.protocol.operate.vp_bu] */
    /* JADX WARN: Type inference failed for: r0v6, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v8, types: [com.veepoo.protocol.operate.vp_bu] */
    private void vp_d(int i) {
        new StringBuilder().append("数据DF读取处理，5分钟原始[Heart Rate]数据,").append(this.vp_p).append("天，读取结束");
        long jCurrentTimeMillis = System.currentTimeMillis();
        List listVp_a = vp_a(this.vp_c);
        new StringBuilder().append("数据DF读取处理，").append(listVp_a.size()).append(",拷贝时长：").append(System.currentTimeMillis() - jCurrentTimeMillis);
        vp_a(i);
        vp_a(listVp_a, i);
        new StringBuilder().append("5分钟原始[Heart Rate]数据,").append(i).append("天，读取结束");
        int i2 = i + 1;
        if (i2 > vp_t - 1 || this.vp_d) {
            vp_a();
            return;
        }
        ?? r0 = this;
        synchronized (r0) {
            try {
                List list = r0.vp_c;
                if (list != null && !list.isEmpty()) {
                    this.vp_c.clear();
                }
                r0 = this;
                r0.vp_e(i2);
            } catch (Throwable unused) {
                throw r0;
            }
        }
    }

    private void vp_e(int i) {
        this.vp_s = false;
        vp_a(this.vp_e, this.vp_f, this.vp_g, new ReadOriginSetting(i, 1, this.vp_d, vp_t), this.vp_n, this.vp_o);
    }

    private void vp_a(List list, int i) {
        if (list == null || list.isEmpty()) {
            return;
        }
        try {
            List listVp_a = this.vp_m.vp_a(list, i);
            new StringBuilder().append("数据DF读取处理，开始保存第").append(i).append("天数据,原始byte共").append(list.size()).append("条");
            if (listVp_a == null || listVp_a.isEmpty()) {
                return;
            }
            List<OriginData3> listVp_c = vp_c(listVp_a);
            if (this.vp_o) {
                this.vp_h.onOriginHRVOriginListDataChange(vp_b(listVp_a));
            }
            List<Spo2hOriginData> listVp_d = vp_d(listVp_a);
            new StringBuilder().append("数据DF读取处理，开始保存第").append(i).append("天数据,OriginData共").append(listVp_c.size()).append("条");
            PrintStream printStream = System.out;
            printStream.println("数据DF读取处理，开始保存第" + i + "天数据,Origin3Data共" + listVp_c.size() + "条");
            this.vp_h.onOriginFiveMinuteListDataChange(listVp_c);
            this.vp_h.onOriginSpo2OriginListDataChange(listVp_d);
            this.vp_h.onOriginHalfHourDataChange(vp_b(listVp_c, i));
            new StringBuilder().append("数据DF读取处理，结束保存第").append(i).append("天数据");
            printStream.println("数据DF读取处理，结束保存第" + i + "天数据");
        } catch (Throwable th) {
            new StringBuilder().append("数据DF读取处理，第").append(i).append("天数据解析失败：").append(th);
        }
    }

    /* JADX WARN: Code duplicated, block: B:30:0x014c A[PHI: r8
  0x014c: PHI (r8v3 int) = (r8v1 int), (r8v4 int) binds: [B:33:0x01b4, B:29:0x0149] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v10 */
    /* JADX WARN: Type inference failed for: r0v5 */
    /* JADX WARN: Type inference failed for: r0v6, types: [java.lang.Throwable] */
    @Override // com.veepoo.protocol.vp_a, com.veepoo.protocol.listener.base.IHandler
    public void handler(byte[] bArr) {
        int iTwoByteToUnsignedInt;
        super.handler(bArr);
        if (bArr.length >= 20 && this.vp_h != null) {
            ?? r0 = this;
            synchronized (r0) {
                try {
                    this.vp_c.add(bArr);
                    r0 = this;
                    int[] iArrByte2HexToIntArr = VpBleByteUtil.byte2HexToIntArr(bArr);
                    byte b = bArr[1];
                    byte b2 = bArr[2];
                    this.vp_s = b == b2 && b2 == -1;
                    if (this.vp_s) {
                        if (this.vp_p == -1) {
                            this.vp_p = iArrByte2HexToIntArr[7];
                        }
                        int iTwoByteToUnsignedInt2 = VpBleByteUtil.twoByteToUnsignedInt(bArr[4], bArr[3]);
                        new StringBuilder().append("数据DF读取处理，结束包:").append(VpBleByteUtil.byte2HexForShow(bArr));
                        new StringBuilder().append("数据DF读取处理，结束包:allBlock->").append(iTwoByteToUnsignedInt2);
                        IOriginData3Listener iOriginData3Listener = this.vp_h;
                        int i = this.vp_p;
                        iOriginData3Listener.onReadOriginProgressDetail(i, com.veepoo.protocol.util.vp_j.vp_a(-i), iTwoByteToUnsignedInt2, iTwoByteToUnsignedInt2);
                        vp_d(this.vp_p);
                        this.vp_r = -1;
                        this.vp_p = -1;
                        return;
                    }
                    if (bArr[3] == 0) {
                        this.vp_p = iArrByte2HexToIntArr[7];
                        iTwoByteToUnsignedInt = VpBleByteUtil.twoByteToUnsignedInt(bArr[2], bArr[1]);
                        this.vp_q = VpBleByteUtil.twoByteToUnsignedInt(bArr[5], bArr[4]);
                        new StringBuilder().append("数据DF读取处理，首包:").append(VpBleByteUtil.byte2HexForShow(bArr)).append(",readingDay:").append(this.vp_p);
                        new StringBuilder().append("数据DF读取处理，首包:currentBlock->").append(iTwoByteToUnsignedInt).append(",allBlock->").append(this.vp_q).append(",currentBlock package index:").append(bArr[3] & 255).append(",currentBlock package all index:").append(bArr[6] & 255);
                        if (this.vp_r != iTwoByteToUnsignedInt) {
                            IOriginData3Listener iOriginData3Listener2 = this.vp_h;
                            int i2 = this.vp_p;
                            iOriginData3Listener2.onReadOriginProgressDetail(i2, com.veepoo.protocol.util.vp_j.vp_a(-i2), this.vp_q, iTwoByteToUnsignedInt);
                        }
                    } else {
                        iTwoByteToUnsignedInt = VpBleByteUtil.twoByteToUnsignedInt(bArr[2], bArr[1]);
                        int i3 = bArr[3] & 255;
                        new StringBuilder().append("数据DF读取处理，中间包:").append(VpBleByteUtil.byte2HexForShow(bArr));
                        new StringBuilder().append("数据DF读取处理，中间包:currentBlock->").append(iTwoByteToUnsignedInt).append(",currentBlock package index:").append(i3);
                        if (this.vp_r != iTwoByteToUnsignedInt) {
                            IOriginData3Listener iOriginData3Listener3 = this.vp_h;
                            int i4 = this.vp_p;
                            iOriginData3Listener3.onReadOriginProgressDetail(i4, com.veepoo.protocol.util.vp_j.vp_a(-i4), this.vp_q, iTwoByteToUnsignedInt);
                        }
                    }
                    this.vp_r = iTwoByteToUnsignedInt;
                } catch (Throwable th) {
                    throw r0;
                }
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0, types: [com.veepoo.protocol.operate.vp_bu] */
    /* JADX WARN: Type inference failed for: r0v1, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v3, types: [com.veepoo.protocol.operate.vp_bu] */
    private void vp_a(int i) {
        ?? r0 = this;
        new StringBuilder().append("数据DF读取处理，取消").append(i).append("天的超时判断");
        try {
            Timer timer = r0.vp_k;
            if (timer != null) {
                r0 = this;
                timer.cancel();
                r0.vp_k = null;
            }
        } catch (Exception unused) {
            r0.printStackTrace();
        }
    }

    private OriginHalfHourData vp_b(List list, int i) {
        if (list == null || list.isEmpty()) {
            return null;
        }
        System.out.println("===============>开始获取 原始数据半小时， 原始数据长度：" + list.size());
        com.veepoo.protocol.util.vp_t vp_tVar = new com.veepoo.protocol.util.vp_t();
        List listVp_b = vp_tVar.vp_b(list, i);
        List listVp_c = vp_tVar.vp_c(list, i);
        List listVp_b2 = vp_tVar.vp_b(list);
        int iVp_a = vp_tVar.vp_a(list);
        Calendar calendar = Calendar.getInstance();
        calendar.add(6, i * (-1));
        calendar.set(10, 0);
        calendar.set(12, 0);
        calendar.set(13, 0);
        calendar.set(14, 0);
        OriginHalfHourData originHalfHourData = new OriginHalfHourData(listVp_b, listVp_c, listVp_b2, iVp_a);
        originHalfHourData.setDate(vp_u.format(calendar.getTime()));
        return originHalfHourData;
    }

    private List vp_c(List list) {
        OriginData3 originData3Vp_a;
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            FiveMinuteData fiveMinuteData = (FiveMinuteData) list.get(i);
            if (fiveMinuteData.getTimeBean() != null && (originData3Vp_a = vp_a(fiveMinuteData, list.size(), i + 1)) != null) {
                arrayList.add(originData3Vp_a);
                new StringBuilder().append("数据DF读取处理:originData3= ").append(originData3Vp_a.toString());
            }
        }
        return arrayList;
    }

    private List vp_d(List list) {
        new StringBuilder().append("数据DF读取处理，fiveMinuteDataList:").append(list.size());
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            arrayList.addAll(vp_b((FiveMinuteData) list.get(i)));
        }
        new StringBuilder().append("数据DF读取处理，开始获取Spo2hOriginData:").append(arrayList.size());
        return arrayList;
    }

    private List vp_b(List list) {
        new StringBuilder().append("数据DF读取处理，fiveMinuteDataList:").append(list.size());
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            List listVp_a = vp_a((FiveMinuteData) list.get(i));
            if (listVp_a != null) {
                arrayList.addAll(listVp_a);
            }
        }
        new StringBuilder().append("数据DF读取处理，20 group alarmHRVlist:").append(arrayList.size());
        return arrayList;
    }

    public synchronized List vp_a(List list) {
        ArrayList arrayList = new ArrayList(list.size());
        Iterator it = list.iterator();
        while (it.hasNext()) {
            arrayList.add((byte[]) ((byte[]) it.next()).clone());
        }
        return arrayList;
    }

    private void vp_a() {
        Timer timer = this.vp_k;
        if (timer != null) {
            timer.cancel();
            this.vp_k = null;
        }
        IOriginData3Listener iOriginData3Listener = this.vp_h;
        if (iOriginData3Listener == null) {
            return;
        }
        iOriginData3Listener.onReadOriginProgress(1.0f);
        this.vp_h.onReadOriginComplete();
    }

    private List vp_b(FiveMinuteData fiveMinuteData) {
        int i;
        ArrayList arrayList = new ArrayList();
        Spo2hMinuteData spo2HMinuteData = fiveMinuteData.getSpo2HMinuteData();
        if (spo2HMinuteData == null) {
            return arrayList;
        }
        TimeData timeBean = fiveMinuteData.getTimeBean();
        int[] rate = fiveMinuteData.getRate();
        int[] heart = fiveMinuteData.getHeart();
        int[] breath = fiveMinuteData.getBreath();
        int[] sleepSport = fiveMinuteData.getSleepSport();
        HRVFiveMinuteData hrvFiveMinuteData = fiveMinuteData.getHrvFiveMinuteData();
        int[] hrv5 = hrvFiveMinuteData != null ? hrvFiveMinuteData.getHrv5() : null;
        int[] spo2hValue = spo2HMinuteData.getSpo2hValue();
        int[] apneaCount = spo2HMinuteData.getApneaCount();
        int[] apneaResult = spo2HMinuteData.getApneaResult();
        int[] hypoxiaTime = spo2HMinuteData.getHypoxiaTime();
        int[] cardiacLoad = spo2HMinuteData.getCardiacLoad();
        int[] checkFlag = spo2HMinuteData.getCheckFlag();
        for (int i2 = 0; i2 < Math.min(spo2hValue.length, 5); i2++) {
            int[] iArr = hrv5;
            int i3 = cardiacLoad[i2];
            int i4 = spo2hValue[i2];
            int i5 = apneaCount[i2];
            int i6 = apneaResult[i2];
            int i7 = hypoxiaTime[i2];
            int i8 = checkFlag[i2];
            int i9 = iArr != null ? hrv5[i2] : 0;
            int i10 = breath[i2];
            int i11 = 0;
            if (this.vp_n && sleepSport != null && sleepSport.length > 0) {
                i11 = sleepSport[i2];
            }
            int i12 = 0;
            if (heart != null && i2 < heart.length && (i = heart[i2]) >= 30 && i <= 200) {
                i12 = i;
            } else if (rate != null && i2 < rate.length) {
                i12 = rate[i2];
            }
            TimeData timeDataVp_a = vp_a(timeBean, i2 * 60);
            Spo2hOriginData spo2hOriginData = new Spo2hOriginData();
            spo2hOriginData.setDate(timeDataVp_a.toDatabaseDateString());
            spo2hOriginData.setmTime(timeDataVp_a);
            spo2hOriginData.setOxygenValue(i4);
            if (this.vp_n) {
                spo2hOriginData.setHeartValue(i12);
                spo2hOriginData.setSportValue(i11);
                spo2hOriginData.setApneaResult(i5);
                spo2hOriginData.setIsHypoxia(i6);
                spo2hOriginData.setHypoxiaTime(i7);
                spo2hOriginData.setHypopnea(0);
                spo2hOriginData.setCardiacLoad(i3);
                spo2hOriginData.sethRVariation(i9);
                spo2hOriginData.setStepValue(0);
                spo2hOriginData.setRespirationRate(i10);
                spo2hOriginData.setTemp1(i8);
            }
            arrayList.add(spo2hOriginData);
        }
        return arrayList;
    }

    public TimeData vp_a(TimeData timeData, int i) {
        int year = timeData.getYear();
        int month = timeData.getMonth() - 1;
        int day = timeData.getDay();
        int hour = timeData.getHour();
        int minute = timeData.getMinute();
        int second = timeData.getSecond();
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day, hour, minute, second);
        calendar.add(13, i);
        return new TimeData(calendar.get(1), calendar.get(2) + 1, calendar.get(5), calendar.get(11), calendar.get(12), calendar.get(13));
    }

    private List vp_a(FiveMinuteData fiveMinuteData) {
        ArrayList arrayList = new ArrayList();
        HRVFiveMinuteData hrvFiveMinuteData = fiveMinuteData.getHrvFiveMinuteData();
        TimeData timeBean = fiveMinuteData.getTimeBean();
        if (hrvFiveMinuteData == null) {
            return arrayList;
        }
        int hrvType = hrvFiveMinuteData.getHrvType();
        int[] hrv5 = hrvFiveMinuteData.getHrv5();
        int[] rr50 = hrvFiveMinuteData.getRr50();
        for (int i = 0; i < hrv5.length; i++) {
            TimeData timeDataVp_a = vp_a(timeBean, i * 60);
            HRVOriginData hRVOriginData = new HRVOriginData();
            hRVOriginData.setDate(timeDataVp_a.toDatabaseDateString());
            hRVOriginData.setmTime(timeDataVp_a);
            hRVOriginData.setTempOne(0);
            hRVOriginData.setHrvValue(hrv5[i]);
            hRVOriginData.setRate(vp_a(rr50, i));
            hRVOriginData.setCurrentPackNumber(0);
            hRVOriginData.setAllCurrentPackNumber(0);
            hRVOriginData.setHrvType(hrvType);
            arrayList.add(hRVOriginData);
        }
        return arrayList;
    }

    private OriginData3 vp_a(FiveMinuteData fiveMinuteData, int i, int i2) {
        TimeData timeBean = fiveMinuteData.getTimeBean();
        OriginData3 originData3 = new OriginData3();
        originData3.setDate(timeBean.toDatabaseDateString());
        originData3.setmTime(timeBean);
        SportFiveMinuteData sportFiveMinuteData = fiveMinuteData.getSportFiveMinuteData();
        originData3.setSportValue(sportFiveMinuteData.getSport());
        originData3.setGesture(new int[]{sportFiveMinuteData.getPose()});
        originData3.setStepValue(sportFiveMinuteData.getStep());
        originData3.setWear(sportFiveMinuteData.getWear());
        int dis = sportFiveMinuteData.getDis();
        int kal = sportFiveMinuteData.getKal();
        double positionDouble = SportUtil.getPositionDouble(((double) dis) / 1000.0d, 3);
        double positionDouble2 = SportUtil.getPositionDouble(((double) kal) / 10.0d, 1);
        originData3.setDisValue(positionDouble);
        originData3.setCalValue(positionDouble2);
        if (fiveMinuteData.getBp() != null && fiveMinuteData.getBp().length == 2) {
            originData3.setHighValue(fiveMinuteData.getBp()[0]);
            originData3.setLowValue(fiveMinuteData.getBp()[1]);
        }
        int[] heart = fiveMinuteData.getHeart();
        int[] rate = fiveMinuteData.getRate();
        if (this.vp_n) {
            originData3.setSleepSports(fiveMinuteData.getSleepSport());
        }
        originData3.setPpgs(rate);
        originData3.setEcgs(heart);
        originData3.setPressure(fiveMinuteData.getPressure());
        originData3.setMet(fiveMinuteData.getMet());
        if (this.vp_n) {
            originData3.setResRates(fiveMinuteData.getBreath());
        }
        originData3.setSleepStates(fiveMinuteData.getSleep());
        originData3.setSleepStatusQuantity(fiveMinuteData.getSleepStatus());
        originData3.setResetTagContent(fiveMinuteData.getResetTagContent());
        originData3.setBloodGlucose(fiveMinuteData.getBloodGlucose() / 100.0f);
        if (fiveMinuteData.getBloodGlucoseRiskLevel() == 1) {
            originData3.setBloodGlucoseRiskLevel(EBloodGlucoseRiskLevel.LOW);
        } else if (fiveMinuteData.getBloodGlucoseRiskLevel() == 2) {
            originData3.setBloodGlucoseRiskLevel(EBloodGlucoseRiskLevel.MIDDLE);
        } else if (fiveMinuteData.getBloodGlucoseRiskLevel() == 3) {
            originData3.setBloodGlucoseRiskLevel(EBloodGlucoseRiskLevel.HIGH);
        }
        originData3.setSportStatusVersion(fiveMinuteData.getSportStatusVersion());
        originData3.setSportStatus(fiveMinuteData.getSportStatus());
        Spo2hMinuteData spo2HMinuteData = fiveMinuteData.getSpo2HMinuteData();
        if (spo2HMinuteData != null) {
            originData3.setOxygens(spo2HMinuteData.getSpo2hValue());
            if (this.vp_n) {
                originData3.setApneaResults(spo2HMinuteData.getApneaCount());
                originData3.setHypoxiaTimes(spo2HMinuteData.getHypoxiaTime());
                originData3.setIsHypoxias(spo2HMinuteData.getApneaResult());
                originData3.setCardiacLoads(spo2HMinuteData.getCardiacLoad());
                originData3.setCorrects(spo2HMinuteData.getCheckFlag());
            }
        }
        BloodComponent bloodComposition = fiveMinuteData.getBloodComposition();
        if (bloodComposition != null) {
            originData3.setBloodComposition(bloodComposition);
        }
        originData3.setBaseTemperature(fiveMinuteData.getBaseTemperature());
        originData3.setTemperature(fiveMinuteData.getTemperature());
        originData3.setAllPackage(i);
        originData3.setPackageNumber(i2);
        return originData3;
    }

    public byte[] vp_a(ReadOriginSetting readOriginSetting) {
        int day = readOriginSetting.getDay();
        byte[] bArr = new byte[20];
        bArr[0] = -33;
        bArr[1] = VpBleByteUtil.loUint16((short) readOriginSetting.getPosition());
        bArr[2] = VpBleByteUtil.hiUint16((short) readOriginSetting.getPosition());
        bArr[3] = VpBleByteUtil.loUint16((short) day);
        return bArr;
    }

    private String vp_a(int[] iArr, int i) {
        String str = "";
        int i2 = i * 10;
        for (int i3 = i2; i3 < i2 + 10; i3++) {
            int i4 = i3;
            str = str + iArr[i3];
            if (i4 != i2 + 9) {
                str = str + ",";
            }
        }
        return str;
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_a(byte[] bArr, IListener iListener) {
        this.vp_h = (IOriginData3Listener) iListener;
        handler(bArr);
    }

    public void vp_a(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, ReadOriginSetting readOriginSetting, boolean z, boolean z2) {
        this.vp_e = bluetoothClient;
        this.vp_f = str;
        this.vp_j = 0.0f;
        this.vp_g = bleWriteResponse;
        int day = readOriginSetting.getDay();
        this.vp_d = readOriginSetting.isReadOneDay();
        this.vp_n = z;
        this.vp_o = z2;
        vp_t = readOriginSetting.getWatchday();
        new StringBuilder().append("数据DF读取处理，ORIGIN_DATA_DAY:").append(vp_t);
        vp_b(day);
        byte[] bArrVp_a = vp_a(readOriginSetting);
        new StringBuilder().append("数据DF读取处理：").append(VpBleByteUtil.byte2HexForShow(bArrVp_a));
        this.vp_m = new vp_ar();
        this.vp_s = false;
        if (vp_ar.vp_a) {
            return;
        }
        super.send(bArrVp_a, bluetoothClient, str, bleWriteResponse);
    }
}
