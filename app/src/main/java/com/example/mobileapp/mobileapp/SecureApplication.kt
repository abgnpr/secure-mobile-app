package com.example.mobileapp.mobileapp

import android.app.Application
import android.util.Log
import com.aheaditec.talsec_security.security.api.Talsec
import com.aheaditec.talsec_security.security.api.TalsecConfig
import com.aheaditec.talsec_security.security.api.ThreatListener

class SecureApplication : Application(), ThreatListener.ThreatDetected {

    override fun onCreate() {
        super.onCreate()

        val config = TalsecConfig(
            expectedPackageName,
            expectedSigningCertificateHashBase64,
            watcherMail,
            supportedAlternativeStores,
            isProd
        )

        ThreatListener(this).registerListener(this)
        Talsec.start(this, config)
        //Log.e("SigningCertificateHash", Utils.computeSigningCertificateHash(this))
    }

    override fun onRootDetected() {
        Log.e("RootDetected", "RootDetected")
        VulnerabilityState.vulnerabilities.add(Vulnerability.ROOTED)
    }

    override fun onDebuggerDetected() {
        Log.e("RootDetected", "Debugger Detected")
//        VulnerabilityState.vulnerabilities.add(Vulnerability.DEBUGGER)
    }

    override fun onEmulatorDetected() {
        Log.e("RootDetected", "Emulator Detected")
//        VulnerabilityState.vulnerabilities.add(Vulnerability.EMULATOR)
    }

    override fun onTamperDetected() {
        Log.e("RootDetected", "Tampering Detected")
        VulnerabilityState.vulnerabilities.add(Vulnerability.TAMPERING)
    }

    override fun onUntrustedInstallationSourceDetected() {
        Log.e("RootDetected", "Untrusted installation Detected")
//        VulnerabilityState.vulnerabilities.add(Vulnerability.UNTRUSTED_INSTALLATION)
    }

    override fun onHookDetected() {
        Log.e("RootDetected", "Hook Detected")
        VulnerabilityState.vulnerabilities.add(Vulnerability.HOOK)
    }

    override fun onDeviceBindingDetected() {
        Log.e("RootDetected", "Device Binding Detected")
//        VulnerabilityState.vulnerabilities.add(Vulnerability.DEVICE_BINDING)
    }

    companion object {
        private const val expectedPackageName = "com.example.mobileapp.mobileapp" // Don't use Context.getPackageName!
        private val expectedSigningCertificateHashBase64 = arrayOf("m5In0UGXK/mbrW9WIJWYKhCFeEMcEawVHC3GJ4+9tQY=")
        private const val watcherMail = "john@example.com" // for Alerts and Reports
        private val supportedAlternativeStores = arrayOf("com.sec.android.app.samsungapps")
        private const val isProd = true
    }


}
