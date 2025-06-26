# PowerShell script: zip-folder.ps1
# Run from the folder you want to package

$zipfile = "$PWD\package.zip"   # Output zip file in current folder

# Remove old zip if it exists
if (Test-Path $zipfile) { Remove-Item $zipfile }

# Exclude: .idea directories, and all *-0.0.1-SNAPSHOT.jar files
$files = Get-ChildItem -Path $PWD -Recurse -File | Where-Object {
    $path = $_.FullName

    # Exclude common junk folders and files
    if ($path -match '\\node_modules\\') { return $false }
    if ($path -match '\\.git\\') { return $false }
    if ($path -match '\\dist\\' -or $path -match '\\build\\' -or $path -match '\\out\\' -or $path -match '\\.next\\') { return $false }
    if ($path -match '\\.idea\\' -or $path -match '\\.vscode\\' -or $path -match '\\.cache\\' -or $path -match '\\coverage\\' -or $path -match '\\__snapshots__\\' -or $path -match '\\reports\\') { return $false }
    if ($path -match '\\public\\') { return $false } # skip public folder unless you actually write code here

    # Exclude file types
    if ($_.Extension -in @('.log', '.png', '.jpg', '.jpeg', '.svg', '.ico', '.gif', '.woff', '.woff2', '.ttf', '.eot', '.mp4', '.mp3', '.webm', '.zip', '.rar')) { return $false }
    if ($_.Extension -in @('.css', '.scss', '.sass')) { return $false }

    # Exclude config/artifact files
    if ($_.Name -in @('package-lock.json', 'yarn.lock', 'pnpm-lock.yaml', '.env', '.env.local', '.env.development', '.env.production', '.DS_Store', 'Thumbs.db')) { return $false }
    if ($_.Name -like '*-0.0.1-SNAPSHOT.jar') { return $false }

    # Usually skip README, test, and Storybook files
    if ($_.Name -eq 'README.md' -or $path -match '\\tests?\\' -or $path -match '\\__tests__\\' -or $path -match '\\.storybook\\') { return $false }

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

