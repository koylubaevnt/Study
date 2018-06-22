package com.apress.prospring4.ch18;

import java.util.Date;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class PersonJob {

	public static void main(String[] args) throws JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException, JobParametersInvalidException {
		ApplicationContext context = new ClassPathXmlApplicationContext("/META-INF/spring/jobs/personJob/personJob.xml");
		
		Job job = context.getBean(Job.class);
		JobLauncher launcher = context.getBean(JobLauncher.class);
		
		JobParameters jobParameters = new JobParametersBuilder()
				.addDate("date", new Date())
				.toJobParameters();
		
		launcher.run(job, jobParameters);
	}

}
