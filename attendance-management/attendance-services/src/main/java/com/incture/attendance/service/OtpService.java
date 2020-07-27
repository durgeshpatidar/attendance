package com.incture.attendance.service;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.google.common.cache.LoadingCache;
import com.incture.attendance.utils.ResponseDto;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;

@Service
@Transactional
public class OtpService {
	// cache based on username and OPT MAX 8
	private static final Integer EXPIRE_MINS = 3;
	private LoadingCache<String, Integer> otpCache;

	public OtpService() {
		super();
		otpCache = CacheBuilder.newBuilder().expireAfterWrite(EXPIRE_MINS, TimeUnit.MINUTES)
				.build(new CacheLoader<String, Integer>() {
					public Integer load(String key) {
						return 0;
					}
				});
	}

	// This method is used to push the opt number against Key. Rewrite the OTP if it
	// exists
	// Using user id as key
	public int generateOTP(String key) {
		Random random = new Random();
		int otp = 100000 + random.nextInt(900000);
		otpCache.put(key, otp);
		return otp;
	}

	// This method is used to return the OPT number against Key->Key values is
	// username
	public int getOtp(String key) {
		try {
			return otpCache.get(key);
		} catch (Exception e) {
			return 0;
		}
	}

	// This method is used to clear the OTP catched already
	public void clearOTP(String key) {
		otpCache.invalidate(key);
	}

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	public ResponseDto validateOtp(int otp, String email) {
		logger.info("OtpService | validateOtp | Execution start input " + otp + " " + email);

		ResponseDto responseDto = new ResponseDto();
		responseDto.setStatus(Boolean.TRUE);
		responseDto.setStatusCode(200);
		responseDto.setMessage("OTP is valid");
		int realOtp = getOtp(email);
		if (realOtp != otp) {
			responseDto.setStatus(Boolean.FALSE);
			responseDto.setStatusCode(500);
			responseDto.setMessage("OTP is invalid");
		} else
			clearOTP(email);
		logger.info("OtpService  | validateOtp  | Execution end ouput " + responseDto);
		return responseDto;
	}

}