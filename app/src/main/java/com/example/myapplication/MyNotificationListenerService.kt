package com.example.myapplication

import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MyNotificationListenerService : NotificationListenerService() {
    // Create a MutableStateFlow to keep track of notification counts
    private val _notificationCounts = MutableStateFlow<Map<String, Int>>(emptyMap())
    val notificationCounts: StateFlow<Map<String, Int>> get() = _notificationCounts

    override fun onNotificationPosted(sbn: StatusBarNotification) {
        val packageName = sbn.packageName
        if (packageName == "com.whatsapp") { // Check if the notification is for WhatsApp
            val currentCount = _notificationCounts.value[packageName] ?: 0
            _notificationCounts.value = _notificationCounts.value + (packageName to currentCount + 1)
        }
    }

    override fun onNotificationRemoved(sbn: StatusBarNotification) {
        val packageName = sbn.packageName
        if (packageName == "com.whatsapp") { // Check if the notification is for WhatsApp
            val currentCount = _notificationCounts.value[packageName] ?: 1
            _notificationCounts.value = _notificationCounts.value + (packageName to (currentCount - 1).coerceAtLeast(0))
        }
    }
}
