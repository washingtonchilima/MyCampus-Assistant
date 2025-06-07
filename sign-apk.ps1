# sign-apk.ps1 using apksigner

$apkUnsigned = ".\app\build\outputs\apk\release\app-release-unsigned.apk"
$finalApk = ".\app\build\outputs\apk\release\app-release.apk"

$keyStore = "$env:USERPROFILE\.android\debug.keystore"
$keyAlias = "androiddebugkey"
$storePass = "android"
$keyPass = "android"

$zipalignPath = "C:\Users\stati\AppData\Local\Android\Sdk\build-tools\36.0.0\zipalign.exe"
$apksignerPath = "C:\Users\stati\AppData\Local\Android\Sdk\build-tools\36.0.0\apksigner.bat"

Write-Host "Aligning the APK..."
& $zipalignPath -v 4 $apkUnsigned $finalApk

Write-Host "Signing the APK with apksigner..."
& $apksignerPath sign --ks $keyStore --ks-key-alias $keyAlias --ks-pass pass:$storePass --key-pass pass:$keyPass --out $finalApk $finalApk

Write-Host "Signing and alignment complete. Final APK: $finalApk"
