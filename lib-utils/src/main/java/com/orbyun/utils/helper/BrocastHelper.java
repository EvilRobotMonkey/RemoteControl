package com.orbyun.utils.helper;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.wifi.WifiManager;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import java.util.List;


/**
 * @package com.guotaijun.gtj.guotaijun.fragment
 * @file BrocaseHelper
 * @date 2018/9/1  下午2:49
 * @autor wangxiongfeng
 */
public class BrocastHelper {

    private static BrocastHelper brocastHelper = new BrocastHelper();
    private static String BROCASET_FLAG = "BrocastHelper";


    public static void setBrocasetFlag(String brocasetFlag) {
        BROCASET_FLAG = brocasetFlag;
    }

    public static BroadcastReceiver registBrocast(Context context, BroadcastReceiver localReceiver) {
        LocalBroadcastManager localBroadcastManager = LocalBroadcastManager.getInstance(context);

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(BROCASET_FLAG);
        //注册本地接收器
        localBroadcastManager.registerReceiver(localReceiver, intentFilter);
        return localReceiver;
    }

    public static void unregistBrocast(Context context, BroadcastReceiver localReceiver) {
        LocalBroadcastManager localBroadcastManager = LocalBroadcastManager.getInstance(context);
        localBroadcastManager.unregisterReceiver(localReceiver);

    }


    public static BroadcastReceiver registNetBrocaset(BroadcastReceiver localReceiver, Activity activity) {

        IntentFilter filter = new IntentFilter();
        filter.addAction(WifiManager.WIFI_STATE_CHANGED_ACTION);
        filter.addAction(WifiManager.NETWORK_STATE_CHANGED_ACTION);
        filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);

        activity.registerReceiver(localReceiver, filter);
        return localReceiver;
    }

    public static void unregistSysBrocast(BroadcastReceiver localReceiver, Activity activity) {
        activity.unregisterReceiver(localReceiver);

    }

    public static void sendBracast(Context context, String type, ReceivelBean receivelBean) {
        LocalBroadcastManager localBroadcastManager = LocalBroadcastManager.getInstance(context);
        Intent intent = new Intent(BROCASET_FLAG);
        intent.putExtra("type", type);

        if (receivelBean != null) {
            intent.putExtra("result", receivelBean);
        }

        localBroadcastManager.sendBroadcast(intent);
    }

    public class ReceivelBean implements Parcelable {
        /**
         * battery_capatity : 72.22000122070312
         * basic_status : {"uwb":0,"laser":0,"base":0,"odom":0}
         * report_stat : {"nav_count":0,"dist":0,"time":0}
         * names : ["办公室","会议室","冰箱","前台","电梯","卫生间"]
         */

        private String battery_capatity;
        private BasicStatusBean basic_status;
        private ReportStatBean report_stat;
        private List<String> names;
        private String text;
        private String content;
        private String result;
        private String event;
        private Double rotation;
        private int maxVolume;
        private int volume;

        private InfoBean info;

        private String behaviorSpeak;


        public String getBehaviorSpeak() {
            return behaviorSpeak;
        }

        public void setBehaviorSpeak(String behaviorSpeak) {
            this.behaviorSpeak = behaviorSpeak;
        }

        public InfoBean getInfo() {
            return info;
        }

        public void setInfo(InfoBean info) {
            this.info = info;
        }

        public int getMaxVolume() {
            return maxVolume;
        }

        public void setMaxVolume(int maxVolume) {
            this.maxVolume = maxVolume;
        }

        public int getVolume() {
            return volume;
        }

        public void setVolume(int volume) {
            this.volume = volume;
        }

        /**
         * confidence : 100
         * voiceId : -1
         */


        private Integer confidence;
        private int voiceId;


        public Double getRotation() {
            return rotation;
        }

        public void setRotation(Double rotation) {
            this.rotation = rotation;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getEvent() {
            return event;
        }

        public void setEvent(String event) {
            this.event = event;
        }

        public String getResult() {
            return result;
        }

        public void setResult(String result) {
            this.result = result;
        }

        public String getBattery_capatity() {
            return battery_capatity;
        }

        public void setBattery_capatity(String battery_capatity) {
            this.battery_capatity = battery_capatity;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public BasicStatusBean getBasic_status() {
            return basic_status;
        }

        public void setBasic_status(BasicStatusBean basic_status) {
            this.basic_status = basic_status;
        }

        public ReportStatBean getReport_stat() {
            return report_stat;
        }

        public void setReport_stat(ReportStatBean report_stat) {
            this.report_stat = report_stat;
        }

        public List<String> getNames() {
            return names;
        }

        public void setNames(List<String> names) {
            this.names = names;
        }

        public Integer getConfidence() {
            return confidence;
        }

        public void setConfidence(int confidence) {
            this.confidence = confidence;
        }

        public int getVoiceId() {
            return voiceId;
        }

        public void setVoiceId(int voiceId) {
            this.voiceId = voiceId;
        }

        public class BasicStatusBean implements Parcelable {
            /**
             * uwb : 0
             * laser : 0
             * base : 0
             * odom : 0
             */

            private int uwb;
            private int laser;
            private int base;
            private int odom;

            public int getUwb() {
                return uwb;
            }

            public void setUwb(int uwb) {
                this.uwb = uwb;
            }

            public int getLaser() {
                return laser;
            }

            public void setLaser(int laser) {
                this.laser = laser;
            }

            public int getBase() {
                return base;
            }

            public void setBase(int base) {
                this.base = base;
            }

            public int getOdom() {
                return odom;
            }

            public void setOdom(int odom) {
                this.odom = odom;
            }


            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeInt(this.uwb);
                dest.writeInt(this.laser);
                dest.writeInt(this.base);
                dest.writeInt(this.odom);
            }

            public BasicStatusBean() {
            }

            protected BasicStatusBean(Parcel in) {
                this.uwb = in.readInt();
                this.laser = in.readInt();
                this.base = in.readInt();
                this.odom = in.readInt();
            }

            public final Creator<BasicStatusBean> CREATOR = new Creator<BasicStatusBean>() {
                @Override
                public BasicStatusBean createFromParcel(Parcel source) {
                    return new BasicStatusBean(source);
                }

                @Override
                public BasicStatusBean[] newArray(int size) {
                    return new BasicStatusBean[size];
                }
            };
        }

        public class ReportStatBean implements Parcelable {
            /**
             * nav_count : 0
             * dist : 0
             * time : 0
             */

            private int nav_count;
            private int dist;
            private int time;

            public int getNav_count() {
                return nav_count;
            }

            public void setNav_count(int nav_count) {
                this.nav_count = nav_count;
            }

            public int getDist() {
                return dist;
            }

            public void setDist(int dist) {
                this.dist = dist;
            }

            public int getTime() {
                return time;
            }

            public void setTime(int time) {
                this.time = time;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeInt(this.nav_count);
                dest.writeInt(this.dist);
                dest.writeInt(this.time);
            }

            public ReportStatBean() {
            }

            protected ReportStatBean(Parcel in) {
                this.nav_count = in.readInt();
                this.dist = in.readInt();
                this.time = in.readInt();
            }

            public final Creator<ReportStatBean> CREATOR = new Creator<ReportStatBean>() {
                @Override
                public ReportStatBean createFromParcel(Parcel source) {
                    return new ReportStatBean(source);
                }

                @Override
                public ReportStatBean[] newArray(int size) {
                    return new ReportStatBean[size];
                }
            };
        }


        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.battery_capatity);
            dest.writeParcelable(this.basic_status, flags);
            dest.writeParcelable(this.report_stat, flags);
            dest.writeParcelable(this.info, flags);
            dest.writeStringList(this.names);
            dest.writeString(this.text);
            dest.writeString(this.content);
            dest.writeString(this.result);
            dest.writeString(this.behaviorSpeak);
            dest.writeInt(this.confidence);
            dest.writeString(this.event);
            dest.writeInt(this.voiceId);
            dest.writeDouble(this.rotation);
            dest.writeDouble(this.maxVolume);
            dest.writeDouble(this.voiceId);
        }

        public ReceivelBean() {
        }

        protected ReceivelBean(Parcel in) {
            this.battery_capatity = in.readString();
            this.basic_status = in.readParcelable(BasicStatusBean.class.getClassLoader());
            this.report_stat = in.readParcelable(ReportStatBean.class.getClassLoader());
            this.info = in.readParcelable(ReportStatBean.class.getClassLoader());
            this.names = in.createStringArrayList();
            this.text = in.readString();
            this.content = in.readString();
            this.result = in.readString();
            this.behaviorSpeak = in.readString();
            this.event = in.readString();
            this.voiceId = in.readInt();
            this.confidence = in.readInt();
            this.rotation = in.readDouble();
            this.maxVolume = in.readInt();
            this.volume = in.readInt();
        }

        public final Creator<ReceivelBean> CREATOR = new Creator<ReceivelBean>() {
            @Override
            public ReceivelBean createFromParcel(Parcel source) {
                return new ReceivelBean(source);
            }

            @Override
            public ReceivelBean[] newArray(int size) {
                return new ReceivelBean[size];
            }
        };


        public class InfoBean implements Parcelable {
            /**
             * address : 河北省石家庄市无极县里城道乡西东丈村幸福街平安巷23号
             * expiry_data : 2014.05.26-2019.05.26
             * ethnic : 汉
             * name : 刘佳乐
             * sex : 男
             * birthday : 1998年07月01日
             * photo : /mnt/sdcard/smartOpenAccount/download/zp.bmp
             * department : 无极县公安局
             * No : 130682198708113792
             */


            private String address;
            private String expiry_data;
            private String ethnic;
            private String name;
            private String sex;
            private String birthday;
            private String photo;
            private String department;
            private String No;


            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public String getExpiry_data() {
                return expiry_data;
            }

            public void setExpiry_data(String expiry_data) {
                this.expiry_data = expiry_data;
            }

            public String getEthnic() {
                return ethnic;
            }

            public void setEthnic(String ethnic) {
                this.ethnic = ethnic;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getSex() {
                return sex;
            }

            public void setSex(String sex) {
                this.sex = sex;
            }

            public String getBirthday() {
                return birthday;
            }

            public void setBirthday(String birthday) {
                this.birthday = birthday;
            }

            public String getPhoto() {
                return photo;
            }

            public void setPhoto(String photo) {
                this.photo = photo;
            }

            public String getDepartment() {
                return department;
            }

            public void setDepartment(String department) {
                this.department = department;
            }

            public String getNo() {
                return No;
            }

            public void setNo(String No) {
                this.No = No;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.address);
                dest.writeString(this.expiry_data);
                dest.writeString(this.ethnic);
                dest.writeString(this.name);
                dest.writeString(this.sex);
                dest.writeString(this.birthday);
                dest.writeString(this.photo);
                dest.writeString(this.department);
                dest.writeString(this.No);
            }

            public InfoBean() {
            }

            protected InfoBean(Parcel in) {
                this.address = in.readString();
                this.expiry_data = in.readString();
                this.ethnic = in.readString();
                this.name = in.readString();
                this.sex = in.readString();
                this.birthday = in.readString();
                this.photo = in.readString();
                this.department = in.readString();
                this.No = in.readString();
            }

            public final Creator<InfoBean> CREATOR = new Creator<InfoBean>() {
                @Override
                public InfoBean createFromParcel(Parcel source) {
                    return new InfoBean(source);
                }

                @Override
                public InfoBean[] newArray(int size) {
                    return new InfoBean[size];
                }
            };
        }
    }


}
