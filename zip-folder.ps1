# PowerShell script: zip-folder.ps1
# Run from the folder you want to package

$zipfile = "$PWD\package.zip"   # Output zip file in current folder

# Remove old zip if it exists
if (Test-Path $zipfile) { Remove-Item $zipfile }

# Exclude: .idea directories, and all *-0.0.1-SNAPSHOT.jar files
$files = Get-ChildItem -Path $PWD -Recurse -File | Where-Object {
    # Exclude any file in a .idea folder
    if ($_.FullName -match '\\\.idea\\') { return $false }
    # Exclude any file matching *-0.0.1-SNAPSHOT.jar
    if ($_.Name -like '*-0.0.1-SNAPSHOT.jar') { return $false }
    return $true
}

Add-Type -AssemblyName 'System.IO.Compression.FileSystem'
$zip = [System.IO.Compression.ZipFile]::Open($zipfile, 'Create')
foreach ($file in $files) {
    # Path relative to the current folder
    $entryName = $file.FullName.Substring($PWD.Path.Length + 1)
    [System.IO.Compression.ZipFileExtensions]::CreateEntryFromFile($zip, $file.FullName, $entryName)
}
$zip.Dispose()

Write-Host "Packaged into: $zipfile"
