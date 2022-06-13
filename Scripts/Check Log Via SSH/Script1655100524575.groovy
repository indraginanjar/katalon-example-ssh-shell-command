import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.exception.StepFailedException
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.mobile.keyword.builtin.VerifyElementNotExistKeyword as VerifyElementNotExistKeyword
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.webui.keyword.builtin.VerifyTextNotPresentKeyword
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows

import groovy.util.logging.Commons
import internal.GlobalVariable

import org.assertj.core.api.Fail
import org.junit.runner.notification.Failure
import org.openqa.selenium.Keys as Keys

String remoteCommand = "cat /var/log/alternatives.log | grep run"

String sshCommand = "ssh -i $GlobalVariable.SshIdentity -p $GlobalVariable.RemotePort $GlobalVariable.RemoteUsername@$GlobalVariable.RemoteHost $remoteCommand"

Process proc = "$sshCommand".execute();

StringBuffer outputStream = new StringBuffer();

proc.waitForProcessOutput(outputStream, System.err);

String consoleOutput = outputStream.toString();

println("Console output:");

println(consoleOutput);

if(consoleOutput.length() == 0) {
	throw new StepFailedException("No string returned from log reading.")
}
else if(consoleOutput.contains("error")) {
	throw new StepFailedException("An error or more is found in log.")
}
