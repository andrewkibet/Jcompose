package com.example.jcompose

import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MyNotificationListenerService : NotificationListenerService() {
    private val _notificationCounts = MutableStateFlow<Map<String, Int>>(emptyMap())
    val notificationCounts: StateFlow<Map<String, Int>> get() = _notificationCounts

    override fun onNotificationPosted(sbn: StatusBarNotification) {
        val packageName = sbn.packageName
        // Create a new map with the updated count and emit it
        val updatedMap = _notificationCounts.value.toMutableMap().apply {
            this[packageName] = (this[packageName] ?: 0) + 1
        }
        _notificationCounts.value = updatedMap
    }

    override fun onNotificationRemoved(sbn: StatusBarNotification) {
        val packageName = sbn.packageName
        // Create a new map with the updated count and emit it
        val updatedMap = _notificationCounts.value.toMutableMap().apply {
            val currentCount = this[packageName] ?: 0
            if (currentCount > 1) {
                this[packageName] = currentCount - 1
            } else {
                this.remove(packageName)
            }
        }
        _notificationCounts.value = updatedMap
    }
}
