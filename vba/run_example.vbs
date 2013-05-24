Option Explicit 

'
' This VBScript file is used to run the Excel VBA example code
' from the command line.  Mainly for testing purposes.
'
' It accepts 2 args, 1. a valid application key and 2. a valid session token
' these args are then fed into the workbook for the macro to use.
'

On Error Resume Next

' Check args are passed
If Wscript.Arguments.Count <> 2 Then
	WScript.Echo "Invalid args, please pass appkey and session token"
	'log instead
	Wscript.Quit
End If

Dim appKey: appKey = WScript.Arguments(0)
Dim session: session = WScript.Arguments(1)
Dim oExcel: Set oExcel = CreateObject("excel.application")

' Open the example code workbook
oExcel.Workbooks.Open GetCurrentDir + "\SampleCode.xlsm"

If Err <> 0 Then
    Wscript.Echo Err.Description
	'log instead
    Wscript.Quit
End If

oExcel.Visible = False
' set the appkey and session token in the sheet
oExcel.Cells(3, 2).Value = appKey
oExcel.Cells(4, 2).Value = session

' Run the clear macro
oExcel.Run "Button3_Click" 
If Err <> 0 Then
    Wscript.Echo Err.Description
	'log instead
    Wscript.Quit
End If

' Run the JSON-RPC demo macro
oExcel.Run "Button1_Click" ' 
If Err <> 0 Then
    Wscript.Echo Err.Description
	'log instead
    Wscript.Quit
End If

oExcel.ActiveWindow.Close(False)
oExcel.Quit
Set oExcel = Nothing

On Error GoTo 0

Function GetCurrentDir() 
	Dim objShell: set objShell = WScript.CreateObject ("WScript.Shell")
	GetCurrentDir = objShell.CurrentDirectory
End Function

