package com.github.tatsuyafujisaki.androidplayground.bluetooth

import android.bluetooth.BluetoothDevice
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.content.IntentCompat

class BluetoothBondReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) = with(intent) {
        val device = IntentCompat.getParcelableExtra(this, BluetoothDevice.EXTRA_DEVICE, BluetoothDevice::class.java)
        val address = device?.address

        when (action) {
            ACTION_KEY_MISSING -> Log.d(TAG, "Bond loss detected via ACTION_KEY_MISSING: $address")
            ACTION_ENCRYPTION_CHANGE -> Log.d(TAG, "Encryption changed: $address")
            BluetoothDevice.ACTION_BOND_STATE_CHANGED -> {
                val state = getIntExtra(BluetoothDevice.EXTRA_BOND_STATE, BluetoothDevice.ERROR)
                val prevState = getIntExtra(BluetoothDevice.EXTRA_PREVIOUS_BOND_STATE, BluetoothDevice.ERROR)
                Log.d(TAG, "Bond state changed: $prevState -> $state for $address")

                if (state == BluetoothDevice.BOND_NONE && prevState == BluetoothDevice.BOND_BONDED) {
                    Log.d(TAG, "Traditional bond loss detected (fallback).")
                }
            }
        }
    }

    companion object {
        private const val TAG = "BluetoothBondReceiver"
        const val ACTION_KEY_MISSING = "android.bluetooth.device.action.KEY_MISSING"
        const val ACTION_ENCRYPTION_CHANGE = "android.bluetooth.device.action.ENCRYPTION_CHANGE"
    }
}
