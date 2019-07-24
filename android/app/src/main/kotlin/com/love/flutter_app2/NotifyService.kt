package com.love.flutter_app2

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.app.PendingIntent
import android.R
import android.app.Notification
import android.graphics.Color
import android.app.Notification.VISIBILITY_SECRET
import android.app.NotificationManager.IMPORTANCE_DEFAULT
import android.app.NotificationChannel
import android.os.Build
import android.app.NotificationManager
import android.content.Context


/**
 * @author Jason
 * @description:
 * @date :2019-07-23 18:33
 */
class NotifyService : Service() {
    override fun onBind(intent: Intent?): IBinder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreate() {
        super.onCreate()

    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return START_STICKY
    }

    private fun sendNotification() {
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            //26及以上
            val notificationChannel = NotificationChannel("id", "name", IMPORTANCE_DEFAULT)
            notificationChannel.canBypassDnd()//可否绕过请勿打扰模式
            notificationChannel.canShowBadge()//桌面lanchener显示角标
            notificationChannel.enableLights(true)//闪光
            notificationChannel.shouldShowLights()//闪光
            notificationChannel.lockscreenVisibility = VISIBILITY_SECRET//锁屏显示通知
            notificationChannel.enableVibration(true)//是否允许震动
            notificationChannel.vibrationPattern = longArrayOf(100, 100, 200)//设置震动方式（事件长短）
            notificationChannel.audioAttributes//获取系统响铃配置
            notificationChannel.group//获取消息渠道组
            notificationChannel.setBypassDnd(true)
            notificationChannel.description = "description"
            notificationChannel.lightColor = Color.GREEN//制定闪灯是灯光颜色
            notificationChannel.setShowBadge(true)
            notificationManager.createNotificationChannel(notificationChannel)

            val builder = Notification.Builder(applicationContext, "id")
            builder.setSmallIcon(R.mipmap.sym_def_app_icon)
            builder.setAutoCancel(true)
            builder.setChannelId("id")
            builder.setWhen(System.currentTimeMillis())
            builder.setContentTitle("题目")
            builder.setContentText("内容")
            builder.setNumber(3)
//            val intent = Intent(this, SecondActivity::class.java)
//            val pendingIntent = PendingIntent.getActivity(this, PENDING_INTENT_REQUEST_CODE, intent, PendingIntent.FLAG_UPDATE_CURRENT)
//            builder.setContentIntent(pendingIntent)

            notificationManager.notify(2, builder.build())
        } else {
            val builder = NotificationCompat.Builder(applicationContext)
            builder.setSmallIcon(R.mipmap.sym_def_app_icon)
            builder.setAutoCancel(true)
            builder.setWhen(System.currentTimeMillis())
            builder.setContentTitle("题目")
            builder.setContentText("内容")
//            val intent = Intent(this, SecondActivity::class.java)
//            val pendingIntent = PendingIntent.getActivity(this, PENDING_INTENT_REQUEST_CODE, intent, PendingIntent.FLAG_UPDATE_CURRENT)
//            builder.setContentIntent(pendingIntent)
            notificationManager.notify(2, builder.build())
        }

    }
}