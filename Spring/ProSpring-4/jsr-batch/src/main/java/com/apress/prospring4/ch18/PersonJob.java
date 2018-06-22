package com.apress.prospring4.ch18;

import java.util.Properties;

import javax.batch.runtime.BatchStatus;
import javax.batch.runtime.JobExecution;

import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.jsr.launch.JsrJobOperator;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;

public class PersonJob {

	public static void main(String[] args) throws JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException, JobParametersInvalidException {
		JsrJobOperator jobOperator = new JsrJobOperator();
		long executionId = jobOperator.start("personJob", new Properties());
		JobExecution jobExecution = jobOperator.getJobExecution(executionId);
				waitForJob(jobOperator, jobExecution);
	}

	private static void waitForJob(JsrJobOperator jobOperator, JobExecution jobExecution) {
		BatchStatus batchStatus = jobExecution.getBatchStatus();
		
		while(true) {
			if(batchStatus == BatchStatus.STOPPED ||
					batchStatus == BatchStatus.COMPLETED ||
					batchStatus == BatchStatus.FAILED) {
				return;
			}
			jobExecution = 
					jobOperator.getJobExecution(jobExecution.getExecutionId());
			batchStatus = jobExecution.getBatchStatus();
		}
	}

}
